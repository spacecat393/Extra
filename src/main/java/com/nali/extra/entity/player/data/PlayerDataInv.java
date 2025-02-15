//package com.nali.extra.entity.player.data;
//
//import com.nali.Nali;
//import com.nali.list.gui.da.server.SDaInv;
//import com.nali.system.bytes.ByteReader;
//import com.nali.system.bytes.ByteWriter;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static com.nali.Nali.warn;
//
//public class PlayerDataInv
//{
//	public static Map<UUID, int[]> INV_MAP;
//
//	public static void read(File world_file)
//	{
//		INV_MAP = new HashMap();
//
//		File nali_file = new File(world_file, "nali");
//		nali_file.mkdir();
//		File player_file = new File(nali_file, "player");
//		player_file.mkdir();
//		File inv_file = new File(player_file, "inv");
//		inv_file.mkdir();
//
//		File[] file_array = inv_file.listFiles();
//		if (file_array != null)
//		{
//			for (File f : file_array)
//			{
//				try
//				{
//					UUID player_uuid = UUID.fromString(f.getName());
//					byte[] byte_array = Files.readAllBytes(f.toPath());
//
//					int byte_array_length = byte_array.length;
//					int[] inv_int_array = new int[byte_array_length / 4];
//					int index = 0;
//					while (index < byte_array_length)
//					{
//						inv_int_array[index / 4] = ByteReader.getInt(byte_array, index);
//						index += 4;
//					}
//
//					INV_MAP.put(player_uuid, inv_int_array);
//				}
//				catch (Exception e)
//				{
//					warn(e);
//					f.delete();
//				}
//			}
//		}
//
//		SDaInv.ST &= 255 - SDaInv.BS_LOCK;
//	}
//
//	public static void write(File world_file)
//	{
//		File inv_file = new File(world_file, "nali/player/inv");
//
//		for (File file : inv_file.listFiles())
//		{
//			file.delete();
//		}
//
//		for (UUID uuid : INV_MAP.keySet())
//		{
//			try
//			{
//				int[] inv_int_array = INV_MAP.get(uuid);
//				int inv_int_array_length = inv_int_array.length;
//				byte[] byte_array = new byte[inv_int_array_length * 4];
//				for (int i = 0; i < inv_int_array_length; ++i)
//				{
//					ByteWriter.set(byte_array, inv_int_array[i], i * 4);
//				}
//				Files.write(new File(inv_file, uuid.toString()).toPath(), byte_array);
//			}
//			catch (IOException e)
//			{
//				Nali.error(e);
//			}
//		}
//
//		INV_MAP = null;
//	}
//}
