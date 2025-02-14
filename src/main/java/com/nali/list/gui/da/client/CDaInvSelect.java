package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.inv.select.PageSelect;
import com.nali.list.network.message.ClientMessage;

public class CDaInvSelect
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSelect.ST & PageSelect.B_LOCK_DRAW) == 0)
		{
			PageSelect.ST |= PageSelect.B_LOCK_DRAW;
			PageSelect.BYTE_ARRAY = clientmessage.data;
			PageSelect.ST |= PageSelect.B_DRAW;
		}
	}
}
