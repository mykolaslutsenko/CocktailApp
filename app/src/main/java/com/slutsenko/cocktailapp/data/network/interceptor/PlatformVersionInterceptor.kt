package com.slutsenko.cocktailapp.data.network.interceptor

import android.os.Build
import com.slutsenko.cocktailapp.data.network.Constant
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class PlatformVersionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return with(original.newBuilder()) {
            header(Constant.Header.PLATFORM_VERSION, Build.VERSION.RELEASE ?: "API ${Build.VERSION.SDK_INT}")
            method(original.method, original.body)
            chain.proceed(build())
        }
    }
}