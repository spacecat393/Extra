package com.nali.extra;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
public class ExtraColor
{
	public static float
		RED,
		GREEN,
		BLUE;

	public static void update()
	{
		float c = Minecraft.getSystemTime() % 1000 / 1000.0F;
		int color = Color.HSBtoRGB(c, 1.0F, 1.0F);
		RED = ((color >> 16) & 0xFF) / 255.0F;
		GREEN = ((color >> 8) & 0xFF) / 255.0F;
		BLUE = (color & 0xFF) / 255.0F;
	}
}
