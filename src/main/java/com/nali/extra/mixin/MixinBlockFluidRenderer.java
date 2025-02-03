package com.nali.extra.mixin;

import com.nali.extra.ExtraView;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BlockFluidRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//force chunk render
@Mixin(BlockFluidRenderer.class)
public abstract class MixinBlockFluidRenderer
{
//	@Overwrite
//	public boolean renderFluid(IBlockAccess blockAccess, IBlockState blockStateIn, BlockPos blockPosIn, BufferBuilder bufferBuilderIn)
//	{
//		return false;
//	}

	@Redirect(method = "renderFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/state/IBlockState;shouldSideBeRendered(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumFacing;)Z"))
	private boolean nali_extra_renderFluid(IBlockState instance, IBlockAccess iBlockAccess, BlockPos blockPos, EnumFacing enumFacing)
	{
		return ExtraView.check(instance.getBlock(), blockPos, iBlockAccess, enumFacing) && instance.shouldSideBeRendered(iBlockAccess, blockPos, enumFacing);
	}

	@Redirect(method = "renderFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockLiquid;shouldRenderSides(Lnet/minecraft/world/IBlockAccess;Lnet/minecraft/util/math/BlockPos;)Z"))
	private boolean nali_extra_renderFluid(BlockLiquid instance, IBlockAccess j, BlockPos i)
	{
		return false;
//		return ExtraView.check(j.getBlock(), i, iBlockAccess, enumFacing) && instance.shouldRenderSides(j, i);
	}
}
