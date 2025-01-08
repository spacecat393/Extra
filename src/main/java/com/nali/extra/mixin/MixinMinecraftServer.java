package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//remove CreativeTabs
@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;applyServerIconToResponse(Lnet/minecraft/network/ServerStatusResponse;)V", shift = At.Shift.AFTER))
	private void nali_small_extra_run(CallbackInfo ci)
	{
		CreativeTabs.CREATIVE_TAB_ARRAY = null;
	}
}
