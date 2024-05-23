package com.star.kino.feature_auth.presentation.shared

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ForgotPassword(
    text: String,
    onSelected: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier.clickable { onSelected() }
    )
}
