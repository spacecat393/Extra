package com.nali.extra.mixin;

import net.minecraft.client.renderer.block.model.BakedQuad;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove light
@Mixin(BakedQuad.class)
public abstract class MixinBakedQuad
{
	@Overwrite(remap = false)
	public boolean shouldApplyDiffuseLighting()
	{
		return false;
	}
}
