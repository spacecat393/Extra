package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEFollow;
import com.nali.list.network.message.ClientMessage;

public class CSIEFollow
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEFollow.ST & PageSIEFollow.B_LOCK_DRAW) == 0)
		{
			PageSIEFollow.ST |= PageSIEFollow.B_LOCK_DRAW;
			PageSIEFollow.FLAG = clientmessage.data[2];
			PageSIEFollow.ST |= PageSIEFollow.B_DRAW;
		}
	}
}
