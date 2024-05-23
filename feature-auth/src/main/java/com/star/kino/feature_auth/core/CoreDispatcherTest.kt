package com.star.kino.feature_auth.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoreDispatcherTest : CoreDispatcher {
    override fun main(): CoroutineContext = Dispatchers.Unconfined

    override fun io(): CoroutineContext = Dispatchers.Unconfined

    override fun default(): CoroutineContext = Dispatchers.Unconfined
    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}