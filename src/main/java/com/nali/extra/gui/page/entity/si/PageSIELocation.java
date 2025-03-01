package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.page.PageEdit;
import com.nali.list.entity.si.SIELocation;
import com.nali.list.gui.si.server.SSIELocation;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIELocation extends PageEdit
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,
		FLAG;
	public static float
		FAR,
		X, Y, Z;

	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"SIE-LOCATION".toCharArray(),
			"FLAG".toCharArray(),
			("ON " + (byte)Math.signum(FLAG & SIELocation.B_ON)).toCharArray(),
			"DATA".toCharArray(),
			this.getChar("X " + X),
			this.getChar("Y " + Y),
			this.getChar("Z " + Z),
			this.getChar("FAR " + FAR),
			"ACTION".toCharArray(),
			"FETCH".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[2 / 8] |= 1 << 2 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 9;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 4:
				if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
				{
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
					ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(this.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
					this.sendNet(SSIELocation.B_SET_X);
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(X);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 5:
				if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
				{
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
					ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(this.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
					this.sendNet(SSIELocation.B_SET_Y);
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(Y);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 6:
				if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
				{
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
					ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(this.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
					this.sendNet(SSIELocation.B_SET_Z);
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(Z);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 7:
				if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
				{
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 4];
					ByteWriter.set(PageExtra.NET_BYTE_ARRAY, Float.parseFloat(this.input_stringbuilder.toString()), 1 + 1 + 1 + 8);
					this.sendNet(SSIELocation.B_SET_FAR);
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(FAR);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 9:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
				this.sendNet(SSIELocation.B_FETCH);
				break;
			case 10:
				this.back();
				break;
			default:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 1];
//				PageExtra.NET_BYTE_ARRAY[3 + 8] = (byte)(1 << this.select - 2);
				PageExtra.NET_BYTE_ARRAY[3 + 8] = 2;
				this.sendNet(SSIELocation.B_SET);
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
		PageExtra.NET_BYTE_ARRAY[1] = SSIELocation.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
