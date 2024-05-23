package com.star.kino.feature_auth.presentation.forgotpassword

interface ForgotPasswordComponent {
    fun onEmailChanged(text: String)
    fun onApplyRecoveryPassword()
    fun onBackClicked()

    sealed class Output {
        data object Back : com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordComponent.Output()
    }
}