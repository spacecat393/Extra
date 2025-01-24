package com.nali.extra.mixin;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//*extra
@Mixin(EntityPlayer.class)
public class MixinEntityPlayer
{
	@Overwrite
	public float getCooledAttackStrength(float adjustTicks)
	{
		return 1.0F;
	}

	//clean sleep
	@Overwrite
	public int getSleepTimer()
	{
		return 0;
	}

	//sleep forever
	@Overwrite
	public boolean isPlayerFullyAsleep()
	{
		return false;
	}

//	@Overwrite
//	public boolean isPlayerSleeping()
//	{
//		return false;
//	}

//	//remove hitbox
//	@Overwrite
//	private void collideWithPlayer(Entity entityIn)
//	{
//	}
//
//	@Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;getHealth()F", ordinal = 3))
//	private float nali_extra_onLivingUpdate(EntityPlayer instance)
//	{
//		return -1;
//	}
}
