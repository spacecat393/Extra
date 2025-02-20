//package com.nali.extra.mixin;
//
//import com.nali.extra.ExtraCostume;
//import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.EntityEquipmentSlot;
//import net.minecraft.item.ItemStack;
//import net.minecraft.network.datasync.EntityDataManager;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.Redirect;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
////costume
//@Mixin(LayerArmorBase.class)
//public abstract class MixinLayerArmorBase
//{
//	private boolean render_head;
//	private boolean render_chest;
//	private boolean render_legs;
//	private boolean render_feet;
//	private ItemStack head_itemstack;
//	private ItemStack chest_itemstack;
//	private ItemStack legs_itemstack;
//	private ItemStack feet_itemstack;
//
//	@Inject(method = "doRenderLayer", at = @At("HEAD"), cancellable = true)
//	private void nali_extra_doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci)
//	{
//		if (entitylivingbaseIn instanceof EntityPlayer)
//		{
//			EntityDataManager entitydatamanager = entitylivingbaseIn.getDataManager();
//			byte inv = entitydatamanager.get(ExtraCostume.INV_BYTE_DATAPARAMETER);
//			this.render_head = (inv & 1) == 1;
//			this.render_chest = (inv & 2) == 2;
//			this.render_legs = (inv & 4) == 4;
//			this.render_feet = (inv & 8) == 8;
//			this.head_itemstack = entitydatamanager.get(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER);
//			this.chest_itemstack = entitydatamanager.get(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER);
//			this.legs_itemstack = entitydatamanager.get(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER);
//			this.feet_itemstack = entitydatamanager.get(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER);
//
////			this.head_itemstack = null;
////			this.chest_itemstack = null;
////			this.legs_itemstack = null;
////			this.feet_itemstack = null;
////			int index = 0;
////			byte[] byte_array = entitylivingbaseIn.getUniqueID();
////			this.render_head = (byte_array[index] & 1) == 1;
////			this.render_chest = (byte_array[index] & 2) == 2;
////			this.render_legs = (byte_array[index] & 4) == 4;
////			this.render_feet = (byte_array[index] & 8) == 8;
////			++index;
////
////			if (this.render_head)
////			{
////				int line = ByteReader.getInt(byte_array, index);
////				index += 4;
////				if (line != 0)
////				{
////					int itemstack_byte_array_size = line - index;
////					byte[] itemstack_byte_array = new byte[itemstack_byte_array_size];
////					System.arraycopy(byte_array, index, itemstack_byte_array, 0, itemstack_byte_array_size);
////					this.head_itemstack = new ItemStack(ByteReader.deserializeNBT(itemstack_byte_array));
////					index += itemstack_byte_array_size;
////				}
////			}
////
////			if (this.render_chest)
////			{
////				int line = ByteReader.getInt(byte_array, index);
////				index += 4;
////				if (line != 0)
////				{
////					int itemstack_byte_array_size = line - index;
////					byte[] itemstack_byte_array = new byte[itemstack_byte_array_size];
////					System.arraycopy(byte_array, index, itemstack_byte_array, 0, itemstack_byte_array_size);
////					this.chest_itemstack = new ItemStack(ByteReader.deserializeNBT(itemstack_byte_array));
////					index += itemstack_byte_array_size;
////				}
////			}
////
////			if (this.render_legs)
////			{
////				int line = ByteReader.getInt(byte_array, index);
////				index += 4;
////				if (line != 0)
////				{
////					int itemstack_byte_array_size = line - index;
////					byte[] itemstack_byte_array = new byte[itemstack_byte_array_size];
////					System.arraycopy(byte_array, index, itemstack_byte_array, 0, itemstack_byte_array_size);
////					this.legs_itemstack = new ItemStack(ByteReader.deserializeNBT(itemstack_byte_array));
////					index += itemstack_byte_array_size;
////				}
////			}
////
////			if (this.render_feet)
////			{
////				int line = ByteReader.getInt(byte_array, index);
////				index += 4;
////				if (line != 0)
////				{
////					int itemstack_byte_array_size = line - index;
////					byte[] itemstack_byte_array = new byte[itemstack_byte_array_size];
////					System.arraycopy(byte_array, index, itemstack_byte_array, 0, itemstack_byte_array_size);
////					this.feet_itemstack = new ItemStack(ByteReader.deserializeNBT(itemstack_byte_array));
//////					index += itemstack_byte_array_size;
////				}
////			}
////////			if ((Small.FLAG & 1) == 0)
////////			{
//////			ci.cancel();
////////			}
//		}
//	}
//
//	@Inject(method = "renderArmorLayer", at = @At("HEAD"), cancellable = true)
//	private void nali_extra_renderArmorLayer(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
//	{
//		switch (slotIn)
//		{
//			case HEAD:
//				if (!this.render_head)
//				{
//					ci.cancel();
//				}
//				break;
//			case CHEST:
//				if (!this.render_chest)
//				{
//					ci.cancel();
//				}
//				break;
//			case LEGS:
//				if (!this.render_legs)
//				{
//					ci.cancel();
//				}
//				break;
//			case FEET:
//				if (!this.render_feet)
//				{
//					ci.cancel();
//				}
//		}
//	}
//
//	@Redirect(method = "renderArmorLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;getItemStackFromSlot(Lnet/minecraft/inventory/EntityEquipmentSlot;)Lnet/minecraft/item/ItemStack;"))
//	private ItemStack nali_extra_renderArmorLayer(EntityLivingBase instance, EntityEquipmentSlot entityEquipmentSlot)
//	{
//		switch (entityEquipmentSlot)
//		{
//			case HEAD:
//				if (!this.head_itemstack.isEmpty())
//				{
//					return this.head_itemstack;
//				}
//				break;
//			case CHEST:
//				if (!this.chest_itemstack.isEmpty())
//				{
//					return this.chest_itemstack;
//				}
//				break;
//			case LEGS:
//				if (!this.legs_itemstack.isEmpty())
//				{
//					return this.legs_itemstack;
//				}
//				break;
//			case FEET:
//				if (!this.feet_itemstack.isEmpty())
//				{
//					return this.feet_itemstack;
//				}
//		}
//		return instance.getItemStackFromSlot(entityEquipmentSlot);
//	}
//
//	//alpha armor
////	@Inject(method = "renderArmorLayer", at = @At("HEAD"))
////	private void nali_extra_renderArmorLayerH(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
////	{
//////		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
////	}
////
////	@Inject(method = "renderArmorLayer", at = @At("TAIL"))
////	private void nali_extra_renderArmorLayerT(EntityLivingBase entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale, EntityEquipmentSlot slotIn, CallbackInfo ci)
////	{
//////		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//////		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
////	}
//}
