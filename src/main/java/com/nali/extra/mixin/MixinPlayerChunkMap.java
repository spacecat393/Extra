package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.extra.ExtraConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.PlayerChunkMap;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

//force multi thread
@Mixin(PlayerChunkMap.class)
public abstract class MixinPlayerChunkMap
{
	private final static byte B_LOCK = 1;
	private final static byte B_LOCK_IN_ISPLAYERWATCHINGCHUNK = 2;
	private final static byte B_LOCK_IN_UPDATEMOVINGPLAYER = 4;
	private final static byte B_LOCK_IN_REMOVEPLAYER = 8;
	private final static byte B_MARKBLOCKFORUPDATE = 16;
	private final static byte B_LOCK_IN_SETPLAYERVIEWRADIUS = 32;
	private final static byte B_LOCK_IN_ADDPLAYER = 64;
	private final static short B_LOCK_IN_TICK = 128;
	private byte state;

	@Inject(method = "getChunkIterator", at = @At(value = "HEAD"))
	private void nali_extra_getChunkIteratorH(CallbackInfoReturnable<Iterator<Chunk>> cir)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "getChunkIterator", at = @At(value = "TAIL"))
	private void nali_extra_getChunkIteratorT(CallbackInfoReturnable<Iterator<Chunk>> cir)
	{
		this.state &= 255 - B_LOCK;
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

	@Inject(method = "contains", at = @At(value = "HEAD"))
	private void nali_extra_containsH(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "contains", at = @At(value = "TAIL"))
	private void nali_extra_containsT(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "getEntry", at = @At(value = "HEAD"))
	private void nali_extra_getEntryH(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_ISPLAYERWATCHINGCHUNK + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_REMOVEPLAYER + B_MARKBLOCKFORUPDATE) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "getEntry", at = @At(value = "TAIL"))
	private void nali_extra_getEntryT(int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_ISPLAYERWATCHINGCHUNK + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_REMOVEPLAYER + B_MARKBLOCKFORUPDATE) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "getOrCreateEntry", at = @At(value = "HEAD"))
	private void nali_extra_getOrCreateEntryH(int chunkX, int chunkZ, CallbackInfoReturnable<PlayerChunkMapEntry> cir)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_SETPLAYERVIEWRADIUS + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_ADDPLAYER) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "getOrCreateEntry", at = @At(value = "TAIL"))
	private void nali_extra_getOrCreateEntryT(int chunkX, int chunkZ, CallbackInfoReturnable<PlayerChunkMapEntry> cir)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_SETPLAYERVIEWRADIUS + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_ADDPLAYER) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "markBlockForUpdate", at = @At(value = "HEAD"))
	private void nali_extra_markBlockForUpdateH(BlockPos pos, CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_MARKBLOCKFORUPDATE;
	}

	@Inject(method = "markBlockForUpdate", at = @At(value = "TAIL"))
	private void nali_extra_markBlockForUpdateT(BlockPos pos, CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_MARKBLOCKFORUPDATE);
	}

	@Inject(method = "addPlayer", at = @At(value = "HEAD"))
	private void nali_extra_addPlayerH(EntityPlayerMP player, CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_ADDPLAYER;
	}

	@Inject(method = "addPlayer", at = @At(value = "TAIL"))
	private void nali_extra_addPlayerT(EntityPlayerMP player, CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_LOCK_IN_ADDPLAYER);
	}

	@Inject(method = "removePlayer", at = @At(value = "HEAD"))
	private void nali_extra_removePlayerH(EntityPlayerMP player, CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_REMOVEPLAYER;
	}

	@Inject(method = "removePlayer", at = @At(value = "TAIL"))
	private void nali_extra_removePlayerT(EntityPlayerMP player, CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_LOCK_IN_REMOVEPLAYER);
	}

	@Inject(method = "updateMovingPlayer", at = @At(value = "HEAD"))
	private void nali_extra_updateMovingPlayerH(EntityPlayerMP player, CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_UPDATEMOVINGPLAYER;
	}

	@Inject(method = "updateMovingPlayer", at = @At(value = "TAIL"))
	private void nali_extra_updateMovingPlayerT(EntityPlayerMP player, CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_LOCK_IN_UPDATEMOVINGPLAYER);
	}

	@Inject(method = "isPlayerWatchingChunk", at = @At(value = "HEAD"))
	private void nali_extra_isPlayerWatchingChunkH(EntityPlayerMP player, int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_ISPLAYERWATCHINGCHUNK;
	}

	@Inject(method = "isPlayerWatchingChunk", at = @At(value = "TAIL"))
	private void nali_extra_isPlayerWatchingChunkT(EntityPlayerMP player, int chunkX, int chunkZ, CallbackInfoReturnable<Boolean> cir)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_TICK) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}

		this.state &= 255 - B_LOCK_IN_ISPLAYERWATCHINGCHUNK;
	}

	@Inject(method = "setPlayerViewRadius", at = @At(value = "HEAD"))
	private void nali_extra_setPlayerViewRadiusH(int radius, CallbackInfo ci)
	{
		while ((this.state & B_LOCK) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK | B_LOCK_IN_SETPLAYERVIEWRADIUS;
	}

	@Inject(method = "setPlayerViewRadius", at = @At(value = "TAIL"))
	private void nali_extra_setPlayerViewRadiusT(int radius, CallbackInfo ci)
	{
		this.state &= 255 - (B_LOCK + B_LOCK_IN_SETPLAYERVIEWRADIUS);
	}

	@Inject(method = "entryChanged", at = @At(value = "HEAD"))
	private void nali_extra_entryChangedH(PlayerChunkMapEntry entry, CallbackInfo ci)
	{
		while ((this.state & B_LOCK + B_MARKBLOCKFORUPDATE) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "entryChanged", at = @At(value = "TAIL"))
	private void nali_extra_entryChangedT(PlayerChunkMapEntry entry, CallbackInfo ci)
	{
		if ((this.state & B_LOCK + B_MARKBLOCKFORUPDATE) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}

	@Inject(method = "removeEntry", at = @At(value = "HEAD"))
	private void nali_extra_removeEntryH(PlayerChunkMapEntry entry, CallbackInfo ci)
	{
		while ((this.state & B_LOCK + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_REMOVEPLAYER) == B_LOCK)
		{
			if (ExtraConfig.DEBUG_THREAD)
			{
				Nali.error("");
			}
		}

		this.state |= B_LOCK;
	}

	@Inject(method = "removeEntry", at = @At(value = "TAIL"))
	private void nali_extra_removeEntryT(PlayerChunkMapEntry entry, CallbackInfo ci)
	{
		if ((this.state & B_LOCK + B_LOCK_IN_UPDATEMOVINGPLAYER + B_LOCK_IN_REMOVEPLAYER) == B_LOCK)
		{
			this.state &= 255 - B_LOCK;
		}
	}
}
