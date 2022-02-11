package xyz.deftu.hbc.api

import xyz.deftu.hbc.api.responses.HypixelKeyResponse

enum class HypixelEndpoint(
    val route: String,
    val method: HttpMethod,
    val responseType: Class<out HypixelResponse>
) {
    KEY("https://api.hypixel.net/key?key={}", HttpMethod.GET, HypixelKeyResponse::class.java)
}