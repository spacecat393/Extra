package com.nali.extra.mixin;

import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//clean sleep
@Mixin(WorldServer.class)
public abstract class MixinWorldServer
{
	@Overwrite
	public boolean areAllPlayersAsleep()
	{
		return false;
	}
}
