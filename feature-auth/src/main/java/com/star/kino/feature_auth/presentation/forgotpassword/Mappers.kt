package com.star.kino.feature_auth.presentation.forgotpassword

internal val addLabelToOutput: (ForgotPasswordStore.Label) -> ForgotPasswordComponent.Output =
    { label ->
        when(label) {
            ForgotPasswordStore.Label.NavigateBack -> ForgotPasswordComponent.Output.Back
        }
    }