package com.nali.extra.mixin;

import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove light
@Mixin(BlockStateContainer.StateImplementation.class)
public abstract class MixinStateImplementation
{
	@Overwrite
	public int getPackedLightmapCoords(IBlockAccess source, BlockPos pos)
	{
		return 0;
	}

	@Overwrite
	public float getAmbientOcclusionLightValue()
	{
		return 0;
	}
}
