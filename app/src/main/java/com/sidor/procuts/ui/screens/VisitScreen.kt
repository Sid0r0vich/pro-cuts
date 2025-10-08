package com.sidor.procuts.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sidor.procuts.R
import com.sidor.procuts.data.models.CutDTO
import com.sidor.procuts.data.models.CutDateDTO
import com.sidor.procuts.data.readableDMYDateFormat
import com.sidor.procuts.ui.components.DottedLine
import com.sidor.procuts.ui.components.PaddingSpaces
import com.sidor.procuts.ui.components.RowKeyValue
import com.sidor.procuts.ui.screens.cards.DefaultCard
import com.sidor.procuts.ui.screens.items.CutItem
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette
import com.sidor.procuts.ui.viewmodels.ClientViewModel
import com.sidor.procuts.utils.getPainterFromByteArray
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitScreen(
    visit: CutDateDTO?,
    onBack: () -> Unit,
    onCutClick: (CutDTO) -> Unit,
    cutParams: Map<String, String>,
    cut: CutDTO?,
    viewModel: ClientViewModel = hiltViewModel()
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.visit_tab_app_bar),
                actions = {
                    IconButton(
                        onClick = {
                            visit?.let { viewModel.deleteCutDate(it.id) }
                            onBack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.button_back),
                            tint = LocalColorPalette.current.mainColor
                        )
                    }
                },
                onBack = onBack
            )
        },
    ) {
        if (cut != null && visit != null) {
            VisitScreenContent(
                visit = visit,
                onCutClick = onCutClick,
                cutParams = cutParams,
                cut = cut
            )
        } else Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(if (cut == null) R.string.no_cut_found else R.string.no_visit_found),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalEncodingApi::class)
@Composable
fun VisitScreenContent(
    visit: CutDateDTO,
    onCutClick: (CutDTO) -> Unit,
    cutParams: Map<String, String>,
    cut: CutDTO
) {
    LazyPaddingScreen(
        paddingSpaces = PaddingSpaces(2)
    ) {
        getPainterFromByteArray(
            visit.cutPhoto?.let { Base64.decode(it) }
        )?.let { photo ->
            item {
                DefaultCard {
                    Image(
                        painter = photo,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
                DefaultSpacer()
            }
        }
        item {
            DefaultCard {
                cutParams.forEach { (name, option) ->
                    if (cutParams[name] != null) {
                        RowKeyValue(
                            field = name,
                            value = option,
                            style = MaterialTheme.typography.titleLarge
                        )
                        DefaultSpacer()
                        DottedLine()
                        DefaultSpacer()
                    }
                }
                RowKeyValue(
                    field = stringResource(R.string.date_field),
                    value = readableDMYDateFormat.format(visit.date),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
        item {
            val cut = cut
            DefaultSpacer()
            CutItem(
                name = cut.name,
                onClick = { onCutClick(cut) }
            )
        }
    }
}