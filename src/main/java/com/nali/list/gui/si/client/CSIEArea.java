package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEArea;
import com.nali.list.network.message.ClientMessage;

public class CSIEArea
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEArea.ST & PageSIEArea.B_LOCK_DRAW) == 0)
		{
			PageSIEArea.ST |= PageSIEArea.B_LOCK_DRAW;
			PageSIEArea.FLAG = clientmessage.data[2];
//			PageSIEArea.BYTE_ARRAY = clientmessage.data;
			PageSIEArea.ST |= PageSIEArea.B_DRAW;
		}
	}
}
