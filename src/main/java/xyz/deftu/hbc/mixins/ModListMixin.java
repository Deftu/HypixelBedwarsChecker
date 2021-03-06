package xyz.deftu.hbc.mixins;

import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.network.handshake.FMLHandshakeMessage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.deftu.hbc.HypixelBedwarsChecker;

import java.util.List;
import java.util.Map;

/**
 * We remove HypixelBedwarsChecker from the packet
 * sent to servers containing the players' current mod
 * list. It's likely that Hypixel would find this mod
 * and ban it because it's technically an unfair
 * advantage over other players despite it using
 * their API.
 */
@Mixin({FMLHandshakeMessage.ModList.class})
public class ModListMixin extends FMLHandshakeMessage {
    @Shadow(remap = false) private Map<String, String> modTags;

    @Inject(method = "<init>(Ljava/util/List;)V", at = @At("RETURN"), remap = false)
    private void onCreated(List<ModContainer> modList, CallbackInfo ci) {
        modTags.keySet().removeIf(key -> key.equalsIgnoreCase(HypixelBedwarsChecker.id));
    }
}