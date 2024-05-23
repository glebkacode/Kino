package com.star.kino.feature_auth.presentation.signin

import kotlinx.coroutines.flow.Flow

interface SignInComponent {
    val models: Flow<Model>

    fun onEmailChanged(text: String)
    fun onPasswordChanged(text: String)
    fun onApplyButtonClicked()
    fun onNoAccountClicked()
    fun onForgotPasswordClicked()

    sealed class Output {
        data object OpenForgotPassword : Output()
        data object OpenSignUp : Output()
        data object OpenLk : Output()
    }
}