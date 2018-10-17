package com.jokopriyono.dicodingfootball

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object HelperMethods {
    /**
     * Pengecekan koneksi data device
     * @return default true, jika false artinya tidak ada sambungan data
     * @param context context
     */
    fun checkInternet(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var netInfo: NetworkInfo? = null
        if (true) {
            netInfo = cm.activeNetworkInfo
        }
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
