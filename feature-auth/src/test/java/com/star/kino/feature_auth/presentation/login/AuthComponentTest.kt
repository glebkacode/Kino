package com.star.kino.feature_auth.presentation.login

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.router.stack.active
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.star.kino.feature_auth.core.CoreDispatcherTest
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCase
import com.star.kino.feature_auth.domain.registeruser.RegisterUserUseCase
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk

class AuthComponentTest : FreeSpec() {
    private val lifecycle = LifecycleRegistry()
    private val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
    private val registerUserUseCase = mockk<RegisterUserUseCase>(relaxed = true)
    private val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
    private val authComponent = AuthComponentImpl(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
        storeFactory = DefaultStoreFactory(),
        loginUserUseCase = loginUserUseCase,
        registerUserUseCase = registerUserUseCase,
        forgotPasswordUseCase = forgotPasswordUseCase,
        coreDispatcher = CoreDispatcherTest(),
        openLk = { }
    )
    private val activeScreen: AuthComponent.Child
        get() = authComponent.childStack.active.instance

    init {
        "check opening sign in" {
            lifecycle.resume()
            val expected = true

            val actual = activeScreen is AuthComponent.Child.SignInChild

            expected shouldBe actual
        }

        "check opening sign up" {
            lifecycle.resume()
            val expected = true

            authComponent.openSignUp()
            val actual = activeScreen is AuthComponent.Child.SignUpChild

            expected shouldBe actual
        }

        "check opening forgot password" {
            lifecycle.resume()
            val expected = true

            authComponent.openForgotPassword()
            val actual = activeScreen is AuthComponent.Child.ForgotPasswordChild

            expected shouldBe actual
        }
    }
}