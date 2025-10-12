package com.sidor.procuts.ui.components

import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sidor.procuts.R
import com.sidor.procuts.utils.getPainterFromByteArray
import com.sidor.procuts.utils.loadImageAsByteArray

@Composable
fun ImagePicker(
    clientImage: ByteArray? = null,
    onImageLoad: (ByteArray) -> Unit
) {
    val painter = clientImage?.let { getPainterFromByteArray(it) }
    var imageUri: Uri? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                imageUri = uri
                loadImageAsByteArray(context.contentResolver, uri)?.let {
                    onImageLoad(it)
                }
            }
        }
    )

    Box(
        modifier = Modifier
            .clickable { launcher.launch("image/*") }
            .size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        if (imageUri != null || painter != null) {
            Image(
                painter = if (imageUri != null) rememberAsyncImagePainter(imageUri) else painter!!,
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