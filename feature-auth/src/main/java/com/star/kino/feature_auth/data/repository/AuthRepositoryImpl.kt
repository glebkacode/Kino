package com.star.kino.feature_auth.data.repository

import com.star.kino.feature_auth.domain.model.User

class AuthRepositoryImpl : AuthRepository {

    override suspend fun registerUser(user: User) {
        println("registerUser was successful")
    }

    override suspend fun authUser(user: User) {
        println("authUser was successful")
    }

    override suspend fun resetPassword(email: String) {
        println("resetPassword was successful")
    }
}