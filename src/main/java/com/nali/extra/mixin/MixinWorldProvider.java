package com.nali.extra.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//clean sleep
@Mixin(WorldProvider.class)
public abstract class MixinWorldProvider
{
	@Overwrite(remap = false)
	public WorldProvider.WorldSleepResult canSleepAt(net.minecraft.entity.player.EntityPlayer player, BlockPos pos)
	{
		return WorldProvider.WorldSleepResult.ALLOW;
	}
}
