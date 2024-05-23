package com.star.kino.feature_auth.presentation.signup

internal val statesToModel: (SignUpStore.State) -> Model = { state ->
    Model(
        email = state.email,
        password = state.password
    )
}