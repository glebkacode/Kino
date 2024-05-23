package com.star.kino.feature_auth.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

interface CoreDispatcher {
    fun main(): CoroutineContext
    fun io(): CoroutineContext
    fun default(): CoroutineContext
    fun unconfined(): CoroutineDispatcher
}