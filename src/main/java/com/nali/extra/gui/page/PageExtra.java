package com.nali.extra.gui.page;

import com.nali.extra.gui.key.KeyMap;
import com.nali.extra.gui.page.chunk.PageChunk;
import com.nali.extra.gui.page.entity.PageEntity;
import com.nali.extra.gui.page.inv.PageInv;
import com.nali.extra.gui.page.map.PageMap;
import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageConfig;
import com.nali.gui.page.PageEdit;
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
			new BoxTextAll("COSTUME".toCharArray()),
			new BoxTextAll("CONFIG".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("COMMAND".toCharArray()),
			new BoxTextAll("SYNC_CHUNK".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[7 / 8] |= 1 << 7 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		PageSelect ps;
		PageEdit pe;
		PageMap pm;
		switch (this.select)
		{
			case 2:
				pe = new PageInv();
				this.set(pe, new KeyEdit(pe));
				break;
			case 3:
				pm = new PageMap();
				this.set(pm, new KeyMap(pm));
				break;
			case 4:
				ps = new PageEntity();
				this.set(ps, new KeySelect(ps));
				break;
			case 5:
				pe = new PageChunk();
				this.set(pe, new KeyEdit(pe));
				break;
			case 6:
				ps = new PageCostume();
				this.set(ps, new KeySelect(ps));
				break;
			case 7:
				ps = new PageConfig();
				this.set(ps, new KeySelect(ps));
				break;
			case 9:
				break;
			case 10:
				NetworkRegistry.I.sendToServer(new ServerMessage(new byte[]{SSyncChunk.ID}));
				break;
			case 11:
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
//		Nali.warn(Page.TEMP_PAGE_LIST.size() + " STEMP_PAGE_LIST " + Page.TEMP_PAGE_LIST);
//		Nali.warn(Page.TEMP_KEY_LIST.size() + " STEMP_KEY_LIST " + Page.TEMP_KEY_LIST);

		ExtraPage.setSmallPage();
		if (TEMP_PAGE_LIST.size() == 2)
		{
			this.clear();
			TEMP_PAGE_LIST.clear();
			TEMP_KEY_LIST.clear();
		}
		else
		{
			super.exit();
		}

//		Nali.warn(Page.TEMP_PAGE_LIST.size() + " ETEMP_PAGE_LIST " + Page.TEMP_PAGE_LIST);
//		Nali.warn(Page.TEMP_KEY_LIST.size() + " ETEMP_KEY_LIST " + Page.TEMP_KEY_LIST);
	}
}
