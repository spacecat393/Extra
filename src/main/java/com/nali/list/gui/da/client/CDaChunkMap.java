package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.chunk.PageMap;
import com.nali.list.network.message.ClientMessage;

public class CDaChunkMap
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageMap.ST & PageMap.B_LOCK_DRAW) == 0)
		{
			PageMap.ST |= PageMap.B_LOCK_DRAW;
			PageMap.BYTE_ARRAY = clientmessage.data;
			PageMap.ST |= PageMap.B_DRAW;
		}
	}
}
