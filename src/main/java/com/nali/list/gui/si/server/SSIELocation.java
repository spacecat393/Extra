package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIELocation;
import com.nali.list.gui.si.client.CSIELocation;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIELocation
{
	public static byte ID;

	public final static byte B_FETCH = 0;
	public final static byte B_SET = 1;
	public final static byte B_SET_X = 2;
	public final static byte B_SET_Y = 3;
	public final static byte B_SET_Z = 4;
	public final static byte B_SET_FAR = 5;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
//		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 3));
		SIELocation sielocation = (SIELocation)ms.getSI(entityplayermp, SIELocation.ID);
		if (sielocation != null)
		{
			switch (servermessage.data[2])
			{
				case B_SET:
					sielocation.flag ^= servermessage.data[3+8];
					servermessage.data[2] = B_FETCH;
					break;
				case B_SET_X:
					sielocation.x = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH;
					break;
				case B_SET_Y:
					sielocation.y = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH;
					break;
				case B_SET_Z:
					sielocation.z = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH;
					break;
				case B_SET_FAR:
					sielocation.far = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH;
			}

			if (servermessage.data[2] == B_FETCH)
			{
				byte[] byte_array = new byte[1 + 1 + 1 + 4 + 4 + 4 + 4];
				byte_array[0] = CPageSI.ID;
				byte_array[1] = CSIELocation.ID;
				byte_array[2] = sielocation.flag;
				ByteWriter.set(byte_array, sielocation.x, 3);
				ByteWriter.set(byte_array, sielocation.y, 3+4);
				ByteWriter.set(byte_array, sielocation.z, 3+4+4);
				ByteWriter.set(byte_array, sielocation.far, 3+4+4+4);
				NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
			}
		}
	}
}
