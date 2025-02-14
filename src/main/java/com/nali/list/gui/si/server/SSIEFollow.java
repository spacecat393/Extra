package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIEFollow;
import com.nali.list.gui.si.client.CSIEFollow;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIEFollow
{
	public static byte ID;

	public final static byte B_FETCH = 0;
	public final static byte B_SET = 1;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
//		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 3));
		SIEFollow siefollow = (SIEFollow)ms.getSI(entityplayermp, SIEFollow.ID);
		if (siefollow != null)
		{
			if (servermessage.data[2] == B_SET)
			{
				siefollow.state ^= servermessage.data[3+8];
				servermessage.data[2] = B_FETCH;
			}

			if (servermessage.data[2] == B_FETCH)
			{
				byte[] byte_array = new byte[1 + 1 + 1];
				byte_array[0] = CPageSI.ID;
				byte_array[1] = CSIEFollow.ID;
				byte_array[2] = siefollow.state;
				NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
			}
		}
	}
}
