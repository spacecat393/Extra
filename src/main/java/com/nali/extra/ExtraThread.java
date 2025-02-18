//package com.nali.extra;
//
//import com.nali.Nali;
//import com.nali.extra.mixin.IMixinMinecraftServer;
//import net.minecraft.network.play.server.SPacketTimeUpdate;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.world.WorldServer;
//
//public class ExtraThread
//{
////	public final static byte TICKPLAYER = 1;
//	public final static byte TICKWORLDS = 1;
////	public final static byte UPDATEENTITIES = 2;
//	public static byte THREAD;
//
////	//tickPlayer
////	public static PlayerList PLAYERLIST;
//
////	//tickWorlds
////	public static WorldServer[] TICK_WORLDSERVER_ARRAY;
////	//updateEntities
////	public static WorldServer[] ENTITY_WORLDSERVER_ARRAY;
//	public static MinecraftServer[] MINECRAFTSERVER_ARRAY;
//
////	//n
////	public static void tickPlayer()
////	{
////		long time = MinecraftServer.getCurrentTimeMillis();
////		long i = 0L;
////		while ((THREAD & TICKPLAYER) == TICKPLAYER)
////		{
////			long k = MinecraftServer.getCurrentTimeMillis();
////			long j = k - time;
////			i += j;
////			time = k;
////
////			if (i > 50L)
////			{
////				i -= 50L;
////				if (PLAYERLIST != null)
////				{
//////					Nali.warn("tick");
////					PLAYERLIST.onTick();
////					PLAYERLIST = null;
////				}
////			}
////
////			try
////			{
////				Thread.sleep(Math.max(1L, 50L - i));
////			}
////			catch (InterruptedException e)
////			{
////				Nali.error(e);
////			}
////		}
////	}
//
////	//s
////	public static void tickEntities(int id)
////	{
////		long time = MinecraftServer.getCurrentTimeMillis();
////		long i = 0L;
////		while ((THREAD & UPDATEENTITIES) == UPDATEENTITIES)
////		{
////			long k = MinecraftServer.getCurrentTimeMillis();
////			long j = k - time;
////			i += j;
////			time = k;
////
////			if (i > 50L)
////			{
////				i -= 50L;
////				if (ENTITY_WORLDSERVER_ARRAY[id] != null)
////				{
//////					Nali.warn("tick");
////					try
////					{
////						ENTITY_WORLDSERVER_ARRAY[id].updateEntities();
////					}
////					catch (Exception e)
////					{
////						Nali.warn(e);
////					}
////					ENTITY_WORLDSERVER_ARRAY[id] = null;
////				}
////			}
////
////			try
////			{
////				Thread.sleep(Math.max(1L, 50L - i));
////			}
////			catch (InterruptedException e)
////			{
////				Nali.warn(e);
////			}
////		}
////	}
////
////	//s
////	public static void tickWorlds(int id)
////	{
////		long time = MinecraftServer.getCurrentTimeMillis();
////		long i = 0L;
////		while ((THREAD & TICKWORLDS) == TICKWORLDS)
////		{
////			long k = MinecraftServer.getCurrentTimeMillis();
////			long j = k - time;
////			i += j;
////			time = k;
////
////			if (i > 50L)
////			{
////				i -= 50L;
////				if (TICK_WORLDSERVER_ARRAY[id] != null)
////				{
//////					Nali.warn("tick");
////					try
////					{
////						TICK_WORLDSERVER_ARRAY[id].tick();
////					}
////					catch (Exception e)
////					{
////						Nali.warn(e);
////					}
////					TICK_WORLDSERVER_ARRAY[id] = null;
////				}
////			}
////
////			try
////			{
////				Thread.sleep(Math.max(1L, 50L - i));
////			}
////			catch (InterruptedException e)
////			{
////				Nali.warn(e);
////			}
////		}
////	}
//
//	public static Integer[] INTEGER_ARRAY;
//	public static void test(int id)
//	{
//		long time = MinecraftServer.getCurrentTimeMillis();
//		long i = 0L;
//		while ((THREAD & TICKWORLDS) == TICKWORLDS)
//		{
//			long k = MinecraftServer.getCurrentTimeMillis();
//			long j = k - time;
//			i += j;
//			time = k;
//
//			if (i > 50L)
//			{
//				i -= 50L;
//				if (MINECRAFTSERVER_ARRAY[id] != null)
//				{
//					try
//					{
//						long ii = System.nanoTime();
//						int ID = INTEGER_ARRAY[id];
//						MinecraftServer minecraftserver = MINECRAFTSERVER_ARRAY[id];
//						IMixinMinecraftServer imixinminecraftserver = (IMixinMinecraftServer)minecraftserver;
//						if (ID == 0 || minecraftserver.getAllowNether())
//						{
//							WorldServer worldserver = net.minecraftforge.common.DimensionManager.getWorld(ID);
//							minecraftserver.profiler.func_194340_a(() ->
//							{
//								return worldserver.getWorldInfo().getWorldName();
//							});
//
//							if (imixinminecraftserver.tickCounter() % 20 == 0)
//							{
//								minecraftserver.profiler.startSection("timeSync");
//								imixinminecraftserver.playerList().sendPacketToAllPlayersInDimension(new SPacketTimeUpdate(worldserver.getTotalWorldTime(), worldserver.getWorldTime(), worldserver.getGameRules().getBoolean("doDaylightCycle")), worldserver.provider.getDimension());
//								minecraftserver.profiler.endSection();
//							}
//
//							minecraftserver.profiler.startSection("tick");
//							net.minecraftforge.fml.common.FMLCommonHandler.instance().onPreWorldTick(worldserver);
//
//							try
//							{
//								worldserver.tick();
//							}
//							catch (Throwable throwable1)
//							{
//								Nali.warn(throwable1);
//							}
//
//							try
//							{
//								worldserver.updateEntities();
//							}
//							catch (Throwable throwable)
//							{
//								Nali.warn(throwable);
//							}
//
//							net.minecraftforge.fml.common.FMLCommonHandler.instance().onPostWorldTick(worldserver);
//							minecraftserver.profiler.endSection();
//							minecraftserver.profiler.startSection("tracker");
//							worldserver.getEntityTracker().tick();
//							minecraftserver.profiler.endSection();
//							minecraftserver.profiler.endSection();
//						}
//						minecraftserver.worldTickTimes.get(id)[imixinminecraftserver.tickCounter() % 100] = System.nanoTime() - ii;
//					}
//					catch (Exception e)
//					{
//						Nali.warn(e);
//					}
//					MINECRAFTSERVER_ARRAY[id] = null;
//				}
//			}
//
//			try
//			{
//				Thread.sleep(Math.max(1L, 50L - i));
//			}
//			catch (InterruptedException e)
//			{
//				Nali.warn(e);
//			}
//		}
//	}
//}
