package com.nali.extra.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSnowball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//gun
@Mixin(ItemSnowball.class)
public abstract class MixinItemSnowball extends Item
{
	@Inject(method = "<init>", at = @At("TAIL"))
	public void nali_extra_init(CallbackInfo ci)
	{
		this.maxStackSize = 64;
	}
}
