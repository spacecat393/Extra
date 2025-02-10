package com.nali.extra.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove light
@Mixin(World.class)
public abstract class MixinWorld
{
	@Overwrite
	public int getCombinedLight(BlockPos pos, int lightValue)
	{
		return 0;
	}

	@Overwrite
	public int getLightFromNeighborsFor(EnumSkyBlock type, BlockPos pos)
	{
		return 0;
	}

//	@Overwrite
//	public int getLightFor(EnumSkyBlock type, BlockPos pos)
//	{
//		return 0;
//	}
}
