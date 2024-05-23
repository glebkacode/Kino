package com.star.kino.feature_auth.domain.loginuser

import com.star.kino.feature_auth.data.repository.AuthRepository
import com.star.kino.feature_auth.domain.model.User

class LoginUserUseCaseImpl(
    private val authRepository: AuthRepository
) : LoginUserUseCase {

    override suspend fun invoke(user: User) {
        authRepository.authUser(user)
    }
}