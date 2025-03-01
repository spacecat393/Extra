package com.nali.extra.gui.page.entity;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.page.PageEdit;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaEntity;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEntity extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (8+4)*?+? +1+1+1
	public static String[] NAME_STRING_ARRAY;

	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,//enter client init
		MAX_PAGE;//0-119
	public static int
		PAGE,
		MAX_MIX_PAGE;
	public static long[] ID_LONG_ARRAY;

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
			this.char_2d_array = new char[3 + MAX_PAGE + 5][];
			this.char_2d_array[index++] = "ENTITY".toCharArray();
//			this.boxtextall_array[index++] = ("PAGE " + PAGE + " - " + MAX_MIX_PAGE).toCharArray());

			this.char_2d_array[index++] = ("PAGE " + PAGE).toCharArray();
			this.char_2d_array[index++] = ("MAX-PAGE " + MAX_MIX_PAGE).toCharArray();

			short i = 2;
			byte n_index = 0;
			NAME_STRING_ARRAY = new String[MAX_PAGE];
			ID_LONG_ARRAY = new long[MAX_PAGE];
			while (i < byte_array_length - 4 - 1 - 4)
			{
				long id = ByteReader.getLong(BYTE_ARRAY, i);
				i += 8;
				int name_length = ByteReader.getInt(BYTE_ARRAY, i);
				i += 4;
				String name_string = new String(BYTE_ARRAY, i, name_length);
				ID_LONG_ARRAY[n_index] = id;
				NAME_STRING_ARRAY[n_index++] = name_string;
				i += name_length;
				this.char_2d_array[index++] = this.getChar((int)id + " " + name_string);
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
			this.char_2d_array[index] = "BACK".toCharArray();

			this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[1 / 8] |= 1 << 1 % 8;
			byte new_index = (byte)(index - 5);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.char_2d_array = new char[][]
			{
				"ENTITY".toCharArray(),
				"ACTION".toCharArray(),
				"MORE".toCharArray(),
				"LESS".toCharArray(),
				"FETCH".toCharArray(),
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
		byte boxtextall_array_length = (byte)this.char_2d_array.length;
		if (boxtextall_array_length == 6)
		{
			switch (this.select)
			{
				case 2:
					this.sendNet(SDaEntity.B_MORE);
					break;
				case 3:
					this.sendNet(SDaEntity.B_LESS);
					break;
				case 4:
					this.sendNet(SDaEntity.B_FETCH);
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaEntity.B_MORE);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				this.sendNet(SDaEntity.B_LESS);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaEntity.B_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				byte select = (byte)(this.select - 3);
				PageEdit pe = new PageMe(ID_LONG_ARRAY[select], NAME_STRING_ARRAY[select]);
				this.set(pe, new KeyEdit(pe));
//				STATE &= 255-1;
			}
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
		PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4];
		PageExtra.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SDaEntity.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PAGE, 3);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
