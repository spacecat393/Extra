package com.nali.extra.mixin;

import net.minecraft.client.multiplayer.PlayerControllerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//use GuiInventory
@Mixin(PlayerControllerMP.class)
public abstract class MixinPlayerControllerMP
{
	@Overwrite
	public boolean isInCreativeMode()
	{
		return false;
	}
}
