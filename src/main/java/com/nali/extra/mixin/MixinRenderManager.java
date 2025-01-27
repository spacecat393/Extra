package com.nali.extra.mixin;

import com.nali.extra.ExtraColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//apply glow
@Mixin(RenderManager.class)
public abstract class MixinRenderManager
{
	@Inject(method = "renderEntity", at = @At("HEAD"))
	private void nali_extra_renderEntityH(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo ci)
	{
		if (entityIn.isGlowing())
		{
			GlStateManager.color(1, 1, 1, 1);
			GL11.glColor4f(ExtraColor.RED, ExtraColor.GREEN, ExtraColor.BLUE, 1);
		}
	}

	@Inject(method = "renderEntity", at = @At("TAIL"))
	private void nali_extra_renderEntityT(Entity entityIn, double x, double y, double z, float yaw, float partialTicks, boolean p_188391_10_, CallbackInfo ci)
	{
		if (entityIn.isGlowing())
		{
			GL11.glColor4f(1, 1, 1, 1);
			GlStateManager.color(1, 1, 1, 1);
		}
	}
}
