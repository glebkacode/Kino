package com.star.kino.feature_auth.presentation.forgotpassword

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.utils.JvmSerializable
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordStore.Intent
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordStore.Label
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordStore.State

interface ForgotPasswordStore : Store<Intent, State, Label> {

    sealed class Intent : JvmSerializable {
        data class EmailChanged(val text: String) : Intent()
        data object ApplyRecoveryPassword : Intent()
        data object ComeBack : Intent()
    }

    sealed class Label : JvmSerializable {
        data object NavigateBack : Label()
    }

    data class State(
        val email: String = ""
    )
}