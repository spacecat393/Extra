package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//gun
@Mixin(EntityEgg.class)
public abstract class MixinEntityEgg
{
	@Redirect(method = "onImpact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;attackEntityFrom(Lnet/minecraft/util/DamageSource;F)Z"))
	private boolean nali_extra_onImpact(Entity instance, DamageSource source, float amount)
	{
		return instance.attackEntityFrom(source.setDamageBypassesArmor(), 10.0F);
	}
}
