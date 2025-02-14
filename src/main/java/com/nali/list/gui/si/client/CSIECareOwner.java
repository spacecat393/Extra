package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIECareOwner;
import com.nali.list.network.message.ClientMessage;

public class CSIECareOwner
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIECareOwner.ST & PageSIECareOwner.B_LOCK_DRAW) == 0)
		{
			PageSIECareOwner.ST |= PageSIECareOwner.B_LOCK_DRAW;
			PageSIECareOwner.FLAG = clientmessage.data[2];
			PageSIECareOwner.ST |= PageSIECareOwner.B_DRAW;
		}
	}
}
