package com.nali.extra.gui.page.inv.select.item;

import com.nali.extra.gui.page.inv.PageInv;
import com.nali.extra.gui.page.inv.select.PageSelect;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.gui.da.server.SDaInvSelectItem;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteReader;
import com.nali.system.bytes.ByteWriter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageItem extends PageEdit
{
//	public static byte[] BYTE_ARRAY;//1+1 4*? +1+1+1

	public final static byte B_LOCK_DRAW = 2;
	public final static byte B_DRAW = 4;
	public static byte
		ST;//4init
//		PAGE,//0-127
//		MAX_PAGE,//0-119
//		MAX_MIX_PAGE;//0-127

	public static long ITEM_SIZE = -1;

	public int item_id;
	public String item_name;
	public long nbt = -1;

	public PageItem(int item_id)
	{
		this.item_id = item_id;
	}

	//item name size nbt nbt_set
	@Override
	public void init()
	{
		//move
		this.item_name = new ItemStack(Item.getItemById(this.item_id)).getDisplayName();
		this.char_2d_array = new char[][]
		{
			"SELECT-ITEM".toCharArray(),
			"MENU".toCharArray(),
			("ID " + this.item_id).toCharArray(),
			this.getChar("NAME " + this.item_name),
			//size
			this.getChar("SIZE " + ITEM_SIZE),

			this.getChar("NBT " + this.nbt),
			"ITEM-NBT".toCharArray(),
			"ACTION".toCharArray(),
			"MOVE".toCharArray(),
			"DELETE".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 10;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{
//		if ((STATE & 1) == 0)
//		{
//			STATE |= 1;
		byte[] byte_array;
		switch (this.select)
		{
			case 2:
				if ((this.fl & BF_ENTER_MODE) == 0)
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.item_id);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 3:
				if ((this.fl & BF_ENTER_MODE) == 0)
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.item_name);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 4://size
				byte_array = new byte[1 + 1 + 4 + 4];
				byte_array[0] = SPageDa.ID;
				byte_array[1] = SDaInvSelectItem.ID;
				ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 2);
				ByteWriter.set(byte_array, this.item_id, 2+4);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				break;
			case 5:
				break;
			case 6:
				break;
			case 8:
				byte_array = new byte[1 + 1 + 1 + 4 + 4 + 4 + 8];
				byte_array[0] = SPageDa.ID;
				byte_array[1] = SDaInvSelect.ID;
				byte_array[2] = SDaInvSelect.B_MOVE;
				ByteWriter.set(byte_array, PageSelect.PAGE, 3);
				ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 3+4);
				ByteWriter.set(byte_array, this.item_id, 3+4+4);
				ByteWriter.set(byte_array, this.nbt, 3+4+4+4);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				this.back();
				break;
			case 9:
				byte_array = new byte[1 + 1 + 1 + 4 + 4 + 4];
				byte_array[0] = SPageDa.ID;
				byte_array[1] = SDaInvSelect.ID;
				byte_array[2] = SDaInvSelect.B_DELETE;
				ByteWriter.set(byte_array, PageSelect.PAGE, 3);
				ByteWriter.set(byte_array, ByteReader.getInt(PageInv.BYTE_ARRAY, 2 + PageInv.SELECT * 4), 3+4);
				ByteWriter.set(byte_array, this.item_id, 3+4+4);
				NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				this.back();
				break;
			case 10:
				this.back();
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
}