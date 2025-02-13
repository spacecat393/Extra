package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIEArea;
import com.nali.list.network.message.ClientMessage;

public class CSIEArea
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIEArea.STATE & 2) == 0)
		{
			PageSIEArea.STATE |= 2;
			PageSIEArea.FLAG = clientmessage.data[2];
//			PageSIEArea.BYTE_ARRAY = clientmessage.data;
			PageSIEArea.STATE |= 4;
		}
	}
}
