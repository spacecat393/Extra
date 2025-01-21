package com.nali.extra.mixin;

import net.minecraft.client.gui.GuiBossOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//clean gui
@Mixin(GuiBossOverlay.class)
public abstract class MixinGuiBossOverlay
{
	@Overwrite
	public void renderBossHealth()
	{
	}
}
