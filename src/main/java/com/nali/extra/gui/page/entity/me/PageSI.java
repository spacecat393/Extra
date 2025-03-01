package com.nali.extra.gui.page.entity.me;

import com.nali.Nali;
import com.nali.extra.gui.page.PageExtra;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.da.server.SDaSI;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.small.entity.memo.server.si.MixSIE;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.entity.EntityList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.InvocationTargetException;

@SideOnly(Side.CLIENT)
public class PageSI extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (1)*?+? +1+1+1

	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,//enter client init
		PAGE,//0-127
		MAX_PAGE,//0-120
		MAX_MIX_PAGE,
		GI;//0-127

	public static int
		ENTITY_ID;

	@Override
	public void init()
	{
		if (BYTE_ARRAY != null)
		{
			short byte_array_length = (short)BYTE_ARRAY.length;
			ENTITY_ID = ByteReader.getInt(BYTE_ARRAY, byte_array_length - 4 - 3);
			PAGE = BYTE_ARRAY[byte_array_length - 3];
			MAX_PAGE = BYTE_ARRAY[byte_array_length - 2];
			MAX_MIX_PAGE = BYTE_ARRAY[byte_array_length - 1];

			byte index = 0;
			this.char_2d_array = new char[2 + MAX_PAGE + 5][];
			this.char_2d_array[index++] = "ME-SI".toCharArray();
			this.char_2d_array[index++] = ("PAGE " + PAGE + " - " + MAX_MIX_PAGE).toCharArray();

			short i = 2;
			while (i < byte_array_length - 3 - 4)
			{
//				int si_id = ByteReader.getInt(BYTE_ARRAY, i);
//				i += 4;
				byte si_id = BYTE_ARRAY[i++];
				this.char_2d_array[index++] = this.getChar(si_id + " " + MixSIE.SI_CLASS_LIST.get(si_id).getName().substring(24));
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
			byte new_index = (byte)(index - 5);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.char_2d_array = new char[][]
			{
				"ME-SI".toCharArray(),
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
					this.sendNet(SDaSI.B_MORE);
					break;
				case 3:
					this.sendNet(SDaSI.B_LESS);
					break;
				case 4:
					this.sendNet(SDaSI.B_FETCH);
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 4))
			{
				this.sendNet(SDaSI.B_MORE);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				this.sendNet(SDaSI.B_LESS);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				this.sendNet(SDaSI.B_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				GI = BYTE_ARRAY[/*2 + */this.select/* - 2*/];
				try
				{
					EntityList.getClassFromID(ENTITY_ID).getMethod("setGI").invoke(null);
				}
				catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
				{
					Nali.error(e);
				}
//				byte select = (byte)(this.select - 2);
//				this.set(new PageAttributeE(), new KeyEdit());
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
		PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 1 + 8];
		PageExtra.NET_BYTE_ARRAY[0] = SPageDa.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SDaSI.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		PageExtra.NET_BYTE_ARRAY[3] = PAGE;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 4);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
