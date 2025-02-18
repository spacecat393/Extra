package com.nali.extra.gui.key;

import com.nali.extra.gui.page.map.PageMap;
import com.nali.extra.gui.page.map.PageView;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyMap extends Key
{
	@Override
	public void enter()
	{
		PageMap pagemap = (PageMap)Page.PAGE;

		int min = Math.min(PageMap.WIDTH, PageMap.HEIGHT);
		float size = min / 16.0F;
////		float fixx, fixy;
//		if ((pagemap.state & PageMap.BS_ENTER_MODE) == 0)
//		{
////			if (min == PageMap.WIDTH)
////			{
////				fixy = (PageMap.HEIGHT - PageMap.WIDTH) / 2.0F;
////				fixx = 0;
////			}
////			else
////			{
////				fixy = 0;
////				fixx = (PageMap.WIDTH - PageMap.HEIGHT) / 2.0F;
////			}

		if (this.key == Keyboard.KEY_UP)
		{
			pagemap.vs_float_array[1] += size / PageMap.HEIGHT * 2;
		}
		else if (this.key == Keyboard.KEY_DOWN)
		{
			pagemap.vs_float_array[1] -= size / PageMap.HEIGHT * 2;
		}
		else if (this.key == Keyboard.KEY_LEFT)
		{
			pagemap.vs_float_array[0] -= size / PageMap.WIDTH * 2;
		}
		else if (this.key == Keyboard.KEY_RIGHT)
		{
			pagemap.vs_float_array[0] += size / PageMap.WIDTH * 2;
		}
		else if (this.key == Keyboard.KEY_O)
		{
			Page.PAGE_LIST.add(pagemap);
			Page.KEY_LIST.add(this);
			pagemap.set(new PageView(), new KeySelect());
		}

		if (pagemap.vs_float_array[0] < 0)
		{
			pagemap.vs_float_array[0] = 0;
		}
		else if (pagemap.vs_float_array[0] > (min - size) / PageMap.WIDTH * 2)
		{
			pagemap.vs_float_array[0] = (min - size) / PageMap.WIDTH * 2;
		}

		if (pagemap.vs_float_array[1] < 0)
		{
			pagemap.vs_float_array[1] = 0;
		}
		else if (pagemap.vs_float_array[1] > (min - size) / PageMap.HEIGHT * 2)
		{
			pagemap.vs_float_array[1] = (min - size) / PageMap.HEIGHT * 2;
		}
//		}
//		else
//		{
//
//		}

		super.enter();
	}
}
