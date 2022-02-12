package xyz.deftu.hbc

import gg.essential.api.EssentialAPI
import gg.essential.api.utils.Multithreading
import gg.essential.universal.ChatColor
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import xyz.deftu.hbc.api.HypixelEndpoint
import xyz.deftu.hbc.api.HypixelResponse
import java.util.concurrent.TimeUnit

class ApiKeyChecker(
    val hypixelBedwarsChecker: HypixelBedwarsChecker
) {
    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    private val apiKeyNotPresetMessage = arrayOf(
        "You don't have an API key set!",
        "Please set one by either running the",
        "\"/api new\" command on Hypixel or set",
        "it in your config"
    )

    fun checkExisting() {
        if (hypixelBedwarsChecker.config.apiKey.isEmpty()) HypixelBedwarsChecker.notify(apiKeyNotPresetMessage.joinToString(" "))
        else if (!hypixelBedwarsChecker.hypixelRequester.create<HypixelResponse>(
                HypixelEndpoint.KEY,
                parameters = arrayOf(hypixelBedwarsChecker.config.apiKey)
            ).successful
        ) HypixelBedwarsChecker.notify("It seems the API key you've set is invalid. Please reset it!")
    }

    @SubscribeEvent fun onChatReceived(event: ClientChatReceivedEvent) {
        if (EssentialAPI.getMinecraftUtil().isHypixel() && hypixelBedwarsChecker.config.saveNewApiKeys) {
            val msg = event.message.unformattedText
            if (!msg.startsWith("Your new API key is ")) return
            val apiKey = msg.replace("Your new API key is ", "")
            HypixelBedwarsChecker.message("${ChatColor.GREEN}Setting and checking new API key... This will take a few seconds.")
            Multithreading.schedule({
                if (!hypixelBedwarsChecker.hypixelRequester.create<HypixelResponse>(
                        HypixelEndpoint.KEY,
                        parameters = arrayOf(apiKey)
                    ).successful) HypixelBedwarsChecker.message("${ChatColor.RED}This new API key is invalid. This shouldn't be possible!").also { return@schedule }
                hypixelBedwarsChecker.config.apiKey = apiKey
                hypixelBedwarsChecker.config.markDirty()
                hypixelBedwarsChecker.config.writeData()
                HypixelBedwarsChecker.message("${ChatColor.GREEN}Successfully set your new API key in the config.")
            }, 5, TimeUnit.SECONDS)
        }
    }
}