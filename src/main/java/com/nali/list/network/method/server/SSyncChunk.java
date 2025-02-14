package com.nali.list.network.method.server;

import com.nali.Nali;
import com.nali.list.network.message.ServerMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChunkData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.chunk.Chunk;

public class SSyncChunk
{
	public static byte ID;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		MinecraftServer minecraftserver = entityplayermp.server;
		minecraftserver.addScheduledTask(() ->
		{
//			Chunk chunk = entityplayermp.world.getChunk(entityplayermp.getPosition());
			int viewdistance = minecraftserver.getPlayerList().getViewDistance();
//			Nali.warn("viewdistance " + viewdistance);

			for (int dx = -viewdistance; dx <= viewdistance; dx++)
			{
				for (int dz = -viewdistance; dz <= viewdistance; dz++)
				{
					try
					{
						int chunk_x = entityplayermp.chunkCoordX + dx;
						int chunk_z = entityplayermp.chunkCoordZ + dz;

						Chunk chunk = entityplayermp.world.getChunk(chunk_x, chunk_z);
						entityplayermp.connection.sendPacket(new SPacketChunkData(chunk, 65535));
					}
					catch (Exception e)
					{
						Nali.warn(e);
					}
				}
			}
		});
	}
}
