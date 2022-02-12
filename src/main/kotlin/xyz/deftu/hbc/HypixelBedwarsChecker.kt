package xyz.deftu.hbc

import cc.woverflow.wcore.utils.Updater
import gg.essential.universal.ChatColor
import gg.essential.api.EssentialAPI
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
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
    lateinit var hypixelRequester: HypixelRequester private set
    lateinit var apiKeyChecker: ApiKeyChecker private set
    lateinit var locrawChecker: LocrawChecker private set

    @Mod.EventHandler fun preInitialize(event: FMLPreInitializationEvent) {
        Updater.addToUpdater(event.sourceFile, name, id, version, "Deftu/$name")
    }

    @Mod.EventHandler fun initialize(event: FMLInitializationEvent) {
        fileHandler = FileHandler(Minecraft.getMinecraft().mcDataDir)

        config = CheckerConfig(fileHandler.of("config/Deftu/$name"))
        config.initialize()

        hypixelRequester = HypixelRequester("$id/$version")

        apiKeyChecker = ApiKeyChecker(this)
        apiKeyChecker.checkExisting()

        locrawChecker = LocrawChecker()

        EssentialAPI.getCommandRegistry().registerCommand(CheckerCommand())
    }

    @Mod.EventHandler fun postInitialize(event: FMLPostInitializationEvent) {

    }

    companion object {
        const val name = "@NAME@"
        const val version = "@VERSION@"
        const val repo = "@REPO@"
        const val id = "@ID@"
        val chatPrefix = "${ChatColor.BOLD}${ChatColor.GRAY}[${ChatColor.RESET}${ChatColor.RED}$name${ChatColor.BOLD}${ChatColor.GRAY}] "
        @JvmStatic @Mod.Instance lateinit var instance: HypixelBedwarsChecker

        @JvmStatic fun notify(message: String, action: () -> Unit) = EssentialAPI.getNotifications().push(name, message, 5f, action)
        @JvmStatic fun notify(message: String) = notify(message) {  }

        @JvmStatic fun message(message: String) = EssentialAPI.getMinecraftUtil().sendMessage(chatPrefix, message)
    }
}