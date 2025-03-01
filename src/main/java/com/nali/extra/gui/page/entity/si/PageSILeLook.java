package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.si.server.SSIELook;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSILeLook extends PageEdit
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte ST;
	public static float YAW_BODY;

	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"SIE-LOOK".toCharArray(),
			"DATA".toCharArray(),
			this.getChar("YAW " + PageSIELook.YAW),
			this.getChar("PITCH " + PageSIELook.PITCH),
			this.getChar("YAW_BODY " + YAW_BODY),
			"ACTION".toCharArray(),
			"FETCH".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[4 / 8] |= 1 << 4 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 6;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{
		try
		{
			switch (this.select)
			{
				case 2:
					PageSIELook.sendYaw();
					break;
				case 3:
					PageSIELook.sendPitch();
					break;
				case 4:
					if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
					{
						PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
						ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(this.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
						sendNet(SSIELook.B_SET_YAW_BODY);
					}
					else
					{
						this.input_stringbuilder.setLength(0);
						this.input_stringbuilder.append(YAW_BODY);
						this.select_box = this.input_stringbuilder.length();
					}
					this.fl ^= BF_ENTER_MODE;
					this.scroll = 0;
					break;
				case 6:
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
					this.sendNet(SSIELook.B_FETCH_LE);
					break;
				case 7:
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

	public void sendNet(byte b2)
	{
		PageExtra.NET_BYTE_ARRAY[0] = SPageSI.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SSIELook.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
