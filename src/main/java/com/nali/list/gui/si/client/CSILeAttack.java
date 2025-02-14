package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSILeAttack;
import com.nali.list.network.message.ClientMessage;

public class CSILeAttack
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSILeAttack.ST & PageSILeAttack.B_LOCK_DRAW) == 0)
		{
			PageSILeAttack.ST |= PageSILeAttack.B_LOCK_DRAW;
			PageSILeAttack.FLAG = clientmessage.data[2];
			PageSILeAttack.ST |= PageSILeAttack.B_DRAW;
		}
	}
}
