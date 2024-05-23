package com.star.kino.feature_auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.star.kino.feature_auth.presentation.forgotpassword.ForgotPasswordScreen
import com.star.kino.feature_auth.presentation.signin.SignInScreen
import com.star.kino.feature_auth.presentation.signup.SignUpScreen

/*typealias LoginScreen = @Composable (*//*SignInController, SignUpController*//*) -> Unit*/

/*@Inject*/
@Composable
fun LoginScreen(component: AuthComponent, modifier: Modifier) {
    Children(
        stack = component.childStack,
        modifier = modifier
    ) {
        when (val child = it.instance) {
            is AuthComponent.Child.SignInChild -> SignInScreen(child.component)
            is AuthComponent.Child.SignUpChild -> SignUpScreen(child.component)
            is AuthComponent.Child.ForgotPasswordChild -> ForgotPasswordScreen(child.component)
        }
    }
}