package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEInv;
import com.nali.list.network.message.ClientMessage;

public class CSIEInv
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEInv.ST & PageSIEInv.B_LOCK_DRAW) == 0)
		{
			PageSIEInv.ST |= PageSIEInv.B_LOCK_DRAW;
			PageSIEInv.BYTE_ARRAY = clientmessage.data;
			PageSIEInv.ST |= PageSIEInv.B_DRAW;
		}
	}
}
