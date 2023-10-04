package com.girogevoro.films.data.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorApi : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            chain.request()
                .newBuilder()
                .build()
        )
}
