package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEWalkTo;
import com.nali.list.network.message.ClientMessage;

public class CSIEWalkTo
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEWalkTo.ST & PageSIEWalkTo.B_LOCK_DRAW) == 0)
		{
			PageSIEWalkTo.ST |= PageSIEWalkTo.B_LOCK_DRAW;
			PageSIEWalkTo.FLAG = clientmessage.data[2];
			PageSIEWalkTo.ST |= PageSIEWalkTo.B_DRAW;
		}
	}
}
