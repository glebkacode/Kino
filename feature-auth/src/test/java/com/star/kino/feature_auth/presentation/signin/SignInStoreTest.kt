package com.star.kino.feature_auth.presentation.signin

import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.star.kino.feature_auth.core.test
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCase
import com.star.kino.feature_auth.domain.model.User
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers

class SignInStoreTest : FreeSpec({
    "check email changed" {
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = SignInStoreFactory(
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
        ).create()

        store.accept(SignInStore.Intent.EmailChanged("email"))

        store.state.apply {
            email shouldBe "email"
            password shouldBe ""
        }
    }

    "check password changed" {
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = SignInStoreFactory(
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
        ).create()

        store.accept(SignInStore.Intent.PasswordChanged("password"))

        store.state.apply {
            email shouldBe ""
            password shouldBe "password"
        }
    }

    "check throw label on no account clicked" {
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val store = SignInStoreFactory(
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
        ).create()
        val labels = store.labels.test()

        store.accept(SignInStore.Intent.NoAccountClicked)

        store.state.apply {
            email shouldBe ""
            password shouldBe ""
        }
        labels shouldBe listOf(SignInStore.Label.RegisterNewUser)
    }

    "check open lk after success login" {
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        coEvery {
            loginUserUseCase(User(email = "email", password = "password"))
        } returns Unit
        val store = SignInStoreFactory(
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
        ).create()
        val labels = store.labels.test()

        store.accept(SignInStore.Intent.EmailChanged(text = "email"))
        store.accept(SignInStore.Intent.PasswordChanged(text = "password"))
        store.accept(SignInStore.Intent.LoginClicked)

        labels shouldBe listOf(SignInStore.Label.OpenLk)
    }

    "check forgot password" {
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        coEvery {
            loginUserUseCase(User(email = "email", password = "password"))
        } returns Unit
        val store = SignInStoreFactory(
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            mainContext = Dispatchers.Unconfined,
            ioContext = Dispatchers.Unconfined,
        ).create()
        val labels = store.labels.test()

        store.accept(SignInStore.Intent.ForgotPasswordClicked)

        labels shouldBe listOf(SignInStore.Label.RecoveryPassword)
    }
})