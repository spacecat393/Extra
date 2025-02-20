package com.nali.extra.mixin;

import com.nali.extra.ExtraCostume;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.NonNullList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//costume
@Mixin(RenderPlayer.class)
public abstract class MixinRenderPlayer
{
	private ItemStack head_itemstack;
	private ItemStack chest_itemstack;
	private ItemStack legs_itemstack;
	private ItemStack feet_itemstack;

	@Inject(method = "doRender(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", at = @At("HEAD"))
	private void nali_extra_doRenderH(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
	{
		EntityDataManager entitydatamanager = entity.getDataManager();
		byte inv = entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER);
		boolean render_head = (inv & 1) == 1;
		boolean render_chest = (inv & 2) == 2;
		boolean render_legs = (inv & 4) == 4;
		boolean render_feet = (inv & 8) == 8;
		ItemStack head_itemstack = entitydatamanager.get(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER);
		ItemStack chest_itemstack = entitydatamanager.get(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER);
		ItemStack legs_itemstack = entitydatamanager.get(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER);
		ItemStack feet_itemstack = entitydatamanager.get(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER);

		NonNullList<ItemStack> itemstack_nonnulllist = entity.inventory.armorInventory;
		int h_i = EntityEquipmentSlot.HEAD.getIndex();
		int c_i = EntityEquipmentSlot.CHEST.getIndex();
		int l_i = EntityEquipmentSlot.LEGS.getIndex();
		int f_i = EntityEquipmentSlot.FEET.getIndex();

		this.head_itemstack = itemstack_nonnulllist.get(h_i);
		this.chest_itemstack = itemstack_nonnulllist.get(c_i);
		this.legs_itemstack = itemstack_nonnulllist.get(l_i);
		this.feet_itemstack = itemstack_nonnulllist.get(f_i);

		if (!render_head)
		{
			itemstack_nonnulllist.set(h_i, ItemStack.EMPTY);
		}
		if (!render_chest)
		{
			itemstack_nonnulllist.set(c_i, ItemStack.EMPTY);
		}
		if (!render_legs)
		{
			itemstack_nonnulllist.set(l_i, ItemStack.EMPTY);
		}
		if (!render_feet)
		{
			itemstack_nonnulllist.set(f_i, ItemStack.EMPTY);
		}

		if (render_head && !head_itemstack.isEmpty())
		{
			itemstack_nonnulllist.set(h_i, head_itemstack);
		}
		if (render_chest && !chest_itemstack.isEmpty())
		{
			itemstack_nonnulllist.set(c_i, chest_itemstack);
		}
		if (render_legs && !legs_itemstack.isEmpty())
		{
			itemstack_nonnulllist.set(l_i, legs_itemstack);
		}
		if (render_feet && !feet_itemstack.isEmpty())
		{
			itemstack_nonnulllist.set(f_i, feet_itemstack);
		}
	}

	@Inject(method = "doRender(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", at = @At("TAIL"))
	private void nali_extra_doRenderT(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci)
	{
		NonNullList<ItemStack> itemstack_nonnulllist = entity.inventory.armorInventory;
		int h_i = EntityEquipmentSlot.HEAD.getIndex();
		int c_i = EntityEquipmentSlot.CHEST.getIndex();
		int l_i = EntityEquipmentSlot.LEGS.getIndex();
		int f_i = EntityEquipmentSlot.FEET.getIndex();
		itemstack_nonnulllist.set(h_i, this.head_itemstack);
		itemstack_nonnulllist.set(c_i, this.chest_itemstack);
		itemstack_nonnulllist.set(l_i, this.legs_itemstack);
		itemstack_nonnulllist.set(f_i, this.feet_itemstack);
	}
}
