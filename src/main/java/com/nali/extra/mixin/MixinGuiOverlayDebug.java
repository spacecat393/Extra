package com.nali.extra.mixin;

import com.nali.small.Small;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;

@Mixin(GuiOverlayDebug.class)
public abstract class MixinGuiOverlayDebug
{
	@Shadow @Final private Minecraft mc;

	private static BlockPos BLOCKPOS;
	private static IBlockState IBLOCKSTATE;

	@Inject(method = "renderDebugInfo", at = @At("HEAD"), cancellable = true)
	private void nali_extra_renderDebugInfo(ScaledResolution scaledResolutionIn, CallbackInfo ci)
	{
		Small.FLAG |= 2;
		if ((Small.FLAG & 1) == 1)
		{
			ci.cancel();
		}
	}

	@Inject(method = "getDebugInfoRight", at = @At("RETURN"))
	private void nali_extra_getDebugInfoRight(CallbackInfoReturnable<List<String>> cir)
	{
		List list = cir.getReturnValue();
		if (IBLOCKSTATE != null)
		{
			Block block = IBLOCKSTATE.getBlock();
			String harvest_tool = block.getHarvestTool(IBLOCKSTATE);
			String harvest_level = "" + block.getHarvestLevel(IBLOCKSTATE);
	//		boolean is_tool_effective = false;
//			if (IBLOCKSTATE.getMaterial().isToolNotRequired())
//			{
//	//				harvest_tool = "-";
//				harvest_level = "-";
//			}
//			else
//			{
	//				harvest_tool = block.getHarvestTool(IBLOCKSTATE);
//			}

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
	//			for (String toolclasses : toolclasses_string_set)
	//			{
	//				is_tool_effective = block.isToolEffective(toolclasses, IBLOCKSTATE);
	//			}
			}

			if (block instanceof BlockCrops)
			{
				BlockCrops blockcrops = (BlockCrops)block;
				list.add("MaxAge " + blockcrops.getMaxAge());
			}
			list.add("ToolClasses " + toolclasses_string_set);
			list.add("ToolLevel " + tool_level);
			list.add("HarvestTool " + harvest_tool);
			list.add("HarvestLevel " + harvest_level);
	//		list.add("isToolEffective " + is_tool_effective);
//			list.add("canHarvestBlock " + itemstack.canHarvestBlock(IBLOCKSTATE));
			list.add("canSilkHarvest " + block.canSilkHarvest(entityplayersp.world, BLOCKPOS, IBLOCKSTATE, entityplayersp));
			list.add("DestroySpeed " + itemstack.getDestroySpeed(IBLOCKSTATE));
	//		cir.setReturnValue(list);
		}
		IBLOCKSTATE = null;
	}

	@Inject(method = "call", at = @At("RETURN"))
	private void nali_extra_call(CallbackInfoReturnable<List<String>> cir)
	{
		List list = cir.getReturnValue();
		EntityPlayerSP entityplayersp = this.mc.player;
		list.add("");
		list.add("AbsorptionAmount " + entityplayersp.getAbsorptionAmount());
		list.add("Health " + entityplayersp.getHealth());
		list.add("MaxHealth " + entityplayersp.getMaxHealth());
		FoodStats foodstats = entityplayersp.getFoodStats();
		list.add("FoodLevel " + foodstats.getFoodLevel());
		list.add("SaturationLevel " + foodstats.getSaturationLevel());
		Entity riding_entity = entityplayersp.getRidingEntity();
		if (riding_entity != null)
		{
			if (riding_entity instanceof EntityLivingBase)
			{
				EntityLivingBase entitylivingbase = (EntityLivingBase)riding_entity;
				list.add("Name " + entitylivingbase.getName());
				list.add("Health " + entitylivingbase.getHealth());
				list.add("MaxHealth " + entitylivingbase.getMaxHealth());
			}
			if (riding_entity instanceof AbstractHorse)
			{
				list.add("HorseJumpPower " + entityplayersp.getHorseJumpPower());
			}
		}
	}

	@Redirect(method = "getDebugInfoRight", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/WorldClient;getBlockState(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;"))
	private IBlockState getDebugInfoRight(WorldClient instance, BlockPos blockPos)
	{
		BLOCKPOS = blockPos;
		IBLOCKSTATE = instance.getBlockState(BLOCKPOS);
		return IBLOCKSTATE;
	}
}
