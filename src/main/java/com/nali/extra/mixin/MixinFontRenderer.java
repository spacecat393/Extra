package com.nali.extra.mixin;

import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

//remove black
@Mixin(FontRenderer.class)
public abstract class MixinFontRenderer
{
	@Shadow public abstract int drawString(String text, float x, float y, int color, boolean dropShadow);

	@Overwrite
	public int drawString(String text, int x, int y, int color)
	{
		return this.drawString(text, (float)x, (float)y, color, true);
	}

	@ModifyVariable(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At("HEAD"), ordinal = 0)
	private int nali_extra_drawStringH(int color)
	{
		return color | 0x00C8C8C8;//200
//		if ((color & 0x00FFFFFF) == 0xFFFFFF)
//		{
//		return ExtraColor.GBW_COLOR;
//		}
//		return color;
	}

//	@Inject(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "HEAD"))
//	private void nali_extra_drawStringH(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> cir)
//	{
//		GlStateManager.disableBlend();
////		GlStateManager.enableBlend();
//////		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
////		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//	}

//	@Inject(method = "drawString(Ljava/lang/String;FFIZ)I", at = @At(value = "TAIL"))
//	private void nali_extra_drawStringT(String text, float x, float y, int color, boolean dropShadow, CallbackInfoReturnable<Integer> cir)
//	{
//		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//	}
}
