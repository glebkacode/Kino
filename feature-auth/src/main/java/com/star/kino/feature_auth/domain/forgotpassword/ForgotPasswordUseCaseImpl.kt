package com.star.kino.feature_auth.domain.forgotpassword

import com.star.kino.feature_auth.data.repository.AuthRepository

class ForgotPasswordUseCaseImpl(
    private val authRepository: AuthRepository
) : ForgotPasswordUseCase {

    override suspend fun invoke(email: String) {
        authRepository.resetPassword(email)
    }
}