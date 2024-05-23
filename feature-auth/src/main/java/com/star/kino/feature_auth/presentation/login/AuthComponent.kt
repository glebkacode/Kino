package com.star.kino.feature_auth.presentation.login

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.star.kino.feature_auth.presentation.signin.SignInComponent
import com.star.kino.feature_auth.presentation.signup.SignUpComponent

interface AuthComponent {

    val childStack: Value<ChildStack<*, Child>>
    fun openSignUp()
    fun openForgotPassword()
    fun back()

    sealed class Child {
        class SignInChild(val component: SignInComponent) : Child()
        class SignUpChild(val component: SignUpComponent) : Child()
        class ForgotPasswordChild(val component: com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordComponent) : Child()
    }
}