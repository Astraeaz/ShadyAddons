package cheaters.get.banned.mixins;

import cheaters.get.banned.events.ClickEvent;
import cheaters.get.banned.events.ResourcePackRefreshEvent;
import cheaters.get.banned.events.ShutdownEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {

    @Mutable @Shadow @Final
    private static ResourceLocation locationMojangPng;

    @Inject(method = "shutdown", at = @At("HEAD"))
    public void onShutdown(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new ShutdownEvent());
    }

    @Inject(method = "drawSplashScreen", at = @At("HEAD"))
    public void modifyMojangLogo(TextureManager textureManagerInstance, CallbackInfo ci) {
        locationMojangPng = new ResourceLocation("shadyaddons:splash.png");
    }

    @Inject(method = "refreshResources", at = @At("HEAD"), cancellable = true)
    public void refreshResourcesPre(CallbackInfo ci) {
        if(MinecraftForge.EVENT_BUS.post(new ResourcePackRefreshEvent.Pre())) ci.cancel();
    }

    @Inject(method = "refreshResources", at = @At("RETURN"))
    public void refreshResourcesPost(CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new ResourcePackRefreshEvent.Post());
    }

    @Inject(method = "rightClickMouse", at = @At("HEAD"), cancellable = true)
    public void rightClickEvent(CallbackInfo ci) {
        if(MinecraftForge.EVENT_BUS.post(new ClickEvent.Right())) ci.cancel();
    }

    @Inject(method = "clickMouse", at = @At("HEAD"), cancellable = true)
    public void leftClickEvent(CallbackInfo ci) {
        if(MinecraftForge.EVENT_BUS.post(new ClickEvent.Left())) ci.cancel();
    }

    @Inject(method = "middleClickMouse", at = @At("HEAD"), cancellable = true)
    public void middleClickEvent(CallbackInfo ci) {
        if(MinecraftForge.EVENT_BUS.post(new ClickEvent.Middle())) ci.cancel();
    }

}
