//package com.nali.extra.mixin;
//
//import com.nali.extra.array.ExtraArrayList;
//import com.nali.extra.array.ExtraHashSet;
//import com.nali.extra.array.ExtraLinkedList;
//import com.nali.extra.array.ExtraLong2ObjectOpenHashMap;
//import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
//import net.minecraft.entity.player.EntityPlayerMP;
//import net.minecraft.server.management.PlayerChunkMap;
//import net.minecraft.server.management.PlayerChunkMapEntry;
//import net.minecraft.world.WorldServer;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Mutable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.List;
//import java.util.Set;
//
////force multi thread
//@Mixin(PlayerChunkMap.class)
//public abstract class MixinPlayerChunkMap
//{
//	@Mutable
//	@Shadow @Final private List<EntityPlayerMP> players;
//
//	@Mutable
//	@Shadow @Final private List<PlayerChunkMapEntry> entries;
//
//	@Mutable
//	@Shadow @Final private List<PlayerChunkMapEntry> pendingSendToPlayers;
//
//	@Mutable
//	@Shadow @Final private List<PlayerChunkMapEntry> entriesWithoutChunks;
//
//	@Mutable
//	@Shadow @Final private Set<PlayerChunkMapEntry> dirtyEntries;
//
//	@Mutable
//	@Shadow @Final private Long2ObjectMap<PlayerChunkMapEntry> entryMap;
//
//	@Inject(method = "<init>", at = @At("TAIL"))
//	public void nali_extra_init(WorldServer theWorldIn, CallbackInfo ci)
//	{
//		this.players = new ExtraArrayList();
//		this.entries = new ExtraArrayList();
//		this.pendingSendToPlayers = new ExtraLinkedList();
//		this.entriesWithoutChunks = new ExtraLinkedList();
//		this.dirtyEntries = new ExtraHashSet();
//		this.entryMap = new ExtraLong2ObjectOpenHashMap(4096);
//	}
//}
