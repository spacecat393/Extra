package com.nali.extra;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Extra.ID)
public class Extra
{
	public final static String ID = "extra";

	@SideOnly(Side.CLIENT)
	public static byte FP;

	@SideOnly(Side.CLIENT)
	public static float
		YAW,
		PITCH,

		P_YAW,
		P_PITCH;
}
