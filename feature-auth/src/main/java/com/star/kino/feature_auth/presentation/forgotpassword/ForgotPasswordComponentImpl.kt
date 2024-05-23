package com.star.kino.feature_auth.presentation.forgotpassword

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.star.kino.feature_auth.core.CoreDispatcher
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import kotlinx.coroutines.flow.mapNotNull

class ForgotPasswordComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory,
    forgotPasswordUseCase: ForgotPasswordUseCase,
    coreDispatcher: CoreDispatcher,
    private val output: (ForgotPasswordComponent.Output) -> Unit
) : ForgotPasswordComponent, ComponentContext by componentContext {

    private val forgotPasswordStore = instanceKeeper.getStore {
        ForgotPasswordStoreFactory(
            storeFactory,
            forgotPasswordUseCase,
            coreDispatcher.main(),
            coreDispatcher.io()
        ).create()
    }

    init {
        bind(lifecycle, BinderLifecycleMode.CREATE_DESTROY, coreDispatcher.unconfined()) {
            forgotPasswordStore.labels.mapNotNull(addLabelToOutput) bindTo output
        }
    }

    override fun onEmailChanged(text: String) {
        forgotPasswordStore.accept(
            ForgotPasswordStore.Intent.EmailChanged(
                text
            )
        )
    }

    override fun onApplyRecoveryPassword() {
        forgotPasswordStore.accept(ForgotPasswordStore.Intent.ApplyRecoveryPassword)
    }

    override fun onBackClicked() {
        forgotPasswordStore.accept(ForgotPasswordStore.Intent.ComeBack)
    }
}