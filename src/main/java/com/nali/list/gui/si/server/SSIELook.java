package com.nali.list.gui.si.server;

import com.nali.list.entity.si.SIELook;
import com.nali.list.gui.si.client.CSIELook;
import com.nali.list.network.message.ClientMessage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.client.CPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.EntityLe;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class SSIELook
{
	public static byte ID;

	public final static byte B_FETCH_E = 0;
	public final static byte B_SET_YAW = 1;
	public final static byte B_SET_PITCH = 2;
	public final static byte B_FETCH_LE = 0;
	public final static byte B_SET_YAW_BODY = 4;

	public static void run(EntityPlayerMP entityplayermp, ServerMessage servermessage)
	{
//		ServerE s = ServerE.S_MAP.get(ByteReader.getLong(servermessage.data, 3));
		MixSIE ms = MixSIE.MS_MAP.get(ByteReader.getLong(servermessage.data, 3));
		if (ms.getSI(entityplayermp, SIELook.ID) != null)
		{
			Entity e = ms.s.i.getE();
	//		SIELook sielook = (SIELook)s.ms.si_map.get(SIELook.ID);
			switch (servermessage.data[2])
			{
				case B_SET_YAW:
					e.rotationYaw = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH_E;
					break;
				case B_SET_PITCH:
					e.rotationPitch = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH_E;
					break;
				case B_SET_YAW_BODY:
					EntityLe le = (EntityLe)e;
					le.rotation_yaw_head = ByteReader.getFloat(servermessage.data, 3+8);
					servermessage.data[2] = B_FETCH_LE;
			}

			if (servermessage.data[2] == B_FETCH_E)
			{
				byte[] byte_array = new byte[1 + 1 + 4 + 4];
				sendNet(byte_array, entityplayermp, e);
			}
			else if (servermessage.data[2] == B_FETCH_LE)
			{
				byte[] byte_array = new byte[1 + 1 + 4 + 4 + 4];
				EntityLe le = (EntityLe)e;
				ByteWriter.set(byte_array, le.rotation_yaw_head, 2+4+4);
				sendNet(byte_array, entityplayermp, e);
			}
		}
	}

	public static void sendNet(byte[] byte_array, EntityPlayerMP entityplayermp, Entity e)
	{
		byte_array[0] = CPageSI.ID;
		byte_array[1] = CSIELook.ID;
		ByteWriter.set(byte_array, e.rotationYaw, 2);
		ByteWriter.set(byte_array, e.rotationPitch, 2+4);
		NetworkRegistry.I.sendTo(new ClientMessage(byte_array), entityplayermp);
	}
}
