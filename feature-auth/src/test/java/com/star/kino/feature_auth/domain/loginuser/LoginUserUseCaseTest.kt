package com.star.kino.feature_auth.domain.loginuser

import com.star.kino.feature_auth.data.repository.AuthRepository
import com.star.kino.feature_auth.domain.loginuser.LoginUserUseCaseImpl
import com.star.kino.feature_auth.domain.model.User
import io.kotest.core.spec.style.FreeSpec
import io.mockk.coVerify
import io.mockk.mockk

class LoginUserUseCaseTest : FreeSpec({
    "check user login" {
        val repository = mockk<AuthRepository>(relaxed = true)
        val useCase = LoginUserUseCaseImpl(repository)
        val user = mockk<User>(relaxed = true)

        useCase(user)

        coVerify(exactly = 1) { repository.authUser(user) }
    }
})