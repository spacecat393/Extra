package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.entity.si.SIELock;
import com.nali.list.gui.si.server.SSIELock;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIELock extends PageSelect
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,
		FLAG;

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SIE-LOCK".toCharArray()),
			new BoxTextAll("FLAG".toCharArray()),
			new BoxTextAll(("ON " + (byte)Math.signum(FLAG & SIELock.B_ON)).toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("FETCH".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[2 / 8] |= 1 << 2 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 4;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 4:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
				this.sendNet(SSIELock.B_FETCH);
				break;
			case 5:
				this.back();
				break;
			default:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 1];
//				PageExtra.NET_BYTE_ARRAY[3 + 8] = (byte)(1 << this.select - 2);
				PageExtra.NET_BYTE_ARRAY[3 + 8] = 1;
				this.sendNet(SSIELock.B_SET);
		}
	}

	@Override
	public void draw()
	{
		if ((ST & B_DRAW) == B_DRAW)
		{
			this.fl &= 255 - BF_SET_SELECT;
			this.clear();
			this.init();

			this.gen();
			ST &= 255 - (B_LOCK_DRAW + B_DRAW);
		}
		super.draw();
	}

	public void sendNet(byte b2)
	{
		PageExtra.NET_BYTE_ARRAY[0] = SPageSI.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SSIELock.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
