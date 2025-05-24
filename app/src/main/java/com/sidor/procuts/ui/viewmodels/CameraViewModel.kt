package com.sidor.procuts.ui.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class CameraViewModel(): ViewModel() {
    data class UIState(
        val capturedImage: Bitmap? = null,
    )
    private val _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun savePhoto(bitmap: Bitmap) {
        viewModelScope.launch {
            updateCapturedPhotoState(bitmap)
        }
    } // TODO(make up func name)

    fun getBitmap() = _uiState.value.capturedImage

    private fun updateCapturedPhotoState(updatedPhoto: Bitmap?) {
        _uiState.value.capturedImage?.recycle()
        _uiState.value = _uiState.value.copy(capturedImage = updatedPhoto)
    }

    override fun onCleared() {
        _uiState.value.capturedImage?.recycle()
        super.onCleared()
    }
}