package com.nali.extra.mixin;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer
{
//	private static int SIZE;
////	private static Integer[] INTEGER_ARRAY;

	@Inject(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;applyServerIconToResponse(Lnet/minecraft/network/ServerStatusResponse;)V", shift = At.Shift.AFTER))
	private void nali_extra_run(CallbackInfo ci)
	{
		//remove CreativeTabs
		CreativeTabs.CREATIVE_TAB_ARRAY = null;

//		//force multi thread
//		while (ExtraThread.THREAD != 0)
//		{
//		}
//		int d_size = DimensionManager.getRegisteredDimensions().size();
//		SIZE = d_size;
//		ExtraThread.TICK_WORLDSERVER_ARRAY = new WorldServer[d_size];
//		ExtraThread.ENTITY_WORLDSERVER_ARRAY = new WorldServer[d_size];
////		ExtraThread.STOP_THREAD = new byte[(int)Math.ceil(d_size / 8.0F)];
//		ExtraThread.THREAD |= ExtraThread.TICKWORLDS | ExtraThread.UPDATEENTITIES;
////		for (int i = 0; i < d_size; ++i)
//		for (int i = 0; i < 1; ++i)
//		{
//			final int id = i;
//			new Thread(() -> ExtraThread.tickWorlds(id)).start();
//			new Thread(() -> ExtraThread.tickEntities(id)).start();
//		}
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

//	//force multi thread
//	private static int WORLD_ID;
//	@Inject(method = "run", at = @At(value = "TAIL"))
//	private void nali_extra_runT(CallbackInfo ci)
//	{
//		ExtraThread.THREAD &= 255 - (ExtraThread.TICKWORLDS + ExtraThread.UPDATEENTITIES);
//	}
//
//	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/DimensionManager;getIDs(Z)[Ljava/lang/Integer;"))
//	private Integer[] nali_extra_updateTimeLightAndEntitiesH(boolean w)
//	{
//		WORLD_ID = 0;
//		INTEGER_ARRAY = DimensionManager.getIDs(w);
//		SIZE = INTEGER_ARRAY.length;
//		return INTEGER_ARRAY;
//	}
//
//	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;tick()V"))
//	private void nali_extra_updateTimeLightAndEntitiesTick(WorldServer instance)
//	{
//		ExtraThread.TICK_WORLDSERVER_ARRAY[WORLD_ID] = instance;
//	}
//
//	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldServer;updateEntities()V"))
//	private void nali_extra_updateTimeLightAndEntitiesLightE(WorldServer instance)
//	{
//		ExtraThread.ENTITY_WORLDSERVER_ARRAY[WORLD_ID] = instance;
//	}
//
//	@Redirect(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J", ordinal = 1))
//	private long nali_extra_updateTimeLightAndEntities()
//	{
//		++WORLD_ID;
//		return System.nanoTime();
//	}
//
////	@Inject(method = "updateTimeLightAndEntities", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/common/DimensionManager;unloadWorlds(Ljava/util/Hashtable;)V", shift = At.Shift.BEFORE))
////	private void nali_extra_updateTimeLightAndEntities(CallbackInfo ci)
////	{
////		int s = INTEGER_ARRAY.length;
////		int e = ;
////
////		for (int i = 0; i < ExtraThread.TICK_WORLDSERVER_ARRAY.length; ++i)
////		{
////			int index = i / 8;
////			if (SIZE - i != 0)
////			{
////				ExtraThread.STOP_THREAD[i / 8] |= 1;
////			}
////			else
////			{
////				ExtraThread.STOP_THREAD[i / 8] &= 255 - 1;
////			}
////		}
////	}
//
////	private static byte TICK = 0;
////
////	//add cloud
////	@Inject(method = "tick", at = @At("HEAD"))
////	public void nali_extra_tick(CallbackInfo ci)
////	{
////		if ((this.tickCounter % 20) == 0)
////		{
//////			if (TICK-- == 0)
//////			{
////			ExtraCloud.X = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
//////			ExtraCloud.Y = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
////			ExtraCloud.Z = (byte)(this.random.nextInt(2) - this.random.nextInt(2));
////
////////				else
////////				{
////////					ExtraCloud.STATE |= ExtraCloud.B_UPDATE;
////////				}
//////				TICK = (byte)this.random.nextInt(100);
//////			}
////
////			ExtraCloud.STATE ^= ExtraCloud.B_UPDATE;
////
////			if (ExtraCloud.X == 0/* && ExtraCloud.Y == 0*/ && ExtraCloud.Z == 0)
////			{
////				ExtraCloud.STATE &= 255 - ExtraCloud.B_UPDATE;
////			}
////		}
////	}
}
