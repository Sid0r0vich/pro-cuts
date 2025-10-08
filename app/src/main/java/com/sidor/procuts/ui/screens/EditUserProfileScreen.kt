package com.sidor.procuts.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import com.sidor.procuts.R
import com.sidor.procuts.data.models.ClientInfoDTO
import com.sidor.procuts.data.models.UserDTO
import com.sidor.procuts.ui.components.ImagePicker
import com.sidor.procuts.ui.components.MyStyledTextField
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.screens.topbars.DefaultTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Composable
fun EditUserProfileScreen(
    onBack: () -> Unit,
    userDTO: UserDTO,
    onSaveClick: (UserDTO) -> Unit
) {
    TopAppBarScreen(
        topBar = {
            DefaultTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.edit_user_profile_tab_app_bar),
                        style = MaterialTheme.typography.headlineSmall,
                        color = LocalColorPalette.current.mainColor
                    )
                },
                actions = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = stringResource(R.string.button_back),
                            tint = LocalColorPalette.current.mainColor
                        )
                    }
                },
            )
        },
    ) {
        EditUserProfileScreenContent(
            userDTO = userDTO,
            onSave = onSaveClick
        )
    }
}

@OptIn(ExperimentalEncodingApi::class)
@Composable
fun EditUserProfileScreenContent(
    userDTO: UserDTO,
    onSave: (UserDTO) -> Unit,
) {
    var name by rememberSaveable { mutableStateOf<String>(userDTO.name) }
    var photo by rememberSaveable { mutableStateOf<ByteArray?>(
        userDTO.photo?.let { Base64.decode(it) }
    ) }

    PaddingScreenWithBottomButtons(
        buttons = {
            Button(
                onClick = {
                    onSave(
                        UserDTO(
                            id = userDTO.id,
                            name = name,
                            photo = photo?.let { Base64.encode(it) }
                        )
                    )
                },
                enabled = name.isNotBlank(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalColorPalette.current.buttonColor
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.save_profile))
            }
        },
        paddingSpaces = PaddingSpaces(2)
    ) {
        MyStyledTextField(
            value = name ?: "",
            onValueChange = { name = it },
            label = stringResource(R.string.first_name),
        )

        DefaultSpacer(2)
        Text(text = stringResource(R.string.photo))
        DefaultSpacer()
        ImagePicker(
            clientImage = photo,
            onImageLoad = { photo = it }
        )
    }
}