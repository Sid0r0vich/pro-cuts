package com.sidor.procuts.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.core.graphics.scale
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


fun Bitmap.rotate(degrees: Float): Bitmap {
    val matrix = Matrix().apply {
        postRotate(degrees)
    }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
}

fun Bitmap.cropCenterSquare(): Bitmap {
    val size = minOf(width, height)
    val x = (width - size) / 2
    val y = (height - size) / 2
    return Bitmap.createBitmap(this, x, y, size, size)
}

fun Bitmap.resizeMax1024(): Bitmap {
    val maxSize = 1024
    val width = this.width
    val height = this.height

    if (width <= maxSize && height <= maxSize) {
        return this
    }

    val scaleFactor = maxSize.toFloat() / maxOf(width, height)

    val newWidth = (width * scaleFactor).toInt()
    val newHeight = (height * scaleFactor).toInt()

    return this.scale(newWidth, newHeight)
}