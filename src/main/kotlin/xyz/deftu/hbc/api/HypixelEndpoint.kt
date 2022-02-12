package xyz.deftu.hbc.api

enum class HypixelEndpoint(
    val route: String,
    val method: HttpMethod,
    val responseType: Class<out HypixelResponse>
) {
    KEY("https://api.hypixel.net/key?key={}", HttpMethod.GET, HypixelResponse::class.java)
}