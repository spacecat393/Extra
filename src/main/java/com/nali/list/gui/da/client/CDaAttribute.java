package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.entity.me.PageAttribute;
import com.nali.list.network.message.ClientMessage;

public class CDaAttribute
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageAttribute.ST & PageAttribute.B_LOCK_DRAW) == 0)
		{
			PageAttribute.ST |= PageAttribute.B_LOCK_DRAW;
			PageAttribute.BYTE_ARRAY = clientmessage.data;
			PageAttribute.ST |= PageAttribute.B_DRAW;
		}
	}
}
