package com.star.kino.feature_auth.domain.registeruser

import com.star.kino.feature_auth.data.repository.AuthRepository
import com.star.kino.feature_auth.domain.model.User
import io.kotest.core.spec.style.FreeSpec
import io.mockk.coVerify
import io.mockk.mockk

class RegisterUserUseCaseTest : FreeSpec({
    "check register user" {
        val user = mockk<User>(relaxed = true)
        val repository = mockk<AuthRepository>(relaxed = true)
        val useCase = RegisterUserUseCaseImpl(repository)

        useCase(user)

        coVerify(exactly = 1) { repository.registerUser(user) }
    }
})