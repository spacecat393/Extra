package com.nali.extra.mixin;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityWolf.class)
public abstract class MixinEntityWolf extends EntityTameable
{
	public MixinEntityWolf(World worldIn)
	{
		super(worldIn);
	}

//	@Overwrite
//	public boolean isWolfWet()
	@Inject(method = "isWolfWet", at = @At("HEAD"), cancellable = true)
	public void nali_extra_isWolfWet(CallbackInfoReturnable<Boolean> cir)
	{
//		return false;
		cir.setReturnValue(false);
	}

	@Overwrite
	public boolean isAngry()
	{
		if (!this.world.isRemote)
		{
			return false;
		}

		return (this.dataManager.get(TAMED).byteValue() & 2) != 0;
	}
}
