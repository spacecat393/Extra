package com.nali.extra.gui.page;

import com.nali.extra.gui.page.chunk.PageChunk;
import com.nali.extra.gui.page.entity.PageEntity;
import com.nali.extra.gui.page.inv.PageInv;
import com.nali.extra.gui.page.map.PageMap;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageConfig;
import com.nali.gui.page.PageSelect;
import com.nali.list.key.ExtraPage;
import com.nali.list.network.message.ServerMessage;
import com.nali.list.network.method.server.SSyncChunk;
import com.nali.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageExtra extends PageSelect
{
	public static byte[] NET_BYTE_ARRAY;

	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("EXTRA".toCharArray()),
			new BoxTextAll("GAME".toCharArray()),
			new BoxTextAll("INV".toCharArray()),
			new BoxTextAll("MAP".toCharArray()),
			new BoxTextAll("ENTITY".toCharArray()),
			new BoxTextAll("CHUNK".toCharArray()),
			new BoxTextAll("CONFIG".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("COMMAND".toCharArray()),
			new BoxTextAll("SYNC_CHUNK".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[6 / 8] |= 1 << 6 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		switch (this.select)
		{
			case 2:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageInv(), new KeyEdit());
				break;
			case 3:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageMap(), new Key());
				break;
			case 4:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageEntity(), new KeySelect());
				break;
			case 5:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageChunk(), new KeyEdit());
				break;
			case 6:
				PAGE_LIST.add(this);
				KEY_LIST.add(Key.KEY);
				this.set(new PageConfig(), new KeySelect());
				break;
			case 8:
				break;
			case 9:
				NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSyncChunk.ID}));
				break;
			case 10:
				this.back();
		}
	}

	@Override
	public void back()
	{
		this.exit();
//		this.state |= 2;
	}

	@Override
	public void exit()
	{
		ExtraPage.setSmallPage();

		super.exit();
	}
}
