package xyz.deftu.hbc.gui

import gg.essential.elementa.components.Window
import gg.essential.elementa.dsl.*
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import java.util.*
import java.util.concurrent.LinkedBlockingDeque

class PopupManager(
    private val window: Window
) {
    private val queue = LinkedBlockingDeque<UUID>()

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    fun queue(uuid: UUID) = queue.add(uuid)

    @SubscribeEvent fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (event.phase == TickEvent.Phase.END) {
            val current = queue.poll() ?: return
            Popup(current) childOf window
        }
    }
}