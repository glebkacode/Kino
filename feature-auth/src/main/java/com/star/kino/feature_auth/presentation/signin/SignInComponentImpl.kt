package com.star.kino.feature_auth.presentation.signin

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.star.kino.feature_auth.core.CoreDispatcher
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCase
import kotlinx.coroutines.flow.mapNotNull

class SignInComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    loginUserUseCase: LoginUserUseCase,
    forgotPasswordUseCase: ForgotPasswordUseCase,
    coreDispatcher: CoreDispatcher,
    private val openForgotPassword: () -> Unit,
    private val openSignUp: () -> Unit,
    private val openLk: () -> Unit
) : SignInComponent, ComponentContext by componentContext {

    private val signInStore = instanceKeeper.getStore {
        SignInStoreFactory(
            storeFactory,
            loginUserUseCase,
            forgotPasswordUseCase,
            coreDispatcher.main(),
            coreDispatcher.io()
        ).create()
    }

    override val models = signInStore.states.mapNotNull { state -> statesToModel.invoke(state) }

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY, coreDispatcher.unconfined()) {
            signInStore.labels bindTo { label ->
                when (label) {
                    SignInStore.Label.OpenLk -> openLk()
                    SignInStore.Label.RecoveryPassword -> openSignUp()
                    SignInStore.Label.RegisterNewUser -> openLk()
                }
            }
        }
    }

    override fun onEmailChanged(text: String) {
        signInStore.accept(SignInStore.Intent.EmailChanged(text))
    }

    override fun onPasswordChanged(text: String) {
        signInStore.accept(SignInStore.Intent.PasswordChanged(text))
    }

    override fun onApplyButtonClicked() {
        signInStore.accept(SignInStore.Intent.LoginClicked)
    }

    override fun onNoAccountClicked() {
        signInStore.accept(SignInStore.Intent.NoAccountClicked)
    }

    override fun onForgotPasswordClicked() {
        openForgotPassword()
    }
}