package xyz.deftu.hbc

import net.minecraftforge.common.MinecraftForge
import xyz.deftu.hbc.api.HypixelEndpoint
import xyz.deftu.hbc.api.responses.HypixelKeyResponse

class ApiKeyChecker(
    val hypixelBedwarsChecker: HypixelBedwarsChecker
) {
    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    fun checkExisting() {
        if (hypixelBedwarsChecker.config.apiKey.isEmpty()) HypixelBedwarsChecker.notify("You don't have an API key set! Please set one by either running the \"/api new\" command on Hypixel or set it in your config.")
        else if (!hypixelBedwarsChecker.hypixelRequester.create<HypixelKeyResponse>(
                HypixelEndpoint.KEY,
                parameters = arrayOf(hypixelBedwarsChecker.config.apiKey)
            ).success
        ) HypixelBedwarsChecker.notify("It seems the API key you've set is invalid. Please reset it!")
    }
}