package com.star.kino.feature_auth.presentation.signin

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.star.kino.feature_auth.presentation.signin.SignInStore.Intent
import com.star.kino.feature_auth.presentation.signin.SignInStore.Label
import com.star.kino.feature_auth.presentation.signin.SignInStore.State

interface SignInStore : Store<Intent, State, Label> {

    sealed class Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent()
        data class PasswordChanged(val text: String) : Intent()
        data object ForgotPasswordClicked : Intent()
        data object LoginClicked : Intent()
        data object NoAccountClicked : Intent()
    }

    sealed class Label : JvmSerializable {
        data object RecoveryPassword : Label()
        data object RegisterNewUser : Label()
        data object OpenLk : Label()
    }

    data class State(
        val email: String = "",
        val isEmailValid: Boolean = false,
        val password: String = "",
        val isPasswordValid: Boolean = false,
        val errorType: ErrorType = ErrorType.None
    ) : JvmSerializable

    enum class ErrorType {
        None,
        Network
    }
}