package com.nali.extra.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

//clean mainmenu
@Mixin(GuiMainMenu.class)
public abstract class MixinGuiMainMenu extends GuiScreen
{
	@Shadow @Final private static ResourceLocation MINECRAFT_TITLE_TEXTURES;

	@Inject(method = "initGui", at = @At("TAIL"))
	public void initGui(CallbackInfo ci)
	{
		int y = 0;
		for (GuiButton guibutton : this.buttonList)
		{
			guibutton.x = this.width - 200;
			guibutton.y = y;
			guibutton.width = 200;
			guibutton.height = 20;
			y += 20;
		}
	}

	@Overwrite
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
		List<String> brandings = Lists.reverse(FMLCommonHandler.instance().getBrandings(true));
		for (int brdline = 0; brdline < brandings.size(); brdline++)
		{
			String brd = brandings.get(brdline);
			if (!com.google.common.base.Strings.isNullOrEmpty(brd))
			{
				this.drawString(this.fontRenderer, brd, 2, this.height - (10 + brdline * (this.fontRenderer.FONT_HEIGHT + 1)), 0xFFFFFFFF);
			}
		}

		super.drawScreen(mouseX, mouseY, partialTicks);
	}

//	@Overwrite
//	private void renderSkybox(int mouseX, int mouseY, float partialTicks)
//	{
//	}
//
//	@Overwrite
//	private void rotateAndBlurSkybox()
//	{
//	}
//
//	@Overwrite
//	private void drawPanorama(int mouseX, int mouseY, float partialTicks)
//	{
//	}
}
