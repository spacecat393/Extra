package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIELookTo;
import com.nali.list.network.message.ClientMessage;

public class CSIELookTo
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIELookTo.ST & PageSIELookTo.B_LOCK_DRAW) == 0)
		{
			PageSIELookTo.ST |= PageSIELookTo.B_LOCK_DRAW;
			PageSIELookTo.FLAG = clientmessage.data[2];
			PageSIELookTo.ST |= PageSIELookTo.B_DRAW;
		}
	}
}
