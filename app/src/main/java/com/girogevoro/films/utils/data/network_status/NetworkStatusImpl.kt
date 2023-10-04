package com.girogevoro.films.utils.data.network_status

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkStatusImpl constructor(private val context: Context) : NetworkStatus {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val availableNetworks = mutableSetOf<Network>()
    private val stateFlowNetworkCallback = MutableStateFlow(false)

    private val networkCallback: ConnectivityManager.NetworkCallback =
        object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                availableNetworks.add(network)
                stateFlowNetworkCallback.value = true
            }

            override fun onUnavailable() {
                super.onUnavailable()
                stateFlowNetworkCallback.value = false
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                availableNetworks.remove(network)
                stateFlowNetworkCallback.value = availableNetworks.isNotEmpty()
            }
        }

    override fun registerNetworkCallback(): StateFlow<Boolean> {
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
        return stateFlowNetworkCallback
    }

    override fun unregisterNetworkCallback() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}