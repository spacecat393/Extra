package com.nali.extra.mixin;

import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//fix tr
@Mixin(value = ItemRenderer.class/*, priority = Integer.MIN_VALUE*/)
public abstract class MixinItemRenderer
{
	@Inject(method = "renderWaterOverlayTexture", at = @At("HEAD"), cancellable = true)
	private void nali_extra_renderWaterOverlayTexture(float partialTicks, CallbackInfo ci)
	{
		ci.cancel();
	}
//	@Overwrite
//	private void renderWaterOverlayTexture(float partialTicks)
//	{
//	}
}
