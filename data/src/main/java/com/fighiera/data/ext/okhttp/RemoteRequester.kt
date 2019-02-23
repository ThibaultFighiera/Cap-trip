package com.fighiera.data.ext.okhttp

import com.fighiera.data.BuildConfig
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteRequester {

    private val url = HttpUrl.Builder()
        .scheme(BuildConfig.SERVER_SCHEME)
        .host(BuildConfig.SERVER_BASE_URL)
        .addPathSegment(BuildConfig.SERVER_PAYLOAD)

    fun requestJson(): String? {
        val request = Request.Builder()
            .header("Accept", "application/json")
            .url(url.build())
            .build()
        return client.newCall(request).execute().body()?.string()
    }

    fun addPathSegment(scheme: String) {
        url.addPathSegment(scheme)
    }

    companion object {
        private val client = OkHttpClient()
    }
}