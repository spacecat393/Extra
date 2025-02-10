package com.nali.extra.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.EnumSkyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove light
@Mixin(ChunkCache.class)
public abstract class MixinChunkCache
{
	@Overwrite
	public int getCombinedLight(BlockPos pos, int lightValue)
	{
		return 0;
	}

	@Overwrite
	private int getLightForExt(EnumSkyBlock type, BlockPos pos)
	{
		return 0;
	}

	@Overwrite
	public int getLightFor(EnumSkyBlock type, BlockPos pos)
	{
		return 0;
	}
}
