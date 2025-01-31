package com.nali.extra.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//cull leaves
@Mixin(Block.class)
public abstract class MixinBlock
{
	@Inject(method = "shouldSideBeRendered", at = @At("HEAD"), cancellable = true)
	private void shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> cir)
	{
		if (blockAccess.getBlockState(pos.offset(side)).getBlock() == (Object)this)
		{
			cir.setReturnValue(false);
		}
	}
}
