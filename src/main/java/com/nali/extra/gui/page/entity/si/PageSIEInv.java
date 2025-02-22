package com.nali.extra.gui.page.entity.si;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import com.nali.list.gui.si.server.SSIEInv;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageSI;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIEInv extends PageSelect
{
	public static byte[] BYTE_ARRAY;//1+1 (4)*? +1+1+1

	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST,
		MAX_PAGE;
//		SELECT;
	public static int
		PAGE,
		MAX_MIX_PAGE;

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
			this.boxtextall_array = new BoxTextAll[3 + MAX_PAGE + 6];
			this.boxtextall_array[index++] = new BoxTextAll("ME-INV".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("PAGE " + PAGE).toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll(("MAX-PAGE " + MAX_MIX_PAGE).toCharArray());

			short i = 2;
			while (i < byte_array_length - 4 - 1 - 4)
			{
				int id = ByteReader.getInt(BYTE_ARRAY, i);
				i += 4;
				this.boxtextall_array[index] = new BoxTextAll(this.getChar((index - 3) + " " + new ItemStack(Item.getItemById(id)).getDisplayName()));
				++index;
			}

			this.boxtextall_array[index++] = new BoxTextAll("ACTION".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("CLEAN".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("MORE".toCharArray());
			this.boxtextall_array[index++] = new BoxTextAll("LESS".toCharArray());

			if ((this.fl & BF_SET_SELECT) == 0)
			{
				this.select = index;
				this.fl |= BF_SET_SELECT;
			}

			this.boxtextall_array[index++] = new BoxTextAll("FETCH".toCharArray());
			this.boxtextall_array[index] = new BoxTextAll("BACK".toCharArray());

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;
			this.group_byte_array[1 / 8] |= 1 << 1 % 8;
			byte new_index = (byte)(index - 6);
			this.group_byte_array[new_index / 8] |= 1 << new_index % 8;
		}
		else
		{
			this.boxtextall_array = new BoxTextAll[]
			{
				new BoxTextAll("ME-INV".toCharArray()),
				new BoxTextAll("ACTION".toCharArray()),
				new BoxTextAll("MORE".toCharArray()),
				new BoxTextAll("LESS".toCharArray()),
				new BoxTextAll("FETCH".toCharArray()),
				new BoxTextAll("BACK".toCharArray())
			};

			this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
			this.group_byte_array[0 / 8] |= 1 << 0 % 8;

			if ((this.fl & BF_SET_SELECT) == 0)
			{
				this.select = 4;
				this.fl |= BF_SET_SELECT;
			}
		}
	}

	@Override
	public void enter()
	{
		byte boxtextall_array_length = (byte)this.boxtextall_array.length;
		if (boxtextall_array_length == 6)
		{
			switch (this.select)
			{
				case 2:
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
					this.sendNet(SSIEInv.B_MORE);
					break;
				case 3:
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
					this.sendNet(SSIEInv.B_LESS);
					break;
				case 4:
					PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
					this.sendNet(SSIEInv.B_FETCH);
					break;
				case 5:
					this.back();
			}
		}
		else
		{
			if (this.select == (boxtextall_array_length - 5))
			{
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
				this.sendNet(SSIEInv.B_DELETE);
			}
			else if (this.select == (boxtextall_array_length - 4))
			{
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
				this.sendNet(SSIEInv.B_MORE);
			}
			else if (this.select == (boxtextall_array_length - 3))
			{
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
				this.sendNet(SSIEInv.B_LESS);
			}
			else if (this.select == (boxtextall_array_length - 2))
			{
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8];
				this.sendNet(SSIEInv.B_FETCH);
			}
			else if (this.select == (boxtextall_array_length - 1))
			{
				this.back();
			}
			else
			{
				PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4 + 8 + 1];
				PageExtra.NET_BYTE_ARRAY[1 + 1 + 1 + 4 + 8] = (byte)(this.select - 3);
				this.sendNet(SSIEInv.B_CHECK);
//				SELECT = (byte)(this.select - 3);
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
//		PageExtra.NET_BYTE_ARRAY = new byte[1 + 1 + 1 + 4];
		PageExtra.NET_BYTE_ARRAY[0] = SPageSI.ID;
		PageExtra.NET_BYTE_ARRAY[1] = SSIEInv.ID;
		PageExtra.NET_BYTE_ARRAY[2] = b2;
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PAGE, 3);
		ByteWriter.set(PageExtra.NET_BYTE_ARRAY, PageMe.ID, 3+4);
		NetworkRegistry.I.sendToServer(new ServerMessage(PageExtra.NET_BYTE_ARRAY));
	}
}
