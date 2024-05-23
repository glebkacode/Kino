package com.star.kino.feature_auth.presentation.signup

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.star.kino.feature_auth.core.CoreDispatcher
import com.star.kino.feature_auth.domain.registeruser.RegisterUserUseCase
import kotlinx.coroutines.flow.mapNotNull

class SignUpComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    registerUserUseCase: RegisterUserUseCase,
    coreDispatcher: CoreDispatcher,
    private val output: (SignUpComponent.Output) -> Unit
) : SignUpComponent, ComponentContext by componentContext {

    private val signUpStore = instanceKeeper.getStore {
        SignUpStoreFactory(
            storeFactory,
            registerUserUseCase,
            coreDispatcher.main(),
            coreDispatcher.io()
        ).create()
    }

    override val models = signUpStore.states.mapNotNull { state -> statesToModel(state) }
    override fun onSignUpClicked() {
        output(SignUpComponent.Output.Back)
    }

    override fun onEmailChanged(text: String) {
        signUpStore.accept(SignUpStore.Intent.EmailChanged(text))
    }

    override fun onPasswordChanged(text: String) {
        signUpStore.accept(SignUpStore.Intent.PasswordChanged(text))
    }

    override fun onApplyButtonClicked() {
        signUpStore.accept(SignUpStore.Intent.RegistrationClicked)
    }
}