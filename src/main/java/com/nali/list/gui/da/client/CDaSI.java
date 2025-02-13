package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.entity.me.PageSI;
import com.nali.list.network.message.ClientMessage;

public class CDaSI
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSI.STATE & 2) == 0)
		{
			PageSI.STATE |= 2;
			PageSI.BYTE_ARRAY = clientmessage.data;
			PageSI.STATE |= 4;
		}
	}
}
