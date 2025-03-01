package com.nali.extra.gui.page.entity.si;

import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageSIN extends PageSelect
{
	@Override
	public void init()
	{
		this.char_2d_array = new char[][]
		{
			"SIN".toCharArray(),
			"EMPTY".toCharArray(),
			"ACTION".toCharArray(),
			"BACK".toCharArray()
		};

		this.group_byte_array = new byte[(byte)Math.ceil((this.char_2d_array.length - 1) / 8.0F)];
		this.group_byte_array[0 / 8] |= 1 << 0 % 8;
		this.group_byte_array[1 / 8] |= 1 << 1 % 8;

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 3;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{
		this.back();
	}
}
