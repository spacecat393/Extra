package com.nali.extra.mixin;

import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//clear armor
@Mixin(LayerArmorBase.class)
public abstract class MixinLayerArmorBase
{
	@Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
	private void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci)
	{
		if (entitylivingbaseIn instanceof EntityPlayer)
		{
//			if ((Small.FLAG & 1) == 0)
//			{
			ci.cancel();
//			}
		}
	}

	//alpha armor
//	@Inject(method = "renderArmorLayer", at = @At("HEAD"))
//	private void nali_extra_renderArmorLayerH(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
//	{
////		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
//	}
//
//	@Inject(method = "renderArmorLayer", at = @At("TAIL"))
//	private void nali_extra_renderArmorLayerT(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
//	{
////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
////		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//	}
}
