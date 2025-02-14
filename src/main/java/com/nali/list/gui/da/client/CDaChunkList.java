package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.chunk.PageList;
import com.nali.list.network.message.ClientMessage;

public class CDaChunkList
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageList.ST & PageList.B_LOCK_DRAW) == 0)
		{
			PageList.ST |= PageList.B_LOCK_DRAW;
			PageList.BYTE_ARRAY = clientmessage.data;
			PageList.ST |= PageList.B_DRAW;
		}
	}
}
