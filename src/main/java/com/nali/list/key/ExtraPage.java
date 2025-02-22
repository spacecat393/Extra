package com.nali.list.key;

import com.nali.extra.gui.page.PageExtra;
import com.nali.extra.gui.page.entity.me.PageMe;
import com.nali.gui.key.KeyEdit;
import com.nali.gui.key.KeySelect;
import com.nali.gui.page.Page;
import com.nali.gui.page.PageEdit;
import com.nali.gui.page.PageSelect;
import com.nali.key.Key;
import com.nali.small.entity.memo.client.box.mix.MixBoxSle;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ExtraPage extends Key
{
	public final static byte K_KEY = 1;
	public final static byte K_BIT = 2;
	public static byte KS;

//	public static Page PAGE;
//	public static com.nali.gui.key.Key KEY;

	@Override
	public void run()
	{
		if (MixBoxSle.ENTITYLE != null)
		{
			if ((KS & K_KEY) == 0)
			{
				setSmallPage();

//				Nali.warn("TEMP_PAGE_LIST " + Page.TEMP_PAGE_LIST);
//				Nali.warn("TEMP_KEY_LIST " + Page.TEMP_KEY_LIST);
				PageEdit pe = new PageMe((long)MixBoxSle.ENTITYLE.world.provider.getDimension() << 32 | MixBoxSle.ENTITYLE.getEntityId(), MixBoxSle.ENTITYLE.getName());
				Page.PAGE.set(pe, new KeyEdit(pe));

				KS |= K_KEY;
			}

			MixBoxSle.ENTITYLE = null;
		}
		else if (Minecraft.getMinecraft().currentScreen == null)
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_P))
			{
				if ((KS & K_KEY) == 0)
				{
					setSmallPage();

//					Nali.warn("TEMP_PAGE_LIST " + Page.TEMP_PAGE_LIST);
//					Nali.warn("TEMP_KEY_LIST " + Page.TEMP_KEY_LIST);
					KS |= K_KEY;
				}
			}
			else
			{
				KS &= 255 - K_KEY;
			}
		}
	}

	public static void setSmallPage()
	{
		if (Page.TEMP_PAGE_LIST.isEmpty())
//		if (PAGE == null)
		{
			PageSelect ps = new PageExtra();
			Page.PAGE = ps;
			com.nali.gui.key.Key.KEY = new KeySelect(ps);

			Page.TEMP_PAGE_LIST.add(Page.PAGE);
			Page.TEMP_KEY_LIST.add(com.nali.gui.key.Key.KEY);

//			PAGE = Page.PAGE;
//			KEY = com.nali.gui.key.Key.KEY;

			Page.PAGE.init();
			Page.WIDTH = -1;
		}
		else
		{
			if ((KS & K_BIT) == K_BIT)
			{
				Page.PAGE.clear();
				Page.TEMP_PAGE_LIST.add(Page.PAGE);
				Page.TEMP_KEY_LIST.add(com.nali.gui.key.Key.KEY);

//				PAGE = Page.PAGE;
//				KEY = com.nali.gui.key.Key.KEY;

				Page.PAGE = null;
				com.nali.gui.key.Key.KEY = null;

				Minecraft.getMinecraft().mouseHelper.grabMouseCursor();
			}
			else
			{
				int index = Page.TEMP_PAGE_LIST.size() - 1;
				Page.PAGE = Page.TEMP_PAGE_LIST.get(index);
				com.nali.gui.key.Key.KEY = Page.TEMP_KEY_LIST.get(index);
				Page.TEMP_PAGE_LIST.remove(index);
				Page.TEMP_KEY_LIST.remove(index);

//				Page.PAGE = PAGE;
//				com.nali.gui.key.Key.KEY = KEY;

				Page.PAGE.init();
				Page.WIDTH = -1;
			}
		}

		KS ^= K_BIT;
	}
}
