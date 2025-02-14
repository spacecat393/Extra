package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.entity.PageEntity;
import com.nali.list.network.message.ClientMessage;

public class CDaEntity
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageEntity.ST & PageEntity.B_LOCK_DRAW) == 0)
		{
			PageEntity.ST |= PageEntity.B_LOCK_DRAW;
			PageEntity.BYTE_ARRAY = clientmessage.data;
			PageEntity.ST |= PageEntity.B_DRAW;
		}
	}
}
