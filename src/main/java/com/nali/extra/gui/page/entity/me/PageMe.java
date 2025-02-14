package com.nali.extra.gui.page.entity.me;

import com.nali.extra.gui.page.entity.PageEntity;
import com.nali.extra.gui.page.inv.PageInv;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageEdit;
import com.nali.list.gui.da.server.SDaEntity;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SPageDa;
import com.nali.network.NetworkRegistry;
import com.nali.system.bytes.ByteWriter;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageMe extends PageEdit
{
//	public int i, d;
	public static long ID;
	public String name;

	public PageMe(long id, String name)
	{
//		this.i = i;
//		this.d = d;
		ID = id;
		this.name = name;
	}

	@Override
	public void init()
	{
		super.init();
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("ENTITY-ME".toCharArray()),
			new BoxTextAll("MENU".toCharArray()),
			//i
			//d
			new BoxTextAll(this.getChar("NAME " + this.name)),
			//hp
			//mp
			//edit inv in si
			new BoxTextAll("ME-ATTRIBUTE".toCharArray()),
			new BoxTextAll("ME-MIXSI".toCharArray()),
			new BoxTextAll("ME-SI".toCharArray()),
			new BoxTextAll("ME-INV".toCharArray()),
//			new BoxTextAll("ME-MAP".toCharArray()),
			new BoxTextAll("ME-EFFECT".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 9;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				if ((this.fl & BF_ENTER_MODE) == BF_ENTER_MODE)
				{
					this.name = this.input_stringbuilder.toString();

					//rename
					byte[] name_byte_array = this.name.getBytes();
					int name_byte_array_length = name_byte_array.length;
					byte[] byte_array = new byte[1 + 1 + 1 + 4 + 8 + name_byte_array_length];
					byte_array[0] = SPageDa.ID;
					byte_array[1] = SDaEntity.ID;
					byte_array[2] = SDaEntity.B_RENAME;
					ByteWriter.set(byte_array, PageEntity.PAGE, 3);
//					ByteWriter.set(byte_array, (long)this.d << 32 | this.i, 4);
					ByteWriter.set(byte_array, ID, 3+4);
					System.arraycopy(name_byte_array, 0, byte_array, 3+4+8, name_byte_array_length);
					NetworkRegistry.I.sendToServer(new ServerMessage(byte_array));
				}
				else
				{
					this.input_stringbuilder.setLength(0);
					this.input_stringbuilder.append(this.name);
					this.select_box = this.input_stringbuilder.length();
				}
				this.fl ^= BF_ENTER_MODE;
				this.scroll = 0;
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageAttribute(), new KeySelect());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageMixSI(), new KeySelect());
				break;
			case 5:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageSI(), new KeySelect());
				break;
//			case 5:
//				PAGE_LIST.add(this);
//				KEY_LIST.add(Key.KEY);
//				this.set(new PageInv(), new KeySelect());
//				break;
			case 6:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				//
				this.set(new PageInv(), new KeySelect());
				break;
			case 7:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEffect(), new KeyEdit());
				break;
			case 9:
				this.back();
				break;
		}
	}
}
