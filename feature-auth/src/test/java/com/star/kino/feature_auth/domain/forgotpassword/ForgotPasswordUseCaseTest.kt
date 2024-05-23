package com.star.kino.feature_auth.domain.forgotpassword

import com.star.kino.feature_auth.data.repository.AuthRepository
import io.kotest.core.spec.style.FreeSpec
import io.mockk.coVerify
import io.mockk.mockk

class ForgotPasswordUseCaseTest : FreeSpec({
    "check forgot password" {
        val repository = mockk<AuthRepository>(relaxed = true)
        val useCase = ForgotPasswordUseCaseImpl(repository)

        useCase("email")

        coVerify(exactly = 1) { repository.resetPassword("email") }
    }
})