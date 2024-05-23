package com.star.kino.feature_auth.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class CoreDispatcherImpl : CoreDispatcher {
    override fun main(): CoroutineContext = Dispatchers.Main

    override fun io(): CoroutineContext = Dispatchers.IO

    override fun default(): CoroutineContext = Dispatchers.Default
    override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}