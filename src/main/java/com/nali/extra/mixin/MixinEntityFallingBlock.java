package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityFallingBlock.class)
public abstract class MixinEntityFallingBlock extends Entity
{
	@Shadow public int fallTime;
	private boolean extra;

	public MixinEntityFallingBlock(World worldIn)
	{
		super(worldIn);
	}

	@Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
	private void nali_extra_onUpdate(CallbackInfo ci)
	{
//		if (this.world.isRemote)
//		{
//			if (this.onGround)
//			{
//				this.setDead();
//				ci.cancel();
//			}
//		}
		if (!this.world.isRemote)
		{
			if (this.fallTime == Integer.MIN_VALUE)
			{
				this.extra = true;
			}

			if (this.extra && this.onGround || this.posY < 0 || this.posY > 256)
			{
				this.setDead();
				ci.cancel();
			}
		}
	}

	@Redirect(method = "onUpdate", at = @At(value = "FIELD", target = "Lnet/minecraft/world/World;isRemote:Z", ordinal = 1))
	private boolean nali_extra_onUpdate(World instance)
	{
		return instance.isRemote || this.extra;
	}

//	@Inject(method = "readEntityFromNBT", at = @At("TAIL"))
	@Inject(method = "readEntityFromNBT", at = @At("HEAD"))
	private void nali_extra_readEntityFromNBT(NBTTagCompound compound, CallbackInfo ci)
	{
//		if (this.fallTime == Integer.MIN_VALUE)
//		{
//			this.extra = true;
//		}
		this.extra = compound.getBoolean("extra");
	}

	@Inject(method = "writeEntityToNBT", at = @At("HEAD"))
	private void nali_extra_writeEntityToNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		compound.setBoolean("extra", this.extra);
	}
}
