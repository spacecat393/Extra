package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIERandomWalk;
import com.nali.list.network.message.ClientMessage;

public class CSIERandomWalk
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIERandomWalk.ST & PageSIERandomWalk.B_LOCK_DRAW) == 0)
		{
			PageSIERandomWalk.ST |= PageSIERandomWalk.B_LOCK_DRAW;
			PageSIERandomWalk.FLAG = clientmessage.data[2];
			PageSIERandomWalk.ST |= PageSIERandomWalk.B_DRAW;
		}
	}
}
