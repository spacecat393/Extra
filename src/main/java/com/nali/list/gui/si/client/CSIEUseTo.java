package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEUseTo;
import com.nali.list.network.message.ClientMessage;

public class CSIEUseTo
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEUseTo.ST & PageSIEUseTo.B_LOCK_DRAW) == 0)
		{
			PageSIEUseTo.ST |= PageSIEUseTo.B_LOCK_DRAW;
			PageSIEUseTo.FLAG = clientmessage.data[2];
			PageSIEUseTo.ST |= PageSIEUseTo.B_DRAW;
		}
	}
}
