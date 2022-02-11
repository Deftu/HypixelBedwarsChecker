package xyz.deftu.hbc

import net.minecraftforge.common.MinecraftForge

class LocrawChecker {
    init {
        MinecraftForge.EVENT_BUS.register(this)
    }
}