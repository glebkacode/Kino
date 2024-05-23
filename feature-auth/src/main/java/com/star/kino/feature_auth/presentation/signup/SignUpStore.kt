package com.star.kino.feature_auth.presentation.signup

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.star.kino.feature_auth.presentation.signup.SignUpStore.Intent
import com.star.kino.feature_auth.presentation.signup.SignUpStore.State

interface SignUpStore : Store<Intent, State, Nothing> {

    sealed interface Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent
        data class PasswordChanged(val text: String) : Intent
        data object RegistrationClicked : Intent
    }

    data class State(
        val email: String = "",
        val password: String = ""
    ) : JvmSerializable
}