package xyz.deftu.hbc.gui

import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.UIContainer
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.ChildBasedSizeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import xyz.deftu.hbc.HypixelBedwarsChecker
import xyz.deftu.hbc.api.HypixelEndpoint
import xyz.deftu.hbc.api.responses.HypixelPlayerBedwarsResponse
import java.awt.Color
import java.util.*

class Popup(
    val uuid: UUID
) : UIBlock(
    Color(31, 31, 31)
) {
    init {
        constrain {
            x = CenterConstraint()
            y = 25.percent()
            width = ChildBasedSizeConstraint(2f)
            height = ChildBasedSizeConstraint(2f)
        } effect OutlineEffect(HypixelBedwarsChecker.mainColor, 2f)
        val text = UIText("Loading...").constrain {
            x = CenterConstraint()
            y = CenterConstraint()
        } childOf this
    }

    override fun afterInitialization() {
        val response = HypixelBedwarsChecker.instance.hypixelRequester.create<HypixelPlayerBedwarsResponse>(
            HypixelEndpoint.PLAYER_BEDWARS,
            parameters = arrayOf(HypixelBedwarsChecker.instance.config.apiKey, uuid.toString())
        )
        val stats = response.stats.bedwars
        clearChildren()

        val name = UIText(response.username) childOf this
        val winstreak = UIText(stats.winstreak.toString()).constrain {
            y = SiblingConstraint(2f)
        } childOf this
        val wins = UIText(stats.wins.toString()).constrain {
            y = SiblingConstraint(2f)
        } childOf this
        val kills = UIText(stats.kills.toString()).constrain {
            y = SiblingConstraint(2f)
        } childOf this
        val bedBreaks = UIText(stats.bedBreaks.toString()).constrain {
            y = SiblingConstraint(2f)
        } childOf this
    }
}