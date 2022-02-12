package xyz.deftu.hbc

import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler

class CheckerCommand : Command(
    "checker",
    false,
    false
) {
    @DefaultHandler fun handle() {
        EssentialAPI.getGuiUtil().openScreen(HypixelBedwarsChecker.instance.config.gui())
    }
}