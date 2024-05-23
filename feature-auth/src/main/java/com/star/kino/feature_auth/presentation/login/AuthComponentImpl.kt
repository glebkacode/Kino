package com.star.kino.feature_auth.presentation.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.star.kino.feature_auth.core.CoreDispatcher
import com.star.kino.feature_auth.presentation.login.AuthComponent.Child
import com.star.kino.feature_auth.presentation.signin.SignInComponent
import com.star.kino.feature_auth.presentation.signin.SignInComponentImpl
import com.star.kino.feature_auth.presentation.signup.SignUpComponent
import com.star.kino.feature_auth.presentation.signup.SignUpComponentImpl
import com.star.kino.feature_auth.domain.forgotpassword.ForgotPasswordUseCase
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCase
import com.star.kino.feature_auth.domain.registeruser.RegisterUserUseCase
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordComponent
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordComponentImpl
import kotlinx.serialization.Serializable

class AuthComponentImpl(
    componentContext: ComponentContext,
    private val storeFactory: StoreFactory,
    private val loginUserUseCase: LoginUserUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val coreDispatcher: CoreDispatcher,
    private val openLk: () -> Unit
) : AuthComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    private val stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.SignIn,
        childFactory = ::child
    )
    override val childStack: Value<ChildStack<*, Child>> = stack

    private fun child(config: Config, componentContext: ComponentContext): Child =
        when (config) {
            Config.SignIn -> Child.SignInChild(
                SignInComponentImpl(
                    componentContext,
                    storeFactory,
                    loginUserUseCase,
                    forgotPasswordUseCase,
                    coreDispatcher,
                    openLk = { openLk() },
                    openSignUp = { openSignUp() },
                    openForgotPassword = { openForgotPassword() }

                )
            )
            Config.SignUp -> Child.SignUpChild(
                SignUpComponentImpl(
                    componentContext,
                    storeFactory,
                    registerUserUseCase,
                    coreDispatcher,
                    ::onSignUpOutput
                )
            )
            Config.ForgotPassword -> Child.ForgotPasswordChild(
                ForgotPasswordComponentImpl(
                    componentContext,
                    storeFactory,
                    forgotPasswordUseCase,
                    coreDispatcher,
                    ::onForgotPasswordOutput
                )
            )
        }

    override fun openSignUp() {
        navigation.push(Config.SignUp)
    }

    override fun openForgotPassword() {
        navigation.push(Config.ForgotPassword)
    }

    override fun back() {
        navigation.pop()
    }

    private fun onSignUpOutput(output: SignUpComponent.Output) {
        when(output) {
            SignUpComponent.Output.Back -> back()
        }
    }

    private fun onForgotPasswordOutput(output: ForgotPasswordComponent.Output) {
        when(output) {
            ForgotPasswordComponent.Output.Back -> back()
        }
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object SignIn : Config
        @Serializable
        data object SignUp : Config
        @Serializable
        data object ForgotPassword : Config
    }
}