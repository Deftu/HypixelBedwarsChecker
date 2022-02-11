package xyz.deftu.hbc

import gg.essential.universal.ChatColor
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import java.io.File

class CheckerConfig(
    directory: File
) : Vigilant(
    File(directory, "${HypixelBedwarsChecker.id}.toml"),
    "${ChatColor.DARK_RED}Hypixel Bedwars Checker"
) {
    @Property(
        type = PropertyType.TEXT,
        name = "API Key",
        category = "General",
        subcategory = "API",
        protectedText = true
    ) val apiKey = ""
}