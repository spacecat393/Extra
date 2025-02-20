package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.extra.ExtraConfig;
import com.nali.extra.patch.PatchTombstone;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.gui.da.server.SDaInvSelectAdd;
import net.minecraft.profiler.Profiler;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldServer.class)
public abstract class MixinWorldServer extends World
{
	protected MixinWorldServer(ISaveHandler saveHandlerIn, WorldInfo info, WorldProvider providerIn, Profiler profilerIn, boolean client)
	{
		super(saveHandlerIn, info, providerIn, profilerIn, client);
	}

//	@Mutable
//	@Shadow @Final private TreeSet<NextTickListEntry> pendingTickListEntriesTreeSet;
//
//	@Mutable
//	@Shadow @Final private List<NextTickListEntry> pendingTickListEntriesThisTick;
//
//	@Shadow public List<Teleporter> customTeleporters;
//
//	@Shadow protected Set<ChunkPos> doneChunks;
//
//	@Mutable
//	@Shadow @Final private Set<NextTickListEntry> pendingTickListEntriesHashSet;
//
//	@Mutable
//	@Shadow @Final private Map<UUID, Entity> entitiesByUuid;

	//clean sleep
	@Overwrite
	public boolean areAllPlayersAsleep()
	{
		return false;
	}

	@Inject(method = "updateEntities", at = @At("HEAD"))
	private void nali_extra_updateEntities(CallbackInfo ci)
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

	//patch tombstone
	@Inject(method = "tick", at = @At("HEAD"))
	private void nali_extra_tick(CallbackInfo ci)
	{
		if (ExtraConfig.PATCH_TOMBSTONE)
		{
			PatchTombstone.forceEvent(this.getWorldTime(), this.rand);
		}
	}

//	//force multi thread
//	@Inject(method = "<init>", at = @At(value = "TAIL"))
//	private void nali_extra_init(MinecraftServer server, ISaveHandler saveHandlerIn, WorldInfo info, int dimensionId, Profiler profilerIn, CallbackInfo ci)
//	{
//		this.pendingTickListEntriesTreeSet = new ExtraTreeSet();
//		this.pendingTickListEntriesThisTick = new ExtraArrayList();
//		this.customTeleporters = new ExtraArrayList();
//		this.doneChunks = new ExtraHashSet();
//		this.pendingTickListEntriesHashSet = new ExtraHashSet();
//		this.entitiesByUuid = new ExtraHashMap();
//	}
}
