package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIERandomLook;
import com.nali.list.network.message.ClientMessage;

public class CSIERandomLook
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIERandomLook.ST & PageSIERandomLook.B_LOCK_DRAW) == 0)
		{
			PageSIERandomLook.ST |= PageSIERandomLook.B_LOCK_DRAW;
			PageSIERandomLook.FLAG = clientmessage.data[2];
			PageSIERandomLook.ST |= PageSIERandomLook.B_DRAW;
		}
	}
}
