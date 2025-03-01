package com.nali.extra.gui.key;

import com.nali.extra.gui.page.map.PageMap;
import com.nali.extra.gui.page.map.PageView;
import com.nali.gui.box.Box;
import com.nali.gui.key.Key;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.PageSelect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class KeyMap<P extends PageMap> extends Key
{
	public P p;

	public KeyMap(P p)
	{
		this.p = p;
	}

	@Override
	public void enter()
	{
		int min = Math.min(Box.WIDTH, Box.HEIGHT);
		float size = min / 16.0F;
////		float fixx, fixy;
//		if ((this.p.state & PageMap.BS_ENTER_MODE) == 0)
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
			this.p.vs_float_array[1] += size / Box.HEIGHT * 2;
		}
		else if (this.key == Keyboard.KEY_DOWN)
		{
			this.p.vs_float_array[1] -= size / Box.HEIGHT * 2;
		}
		else if (this.key == Keyboard.KEY_LEFT)
		{
			this.p.vs_float_array[0] -= size / Box.WIDTH * 2;
		}
		else if (this.key == Keyboard.KEY_RIGHT)
		{
			this.p.vs_float_array[0] += size / Box.WIDTH * 2;
		}
		else if (this.key == Keyboard.KEY_O)
		{
			PageSelect ps = new PageView();
			this.p.set(ps, new KeySelect(ps));
		}

		if (this.p.vs_float_array[0] < 0)
		{
			this.p.vs_float_array[0] = 0;
		}
		else if (this.p.vs_float_array[0] > (min - size) / Box.WIDTH * 2)
		{
			this.p.vs_float_array[0] = (min - size) / Box.WIDTH * 2;
		}

		if (this.p.vs_float_array[1] < 0)
		{
			this.p.vs_float_array[1] = 0;
		}
		else if (this.p.vs_float_array[1] > (min - size) / Box.HEIGHT * 2)
		{
			this.p.vs_float_array[1] = (min - size) / Box.HEIGHT * 2;
		}
//		}
//		else
//		{
//
//		}

		super.enter();
	}
}
