package xyz.deftu.hbc.gui

import gg.essential.elementa.components.Window
import gg.essential.universal.UMatrixStack
import gg.essential.universal.UResolution
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.input.Mouse

class WindowManager {
    lateinit var window: Window private set

    init {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent fun onGameOverlayRendered(event: RenderGameOverlayEvent.Post) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {
            window.draw(UMatrixStack.Compat.get())
        }
    }

    @SubscribeEvent fun onMouseInput(event: GuiScreenEvent.MouseInputEvent.Post) {
        val mouseX = Mouse.getEventX() * event.gui.width / UResolution.windowWidth
        val mouseY = event.gui.height - Mouse.getEventY() * event.gui.height / UResolution.windowHeight - 1
        val button = Mouse.getEventButton()
        if (Mouse.getEventButtonState()) window.mouseClick(mouseX.toDouble(), mouseY.toDouble(), button)
        else if (button != -1) window.mouseRelease()
    }
}