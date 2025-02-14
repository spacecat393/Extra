package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIESit;
import com.nali.list.network.message.ClientMessage;

public class CSIESit
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIESit.ST & PageSIESit.B_LOCK_DRAW) == 0)
		{
			PageSIESit.ST |= PageSIESit.B_LOCK_DRAW;
			PageSIESit.FLAG = clientmessage.data[2];
			PageSIESit.ST |= PageSIESit.B_DRAW;
		}
	}
}
