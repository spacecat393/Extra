package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.Page;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.si.server.SSIELook;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIELook extends PageEdit
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte ST;
	public static float
		YAW,
		PITCH;

	@Override
	public void init()
	{
		super.init();
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SIE-LOOK".toCharArray()),
			new BoxTextAll("DATA".toCharArray()),
			new BoxTextAll(this.getChar("YAW " + YAW)),
			new BoxTextAll(this.getChar("PITCH " + PITCH)),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("FETCH".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[3 / 8] |= 1 << 3 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 5;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		try
		{
			switch (this.select)
			{
				case 2:
					sendYaw();
					break;
				case 3:
					sendPitch();
					break;
				case 5:
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
					sendNet(SSIELook.B_FETCH_E);
					break;
				case 6:
					this.back();
			}
		}
		catch (Exception e)
		{
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

	public static void sendNet(byte b2)
	{
		PageExtra.NET_BYTE_ARRAY[0] = SPageSI.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SSIELook.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}

	public static void sendYaw()
	{
		PageEdit pageedit = (PageEdit)Page.PAGE;
		if ((pageedit.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
			ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(pageedit.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
			sendNet(SSIELook.B_SET_YAW);
		}
		else
		{
			pageedit.input_stringbuilder.setLength(0);
			pageedit.input_stringbuilder.append(YAW);
			pageedit.select_box = pageedit.input_stringbuilder.length();
		}
		pageedit.fl ^= BF_ENTER_MODE;
		pageedit.scroll = 0;
	}

	public static void sendPitch()
	{
		PageEdit pageedit = (PageEdit)Page.PAGE;
		if ((pageedit.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
		{
			PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
			ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(pageedit.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
			sendNet(SSIELook.B_SET_PITCH);
		}
		else
		{
			pageedit.input_stringbuilder.setLength(0);
			pageedit.input_stringbuilder.append(PITCH);
			pageedit.select_box = pageedit.input_stringbuilder.length();
		}
		pageedit.fl ^= BF_ENTER_MODE;
		pageedit.scroll = 0;
	}
}
