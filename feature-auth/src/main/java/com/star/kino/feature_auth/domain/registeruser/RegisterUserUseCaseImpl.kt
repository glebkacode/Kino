package com.star.kino.feature_auth.domain.registeruser

import com.star.kino.feature_auth.data.repository.AuthRepository
import com.star.kino.feature_auth.domain.model.User

class RegisterUserUseCaseImpl(
    private val authRepository: AuthRepository
) : RegisterUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.registerUser(user)
    }
}