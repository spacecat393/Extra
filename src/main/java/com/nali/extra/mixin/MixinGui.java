package com.nali.extra.mixin;

import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove black
@Mixin(Gui.class)
public abstract class MixinGui
{
	@Overwrite
	public static void drawRect(int left, int top, int right, int bottom, int color)
	{
	}
}
