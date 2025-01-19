package com.nali.extra.mixin;

import net.minecraft.client.renderer.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//fix tr
@Mixin(ItemRenderer.class)
public abstract class MixinItemRenderer
{
	@Overwrite
	private void renderWaterOverlayTexture(float partialTicks)
	{
	}
}
