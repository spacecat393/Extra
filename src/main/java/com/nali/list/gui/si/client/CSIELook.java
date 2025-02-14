package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIELook;
import com.nali.extra.gui.page.entity.si.PageSILeLook;
import com.nali.list.network.message.ClientMessage;
import com.nali.system.bytes.ByteReader;

public class CSIELook
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIELook.ST & PageSIELook.B_LOCK_DRAW) == 0)
		{
			PageSIELook.ST |= PageSIELook.B_LOCK_DRAW;
			PageSIELook.YAW = ByteReader.getFloat(clientmessage.data, 2);
			PageSIELook.PITCH = ByteReader.getFloat(clientmessage.data, 2+4);
			PageSIELook.ST |= PageSIELook.B_DRAW;
		}

		if (clientmessage.data.length == 2+4+4+4)
		{
			if ((PageSILeLook.ST & PageSILeLook.B_LOCK_DRAW) == 0)
			{
				PageSILeLook.ST |= PageSILeLook.B_LOCK_DRAW;
				PageSILeLook.YAW_BODY = ByteReader.getFloat(clientmessage.data, 2+4+4);
				PageSILeLook.ST |= PageSILeLook.B_DRAW;
			}
		}
	}
}
