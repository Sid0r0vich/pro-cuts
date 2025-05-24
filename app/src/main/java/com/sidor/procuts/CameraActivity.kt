package com.sidor.procuts

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.sidor.procuts.ui.screens.CutCameraScreenWithPermission
import com.sidor.procuts.ui.theme.ProCutsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        enableEdgeToEdge()
        setContent {
            ProCutsTheme {
                CutCameraScreenWithPermission { photoBitmap: Bitmap ->
                    val file = File(cacheDir, "photo_${System.currentTimeMillis()}.png")
                    val fos = FileOutputStream(file)
                    photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
                    fos.flush()
                    fos.close()

                    val resultIntent = Intent().apply {
                        putExtra("photoUri", file.absolutePath)
                    }
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}