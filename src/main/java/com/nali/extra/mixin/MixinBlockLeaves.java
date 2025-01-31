package com.nali.extra.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import org.spongepowered.asm.mixin.Mixin;

//cull leaves
@Mixin(BlockLeaves.class)
public abstract class MixinBlockLeaves extends Block
{
	public MixinBlockLeaves(Material materialIn)
	{
		super(materialIn);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

//	@Override
//	public BlockRenderLayer getRenderLayer()
//	{
//		return BlockRenderLayer.SOLID;
//	}

//	@Override
//	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
//	{
////		return blockAccess.getBlockState(pos.offset(side)).getBlock() == Blocks.AIR;
//		return blockAccess.getBlockState(pos.offset(side)).getBlock() == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
//	}
}
