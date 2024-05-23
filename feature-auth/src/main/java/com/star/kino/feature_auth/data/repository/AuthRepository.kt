package com.star.kino.feature_auth.data.repository

import com.star.kino.feature_auth.domain.model.User

interface AuthRepository {
    suspend fun registerUser(user: User)

    suspend fun authUser(user: User)

    suspend fun resetPassword(email: String)
}