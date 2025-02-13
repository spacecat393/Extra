package com.nali.list.key;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.key.Key;
import com.nali.small.entity.memo.client.box.mix.MixBoxSeRSe;
import com.nali.small.entity.memo.client.box.mix.MixBoxSleInv;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class ExtraPage extends Key
{
	public static byte STATE;//1key 2bit

	public static Page PAGE;
	public static com.nali.gui.key.Key KEY;

	public static List<Page> PAGE_LIST = new ArrayList();
	public static List<com.nali.gui.key.Key> KEY_LIST = new ArrayList();

	@Override
	public void run()
	{
		if ((MixBoxSeRSe.PAGE & MixBoxSeRSe.B_OPEN) == MixBoxSeRSe.B_OPEN)
		{
			setSmallPage();
		}
		else if (MixBoxSleInv.ENTITYLE != null)
		{
			setSmallPage();

			if (Page.PAGE_LIST.size() > 1)
			{
				int index = Page.PAGE_LIST.size() - 1;
				Page.PAGE.set(Page.PAGE_LIST.get(index), Page.KEY_LIST.get(index));
				Page.PAGE_LIST.remove(index);
				Page.KEY_LIST.remove(index);
			}

			Page.PAGE_LIST.add(Page.PAGE);
			Page.KEY_LIST.add(KEY);
			Page.PAGE.set(new PageMe((long)MixBoxSleInv.ENTITYLE.world.provider.getDimension() << 32 | MixBoxSleInv.ENTITYLE.getEntityId(), MixBoxSleInv.ENTITYLE.getName()), new KeyEdit());
			MixBoxSleInv.ENTITYLE = null;
		}
		else if (Minecraft.getMinecraft().currentScreen == null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if ((STATE & 1) == 0)
				{
//					if ((this.state & 2) == 2)
//					{
//						Page.PAGE.clear();
//
//						PAGE = Page.PAGE;
//						KEY = com.nali.gui.key.Key.KEY;
//
//						Page.PAGE = null;
//						com.nali.gui.key.Key.KEY = null;
//
//						this.state &= 255-2;
//					}
//					else
//					{
					setSmallPage();
//						this.state |= 2;
//					}
					STATE |= 1;
				}
			}
			else
			{
				STATE &= 255-1;
			}
		}
	}

	public static void setSmallPage()
	{
		if (Page.PAGE != null)
		{
			PAGE.clear();
		}

		if (PAGE == null)
		{
			Page.PAGE = new PageExtra();
			com.nali.gui.key.Key.KEY = new KeySelect();

			PAGE = Page.PAGE;
			KEY = com.nali.gui.key.Key.KEY;

			PAGE_LIST = new ArrayList(Page.PAGE_LIST);
			KEY_LIST = new ArrayList(Page.KEY_LIST);

			Page.PAGE.init();
			Page.WIDTH = -1;
		}
		else
		{
			if ((STATE & 2) == 2)
			{
				PAGE = Page.PAGE;
				KEY = com.nali.gui.key.Key.KEY;

				PAGE_LIST = new ArrayList(Page.PAGE_LIST);
				KEY_LIST = new ArrayList(Page.KEY_LIST);

				Page.PAGE_LIST.clear();
				Page.KEY_LIST.clear();

				Page.PAGE = null;
				com.nali.gui.key.Key.KEY = null;

				Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
			}
			else
			{
				Page.PAGE = PAGE;
				com.nali.gui.key.Key.KEY = KEY;

				Page.PAGE_LIST = PAGE_LIST;
				Page.KEY_LIST = KEY_LIST;

				Page.PAGE.init();
				Page.WIDTH = -1;
			}
		}

		STATE ^= 2;
	}
}
