package xyz.deftu.hbc.api

import com.google.gson.annotations.SerializedName

open class HypixelResponse(
    @SerializedName("success") val successful: Boolean
)