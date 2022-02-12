package xyz.deftu.hbc.api

import xyz.deftu.hbc.api.responses.HypixelPlayerBedwarsResponse

enum class HypixelEndpoint(
    val route: String,
    val method: HttpMethod,
    val responseType: Class<out HypixelResponse>
) {
    KEY("https://api.hypixel.net/key?key={}", HttpMethod.GET, HypixelResponse::class.java),
    PLAYER_BEDWARS("https://api.hypixel.net/player?key={}&uuid={}", HttpMethod.GET, HypixelPlayerBedwarsResponse::class.java)
}