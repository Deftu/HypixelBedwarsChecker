package xyz.deftu.hbc.api.responses

import com.google.gson.annotations.SerializedName
import xyz.deftu.hbc.api.HypixelResponse

class HypixelPlayerBedwarsResponse(
    successful: Boolean
) : HypixelResponse(
    successful
) {
    @SerializedName("displayname") lateinit var username: String
    lateinit var stats: HypixelPlayerStats
}

class HypixelPlayerStats {
    @SerializedName("Bedwars") lateinit var bedwars: HypixelPlayerStatsBedwars
}

class HypixelPlayerStatsBedwars {
    var winstreak: Long = 0
    @SerializedName("wins_bedwars") var wins: Long = 0
    @SerializedName("kills_bedwars") var kills: Long = 0
    @SerializedName("beds_broken_bedwars") var bedBreaks: Long = 0
}