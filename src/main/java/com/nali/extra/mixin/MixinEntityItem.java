package com.nali.extra.mixin;

import com.nali.list.block.ExtraCloud;
import com.nali.list.entity.si.SIEArea;
import com.nali.list.item.ExtraBox;
import com.nali.small.entity.EntityRegistry;
import com.nali.small.mix.block.BlockRegistry;
import com.nali.small.mix.item.ItemRegistry;
import com.nali.small.mixin.IMixinWorldServer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.AbstractChestHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.UUID;

@Mixin(EntityItem.class)
public abstract class MixinEntityItem extends Entity
{
//	private ItemStack itemstack = ItemStack.EMPTY;

	public MixinEntityItem(World worldIn)
	{
		super(worldIn);
	}

//	@Inject(method = "setItem", at = @At(value = "HEAD"))
//	private void nali_small_setItem(ItemStack stack, CallbackInfo ci)
//	{
//		this.itemstack = stack;
//	}

	@Shadow public abstract ItemStack getItem();

	@Inject(method = "onUpdate", at = @At(value = "HEAD"))
	private void nali_extra_onUpdate(CallbackInfo ci)
	{
		if (!this.world.isRemote)
		{
			ItemStack itemstack = this.getItem();
			if (itemstack != ItemStack.EMPTY && itemstack.getItem() == ItemRegistry.ITEM_ARRAY[ExtraBox.ID])
			{
				Map<UUID, Entity> entity_map = ((IMixinWorldServer)this.world).entitiesByUuid();
				for (Entity entity : entity_map.values())
				{
					boolean has_inv = entity instanceof IInventoryChangedListener;
					if (this != entity && !(entity instanceof EntityPlayer) && SIEArea.getDistanceAABBToAABB(this, entity) <= 0)
					{
						if (entity instanceof EntityItem || has_inv && !(entity instanceof AbstractChestHorse))
						{
							EntityItem entityitem = (EntityItem)entity;
//							if (itemstack.getCount() < 64)
//							{
							ItemStack entityitem_itemstack = entityitem.getItem();
							if (entityitem_itemstack.getItem() == Items.GLOWSTONE_DUST)
							{
								int count = entityitem_itemstack.getCount();
								itemstack.grow(count);
								entityitem_itemstack.shrink(count);
							}
//							else if (entityitem_itemstack.getItem() instanceof ItemRecord/* && entityitem_itemstack.getCount() == 64*/)
							else if (entityitem_itemstack.getItem() == Item.getItemFromBlock(Blocks.GLOWSTONE) && entityitem_itemstack.getCount() == 64)
							{
								if (itemstack.getTagCompound() == null)
								{
									if (this.rand.nextBoolean())
									{
										if (itemstack.getCount() == 1 && !EntityRegistry.ENTITIES_CLASS_LIST.isEmpty())
										{
											ExtraBox.randomToBox(this.world, itemstack);
										}
									}
									else
									{
										this.world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ, new ItemStack(BlockRegistry.ITEM_ARRAY[ExtraCloud.ID], 64)));
									}
									entityitem_itemstack.shrink(64);
								}
//								else
//								{
//									itemstack.grow(1);
//									entityitem_itemstack.shrink(1);
//								}
							}
//							}
						}
						else if (itemstack.getTagCompound() == null)
						{
							if (!has_inv || entity instanceof AbstractChestHorse && !((AbstractChestHorse)entity).hasChest())
							{
								ExtraBox.putToBox(entity, itemstack);
							}
						}
						break;
					}
				}
			}
		}
	}
}
