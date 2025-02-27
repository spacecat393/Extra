package com.nali.extra.mixin;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//click
@Mixin(ItemStack.class)
public abstract class MixinItemStack
{
	@Redirect(method = "onItemUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/Item;onItemUse(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/EnumHand;Lnet/minecraft/util/EnumFacing;FFF)Lnet/minecraft/util/EnumActionResult;"))
	private EnumActionResult nali_extra_onItemUse(Item instance, EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		instance.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		return EnumActionResult.PASS;
	}

//	@Inject(method = "onItemUse", at = @At(value = "RETURN"), cancellable = true)
//	private void nali_extra_onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ, CallbackInfoReturnable<EnumActionResult> cir)
//	{
//		cir.setReturnValue(EnumActionResult.SUCCESS);
//	}
}
