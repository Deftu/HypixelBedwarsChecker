package xyz.deftu.hbc.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class HypixelRequester(
    val userAgent: String
) {
    val httpClient = OkHttpClient.Builder()
        .addInterceptor{ it.proceed(it.request().newBuilder().header("User-Agent", userAgent).build()) }
        .build()
    val gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    @JvmOverloads fun <T : HypixelResponse> create(endpoint: HypixelEndpoint, body: RequestBody? = null, vararg parameters: String): T {
        var route = endpoint.route
        for (parameter in parameters) route = route.replaceFirst("{}", parameter)

        val request = Request.Builder()
            .method(endpoint.method.name, body)
            .url(route)
            .build()
        val response = httpClient.newCall(request).execute()
        response.body?.let {
            return gson.fromJson(it.string(), endpoint.responseType) as T
        } ?: throw IllegalStateException("Hypixel did not provide a response body when replying to a request.")
    }
}