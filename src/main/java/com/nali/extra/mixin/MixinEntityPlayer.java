package com.nali.extra.mixin;

import com.nali.extra.ExtraCostume;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//*extra
@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer extends EntityLivingBase
{
//	private byte state;
	@Shadow public abstract boolean isPlayerSleeping();

	static
	{
		ExtraCostume.INV_BYTE_DATAPARAMETER = EntityDataManager.createKey(MixinEntityPlayer.class, DataSerializers.BYTE);
		ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(MixinEntityPlayer.class, DataSerializers.ITEM_STACK);
		ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(MixinEntityPlayer.class, DataSerializers.ITEM_STACK);
		ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(MixinEntityPlayer.class, DataSerializers.ITEM_STACK);
		ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER = EntityDataManager.createKey(MixinEntityPlayer.class, DataSerializers.ITEM_STACK);
	}

	public MixinEntityPlayer(World worldIn)
	{
		super(worldIn);
	}

	@Overwrite
	public float getCooledAttackStrength(float adjustTicks)
	{
		return 1.0F;
	}

	//clean sleep
//	@Overwrite
//	public int getSleepTimer()
	@Inject(method = "getSleepTimer", at = @At("RETURN"), cancellable = true)
	private void nali_extra_getSleepTimer(CallbackInfoReturnable<Integer> cir)
	{
//		return 0;
		cir.setReturnValue(0);
	}

	//clean sleep
//	@Overwrite
//	public boolean isPlayerFullyAsleep()
//	{
//		return false;
//	}

//	@Overwrite
//	public EntityPlayer.SleepResult trySleep(BlockPos bedLocation)
//	{
//		return EntityPlayer.SleepResult.OK;
//	}

	@Redirect(method = "trySleep", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/player/EntityPlayer$SleepResult;NOT_POSSIBLE_NOW:Lnet/minecraft/entity/player/EntityPlayer$SleepResult;"))
	public EntityPlayer.SleepResult trySleep(BlockPos bedLocation)
	{
		return EntityPlayer.SleepResult.OK;
	}

	@Overwrite
	protected boolean isMovementBlocked()
	{
//		this.state |= this.isSneaking() ? 1 : 0;
//		return this.getHealth() <= 0.0F || this.isPlayerSleeping();
		return false;
	}

	//costume
	@Inject(method = "entityInit", at = @At("HEAD"))
	private void nali_extra_entityInit(CallbackInfo ci)
	{
		this.dataManager.register(ExtraCostume.INV_BYTE_DATAPARAMETER, (byte)0);
		this.dataManager.register(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
		this.dataManager.register(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
		this.dataManager.register(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
		this.dataManager.register(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER, ItemStack.EMPTY);
	}

	@Inject(method = "writeEntityToNBT", at = @At("HEAD"))
	private void nali_extra_writeEntityToNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		compound.setByte("e_i", this.dataManager.get(ExtraCostume.INV_BYTE_DATAPARAMETER));

		ItemStack itemstack = this.dataManager.get(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER);
		if (!itemstack.isEmpty())
		{
			compound.setTag("e_h", itemstack.writeToNBT(new NBTTagCompound()));
		}

		itemstack = this.dataManager.get(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER);
		if (!itemstack.isEmpty())
		{
			compound.setTag("e_c", itemstack.writeToNBT(new NBTTagCompound()));
		}

		itemstack = this.dataManager.get(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER);
		if (!itemstack.isEmpty())
		{
			compound.setTag("e_l", itemstack.writeToNBT(new NBTTagCompound()));
		}

		itemstack = this.dataManager.get(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER);
		if (!itemstack.isEmpty())
		{
			compound.setTag("e_f", itemstack.writeToNBT(new NBTTagCompound()));
		}
	}

	@Inject(method = "readEntityFromNBT", at = @At("HEAD"))
	private void nali_extra_readEntityFromNBT(NBTTagCompound compound, CallbackInfo ci)
	{
		this.dataManager.set(ExtraCostume.INV_BYTE_DATAPARAMETER, compound.getByte("e_i"));

		NBTTagCompound nbttagcompound = compound.getCompoundTag("e_h");
		if (!nbttagcompound.isEmpty())
		{
			this.dataManager.set(ExtraCostume.HEAD_ITEMSTACK_DATAPARAMETER, new ItemStack(nbttagcompound));
		}

		nbttagcompound = compound.getCompoundTag("e_c");
		if (!nbttagcompound.isEmpty())
		{
			this.dataManager.set(ExtraCostume.CHEST_ITEMSTACK_DATAPARAMETER, new ItemStack(nbttagcompound));
		}

		nbttagcompound = compound.getCompoundTag("e_l");
		if (!nbttagcompound.isEmpty())
		{
			this.dataManager.set(ExtraCostume.LEGS_ITEMSTACK_DATAPARAMETER, new ItemStack(nbttagcompound));
		}

		nbttagcompound = compound.getCompoundTag("e_f");
		if (!nbttagcompound.isEmpty())
		{
			this.dataManager.set(ExtraCostume.FEET_ITEMSTACK_DATAPARAMETER, new ItemStack(nbttagcompound));
		}
	}
//	@Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;isPlayerSleeping()Z"))
//	private boolean nali_extra_onUpdate(EntityPlayer instance)
//	{
//		return false;
//	}
//	@Overwrite
//	public boolean isPlayerSleeping()
//	{
//		return false;
//	}

//	//remove hitbox
//	@Overwrite
//	private void collideWithPlayer(Entity entityIn)
//	{
//	}
//
//	@Redirect(method = "onLivingUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/EntityPlayer;getHealth()F", ordinal = 3))
//	private float nali_extra_onLivingUpdate(EntityPlayer instance)
//	{
//		return -1;
//	}
}
