//package com.nali.extra.mixin;
//
//import net.minecraft.client.renderer.block.model.BakedQuad;
//import org.spongepowered.asm.mixin.Mixin;
//
////remove light
//@Mixin(value = BakedQuad.class/*, priority = Integer.MAX_VALUE*/)
//public abstract class MixinBakedQuad
//{
////	@Inject(method = "shouldApplyDiffuseLighting", at = @At("HEAD"), remap = false, cancellable = true)
////	private void nali_extra_shouldApplyDiffuseLighting(CallbackInfoReturnable<Boolean> cir)
////	{
////		cir.setReturnValue(false);
////		cir.cancel();
////	}
//////	@Overwrite(remap = false)
//////	public boolean shouldApplyDiffuseLighting()
//////	{
//////		return false;
//////	}
//}
