package com.nali.list.gui.da.server;

import com.nali.list.gui.da.client.CDaMixSI;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.ServerE;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SDaMixSI
{
	public static byte ID;

	public final static byte B_FETCH = 0;
	public final static byte B_SET = 1;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
		MixSIE ms = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3)).ms;
		if (servermessage.data[2] == B_SET)
		{
			ms.flag ^= servermessage.data[3+8];
			servermessage.data[2] = B_FETCH;
		}

		if (servermessage.data[2] == B_FETCH)
		{
			byte[] byte_array = new byte[1 + 1 + 1];
			byte_array[0] = CPageDa.ID;
			byte_array[1] = CDaMixSI.ID;
			byte_array[2] = ms.flag;
			NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
		}
	}
}
