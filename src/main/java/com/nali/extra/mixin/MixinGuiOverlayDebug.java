package com.nali.extra.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;

@Mixin(GuiOverlayDebug.class)
public abstract class MixinGuiOverlayDebug
{
	@Shadow @Final private Minecraft mc;

	private static IBlockState IBLOCKSTATE;

	@Inject(method = "getDebugInfoRight", at = @At("RETURN"))
	protected <T extends Comparable<T>> void nali_extra_getDebugInfoRight(CallbackInfoReturnable<List<String>> cir)
	{
		if (IBLOCKSTATE != null)
		{
			List list = cir.getReturnValue();
			Block block = IBLOCKSTATE.getBlock();
			String harvest_tool;
			String harvest_level;
			if (IBLOCKSTATE.getMaterial().isToolNotRequired())
			{
				harvest_tool = "-";
				harvest_level = "-";
			}
			else
			{
				harvest_tool = block.getHarvestTool(IBLOCKSTATE);
				harvest_level = "" + block.getHarvestLevel(IBLOCKSTATE);
			}
			EntityPlayerSP entityplayersp = this.mc.player;
			ItemStack itemstack = entityplayersp.getHeldItemMainhand();
			Item item = itemstack.getItem();
			int tool_level = -1;

			Set<String> toolclasses_string_set = item.getToolClasses(itemstack);
			if (!toolclasses_string_set.isEmpty())
			{
				for (String toolclasses : toolclasses_string_set)
				{
					if (toolclasses.equals(harvest_tool))
					{
						tool_level = item.getHarvestLevel(itemstack, harvest_tool, entityplayersp, IBLOCKSTATE);
						break;
					}
				}
			}

			list.add("Tool Classes: " + toolclasses_string_set);
			list.add("Tool Level: " + tool_level);
			list.add("Harvest Tool: " + harvest_tool);
			list.add("Harvest Level: " + harvest_level);
	//		cir.setReturnValue(list);
		}
	}

	@Redirect(method = "getDebugInfoRight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;"))
	protected <T extends Comparable<T>> IBlockState getDebugInfoRight(WorldClient instance, BlockPos blockPos)
	{
		IBLOCKSTATE = instance.getBlockState(blockPos);
		return IBLOCKSTATE;
	}
}
