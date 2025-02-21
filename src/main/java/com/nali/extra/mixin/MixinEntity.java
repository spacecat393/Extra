package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//remove hitbox
@Mixin(Entity.class)
public abstract class MixinEntity
{
	@Overwrite
	public void applyEntityCollision(Entity entityIn)
	{
	}

	//force entity render as translucent
	@Inject(method = "isInvisibleToPlayer", at = @At("HEAD"), cancellable = true)
	private void isInvisibleToPlayer(EntityPlayer player, CallbackInfoReturnable<Boolean> cir)
	{
		cir.setReturnValue(false);
	}
}
