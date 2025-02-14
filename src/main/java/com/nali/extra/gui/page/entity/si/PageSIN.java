package com.nali.extra.gui.page.entity.si;

import com.nali.gui.box.text.BoxTextAll;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIN extends PageSelect
{
	@Override
	public void init()
	{
		this.boxtextall_array = new BoxTextAll[]
		{
			new BoxTextAll("SIN".toCharArray()),
			new BoxTextAll("EMPTY".toCharArray()),
			new BoxTextAll("ACTION".toCharArray()),
			new BoxTextAll("BACK".toCharArray())
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.boxtextall_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[1 / 8] |= 1 << 1 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 3;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{
		this.back();
	}
}
