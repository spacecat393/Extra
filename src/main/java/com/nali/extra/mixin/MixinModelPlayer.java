package com.nali.extra.mixin;

import com.nali.extra.Extra;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//fp
@Mixin(ModelPlayer.class)
public abstract class MixinModelPlayer
{
	@Inject(method = "render", at = @At(value = "HEAD"))
	public void nali_extra_render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci)
	{
//		ENTITY = entityIn;
		if ((Extra.FP & 1) == 1)
//		if (entityIn == Minecraft.getMinecraft().getRenderViewEntity() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
		{
			GlStateManager.scale(0.5F, 0.5F, 0.5F);
			GlStateManager.translate(0.0F, -0.2F, 0.4F);
			if (entityIn.isSneaking())
			{
				GlStateManager.translate(0.0F, -0.2F, 0.0F);
			}
		}
	}
}
