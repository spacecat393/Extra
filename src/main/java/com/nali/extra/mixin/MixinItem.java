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

//	@Overwrite
//	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
//	{
//		items.add(new ItemStack((Item)(Object)this));
////		if ((STATE & 1) == 0)
////		{
//////			for (IRecipe irecipe : ForgeRegistries.RECIPES)
//////			{
//////				items.add(irecipe.getRecipeOutput());
//////			}
//////
//////			for (IRecipe irecipe : CraftingManager.REGISTRY)
//////			{
//////				items.add(irecipe.getRecipeOutput());
//////			}
////
////			Map<ItemStack, ItemStack> map = FurnaceRecipes.instance().getSmeltingList();
////			for (ItemStack input_itemstack : map.keySet())
////			{
////				ItemStack output_itemstack = FurnaceRecipes.instance().getSmeltingList().get(input_itemstack);
////				items.add(input_itemstack);
////				items.add(output_itemstack);
////			}
////
////			STATE |= 1;
////		}
//	}

	@Overwrite
	protected boolean isInCreativeTab(CreativeTabs targetTab)
	{
		return true;
	}

	@Overwrite
	public CreativeTabs getCreativeTab()
	{
		return null;
	}
}
