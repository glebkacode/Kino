package com.star.kino.feature_auth.domain.loginuser

import com.star.kino.feature_auth.domain.model.User

typealias LoginUserUseCase = suspend (User) -> Unit