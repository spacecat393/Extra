package com.nali.extra;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class ExtraColor
{
	public static int
		RGB_COLOR;
//		GBW_COLOR;
	public static float
		RED,
		GREEN,
		BLUE;

	public static void update()
	{
		long system_time = Minecraft.getSystemTime();
		RGB_COLOR = Color.HSBtoRGB(system_time % 1000 / 1000.0F, 1.0F, 1.0F);
		RED = ((RGB_COLOR >> 16) & 0xFF) / 255.0F;
		GREEN = ((RGB_COLOR >> 8) & 0xFF) / 255.0F;
		BLUE = (RGB_COLOR & 0xFF) / 255.0F;
//		GBW_COLOR = Color.HSBtoRGB(1.0F / 3.0F, 0.0F, (float)Math.sin(system_time % 2000 / 2000.0F * Math.PI));
//		GBW_COLOR = Color.HSBtoRGB(1.0F / 3.0F, 0.0F, (float)(Math.sin(system_time % 2000 / 2000.0F * Math.PI) * 0.5F) + 0.5F);
//		BW_COLOR = Color.HSBtoRGB(0.0F, 0.0F,  ((float)Math.sin((system_time % 1000) / 1000.0F * Math.PI) * 0.5F) + 0.5F);
	}
}
