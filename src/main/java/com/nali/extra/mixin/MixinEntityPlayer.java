package com.nali.extra.mixin;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//*extra
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends EntityLivingBase
{
//	private byte state;

	@Shadow public abstract boolean isPlayerSleeping();

	public MixinEntityPlayer(World worldIn)
	{
		super(worldIn);
	}

	@Overwrite
	public float getCooledAttackStrength(float adjustTicks)
	{
		return 1.0F;
	}

	//clean sleep
//	@Overwrite
//	public int getSleepTimer()
	@Inject(method = "getSleepTimer", at = @At("RETURN"), cancellable = true)
	private void nali_extra_getSleepTimer(CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

	//clean sleep
//	@Overwrite
//	public boolean isPlayerFullyAsleep()
//	{
//		return false;
//	}

//	@Overwrite
//	public EntityPlayer.SleepResult trySleep(BlockPos bedLocation)
//	{
//		return EntityPlayer.SleepResult.OK;
//	}

	@Redirect(method = "trySleep", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer$SleepResult;NOT_POSSIBLE_NOW:Lnet/minecraft/entity/player/EntityPlayer$SleepResult;"))
	public EntityPlayer.SleepResult trySleep(BlockPos bedLocation)
	{
		return EntityPlayer.SleepResult.OK;
	}

	@Overwrite
	protected boolean isMovementBlocked()
	{
//		this.state |= this.isSneaking() ? 1 : 0;
//		return this.getHealth() <= 0.0F || this.isPlayerSleeping();
		return false;
	}

//	@Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isPlayerSleeping()Z"))
//	private boolean nali_extra_onUpdate(EntityPlayer instance)
//	{
//		return false;
//	}
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
