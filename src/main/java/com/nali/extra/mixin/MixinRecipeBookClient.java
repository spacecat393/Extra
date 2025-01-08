package com.nali.extra.mixin;

import net.minecraft.client.gui.recipebook.RecipeList;
import net.minecraft.client.util.RecipeBookClient;
import net.minecraft.creativetab.CreativeTabs;
import org.spongepowered.asm.mixin.*;

import java.util.List;
import java.util.Map;

//remove CreativeTabs
@Mixin(RecipeBookClient.class)
public abstract class MixinRecipeBookClient
{
	@Mutable
	@Shadow @Final public static Map<CreativeTabs, List<RecipeList>> RECIPES_BY_TAB;

	@Mutable
	@Shadow @Final public static List<RecipeList> ALL_RECIPES;

	@Overwrite
	public static void rebuildTable()
	{
	}

	static
	{
		RECIPES_BY_TAB = null;
		ALL_RECIPES = null;
	}
}
