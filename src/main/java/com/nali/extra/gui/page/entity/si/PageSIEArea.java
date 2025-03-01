package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.page.PageSelect;
import com.nali.list.entity.si.SIEArea;
import com.nali.list.gui.si.server.SSIEArea;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIEArea extends PageSelect
{
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,//enter client init
		FLAG;

	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"SIE-AREA".toCharArray(),
			"FLAG".toCharArray(),
			("BYPASS_ARMY " + (byte)Math.signum(FLAG & SIEArea.B_BYPASS_ARMY)).toCharArray(),
			("PUT_ANIMAL " + (byte)Math.signum(FLAG & SIEArea.B_PUT_ANIMAL)).toCharArray(),
			("PUT_PLAYER " + (byte)Math.signum(FLAG & SIEArea.B_PUT_PLAYER)).toCharArray(),
			("PUT_OWNER " + (byte)Math.signum(FLAG & SIEArea.B_PUT_OWNER)).toCharArray(),
			("PUT_OTHER_TAMEABLE " + (byte)Math.signum(FLAG & SIEArea.B_PUT_OTHER_TAMEABLE)).toCharArray(),
			("PUT_OWNER_TAMEABLE " + (byte)Math.signum(FLAG & SIEArea.B_PUT_OWNER_TAMEABLE)).toCharArray(),
			("PUT_ALL_TAMEABLE " + (byte)Math.signum(FLAG & SIEArea.B_PUT_ALL_TAMEABLE)).toCharArray(),
			("PUT_OBJECT " + (byte)Math.signum(FLAG & SIEArea.B_PUT_OBJECT & 0xFF)).toCharArray(),
			"ACTION".toCharArray(),
			"FETCH".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[9 / 8] |= 1 << 9 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 11;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 11:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
				this.sendNet(SSIEArea.B_FETCH);
				break;
			case 12:
				this.back();
				break;
			default:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 1];
				PageExtra.NET_BYTE_ARRAY[3 + 8] = (byte)(1 << this.select - 2);
				this.sendNet(SSIEArea.B_SET);
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
		PageExtra.NET_BYTE_ARRAY[1] = SSIEArea.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
