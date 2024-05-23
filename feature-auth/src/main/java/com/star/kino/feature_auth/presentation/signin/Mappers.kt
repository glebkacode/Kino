package com.star.kino.feature_auth.presentation.signin

internal val statesToModel: (SignInStore.State) -> Model =
    { state ->
        Model(
            email = EmailModel(
                text = state.email,
                isValid = state.isEmailValid
            ),
            password = PasswordModel(
                text = state.password,
                isValid = state.isPasswordValid
            ),
            isError = state.errorType != SignInStore.ErrorType.None
        )
    }