package com.sidor.procuts.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import java.io.ByteArrayOutputStream


fun loadImageAsByteArray(contentResolver: ContentResolver, uri: Uri): ByteArray? {
    val bitmap = contentResolver.openInputStream(uri)?.use { inputStream ->
        BitmapFactory.decodeStream(inputStream)
    }

    return bitmap?.let {
        val byteArrayOutputStream = ByteArrayOutputStream()
        it.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        byteArrayOutputStream.toByteArray()
    }
}

fun getPainterFromByteArray(photo: ByteArray?): Painter? =
    if (photo != null) {
        val bitmap: Bitmap? = BitmapFactory.decodeByteArray(photo, 0, photo.size)
        bitmap?.asImageBitmap()?.let { imageBitmap ->
            BitmapPainter(imageBitmap)
        }
    } else null