package io.github.brandonkys.fakefps.mixin;

import io.github.brandonkys.fakefps.client.Client;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.RunArgs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Shadow
    private static int currentFps;

    @Inject(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;currentFps:I", ordinal = 0, shift = At.Shift.AFTER))
    public void setFps(boolean tick, CallbackInfo ci) {
        currentFps = ThreadLocalRandom.current().nextInt(Client.getInstance().getConfig().get(Integer.class, "min", 230000), Client.getInstance().getConfig().get(Integer.class, "max", 250000));

    }

    @Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;instance:Lnet/minecraft/client/MinecraftClient;", shift = At.Shift.AFTER))
    public void init(RunArgs args, CallbackInfo ci) {
        Client.getInstance().init();
    }
}
