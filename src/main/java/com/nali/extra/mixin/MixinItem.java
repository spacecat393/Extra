package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

//remove CreativeTabs
@Mixin(Item.class)
public abstract class MixinItem
{
//	@Shadow private CreativeTabs tabToDisplayOn;
	@Overwrite
	public Item setCreativeTab(CreativeTabs tab)
	{
//		Nali.warn(tab + " " + this.tabToDisplayOn);
		return (Item)(Object)this;
	}
}
