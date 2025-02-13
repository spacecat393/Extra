package com.nali.extra.mixin;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//remove light
@Mixin(BlockStateContainer.StateImplementation.class)
public abstract class MixinStateImplementation
{
//	@Overwrite
//	public int getPackedLightmapCoords(IBlockAccess source, BlockPos pos)
	@Inject(method = "getPackedLightmapCoords", at = @At("HEAD"), cancellable = true)
	private void nali_extra_getPackedLightmapCoords(IBlockAccess source, BlockPos pos, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

//	@Overwrite
//	public float getAmbientOcclusionLightValue()
	@Inject(method = "getAmbientOcclusionLightValue", at = @At("HEAD"), cancellable = true)
	private void nali_extra_getAmbientOcclusionLightValue(CallbackInfoReturnable<Float> cir)
	{
//		return 0;
		cir.setReturnValue(0.0F);
	}
}
