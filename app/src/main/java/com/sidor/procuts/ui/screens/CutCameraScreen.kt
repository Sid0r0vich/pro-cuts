package com.sidor.procuts.ui.screens

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.SwitchCamera
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sidor.procuts.ui.ToastNotifier
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.ui.viewmodels.CameraViewModel
import java.util.concurrent.Executor
import com.sidor.procuts.R


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CutCameraScreenWithPermission(
    viewModel: CameraViewModel = hiltViewModel(),
    onPhotoConfirm: (Bitmap) -> Unit
) {
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val ctx = LocalContext.current
    val textPhotoNotUploaded = stringResource(R.string.photo_is_not_uploaded)

    if (cameraPermissionState.status.isGranted) {
        CutCameraScreen(
            onPhotoCaptured = viewModel::savePhoto,
            lastCapturedPhoto = uiState.capturedImage,
            onPhotoConfirm = {
                viewModel.getBitmap()
                    ?.let { p1 -> onPhotoConfirm(p1) }
                    ?: ToastNotifier(ctx).show(message = textPhotoNotUploaded)
            }
        )
    } else {
        NoPermissionScreen(
            onRequestPermission = cameraPermissionState::launchPermissionRequest
        )
    }
}

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Please grant the permission to use the camera to use the core functionality of this app.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRequestPermission) {
            Icon(imageVector = Icons.Default.Camera, contentDescription = "Camera")
            Text(text = "Grant permission")
        }
    }
}

@Composable
fun HideSystemBars() {
    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = false
        )
        systemUiController.isSystemBarsVisible = false
    }
}

@Composable
fun CutCameraScreen(
    onPhotoCaptured: (Bitmap) -> Unit,
    lastCapturedPhoto: Bitmap? = null,
    onPhotoConfirm: () -> Unit
) {
    val context: Context = LocalContext.current
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }

    val cameraSelector = remember { mutableStateOf(CameraSelector.DEFAULT_FRONT_CAMERA) }
    cameraController.cameraSelector = cameraSelector.value
    val onSwitchCamera = {
        cameraSelector.value = if (cameraSelector.value == CameraSelector.DEFAULT_BACK_CAMERA) {
            CameraSelector.DEFAULT_FRONT_CAMERA
        } else {
            CameraSelector.DEFAULT_BACK_CAMERA
        }
        cameraController.cameraSelector = cameraSelector.value
        cameraController.bindToLifecycle(lifecycleOwner)
    }

    HideSystemBars()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues: PaddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        setBackgroundColor(android.graphics.Color.BLACK)
                        implementationMode = PreviewView.ImplementationMode.COMPATIBLE
                        scaleType = PreviewView.ScaleType.FILL_CENTER
                        controller = cameraController
                        cameraController.bindToLifecycle(lifecycleOwner)
                    }
                }
            )

            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                val ovalWidth = width * 0.8f
                val ovalHeight = height * 0.5f

                val centerX = width / 2
                val centerY = height / 2

                drawOval(
                    color = Color.White.copy(alpha = 0.5f),
                    topLeft = Offset(centerX - ovalWidth / 2, centerY - ovalHeight / 2),
                    size = Size(ovalWidth, ovalHeight),
                    style = Stroke(width = 6.dp.toPx())
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    (listOf(
                        onSwitchCamera,
                        { capturePhoto(context, cameraController, onPhotoCaptured) },
                        onPhotoConfirm
                    ) zip listOf(
                        Icons.Default.SwitchCamera,
                        Icons.Default.Camera,
                        Icons.Default.Check
                    )).forEach { (action, icon) ->
                        FloatingActionButton(
                            onClick = action,
                            containerColor = LocalColorPalette.current.buttonColor,
                            contentColor = LocalColorPalette.current.onButtonColor,
                            modifier = Modifier.padding(bottom=14.dp)
                        ) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    }
                }
            }

            lastCapturedPhoto?.let { bitmap ->
                LastPhotoPreview(
                    modifier = Modifier.align(BottomEnd).padding(bottom=15.dp).size(100.dp),
                    lastCapturedPhoto = bitmap
                )
            }
        }
    }
}

@Composable
private fun LastPhotoPreview(
    modifier: Modifier = Modifier,
    lastCapturedPhoto: Bitmap
) {

    val capturedPhoto: ImageBitmap = remember(lastCapturedPhoto.hashCode()) { lastCapturedPhoto.asImageBitmap() }

    Card(
        modifier = modifier
            .size(128.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Image(
            bitmap = capturedPhoto,
            contentDescription = "Last captured photo",
            contentScale = androidx.compose.ui.layout.ContentScale.Crop
        )
    }
}

private fun capturePhoto(
    context: Context,
    cameraController: LifecycleCameraController,
    onPhotoCaptured: (Bitmap) -> Unit
) {
    val mainExecutor: Executor = ContextCompat.getMainExecutor(context)

    cameraController.takePicture(mainExecutor, object : ImageCapture.OnImageCapturedCallback() {
        override fun onCaptureSuccess(image: ImageProxy) {
            val correctedBitmap: Bitmap = image
                .toBitmap()

            onPhotoCaptured(correctedBitmap)
            image.close()
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("CameraContent", "Error capturing image", exception)
        }
    })
}