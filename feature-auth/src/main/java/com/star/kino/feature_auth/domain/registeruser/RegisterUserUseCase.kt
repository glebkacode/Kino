package com.star.kino.feature_auth.domain.registeruser

import com.star.kino.feature_auth.domain.model.User

typealias RegisterUserUseCase = suspend (User) -> Unit