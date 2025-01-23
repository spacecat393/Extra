package com.nali.extra.mixin;

import com.nali.extra.Extra;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//fp
@Mixin(ModelBiped.class)
public abstract class MixinModelBiped extends ModelBase
{
//	private static Entity ENTITY;
//
//	@Inject(method = "render", at = @At(value = "HEAD"))
//	public void nali_extra_render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci)
//	{
//		ENTITY = entityIn;
////		this.isChild = true;
//	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V", ordinal = 7))
	public void nali_extra_render7(ModelRenderer instance, float j)
	{
//		if (ENTITY != Minecraft.getMinecraft().getRenderViewEntity() || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
		if ((Extra.FP & 1) == 0)
		{
			instance.render(j);
		}
//		else
//		{
//			GlStateManager.scale(0.5F, 0.5F, 0.5F);
//			GlStateManager.translate(0.0F, 0.0F, 1.0F);
//		}
	}

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V", ordinal = 13))
	public void nali_extra_render13(ModelRenderer instance, float j)
	{
		if ((Extra.FP & 1) == 0)
		{
			instance.render(j);
		}
	}
}
