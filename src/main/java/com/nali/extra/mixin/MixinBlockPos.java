package com.nali.extra.mixin;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//force chunk render
@Mixin(BlockPos.class)
public abstract class MixinBlockPos extends Vec3i
{
	public MixinBlockPos(int xIn, int yIn, int zIn)
	{
		super(xIn, yIn, zIn);
	}

	@Overwrite
	public BlockPos offset(EnumFacing facing, int n)
	{
		if (facing == null)
		{
			facing = EnumFacing.values()[n];
			return new BlockPos(this.getX() + facing.getXOffset() * n, this.getY() + facing.getYOffset() * n, this.getZ() + facing.getZOffset() * n);
		}
		return n == 0 ? (BlockPos)(Object)this : new BlockPos(this.getX() + facing.getXOffset() * n, this.getY() + facing.getYOffset() * n, this.getZ() + facing.getZOffset() * n);
	}
}
