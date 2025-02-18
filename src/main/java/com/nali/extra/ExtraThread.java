package com.nali.extra;

import com.nali.Nali;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class ExtraThread
{
//	public final static byte TICKPLAYER = 1;
	public final static byte TICKWORLDS = 1;
	public final static byte UPDATEENTITIES = 2;
	public static byte THREAD;

//	//tickPlayer
//	public static PlayerList PLAYERLIST;

	//tickWorlds
	public static WorldServer[] TICK_WORLDSERVER_ARRAY;
	//updateEntities
	public static WorldServer[] ENTITY_WORLDSERVER_ARRAY;

//	//n
//	public static void tickPlayer()
//	{
//		long time = MinecraftServer.getCurrentTimeMillis();
//		long i = 0L;
//		while ((THREAD & TICKPLAYER) == TICKPLAYER)
//		{
//			long k = MinecraftServer.getCurrentTimeMillis();
//			long j = k - time;
//			i += j;
//			time = k;
//
//			if (i > 50L)
//			{
//				i -= 50L;
//				if (PLAYERLIST != null)
//				{
////					Nali.warn("tick");
//					PLAYERLIST.onTick();
//					PLAYERLIST = null;
//				}
//			}
//
//			try
//			{
//				Thread.sleep(Math.max(1L, 50L - i));
//			}
//			catch (InterruptedException e)
//			{
//				Nali.error(e);
//			}
//		}
//	}

	//s
	public static void tickEntities(int id)
	{
		long time = MinecraftServer.getCurrentTimeMillis();
		long i = 0L;
		while ((THREAD & UPDATEENTITIES) == UPDATEENTITIES)
		{
			long k = MinecraftServer.getCurrentTimeMillis();
			long j = k - time;
			i += j;
			time = k;

			if (i > 50L)
			{
				i -= 50L;
				if (ENTITY_WORLDSERVER_ARRAY[id] != null)
				{
//					Nali.warn("tick");
					try
					{
						ENTITY_WORLDSERVER_ARRAY[id].updateEntities();
					}
					catch (Exception e)
					{
						Nali.warn(e);
					}
					ENTITY_WORLDSERVER_ARRAY[id] = null;
				}
			}

			try
			{
				Thread.sleep(Math.max(1L, 50L - i));
			}
			catch (InterruptedException e)
			{
				Nali.warn(e);
			}
		}
	}

	//s
	public static void tickWorlds(int id)
	{
		long time = MinecraftServer.getCurrentTimeMillis();
		long i = 0L;
		while ((THREAD & TICKWORLDS) == TICKWORLDS)
		{
			long k = MinecraftServer.getCurrentTimeMillis();
			long j = k - time;
			i += j;
			time = k;

			if (i > 50L)
			{
				i -= 50L;
				if (TICK_WORLDSERVER_ARRAY[id] != null)
				{
//					Nali.warn("tick");
					try
					{
						TICK_WORLDSERVER_ARRAY[id].tick();
					}
					catch (Exception e)
					{
						Nali.warn(e);
					}
					TICK_WORLDSERVER_ARRAY[id] = null;
				}
			}

			try
			{
				Thread.sleep(Math.max(1L, 50L - i));
			}
			catch (InterruptedException e)
			{
				Nali.warn(e);
			}
		}
	}
}
