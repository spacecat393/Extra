package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.extra.ExtraConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

//force multi thread
@Mixin(EntityTracker.class)
public abstract class MixinEntityTracker
{
	private final static byte B_LOCK = 1;
	private byte state;

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

		this.state |= B_LOCK;
	}

	@Inject(method = "tick", at = @At(value = "TAIL"))
	private void nali_extra_tickT(CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "track(Lnet/minecraft/entity/Entity;)V", at = @At(value = "HEAD"))
	private void nali_extra_trackH(Entity entityIn, CallbackInfo ci)
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

	@Inject(method = "track(Lnet/minecraft/entity/Entity;)V", at = @At(value = "TAIL"))
	private void nali_extra_trackT(Entity entityIn, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "untrack", at = @At(value = "HEAD"))
	private void untrackH(Entity entityIn, CallbackInfo ci)
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

	@Inject(method = "untrack", at = @At(value = "TAIL"))
	private void nali_extra_untrackT(Entity entityIn, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "updateVisibility", at = @At(value = "HEAD"))
	private void nali_extra_updateVisibilityH(EntityPlayerMP player, CallbackInfo ci)
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

	@Inject(method = "updateVisibility", at = @At(value = "TAIL"))
	private void nali_extra_updateVisibilityT(EntityPlayerMP player, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "removePlayerFromTrackers", at = @At(value = "HEAD"))
	private void nali_extra_removePlayerFromTrackersH(EntityPlayerMP player, CallbackInfo ci)
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

	@Inject(method = "removePlayerFromTrackers", at = @At(value = "TAIL"))
	private void nali_extra_removePlayerFromTrackersT(EntityPlayerMP player, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "sendLeashedEntitiesInChunk", at = @At(value = "HEAD"))
	private void nali_extra_sendLeashedEntitiesInChunkH(EntityPlayerMP player, Chunk chunkIn, CallbackInfo ci)
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

	@Inject(method = "sendLeashedEntitiesInChunk", at = @At(value = "TAIL"))
	private void nali_extra_sendLeashedEntitiesInChunkT(EntityPlayerMP player, Chunk chunkIn, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "setViewDistance", at = @At(value = "HEAD"))
	private void nali_extra_setViewDistanceH(int distance, CallbackInfo ci)
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

	@Inject(method = "setViewDistance", at = @At(value = "TAIL"))
	private void nali_extra_setViewDistanceT(int distance, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "sendToTracking", at = @At(value = "HEAD"))
	private void nali_extra_sendToTrackingH(Entity entityIn, Packet<?> packetIn, CallbackInfo ci)
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

	@Inject(method = "sendToTracking", at = @At(value = "TAIL"))
	private void nali_extra_sendToTrackingT(Entity entityIn, Packet<?> packetIn, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "getTrackingPlayers", at = @At(value = "HEAD"), remap = false)
	private void nali_extra_getTrackingPlayersH(Entity entity, CallbackInfoReturnable<Set<? extends EntityPlayer>> cir)
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

	@Inject(method = "getTrackingPlayers", at = @At(value = "TAIL"), remap = false)
	private void nali_extra_getTrackingPlayersT(Entity entity, CallbackInfoReturnable<Set<? extends EntityPlayer>> cir)
	{
		this.state &= 255 - B_LOCK;
	}

	@Inject(method = "sendToTrackingAndSelf", at = @At(value = "HEAD"))
	private void nali_extra_sendToTrackingAndSelfH(Entity entityIn, Packet<?> packetIn, CallbackInfo ci)
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

	@Inject(method = "sendToTrackingAndSelf", at = @At(value = "TAIL"))
	private void nali_extra_sendToTrackingAndSelfT(Entity entityIn, Packet<?> packetIn, CallbackInfo ci)
	{
		this.state &= 255 - B_LOCK;
	}
}
