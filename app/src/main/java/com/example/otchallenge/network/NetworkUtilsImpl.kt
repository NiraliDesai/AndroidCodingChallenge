package com.example.otchallenge.util.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Network Util is to check if internet is connected or not.
 */
class NetworkUtilsImpl @Inject constructor(private val context: Context) : NetworkUtils {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}