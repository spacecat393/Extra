package com.nali.list.gui.da.client;

import com.nali.extra.gui.page.inv.select.item.PageItem;
import com.nali.list.network.message.ClientMessage;
import com.nali.system.bytes.ByteReader;

public class CDaInvSelectItem
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageItem.ST & PageItem.B_LOCK_DRAW) == 0)
		{
//			PageItem.STATE |= 2;
//			PageItem.BYTE_ARRAY = clientmessage.data;
			PageItem.ITEM_SIZE = ByteReader.getLong(clientmessage.data, 2);
			PageItem.ST |= PageItem.B_DRAW;
		}
	}
}
