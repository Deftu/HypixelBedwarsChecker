package xyz.deftu.hbc

import com.google.gson.JsonParser
import gg.essential.api.EssentialAPI
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import xyz.deftu.hbc.api.HypixelRequester

@Mod(
    name = HypixelBedwarsChecker.name,
    version = HypixelBedwarsChecker.version,
    modid = HypixelBedwarsChecker.id,
    acceptedMinecraftVersions = "[1.8.9]",
    clientSideOnly = true
)
class HypixelBedwarsChecker {
    lateinit var fileHandler: FileHandler private set
    lateinit var config: CheckerConfig private set
    lateinit var jsonParser: JsonParser private set
    lateinit var hypixelRequester: HypixelRequester private set
    lateinit var apiKeyChecker: ApiKeyChecker private set
    lateinit var locrawChecker: LocrawChecker private set

    // 7d010519-2de0-4d3f-a455-356b967c7200

    @Mod.EventHandler fun initialize(event: FMLInitializationEvent) {
        fileHandler = FileHandler(Minecraft.getMinecraft().mcDataDir)
        config = CheckerConfig(fileHandler.of("config/Deftu/$name"))
        config.initialize()
        jsonParser = JsonParser()
        hypixelRequester = HypixelRequester("$id/$version")
        apiKeyChecker = ApiKeyChecker(this)
        apiKeyChecker.checkExisting()
        locrawChecker = LocrawChecker()
    }

    @Mod.EventHandler fun postInitialize(event: FMLPostInitializationEvent) {

    }

    companion object {
        const val name = "@NAME@"
        const val version = "@VERSION@"
        const val id = "@ID@"
        @JvmStatic @Mod.Instance lateinit var instance: HypixelBedwarsChecker

        @JvmStatic fun notify(message: String) = EssentialAPI.getNotifications().push(name, message)
        @JvmStatic fun notify(message: String, action: () -> Unit) = EssentialAPI.getNotifications().push(name, message)
    }
}