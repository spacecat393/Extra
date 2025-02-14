package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.entity.me.PageSI;
import com.nali.list.network.message.ClientMessage;

public class CDaSI
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSI.ST & PageSI.B_LOCK_DRAW) == 0)
		{
			PageSI.ST |= PageSI.B_LOCK_DRAW;
			PageSI.BYTE_ARRAY = clientmessage.data;
			PageSI.ST |= PageSI.B_DRAW;
		}
	}
}
