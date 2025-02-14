package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.inv.PageInv;
import com.nali.list.network.message.ClientMessage;

public class CDaInv
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageInv.ST & PageInv.B_LOCK_DRAW) == 0)
		{
			PageInv.ST |= PageInv.B_LOCK_DRAW;
			PageInv.BYTE_ARRAY = clientmessage.data;
			PageInv.ST |= PageInv.B_DRAW;
		}
	}
}
