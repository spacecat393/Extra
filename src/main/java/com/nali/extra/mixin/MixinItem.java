package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
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

	@Overwrite
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
	}

	@Overwrite
	protected boolean isInCreativeTab(CreativeTabs targetTab)
	{
		return false;
	}

	@Overwrite
	public CreativeTabs getCreativeTab()
	{
		return null;
	}
}
