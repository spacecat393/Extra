package com.nali.extra.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemEgg;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//gun
@Mixin(ItemEgg.class)
public abstract class MixinItemEgg extends Item
{
	@Inject(method = "<init>", at = @At("TAIL"))
	public void nali_extra_init(CallbackInfo ci)
	{
		this.maxStackSize = 64;
	}
}
