package com.nali.extra.gui.page.inv;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.inv.select.PageSelect;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaInv;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageInv extends PageEdit
{
//	public final static byte B_ENTER = 1;
	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte[] BYTE_ARRAY;//1+1 4*? +1+1+1

	public static byte
		ST,//enter client init
		MAX_PAGE,//0-118
		SELECT;
	public static int
		PAGE,
		MAX_MIX_PAGE;

////	public static short INV = -1;
////	public static int INV = -1;
//	public final static byte B_PLAYER = 0;
//	public final static byte B_ENTITY = 1;
//	public static byte FLAG;

	@Override
	public void init()
	{
		if (BYTE_ARRAY != null)
		{
			short byte_array_length = (short)BYTE_ARRAY.length;
			PAGE = ByteReader.getInt(BYTE_ARRAY, byte_array_length - 4 - 1 - 4);
			MAX_PAGE = BYTE_ARRAY[byte_array_length - 1 - 4];
			MAX_MIX_PAGE = ByteReader.getInt(BYTE_ARRAY, byte_array_length - 4);

			byte index = 0;
			this.char_2d_array = new char[3 + MAX_PAGE + 6][];
			this.char_2d_array[index++] = "ME-INV".toCharArray();
			this.char_2d_array[index++] = ("PAGE " + PAGE).toCharArray();
			this.char_2d_array[index++] = ("MAX-PAGE " + MAX_MIX_PAGE).toCharArray();

			short i = 2;
			while (i < byte_array_length - 4 - 1 - 4)
			{
				this.char_2d_array[index++] = ("" + ByteReader.getInt(BYTE_ARRAY, i)).toCharArray();
				i += 4;
			}

			this.char_2d_array[index++] = "ACTION".toCharArray();
			this.char_2d_array[index++] = "MORE".toCharArray();
			this.char_2d_array[index++] = "LESS".toCharArray();

			if ((this.fl & BF_SET_SELECT) == 0)
			{
				this.select = index;
				this.fl |= BF_SET_SELECT;
			}

			this.char_2d_array[index++] = "FETCH".toCharArray();
			this.char_2d_array[index++] = "ADD".toCharArray();
			this.char_2d_array[index] = "BACK".toCharArray();

			this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[1 / 8] |= 1 << 1 % 8;
			byte new_index = (byte)(index - 6);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.char_2d_array = new char[][]
			{
				"ME-INV".toCharArray(),
				"ACTION".toCharArray(),
				"MORE".toCharArray(),
				"LESS".toCharArray(),
				"FETCH".toCharArray(),
				"ADD".toCharArray(),
				"BACK".toCharArray()
			};

			this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;

			if ((this.fl & BF_SET_SELECT) == 0)
			{
				this.select = 4;
				this.fl |= BF_SET_SELECT;
			}
		}

		super.init();
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		byte boxtextall_array_length = (byte)this.char_2d_array.length;
		if (boxtextall_array_length == 7)
		{
			switch (this.select)
			{
				case 2:
					this.sendNet(SDaInv.B_MORE);
					break;
				case 3:
					this.sendNet(SDaInv.B_LESS);
					break;
				case 4:
					this.sendNet(SDaInv.B_FETCH);
					break;
				case 5:
					this.sendNet(SDaInv.B_ADD);
					break;
				case 6:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 5))
			{
				this.sendNet(SDaInv.B_MORE);
			}
			else if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaInv.B_LESS);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				this.sendNet(SDaInv.B_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaInv.B_ADD);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else/* if (this.select > 1)*/
			{
				SELECT = (byte)(this.select - 3);
//				INV = ByteReader.getShort(BYTE_ARRAY, (this.select - 3) * 2 + 2);
//				INV = ByteReader.getInt(BYTE_ARRAY, 2 + (this.select - 3) * 4);
				PageSelect ps = new PageSelect();
				this.set(ps, new KeySelect(ps));
//				STATE &= 255-1;
			}
		}
//			STATE &= 255-1;
//		}
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
		PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4];
		PageExtra.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SDaInv.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PAGE, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
