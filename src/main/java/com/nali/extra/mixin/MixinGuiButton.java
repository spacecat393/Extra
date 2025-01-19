package com.nali.extra.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//clean mainmenu
@Mixin(GuiButton.class)
public abstract class MixinGuiButton extends Gui
{
	@Shadow public boolean visible;

	@Shadow protected boolean hovered;

	@Shadow public int width;

	@Shadow public int x;

	@Shadow public int y;

	@Shadow public int height;

	@Shadow protected abstract void mouseDragged(Minecraft mc, int mouseX, int mouseY);

	@Shadow public int packedFGColour;

	@Shadow public String displayString;

	@Shadow public boolean enabled;

//	@Overwrite
	@Inject(method = "drawButton", at = @At("HEAD"), cancellable = true)
	private void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks, CallbackInfo ci)
	{
		if (this.visible)
		{
//			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;

			if (packedFGColour != 0)
			{
				j = packedFGColour;
			}
			else
			if (!this.enabled)
			{
				j = 10526880;
			}
			else if (this.hovered)
			{
				j = 16777120;
			}

			this.drawCenteredString(mc.fontRenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
		}
		ci.cancel();
	}
}
