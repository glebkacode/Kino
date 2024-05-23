package com.star.kino.feature_auth.presentation.signup

import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.star.kino.feature_auth.domain.model.User
import com.star.kino.feature_auth.domain.registeruser.RegisterUserUseCase
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers

class SignUpStoreTest : FreeSpec({
    "check email change" {
        mockk<RegisterUserUseCase>(relaxed = true)
        val store = SignUpStoreFactory(
            storeFactory = DefaultStoreFactory(),
            registerUserUseCase = mockk(relaxed = true),
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()

        store.accept(SignUpStore.Intent.EmailChanged(text = "alex@alex.com"))

        store.state.apply {
            email shouldBe "alex@alex.com"
        }
    }

    "check password change" {
        val store = SignUpStoreFactory(
            storeFactory = DefaultStoreFactory(),
            registerUserUseCase = mockk(relaxed = true),
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()

        store.accept(SignUpStore.Intent.PasswordChanged(text = "alex"))

        store.state.apply {
            password shouldBe "alex"
        }
    }

    "check apply click" {
        val registerUserUseCase = mockk<RegisterUserUseCase>(relaxed = true)
        val store = SignUpStoreFactory(
            storeFactory = DefaultStoreFactory(),
            registerUserUseCase = registerUserUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined
        ).create()

        store.apply {
            accept(SignUpStore.Intent.EmailChanged(text = "alex@alex.com"))
            accept(SignUpStore.Intent.PasswordChanged(text = "alex"))
            accept(SignUpStore.Intent.RegistrationClicked)
        }

        coVerify {
            registerUserUseCase(
                User(
                    email = "alex@alex.com",
                    password = "alex"
                )
            )
        }
    }
})