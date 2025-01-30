package com.nali.extra.mixin;

import com.nali.extra.ExtraColor;
import com.nali.extra.ExtraQuadLine;
import com.nali.gui.page.Page;
import com.nali.small.Small;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//clean gui
@Mixin(GuiIngame.class)
public abstract class MixinGuiIngame extends Gui
{
//	@Redirect(method = "renderPlayerStats", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiIngame;drawTexturedModalRect(IIIIII)V"))
//	private void nali_extra_renderPlayerStats(GuiIngame instance, int i0, int i1, int i2, int i3, int i4, int i5)
//	{
	@Shadow @Final protected Minecraft mc;

	@Shadow @Final protected static ResourceLocation WIDGETS_TEX_PATH;

	@Shadow protected abstract void renderHotbarItem(int x, int y, float partialTicks, EntityPlayer player, ItemStack stack);

	////		instance.drawTexturedModalRect(i0 + 500, i1 + 500, i2, i3, i4, i5);
//	}

	@Overwrite
	protected void renderHotbar(ScaledResolution sr, float partialTicks)
	{
		byte bit = (byte)(Small.FLAG & 1+2);
		if ((bit == 3 || bit == 0) && Page.PAGE == null && this.mc.getRenderViewEntity() instanceof EntityPlayer)
		{
			Small.FLAG &= 255-2;
			EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
			ItemStack itemstack = entityplayer.getHeldItemOffhand();
			int i = sr.getScaledWidth() / 2;
			this.mc.getTextureManager().bindTexture(WIDGETS_TEX_PATH);
//			this.drawTexturedModalRect(i - 91 - 1 + entityplayer.inventory.currentItem * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
//			this.drawTexturedModalRect(i - 91 - 1 + entityplayer.inventory.currentItem * 20, 0, 0, 22, 24, 22);

			GlStateManager.disableTexture2D();


//			float x = i - 91 - 29;
//			if (!itemstack.isEmpty())
//			{
//				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.25F);
//				ExtraQuad.update(x, 1, x + 29, 22 - 1);
//				ExtraQuad.draw();
//			}
//
//			GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.25F);
//			x = i - 91;
//			ExtraQuad.update(x, 1, x + 9 * 20 + 2, 22 - 1);
//			ExtraQuad.draw();

			GlStateManager.color(ExtraColor.RED, ExtraColor.GREEN, ExtraColor.BLUE, 1.0F);

			float x = i - 91 - 1 + entityplayer.inventory.currentItem * 20;
			ExtraQuadLine.update(x, 1, x + 24, 22 + 1);
			ExtraQuadLine.draw();
			GlStateManager.enableTexture2D();

			GlStateManager.enableRescaleNormal();
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderHelper.enableGUIStandardItemLighting();

			//
 			//force blend
//			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
////			GL14.glBlendEquation(GL14.GL_FUNC_SUBTRACT);
////			GL14.glBlendFuncSeparate(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR, GL11.GL_ONE, GL11.GL_ZERO);
//
////			GL14.glBlendFuncSeparate(GL11.GL_ONE_MINUS_SRC_COLOR, GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE, GL11.GL_ZERO);
//			GL14.glBlendFuncSeparate(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_CONSTANT_COLOR, GL11.GL_ONE, GL11.GL_ZERO);
////			GlStateManager.enableAlpha();
//			//
//			if (!itemstack.isEmpty())
//			{
////				int l1 = sr.getScaledHeight() - 16 - 3;
////				this.renderHotbarItem(i - 91 - 26, l1, partialTicks, entityplayer, itemstack);
//				this.renderHotbarItem(i - 91 - 26, 3 + 1, partialTicks, entityplayer, itemstack);
//			}
//
//			for (int l = 0; l < 9; ++l)
//			{
//				int i1 = i - 90 + l * 20 + 2;
////				int j1 = sr.getScaledHeight() - 16 - 3;
////				this.renderHotbarItem(i1, j1, partialTicks, entityplayer, entityplayer.inventory.mainInventory.get(l));
//				this.renderHotbarItem(i1, 3 + 1, partialTicks, entityplayer, entityplayer.inventory.mainInventory.get(l));
//			}

//			GL14.glBlendFuncSeparate(GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ONE);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, 0, -100);
			if (!itemstack.isEmpty())
			{
//				int l1 = sr.getScaledHeight() - 16 - 3;
//				this.renderHotbarItem(i - 91 - 26, l1, partialTicks, entityplayer, itemstack);
//				this.renderHotbarItem(i - 91 - 26, 3 + 1, partialTicks, entityplayer, itemstack);
				this.renderHotbarItem(i - 90 + 4 * 20 + 2, 3 + 1 + 26, partialTicks, entityplayer, itemstack);
			}

			for (int l = 0; l < 9; ++l)
			{
				int i1 = i - 90 + l * 20 + 2;
//				int j1 = sr.getScaledHeight() - 16 - 3;
//				this.renderHotbarItem(i1, j1, partialTicks, entityplayer, entityplayer.inventory.mainInventory.get(l));
				this.renderHotbarItem(i1, 3 + 1, partialTicks, entityplayer, entityplayer.inventory.mainInventory.get(l));
			}
			GL11.glPopMatrix();
//			GL14.glBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
//			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//			GL14.glBlendEquation(GL14.GL_FUNC_ADD);

			RenderHelper.disableStandardItemLighting();
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableBlend();
		}
	}

//	@Redirect(method = "renderHotbarItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItemAndEffectIntoGUI(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;II)V"))
//	protected void nali_extra_renderHotbarItem0(RenderItem itemRenderer, EntityLivingBase player, ItemStack stack, int x, int y)
//	{
//		itemRenderer.renderItemAndEffectIntoGUI(player, stack, x, y);
//	}
//
//	@Redirect(method = "renderHotbarItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderItemOverlays(Lnet/minecraft/client/gui/FontRenderer;Lnet/minecraft/item/ItemStack;II)V"))
//	protected void nali_extra_renderHotbarItem1(RenderItem itemRenderer, FontRenderer fontRenderer, ItemStack stack, int x, int y)
//	{
//		itemRenderer.renderItemOverlays(fontRenderer, stack, x, y);
//	}

	@Overwrite
	public void renderExpBar(ScaledResolution scaledRes, int x)
	{
	}

	@Overwrite
	public void renderHorseJumpBar(ScaledResolution scaledRes, int x)
	{
	}

	@Overwrite
	protected void renderPlayerStats(ScaledResolution scaledRes)
	{
	}

	@Overwrite
	public void renderSelectedItem(ScaledResolution scaledRes)
	{
	}

	@Overwrite
	protected void renderAttackIndicator(float partialTicks, ScaledResolution p_184045_2_)
	{
	}

	@Overwrite
	protected void renderVignette(float lightLevel, ScaledResolution scaledRes)
	{
	}

	@Overwrite
	protected void renderMountHealth(ScaledResolution p_184047_1_)
	{
	}

	@Overwrite
	protected void renderPotionEffects(ScaledResolution resolution)
	{
	}

	@Overwrite
	protected void renderPumpkinOverlay(ScaledResolution scaledRes)
	{
	}
}
