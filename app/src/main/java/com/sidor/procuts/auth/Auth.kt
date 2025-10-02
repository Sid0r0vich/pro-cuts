package com.example.wellness.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

private class Firebase {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val authErrorHandler = AuthErrorHandler()
    val userId: MutableStateFlow<String?> =
        MutableStateFlow(firebaseAuth.currentUser?.uid)

    init {
        firebaseAuth.addAuthStateListener {
            userId.value = firebaseAuth.currentUser?.uid
        }
    }

    fun signIn(
        authData: AuthData,
        onSignIn: (AuthStatus) -> Unit = {}
    ) = firebaseAuth.signInWithEmailAndPassword(
            authData.email,
            authData.password
        ).addAuthenticateListener(onSignIn)

    fun signUp(
        authData: AuthData,
        onSignUp: (AuthStatus) -> Unit = {}
    ) = firebaseAuth.createUserWithEmailAndPassword(
            authData.email,
            authData.password
        ).addAuthenticateListener(onSignUp)

    fun signOut() = firebaseAuth.signOut()

    private fun Task<AuthResult>.addAuthenticateListener(
        onAuthenticate: (AuthStatus) -> Unit = {},
    ) {
        this.addOnCompleteListener { task ->
            onAuthenticate(
                if (task.isSuccessful) AuthStatus.SUCCESS
                else authErrorHandler.handleAuthError(task.exception)
            )
        }
    }
}

class AuthErrorHandler {
    fun handleAuthError(exception: Exception?): AuthStatus {
        return when (exception) {
            is FirebaseAuthInvalidUserException -> AuthStatus.USER_NOT_FOUND
            is FirebaseAuthUserCollisionException -> AuthStatus.EMAIL_COLLISION
            is FirebaseAuthWeakPasswordException -> AuthStatus.WEAK_PASSWORD
            is FirebaseAuthInvalidCredentialsException -> AuthStatus.INVALID_CREDENTIALS
            else -> AuthStatus.UNKNOWN_ERROR
        }
    }
}

interface Auth {
    val authState: AuthState
    val authStateFlow: StateFlow<AuthState>
    val userId: StateFlow<String?>

    fun signIn(authData: AuthData, onSignIn: (AuthStatus) -> Unit = {})
    fun signUp(authData: AuthData, onSignUp: (AuthStatus) -> Unit = {})
    fun signOut()
}

class FirebaseAuth @Inject constructor() : Auth {
    private val firebase = Firebase()

    private val _authState: MutableStateFlow<AuthState> =
        MutableStateFlow(AuthState.Unauthenticated)
    override val authStateFlow: StateFlow<AuthState>
        get() = _authState
    override var authState: AuthState
        get() = _authState.value
        private set(value) {
            _authState.value = value
        }

    override val userId: StateFlow<String?>
        get() = firebase.userId

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        authState = userId.value
            ?.let { AuthState.Authenticated } ?: AuthState.Unauthenticated
    }

    override fun signIn(
        authData: AuthData,
        onSignIn: (AuthStatus) -> Unit,
    ) {
        authState = AuthState.Loading
        firebase.signIn(authData, onAuth(onSignIn))
    }

    override fun signUp(
        authData: AuthData,
        onSignUp: (AuthStatus) -> Unit,
    ) {
        authState = AuthState.Loading
        firebase.signUp(authData, onAuth(onSignUp))
    }

    override fun signOut() {
        authState = AuthState.Unauthenticated
        firebase.signOut()
    }

    private val onAuth = { onComplete: (AuthStatus) -> Unit ->
        { status: AuthStatus ->
            authState = when(status) {
                AuthStatus.SUCCESS -> AuthState.Authenticated
                else -> AuthState.Error(status)
            }
            onComplete(status)
        }
    }
}