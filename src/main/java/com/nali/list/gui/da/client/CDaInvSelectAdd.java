package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.inv.select.PageAdd;
import com.nali.list.network.message.ClientMessage;

public class CDaInvSelectAdd
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageAdd.ST & PageAdd.B_LOCK_DRAW) == 0)
		{
			PageAdd.ST |= PageAdd.B_LOCK_DRAW;
			PageAdd.ST |= PageAdd.B_DRAW;
		}
	}
}
