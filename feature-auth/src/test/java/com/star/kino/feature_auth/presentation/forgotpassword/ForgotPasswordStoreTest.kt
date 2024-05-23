package com.star.kino.feature_auth.presentation.forgotpassword

import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.star.kino.feature_auth.core.test
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers

class ForgotPasswordStoreTest : FreeSpec({
    "check change email" {
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = ForgotPasswordStoreFactory(
            storeFactory = DefaultStoreFactory(),
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()

        store.accept(ForgotPasswordStore.Intent.EmailChanged(text = "alex@alex.com"))

        store.state.apply {
            email shouldBe "alex@alex.com"
        }
    }

    "check recover password" {
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = ForgotPasswordStoreFactory(
            storeFactory = DefaultStoreFactory(),
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()
        val labels = store.labels.test()

        store.apply {
            accept(ForgotPasswordStore.Intent.EmailChanged(text = "alex@alex.com"))
            accept(ForgotPasswordStore.Intent.ApplyRecoveryPassword)
        }

        store.state.apply {
            email shouldBe "alex@alex.com"
        }
        coVerify(exactly = 1) {
            forgotPasswordUseCase("alex@alex.com")
        }
        labels shouldBe listOf(ForgotPasswordStore.Label.NavigateBack)
    }

    "check navigate back" {
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = ForgotPasswordStoreFactory(
            storeFactory = DefaultStoreFactory(),
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()
        val labels = store.labels.test()

        store.accept(ForgotPasswordStore.Intent.ComeBack)

        labels shouldBe listOf(ForgotPasswordStore.Label.NavigateBack)
    }
})