package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.extra.ExtraConfig;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.gui.da.server.SDaInvSelectAdd;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.NextTickListEntry;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

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

	//force multi thread
	private final static byte B_LOCK = 1;
	private final static byte B_LOCK_IN_TICK = 2;
	private byte state;

	@Inject(method = "updateBlockTick", at = @At(value = "HEAD"))
	private void nali_extra_updateBlockTickH(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "updateBlockTick", at = @At(value = "TAIL"))
	private void nali_extra_updateBlockTickT(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "scheduleBlockUpdate", at = @At(value = "HEAD"))
	private void nali_extra_scheduleBlockUpdateH(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "scheduleBlockUpdate", at = @At(value = "TAIL"))
	private void nali_extra_scheduleBlockUpdateT(BlockPos pos, Block blockIn, int delay, int priority, CallbackInfo ci)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "tick", at = @At(value = "HEAD"))
	private void nali_extra_tickH(CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_TICK;
	}

	@Inject(method = "tick", at = @At(value = "TAIL"))
	private void nali_extra_tickT(CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_LOCK_IN_TICK);
	}

	@Inject(method = "isUpdateScheduled", at = @At(value = "HEAD"))
	private void nali_extra_isUpdateScheduledH(BlockPos pos, Block blk, CallbackInfoReturnable<Boolean> cir)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "isUpdateScheduled", at = @At(value = "TAIL"))
	private void nali_extra_isUpdateScheduledT(BlockPos pos, Block blk, CallbackInfoReturnable<Boolean> cir)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "getPendingBlockUpdates(Lnet/minecraft/world/gen/structure/StructureBoundingBox;Z)Ljava/util/List;", at = @At(value = "HEAD"))
	private void nali_extra_getPendingBlockUpdatesH(StructureBoundingBox structureBB, boolean remove, CallbackInfoReturnable<List<NextTickListEntry>> cir)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "getPendingBlockUpdates(Lnet/minecraft/world/gen/structure/StructureBoundingBox;Z)Ljava/util/List;", at = @At(value = "TAIL"))
	private void nali_extra_getPendingBlockUpdatesT(StructureBoundingBox structureBB, boolean remove, CallbackInfoReturnable<List<NextTickListEntry>> cir)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}
//	@Redirect(method = "tickUpdates", at = @At(value = "INVOKE", target = "Ljava/util/TreeSet;size()I"))
//	private int nali_extra_tickUpdates(TreeSet instance)
//	{
//		return 0;
//	}
//
//	@Redirect(method = "tickUpdates", at = @At(value = "INVOKE", target = "Ljava/util/Set;size()I"))
//	private int nali_extra_tickUpdates(Set instance)
//	{
//		return 0;
//	}
}
