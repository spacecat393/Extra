package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//gun
@Mixin(ItemBow.class)
public abstract class MixinItemBow
{
	@Shadow
	public static float getArrowVelocity(int charge)
	{
		return 0;
	}

	private static int CHARGE;
	private static EntityArrow ENTITYARROW;

	@Redirect(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemBow;getArrowVelocity(I)F"))
	private float nali_extra_onPlayerStoppedUsing(int charge)
	{
		CHARGE = charge;
		return getArrowVelocity(charge);
	}

	@Redirect(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/EntityArrow;shoot(Lnet/minecraft/entity/Entity;FFFFF)V"))
	private void nali_extra_onPlayerStoppedUsingS(EntityArrow instance, Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
	{
		ENTITYARROW = instance;
		instance.shoot(shooter, pitch, yaw, p_184547_4_, velocity, inaccuracy);
	}

	@Inject(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;damageItem(ILnet/minecraft/entity/EntityLivingBase;)V", shift = At.Shift.AFTER))
	private void nali_extra_onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft, CallbackInfo ci)
	{
		ENTITYARROW.setDamage(ENTITYARROW.getDamage() + CHARGE);
	}
}
