package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.page.PageSelect;
import com.nali.list.entity.si.SILeAttack;
import com.nali.list.gui.si.server.SSILeAttack;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSILeAttack extends PageSelect
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
			"SILE-ATTACK".toCharArray(),
			"FLAG".toCharArray(),
			("REMOTE " + (byte)Math.signum(FLAG & SILeAttack.B_REMOTE)).toCharArray(),
			("ATTACK_POSE " + (byte)Math.signum(FLAG & SILeAttack.B_ATTACK_POSE)).toCharArray(),
			("WALK_TO " + (byte)Math.signum(FLAG & SILeAttack.B_WALK_TO)).toCharArray(),
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
		switch (this.select)
		{
			case 6:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8];
				this.sendNet(SSILeAttack.B_FETCH);
				break;
			case 7:
				this.back();
				break;
			default:
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 8 + 1];
				PageExtra.NET_BYTE_ARRAY[3 + 8] = (byte)(1 << this.select + 3 - 2);
				this.sendNet(SSILeAttack.B_SET);
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
		PageExtra.NET_BYTE_ARRAY[1] = SSILeAttack.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
