package com.star.kino.feature_auth.presentation.shared

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun Email(
    text: String,
    isValid: Boolean,
    onTextChanged: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChanged
    )
    if (!isValid) {
        Text(
            text = "Email is wrong",
            color = Color.Red
        )
    }
}