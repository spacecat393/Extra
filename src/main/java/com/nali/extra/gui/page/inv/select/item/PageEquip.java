package com.nali.extra.gui.page.inv.select.item;

import com.nali.gui.page.PageEdit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEquip extends PageEdit
{
	@Override
	public void init()
	{
		super.init();

		if ((this.state & 4) == 0)
		{
			this.select = 2;
			this.state |= 4;
		}
	}

	@Override
	public void enter()
	{

	}
}