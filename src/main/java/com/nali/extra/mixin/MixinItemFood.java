package com.nali.extra.mixin;

import net.minecraft.item.ItemFood;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//AlwaysEdible
@Mixin(ItemFood.class)
public abstract class MixinItemFood
{
	@Shadow private boolean alwaysEdible;

	@Inject(method = "<init>(IFZ)V", at = @At(value = "TAIL"))
	private void nali_extra_init(int amount, float saturation, boolean isWolfFood, CallbackInfo ci)
	{
		this.alwaysEdible = true;
	}

	@Overwrite
	public ItemFood setAlwaysEdible()
	{
		return (ItemFood)(Object)this;
	}
}
