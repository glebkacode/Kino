package com.star.kino.feature_auth.data.api

data class RegisterUserRequest(
    private val email: String,
    private val password: String
)