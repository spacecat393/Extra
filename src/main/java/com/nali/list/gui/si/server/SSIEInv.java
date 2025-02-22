package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIEInv;
import com.nali.list.gui.si.client.CSIEInv;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;

public class SSIEInv
{
	public static byte ID;

	public final static byte MAX_SIZE = 118;

	public final static byte B_MORE = 0;
	public final static byte B_LESS = 1;
	public final static byte B_FETCH = 2;
	public final static byte B_DELETE = 3;
//	public final static byte B_ADD = 4;
//	public final static byte B_MOVE = 5;
	public final static byte B_CHECK = 4;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		int page = ByteReader.getInt(servermessage.data, 3);
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 3+4));
		if (ms != null)
		{
			SIEInv sieinv = (SIEInv)ms.getSI(entityplayermp, SIEInv.ID);
			if (sieinv != null/* && (sieinv.state & SIEInv.B_LOCK) == 0*/)
			{
	//			sieinv.state |= SIEInv.B_LOCK;
				int e_inv_size = sieinv.invSize();
	//			for (int i = 0; i < sieinv.invSize(); ++i)
	//			{
	//				if (!sieinv.invGet(i).isEmpty())
	//				{
	//					++e_inv_size;
	//				}
	//			}

				switch (servermessage.data[2])
				{
					case B_MORE:
						if (((page + 1) * MAX_SIZE) < e_inv_size)
						{
							++page;
							servermessage.data[2] = B_FETCH;
						}
						break;
					case B_LESS:
						int new_page = page - 1;
						if (new_page != -1)
						{
							if ((new_page * MAX_SIZE) < e_inv_size)
							{
								--page;
								servermessage.data[2] = B_FETCH;
							}
						}
						break;
					case B_DELETE://need thread
						if (sieinv.net == -1)
						{
							sieinv.net = -2;
							sieinv.entityplayermp = entityplayermp;
							sieinv.net = SIEInv.N_DELETE;
						}
						break;
	//				case B_ADD://need thread
	//					if (sieinv.net == -1)
	//					{
	//						sieinv.net = SIEInv.N_ADD;
	//						sieinv.entityplayermp = entityplayermp;
	//					}
	//					break;
	//				case B_MOVE://need thread
	//					if (sieinv.net == -1)
	//					{
	//						sieinv.net = SIEInv.N_MOVE;
	//						sieinv.net_slot = servermessage.data[3+4+8] + page * MAX_SIZE;
	//						sieinv.entityplayermp = entityplayermp;
	//					}
					case B_CHECK:
						if (sieinv.net == -1)
						{
							sieinv.net = -2;
							sieinv.net_slot = servermessage.data[3+4+8] + page * MAX_SIZE;
							sieinv.entityplayermp = entityplayermp;
							sieinv.net = SIEInv.N_CHECK;
						}
				}

				if (servermessage.data[2] == B_FETCH)
				{
					int max_mix_page = (int)Math.ceil(e_inv_size / (float)MAX_SIZE);
					byte max_page;

					if (max_mix_page > 0)
					{
						max_mix_page -= 1;
					}

					if (page == max_mix_page)
					{
						byte left = (byte)(e_inv_size % MAX_SIZE);
						if (left == 0 && e_inv_size > 0)
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

					byte[] byte_array = new byte[1 + 1 + max_page * 4 + 4 + 1 + 4];
					byte_array[0] = CPageSI.ID;
					byte_array[1] = CSIEInv.ID;
					short byte_array_index = 2;
					int new_page = page * MAX_SIZE;
					for (int i = new_page; i < new_page + max_page; ++i)
					{
						ByteWriter.set(byte_array, Item.getIdFromItem(sieinv.invGet(i).getItem()), byte_array_index);
						byte_array_index += 4;
					}
					ByteWriter.set(byte_array, page, byte_array_index);
					byte_array_index += 4;
					byte_array[byte_array_index++] = max_page;
					ByteWriter.set(byte_array, max_mix_page, byte_array_index);
					NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
				}
	//			sieinv.state &= 255 - SIEInv.B_LOCK;
			}
		}
	}
}
