package com.example.meowmates.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.meowmates.view.ui.theme.MeowMatesTheme

@Composable
fun CustomTextField(
    label: String,
    value: MutableState<String>,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .background(MeowMatesTheme.colors.container, shape = RoundedCornerShape(10.dp)),
        placeholder = { Text(label, color = MeowMatesTheme.colors.text, style = MeowMatesTheme.fonts.textWatermark) },
        value = value.value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        textStyle = MeowMatesTheme.fonts.textWatermark.copy(color = MeowMatesTheme.colors.text)
    )
}