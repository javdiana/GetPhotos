package com.javdiana.getphotos

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager


class NetworkManager {
    companion object {
        fun isNetworkAvailable(context: Context?): Boolean {
            val cm = context?.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}