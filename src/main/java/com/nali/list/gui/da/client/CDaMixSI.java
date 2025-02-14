package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.entity.me.PageMixSI;
import com.nali.list.network.message.ClientMessage;

public class CDaMixSI
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageMixSI.ST & PageMixSI.B_LOCK_DRAW) == 0)
		{
			PageMixSI.ST |= PageMixSI.B_LOCK_DRAW;
			PageMixSI.FLAG = clientmessage.data[2];
			PageMixSI.ST |= PageMixSI.B_DRAW;
		}
	}
}
