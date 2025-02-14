package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIELock;
import com.nali.list.gui.si.client.CSIELock;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIELock
{
	public static byte ID;

	public final static byte B_FETCH = 0;
	public final static byte B_SET = 1;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
//		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 3));
		SIELock sielock = (SIELock)ms.getSI(entityplayermp, SIELock.ID);
		if (sielock != null)
		{
			if (servermessage.data[2] == B_SET)
			{
				sielock.flag ^= servermessage.data[3+8];
				servermessage.data[2] = B_FETCH;
			}

			if (servermessage.data[2] == B_FETCH)
			{
				byte[] byte_array = new byte[1 + 1 + 1];
				byte_array[0] = CPageSI.ID;
				byte_array[1] = CSIELock.ID;
				byte_array[2] = sielock.flag;
				NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
			}
		}
	}
}
