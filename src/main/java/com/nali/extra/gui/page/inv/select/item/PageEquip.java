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

		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}
	}

	@Override
	public void enter()
	{

	}
}