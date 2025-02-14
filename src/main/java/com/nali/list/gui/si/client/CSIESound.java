package com.nali.list.gui.si.client;

import com.nali.extra.gui.page.entity.si.PageSIESound;
import com.nali.list.network.message.ClientMessage;

public class CSIESound
{
	public static byte ID;

	public static void run(ClientMessage clientmessage)
	{
		if ((PageSIESound.ST & PageSIESound.B_LOCK_DRAW) == 0)
		{
			PageSIESound.ST |= PageSIESound.B_LOCK_DRAW;
			PageSIESound.FLAG = clientmessage.data[2];
			PageSIESound.ST |= PageSIESound.B_DRAW;
		}
	}
}
