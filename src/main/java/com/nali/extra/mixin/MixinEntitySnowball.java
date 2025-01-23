package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//gun
@Mixin(EntitySnowball.class)
public abstract class MixinEntitySnowball
{
	@Redirect(method = "onImpact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"))
	private boolean nali_extra_onImpact(Entity instance, DamageSource source, float amount)
	{
		return instance.attackEntityFrom(source.setDamageBypassesArmor(), amount == 0 ? 1.0F : amount);
	}
}
