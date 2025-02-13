package com.nali.extra.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//remove light
@Mixin(World.class)
public abstract class MixinWorld
{
//	@Overwrite
//	public int getCombinedLight(BlockPos pos, int lightValue)
	@Inject(method = "getCombinedLight", at = @At("HEAD"), cancellable = true)
	private void nali_extra_getCombinedLight(BlockPos pos, int lightValue, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

//	@Overwrite
//	public int getLightFromNeighborsFor(EnumSkyBlock type, BlockPos pos)
	@Inject(method = "getLightFromNeighborsFor", at = @At("HEAD"), cancellable = true)
	private void nali_extra_getLightFromNeighborsFor(EnumSkyBlock type, BlockPos pos, CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

//	@Overwrite
//	public int getLightFor(EnumSkyBlock type, BlockPos pos)
//	{
//		return 0;
//	}

	//disable weather
	@Overwrite
	protected void calculateInitialWeather()
	{
	}

	@Overwrite
	protected void updateWeather()
	{
	}
}
