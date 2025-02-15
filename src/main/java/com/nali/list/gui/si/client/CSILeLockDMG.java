package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSILeLockDMG;
import com.nali.list.network.message.ClientMessage;

public class CSILeLockDMG
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSILeLockDMG.ST & PageSILeLockDMG.B_LOCK_DRAW) == 0)
		{
			PageSILeLockDMG.ST |= PageSILeLockDMG.B_LOCK_DRAW;
			PageSILeLockDMG.FLAG = clientmessage.data[2];
			PageSILeLockDMG.ST |= PageSILeLockDMG.B_DRAW;
		}
	}
}
