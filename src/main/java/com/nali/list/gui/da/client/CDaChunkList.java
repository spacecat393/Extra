package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.chunk.PageList;
import com.nali.list.network.message.ClientMessage;

public class CDaChunkList
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageList.STATE & 2) == 0)
		{
			PageList.STATE |= 2;
			PageList.BYTE_ARRAY = clientmessage.data;
			PageList.STATE |= 4;
		}
	}
}
