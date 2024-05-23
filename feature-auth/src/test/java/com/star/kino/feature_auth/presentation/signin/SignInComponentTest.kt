package com.star.kino.feature_auth.presentation.signin

import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.star.kino.feature_auth.core.CoreDispatcherTest
import com.star.kino.feature_auth.core.test
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCase
import com.star.kino.feature_auth.domain.model.User
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk

class SignInComponentTest : FreeSpec({
    "check email changed" {
        val lifecycle = LifecycleRegistry()
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val component = SignInComponentImpl(
            componentContext = DefaultComponentContext(lifecycle),
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            coreDispatcher = CoreDispatcherTest(),
            openLk = {},
            openSignUp = {},
            openForgotPassword = {}
        )
        val actual = component.models.test()
        val expected = listOf(
            Model(
                email = EmailModel(
                    isValid = false
                )
            ),
            Model(
                email = EmailModel(
                    text = "alex@alex.com",
                    isValid = true
                )
            )
        )
        lifecycle.resume()

        component.onEmailChanged(text = "alex@alex.com")

        actual shouldBe expected
    }

    "check password changed" {
        val lifecycle = LifecycleRegistry()
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        val component = SignInComponentImpl(
            componentContext = DefaultComponentContext(lifecycle),
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            coreDispatcher = CoreDispatcherTest(),
            openLk = {},
            openSignUp = {},
            openForgotPassword = {}
        )
        val actual = component.models.test()
        val expected = listOf(
            Model(
                email = EmailModel(
                    isValid = false
                )
            ),
            Model(
                email = EmailModel(
                    text = "",
                    isValid = false
                ),
                password = PasswordModel(
                    text = "alex",
                    isValid = true
                )
            )
        )
        lifecycle.resume()

        component.onPasswordChanged("alex")

        actual shouldBe expected
    }

    "check apply button" {
        val lifecycle = LifecycleRegistry()
        val loginUserUseCase = mockk<LoginUserUseCase>(relaxed = true)
        val forgotPasswordUseCase = mockk<ForgotPasswordUseCase>(relaxed = true)
        var isOpenLkClicked = false
        val component = SignInComponentImpl(
            componentContext = DefaultComponentContext(lifecycle),
            storeFactory = DefaultStoreFactory(),
            loginUserUseCase = loginUserUseCase,
            forgotPasswordUseCase = forgotPasswordUseCase,
            coreDispatcher = CoreDispatcherTest(),
            openLk = {
                isOpenLkClicked = true
            },
            openSignUp = {},
            openForgotPassword = {}
        )
        coEvery {
            loginUserUseCase(
                User(
                    email = "alex@alex.com",
                    password = "alex"
                )
            )
        } returns Unit
        val actual = component.models.test()
        val expected = listOf(
            Model(
                email = EmailModel(
                    isValid = false
                )
            ),
            Model(
                email = EmailModel(
                    text = "alex@alex.com",
                    isValid = true
                ),
                password = PasswordModel(
                    text = "",
                    isValid = false
                )
            ),
            Model(
                email = EmailModel(
                    text = "alex@alex.com",
                    isValid = true
                ),
                password = PasswordModel(
                    text = "alex",
                    isValid = true
                )
            )
        )
        lifecycle.resume()

        component.onEmailChanged(text = "alex@alex.com")
        component.onPasswordChanged("alex")
        component.onApplyButtonClicked()

        actual shouldBe expected
        isOpenLkClicked shouldBe true
    }
})