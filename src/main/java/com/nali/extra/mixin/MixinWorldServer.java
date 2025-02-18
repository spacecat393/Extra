package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.gui.da.server.SDaInvSelectAdd;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer
{
	//clean sleep
	@Overwrite
	public boolean areAllPlayersAsleep()
	{
		return false;
	}

	@Inject(method = "updateEntities", at = @At("HEAD"))
	public void nali_extra_updateEntities(CallbackInfo ci)
	{
		try
		{
			SDaInvSelect.move();
			SDaInvSelectAdd.add();
		}
		catch (Exception e)
		{
			Nali.warn(e);
		}
	}

//	//force multi thread
//	private final static byte B_LOCK = 1;
//	private final static byte B_LOCK_IN_TICKUPDATES = 2;
//	@Unique
//	private byte extra$state;
//
//	@Unique
//	private short extra$state_updateblocktick;
////	@Unique
////	private byte extra$state_tick;
////	@Unique
////	private byte extra$state_getpendingblockupdates;
//
//	@Inject(method = "updateBlockTick", at = @At(value = "HEAD"))
//	private void nali_extra_updateBlockTickH(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
//	{
//		while (this.extra$state_updateblocktick == 0 && (this.extra$state & B_LOCK + B_LOCK_IN_TICKUPDATES) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
//		++this.extra$state_updateblocktick;
//
////		Nali.warn("this.extra$state_updateblocktick " + this.extra$state_updateblocktick);
////		Nali.warn("this.extra$state " + this.extra$state);
//	}
//
//	@Inject(method = "updateBlockTick", at = @At(value = "TAIL"))
//	private void nali_extra_updateBlockTickT(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
//	{
//		--this.extra$state_updateblocktick;
//		if (this.extra$state_updateblocktick == 0 && (this.extra$state & B_LOCK + B_LOCK_IN_TICKUPDATES) == B_LOCK)
//		{
//			this.extra$state &= 255 - B_LOCK;
//		}
//	}
//
//	@Inject(method = "scheduleBlockUpdate", at = @At(value = "HEAD"))
//	private void nali_extra_scheduleBlockUpdateH(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
//	{
//		while (this.extra$state_updateblocktick == 0 && (this.extra$state & B_LOCK) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
//	}
//
//	@Inject(method = "scheduleBlockUpdate", at = @At(value = "TAIL"))
//	private void nali_extra_scheduleBlockUpdateT(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
//	{
//		if (this.extra$state_updateblocktick == 0)
//		{
//			this.extra$state &= 255 - B_LOCK;
//		}
//	}
//
//	@Inject(method = "isUpdateScheduled", at = @At(value = "HEAD"))
//	private void nali_extra_isUpdateScheduledH(BlockPos pos, Block blk, CallbackInfoReturnable<Boolean> cir)
//	{
//		while ((this.extra$state & B_LOCK) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
//	}
//
//	@Inject(method = "isUpdateScheduled", at = @At(value = "TAIL"))
//	private void nali_extra_isUpdateScheduledT(BlockPos pos, Block blk, CallbackInfoReturnable<Boolean> cir)
//	{
//		this.extra$state &= 255 - B_LOCK;
//	}
//
//	@Inject(method = "getPendingBlockUpdates(Lnet/minecraft/world/gen/structure/StructureBoundingBox;Z)Ljava/util/List;", at = @At(value = "HEAD"))
//	private void nali_extra_getPendingBlockUpdatesH(StructureBoundingBox structureBB, boolean remove, CallbackInfoReturnable<List<NextTickListEntry>> cir)
//	{
//		while (this.extra$state_updateblocktick == 0/* && this.extra$state_getpendingblockupdates == 0*/ && (this.extra$state & B_LOCK + B_LOCK_IN_TICKUPDATES) == B_LOCK)
//		{
//			if (ExtraConfig.DEBUG_THREAD)
//			{
//				Nali.error("");
//			}
//		}
//
//		this.extra$state |= B_LOCK;
////		++this.extra$state_getpendingblockupdates;
////		Nali.warn("this.extra$state_getpendingblockupdates " + this.extra$state_getpendingblockupdates);
//	}
//
//	@Inject(method = "getPendingBlockUpdates(Lnet/minecraft/world/gen/structure/StructureBoundingBox;Z)Ljava/util/List;", at = @At(value = "TAIL"))
//	private void nali_extra_getPendingBlockUpdatesT(StructureBoundingBox structureBB, boolean remove, CallbackInfoReturnable<List<NextTickListEntry>> cir)
//	{
////		--this.extra$state_getpendingblockupdates;
//		if (this.extra$state_updateblocktick == 0/* && this.extra$state_getpendingblockupdates == 0*/ && (this.extra$state & B_LOCK + B_LOCK_IN_TICKUPDATES) == B_LOCK)
//		{
//			this.extra$state &= 255 - B_LOCK;
//		}
//	}
//
////	@Inject(method = "tickUpdates", at = @At(value = "HEAD"))
////	private void nali_extra_tickUpdatesH(boolean runAllPending, CallbackInfoReturnable<Boolean> cir)
////	{
////		while (this.extra$state_updateblocktick == 0 && (this.extra$state & B_LOCK) == B_LOCK)
////		{
////			if (ExtraConfig.DEBUG_THREAD)
////			{
////				Nali.error("");
////			}
////		}
////
////		this.extra$state |= B_LOCK | B_LOCK_IN_TICKUPDATES;
////	}
////
////	@Inject(method = "tickUpdates", at = @At(value = "TAIL"))
////	private void nali_extra_tickUpdatesT(boolean runAllPending, CallbackInfoReturnable<Boolean> cir)
////	{
////		if (this.extra$state_updateblocktick == 0)
////		{
////			this.extra$state &= 255 - B_LOCK;
////		}
////		this.extra$state &= 255 - B_LOCK_IN_TICKUPDATES;
////	}
//	@Overwrite
//	public boolean tickUpdates(boolean runAllPending)
//	{
//		return false;
//	}
////	@Redirect(method = "tickUpdates", at = @At(value = "INVOKE", target = "Ljava/util/TreeSet;size()I"))
////	private int nali_extra_tickUpdates(TreeSet instance)
////	{
////		return 0;
////	}
////
////	@Redirect(method = "tickUpdates", at = @At(value = "INVOKE", target = "Ljava/util/Set;size()I"))
////	private int nali_extra_tickUpdates(Set instance)
////	{
////		return 0;
////	}
}
