package com.star.kino.feature_auth.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.star.kino.feature_auth.presentation.shared.ApplyButton
import com.star.kino.feature_auth.presentation.shared.Email
import com.star.kino.feature_auth.presentation.shared.ForgotPassword
import com.star.kino.feature_auth.presentation.shared.Password

@Composable
fun SignInScreen(
    component: SignInComponent
) {
    val state by component.models.collectAsState(initial = Model())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Email(
            text = state.email.text,
            isValid = true
        ) { text ->
            component.onEmailChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Password(
            text = state.password.text,
            isValid = true
        ) { text ->
            component.onPasswordChanged(text)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ApplyButton(text = "SignIn") {
            component.onApplyButtonClicked()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ApplyButton(text = "SignUp") {
            component.onNoAccountClicked()
        }
        Spacer(modifier = Modifier.height(16.dp))
        ForgotPassword(text = "Forgot Password") {
            component.onForgotPasswordClicked()
        }
        if (state.isError) {
            Text(text = "No internet connection",)
        }
    }
}