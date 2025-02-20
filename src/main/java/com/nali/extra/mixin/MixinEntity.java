package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove hitbox
@Mixin(Entity.class)
public abstract class MixinEntity
{
	@Overwrite
	public void applyEntityCollision(Entity entityIn)
	{
	}

	//force entity render as translucent
	@Overwrite
	public boolean isInvisibleToPlayer(EntityPlayer player)
	{
		return false;
	}
}
