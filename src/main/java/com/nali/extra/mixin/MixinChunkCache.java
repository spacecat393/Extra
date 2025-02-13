package com.nali.extra.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//remove light
@Mixin(ChunkCache.class)
public abstract class MixinChunkCache
{
//	@Overwrite
//	public int getCombinedLight(BlockPos pos, int lightValue)
	@Inject(method = "getCombinedLight", at = @At("HEAD"), cancellable = true)
	public void nali_extra_getCombinedLight(BlockPos pos, int lightValue, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

//	@Overwrite
//	private int getLightForExt(EnumSkyBlock type, BlockPos pos)
	@Inject(method = "getLightForExt", at = @At("HEAD"), cancellable = true)
	public void nali_extra_getLightForExt(EnumSkyBlock type, BlockPos pos, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

//	@Overwrite
//	public int getLightFor(EnumSkyBlock type, BlockPos pos)
	@Inject(method = "getLightFor", at = @At("HEAD"), cancellable = true)
	public void nali_extra_getLightFor(EnumSkyBlock type, BlockPos pos, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}
}
