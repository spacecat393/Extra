package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIELock;
import com.nali.list.network.message.ClientMessage;

public class CSIELock
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIELock.ST & PageSIELock.B_LOCK_DRAW) == 0)
		{
			PageSIELock.ST |= PageSIELock.B_LOCK_DRAW;
			PageSIELock.FLAG = clientmessage.data[2];
			PageSIELock.ST |= PageSIELock.B_DRAW;
		}
	}
}
