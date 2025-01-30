package com.nali.extra.mixin;

import net.minecraft.client.gui.GuiScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove black
@Mixin(GuiScreen.class)
public abstract class MixinGuiScreen
{
	@Overwrite
	public void drawDefaultBackground()
	{

	}

	@Overwrite
	public void drawWorldBackground(int tint)
	{

	}

	@Overwrite
	public void drawBackground(int tint)
	{

	}
}
