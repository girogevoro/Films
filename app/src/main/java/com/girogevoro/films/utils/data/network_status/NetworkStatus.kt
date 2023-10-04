package com.girogevoro.films.utils.data.network_status

import kotlinx.coroutines.flow.StateFlow

interface NetworkStatus {
    fun registerNetworkCallback(): StateFlow<Boolean>
    fun unregisterNetworkCallback()
}