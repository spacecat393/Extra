package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.inv.select.PageSelect;
import com.nali.list.network.message.ClientMessage;

public class CDaInvSelect
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSelect.STATE & 2) == 0)
		{
			PageSelect.STATE |= 2;
			PageSelect.BYTE_ARRAY = clientmessage.data;
			PageSelect.STATE |= 4;
		}
	}
}
