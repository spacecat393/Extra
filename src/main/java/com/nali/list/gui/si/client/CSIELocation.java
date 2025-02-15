package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIELocation;
import com.nali.list.network.message.ClientMessage;
import com.nali.system.bytes.ByteReader;

public class CSIELocation
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIELocation.ST & PageSIELocation.B_LOCK_DRAW) == 0)
		{
			PageSIELocation.ST |= PageSIELocation.B_LOCK_DRAW;
			PageSIELocation.FLAG = clientmessage.data[2];
			PageSIELocation.X = ByteReader.getFloat(clientmessage.data, 2+1);
			PageSIELocation.Y = ByteReader.getFloat(clientmessage.data, 2+1+4);
			PageSIELocation.Z = ByteReader.getFloat(clientmessage.data, 2+1+4+4);
			PageSIELocation.FAR = ByteReader.getFloat(clientmessage.data, 2+1+4+4+4);
			PageSIELocation.ST |= PageSIELocation.B_DRAW;
		}
	}
}
