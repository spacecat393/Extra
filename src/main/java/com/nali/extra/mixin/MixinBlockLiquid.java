//package com.nali.extra.mixin;
//
//import net.minecraft.block.Block;
//import net.minecraft.block.BlockLiquid;
//import net.minecraft.block.material.MapColor;
//import net.minecraft.block.material.Material;
//import org.spongepowered.asm.mixin.Mixin;
//
//@Mixin(BlockLiquid.class)
//public abstract class MixinBlockLiquid extends Block
//{
//	public MixinBlockLiquid(Material blockMaterialIn, MapColor blockMapColorIn)
//	{
//		super(blockMaterialIn, blockMapColorIn);
//	}
//
////	@Overwrite
////	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
////	{
////		if (blockAccess.getBlockState(pos.offset(side)).getMaterial() == this.material)
////		{
////			return false;
////		}
////		else
////		{
////			return /*side == EnumFacing.UP ? true : */super.shouldSideBeRendered(blockState, blockAccess, pos, side);
////		}
////	}
//}
