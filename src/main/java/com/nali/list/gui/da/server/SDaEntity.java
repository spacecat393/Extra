package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaEntity;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.ArrayList;
import java.util.List;

public class SDaEntity
{
	public static byte ID;

	public final static byte MAX_SIZE = 119;
	public final static byte B_MORE = 0;
	public final static byte B_LESS = 1;
	public final static byte B_FETCH = 2;
	public final static byte B_RENAME = 3;

	//stop on array
	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		int page = ByteReader.getInt(servermessage.data, 3);
//		int s_map_size = ServerE.S_MAP.size();
		int ms_map_size = MixSIE.MS_MAP.size();

		switch (servermessage.data[2])
		{
			case B_MORE:
				if (((page + 1) * MAX_SIZE) < ms_map_size)
				{
					++page;
					servermessage.data[2] = B_FETCH;
				}
				break;
			case B_LESS:
				int new_page = page - 1;
				if (new_page != -1)
				{
					if ((new_page * MAX_SIZE) < ms_map_size)
					{
						--page;
						servermessage.data[2] = B_FETCH;
					}
				}
				break;
			case B_RENAME:
				long key = ByteReader.getLong(servermessage.data, 3+4);
				String name = new String(servermessage.data, 3+4+8, servermessage.data.length - (3+4+8));
//				ServerE.S_MAP.get(key).i.getE().setCustomNameTag(name);
				MixSIE.MS_MAP.get(key).s.i.getE().setCustomNameTag(name);
				servermessage.data[2] = B_FETCH;
		}

		if (servermessage.data[2] == B_FETCH)
		{
			int max_mix_page = (int)Math.ceil(ms_map_size / (float)MAX_SIZE);
			byte max_page;

			if (max_mix_page > 0)
			{
				max_mix_page -= 1;
			}

			if (page == max_mix_page)
			{
				byte left = (byte)(ms_map_size % MAX_SIZE);
				if (left == 0 && ms_map_size > 0)
				{
					max_page = MAX_SIZE;
				}
				else
				{
					max_page = left;
				}
			}
			else
			{
				max_page = MAX_SIZE;
			}

			short byte_array_index = 2;
			int new_page = page * MAX_SIZE;
//			ArrayList<Long> id_long_arraylist = new ArrayList(ServerE.S_MAP.keySet());
			ArrayList<Long> id_long_arraylist = new ArrayList(MixSIE.MS_MAP.keySet());
			List<byte[]> name_byte_array_list = new ArrayList();
			int i_page = new_page + max_page;
			int name_max_size = 0;
			for (int i = new_page; i < i_page; ++i)
			{
				long id = id_long_arraylist.get(i);
//				ServerE s = ServerE.S_MAP.get(id);
				ServerE s = MixSIE.MS_MAP.get(id).s;
				Entity e = s.i.getE();
				byte[] name_byte_array = e.getName().getBytes();
				name_byte_array_list.add(name_byte_array);
				name_max_size += name_byte_array.length;
			}
			byte[] byte_array = new byte[1 + 1 + max_page * (8 + 4) + name_max_size + 4 + 1 + 4];
			byte_array[0] = CPageDa.ID;
			byte_array[1] = CDaEntity.ID;
			byte name_index = 0;
			for (int i = new_page; i < i_page; ++i)
			{
				long id = id_long_arraylist.get(i);
//				ServerE s = ServerE.S_MAP.get(id);
//				Entity e = s.i.getE();
				ByteWriter.set(byte_array, id, byte_array_index);
				byte_array_index += 8;
				byte[] name_byte_array = name_byte_array_list.get(name_index++);
				ByteWriter.set(byte_array, name_byte_array.length, byte_array_index);
				byte_array_index += 4;
				//data here
				System.arraycopy(name_byte_array, 0, byte_array, byte_array_index, name_byte_array.length);
				byte_array_index += name_byte_array.length;
			}
			ByteWriter.set(byte_array, page, byte_array_index);
			byte_array_index += 4;
			byte_array[byte_array_index++] = max_page;//max_page
			ByteWriter.set(byte_array, max_mix_page, byte_array_index);
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
