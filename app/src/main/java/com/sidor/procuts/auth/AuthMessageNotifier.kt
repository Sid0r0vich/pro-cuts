package com.example.wellness.auth

import android.content.Context
import android.widget.Toast
import com.sidor.procuts.R

class AuthMessageNotifier(private val context: Context) {
    fun notifyUser(errorType: AuthStatus) {
        val message = when (errorType) {
            AuthStatus.SUCCESS -> R.string.success
            AuthStatus.USER_NOT_FOUND -> R.string.user_not_found
            AuthStatus.INVALID_EMAIL_FORMAT -> R.string.invalid_email_format
            AuthStatus.INVALID_PASSWORD_FORMAT -> R.string.invalid_password_format
            AuthStatus.INVALID_NAME_FORMAT -> R.string.invalid_name_format
            AuthStatus.INVALID_CREDENTIALS -> R.string.invalid_credentials
            AuthStatus.EMAIL_COLLISION -> R.string.email_collision
            AuthStatus.WEAK_PASSWORD -> R.string.weak_password
            AuthStatus.UNKNOWN_ERROR -> R.string.unknown_exception
        }
        showToast(message)
    }

    private fun showToast(messageId: Int) =
        Toast.makeText(
            context,
            context.getString(messageId),
            Toast.LENGTH_SHORT
        ).show()
}