package com.nali.extra.mixin;

import com.nali.extra.ExtraConfig;
import com.nali.extra.ExtraThread;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
	private static int WORLD_ID;

	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;applyServerIconToResponse(Lnet/minecraft/network/ServerStatusResponse;)V", shift = At.Shift.AFTER))
	private void nali_extra_run(CallbackInfo ci)
	{
		//remove CreativeTabs
		CreativeTabs.CREATIVE_TAB_ARRAY = null;

		//force multi thread
		while (ExtraThread.THREAD != 0)
		{
		}
		int d_size = DimensionManager.getRegisteredDimensions().size();
		ExtraThread.TICK_WORLDSERVER_ARRAY = new WorldServer[d_size];
		ExtraThread.ENTITY_WORLDSERVER_ARRAY = new WorldServer[d_size];
		ExtraThread.THREAD |= ExtraThread.TICKWORLDS | ExtraThread.UPDATEENTITIES;
//		ExtraThread.THREAD |= ExtraThread.TICKPLAYER | ExtraThread.UPDATEENTITIES;
//		new Thread(ExtraThread::tickPlayer).start();
		for (int i = 0; i < d_size; ++i)
		{
			final int id = i;
			if (!ExtraConfig.DEBUG_THREAD)
			{
				new Thread(() -> ExtraThread.tickWorlds(id)).start();
				new Thread(() -> ExtraThread.tickEntities(id)).start();
			}
		}
	}

	//clean gui
	@Redirect(method = "convertMapIfNeeded", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setUserMessage(Ljava/lang/String;)V"))
	public void nali_extra_convertMapIfNeeded(MinecraftServer instance, String message)
	{
	}

	@Redirect(method = "loadAllWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setUserMessage(Ljava/lang/String;)V"))
	public void nali_extra_loadAllWorlds(MinecraftServer instance, String message)
	{
	}

	@Redirect(method = "initialWorldChunkLoad", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setUserMessage(Ljava/lang/String;)V"))
	public void nali_extra_initialWorldChunkLoad(MinecraftServer instance, String message)
	{

	}
	//force multi thread
	@Inject(method = "run", at = @At(value = "TAIL"))
	private void nali_extra_runT(CallbackInfo ci)
	{
//		ExtraThread.THREAD &= 255 - (ExtraThread.TICKPLAYER + ExtraThread.UPDATEENTITIES);
		ExtraThread.THREAD &= 255 - (ExtraThread.TICKWORLDS + ExtraThread.UPDATEENTITIES);
	}

	//force multi thread
	@Inject(method = "updateTimeLightAndEntities", at = @At(value = "HEAD"))
	private void nali_extra_updateTimeLightAndEntitiesH(CallbackInfo ci)
	{
		WORLD_ID = 0;
	}

	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;tick()V"))
	private void nali_extra_updateTimeLightAndEntitiesTick(WorldServer instance)
	{
		ExtraThread.TICK_WORLDSERVER_ARRAY[WORLD_ID] = instance;
		if (ExtraConfig.DEBUG_THREAD)
		{
			instance.tick();
		}
	}

	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;updateEntities()V"))
	private void nali_extra_updateTimeLightAndEntitiesLightE(WorldServer instance)
	{
//		if (ExtraThread.WORLD_ID == 0)
//		{
//			ExtraThread.WORLDSERVER = instance;
//		}
//		else
//		{
//			instance.updateEntities();
//		}
		ExtraThread.ENTITY_WORLDSERVER_ARRAY[WORLD_ID] = instance;
		if (ExtraConfig.DEBUG_THREAD)
		{
			instance.updateEntities();
		}
	}

	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J", ordinal = 1))
	private long nali_extra_updateTimeLightAndEntities()
	{
		++WORLD_ID;
		return System.nanoTime();
	}

//	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/management/PlayerList;onTick()V"))
//	private void nali_extra_updateTimeLightAndEntities(PlayerList instance)
//	{
//		ExtraThread.PLAYERLIST = instance;
//	}

//	private static byte TICK = 0;

//	//add cloud
//	@Inject(method = "tick", at = @At("HEAD"))
//	public void nali_extra_tick(CallbackInfo ci)
//	{
//		if ((this.tickCounter % 20) == 0)
//		{
////			if (TICK-- == 0)
////			{
//			ExtraCloud.X = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
////			ExtraCloud.Y = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
//			ExtraCloud.Z = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
//
//////				else
//////				{
//////					ExtraCloud.STATE |= ExtraCloud.B_UPDATE;
//////				}
////				TICK = (byte)this.random.nextInt(100);
////			}
//
//			ExtraCloud.STATE ^= ExtraCloud.B_UPDATE;
//
//			if (ExtraCloud.X == 0/* && ExtraCloud.Y == 0*/ && ExtraCloud.Z == 0)
//			{
//				ExtraCloud.STATE &= 255 - ExtraCloud.B_UPDATE;
//			}
//		}
//	}
}
