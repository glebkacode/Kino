package com.star.kino.feature_auth.presentation.forgotpassword

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ForgotPasswordScreen(
    component: com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordComponent
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Forgot Password",
            modifier = Modifier.clickable { component.onBackClicked() }
        )
        TextField(
            value = "",
            onValueChange = component::onEmailChanged
        )
        OutlinedButton(onClick = { component.onApplyRecoveryPassword() }) {
            Text(text = "Recovery password")
        }
    }
}