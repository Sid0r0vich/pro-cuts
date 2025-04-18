package com.sidor.procuts.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sidor.procuts.R
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.platform.LocalContext
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

@Composable
fun ImagePicker(
    onImageLoad: (ByteArray) -> Unit
) {
    var imageUri: Uri? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                imageUri = uri
                loadImageAsByteArray(context.contentResolver, uri)?.let { onImageLoad(it) }
            }
        }
    )

    Box(
        modifier = Modifier
            .clickable { launcher.launch("image/*") }
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null) {
            Log.d("NOTNULL", "NOTNULL")
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = stringResource(R.string.client_photo),
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("+", style = MaterialTheme.typography.titleLarge, color=Color.DarkGray)
                }
            }
        }
    }
}