package com.sidor.procuts.ui.screens.questionnaire


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sidor.procuts.R
import com.sidor.procuts.ui.PaddingSpaces
import com.sidor.procuts.ui.QuestionnaireDropdownMenu
import com.sidor.procuts.ui.screens.DefaultPaddingScreenWithQuestionnaireButtons
import com.sidor.procuts.ui.screens.DefaultSpacer
import com.sidor.procuts.ui.screens.TopAppBarScreen
import com.sidor.procuts.ui.screens.topbars.TitleTopAppBar
import com.sidor.procuts.ui.theme.LocalColorPalette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultCutQuestionnaireScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    enabled: Boolean = true,
    content: @Composable (ColumnScope.() -> Unit) = {}
) {
    TopAppBarScreen(
        topBar = {
            TitleTopAppBar(
                title = stringResource(R.string.add_haircut_tab_app_bar),
                onBack = onBack
            )
        },
    ) {
        DefaultPaddingScreenWithQuestionnaireButtons(
            onNext = onNext,
            paddingSpaces = PaddingSpaces(horizontal = 2, top = 2, bottom = 1),
            enabled = enabled
        ) {
            content()
        }
    }
}

@Composable
fun CutQuestionnaireScreenWithSeveralAnswerOption(
    onBack: () -> Unit,
    onNext: () -> Unit,
    text: String,
    defaultValue: String = "",
    valuesList: List<String>,
    onValueChange: (String) -> Unit,
) {
    var value by remember { mutableStateOf<String>(defaultValue) }
    onValueChange(value)

    DefaultCutQuestionnaireScreen(
        onBack = onBack,
        onNext = onNext,
        enabled = value != ""
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        DefaultSpacer(2)
        MultipleChoiceQuestion(
            value = value,
            onValueChanged = {
                onValueChange(it)
                value = it
            },
            menuList = valuesList
        )
    }
}

@Composable
fun MultipleChoiceQuestion(
    value: String,
    onValueChanged: (String) -> Unit,
    menuList: List<String>
) {
    Column {
        menuList.forEach { option ->
            val isSelected = option == value
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onValueChanged(option) }
                    .background(
                        color = if (isSelected) LocalColorPalette.current.buttonColor else Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = option,
                    color = if (isSelected) LocalColorPalette.current.onButtonColor else LocalColorPalette.current.darkFontColor
                )
            }
            DefaultSpacer(1)
        }
    }
}