package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(CreativeTabs.class)
public abstract class MixinCreativeTabs
{
	@Mutable
	@Shadow @Final public static CreativeTabs BUILDING_BLOCKS;

	@Mutable
	@Shadow @Final public static CreativeTabs DECORATIONS;

	@Mutable
	@Shadow @Final public static CreativeTabs REDSTONE;

	@Mutable
	@Shadow @Final public static CreativeTabs TRANSPORTATION;

	@Mutable
	@Shadow @Final public static CreativeTabs MISC;

	@Mutable
	@Shadow @Final public static CreativeTabs SEARCH;

	@Mutable
	@Shadow @Final public static CreativeTabs FOOD;

	@Mutable
	@Shadow @Final public static CreativeTabs TOOLS;

	@Mutable
	@Shadow @Final public static CreativeTabs COMBAT;

	@Mutable
	@Shadow @Final public static CreativeTabs BREWING;

	@Mutable
	@Shadow @Final public static CreativeTabs MATERIALS;

	@Mutable
	@Shadow @Final public static CreativeTabs HOTBAR;

	@Mutable
	@Shadow @Final public static CreativeTabs INVENTORY;

//	@Shadow public static CreativeTabs[] CREATIVE_TAB_ARRAY;

	static
	{
//		CREATIVE_TAB_ARRAY = null;
		BUILDING_BLOCKS = null;
		DECORATIONS = null;
		REDSTONE = null;
		TRANSPORTATION = null;
		MISC = null;
		SEARCH = null;
		FOOD = null;
		TOOLS = null;
		COMBAT = null;
		BREWING = null;
		MATERIALS = null;
		HOTBAR = null;
		INVENTORY = null;
	}
}
