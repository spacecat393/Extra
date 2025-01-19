package com.nali.extra.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButtonLanguage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//clean mainmenu
@Mixin(GuiButtonLanguage.class)
public abstract class MixinGuiButtonLanguage
{
	@Overwrite
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks)
	{
	}
}
