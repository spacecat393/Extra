package com.nali.extra.gui.page.entity.me;

import com.nali.gui.page.PageEdit;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PageEffect extends PageEdit
{
	@Override
	public void init()
	{
		//show effect
		if ((this.fl & BF_SET_SELECT) == 0)
		{
			this.select = 2;
			this.fl |= BF_SET_SELECT;
		}
		super.init();
	}

	@Override
	public void enter()
	{

	}
}
