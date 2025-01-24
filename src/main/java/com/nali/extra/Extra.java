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

//	@SideOnly(Side.CLIENT)
//	public static int FB1;
//	@SideOnly(Side.CLIENT)
//	public static int FBT1;

//	@SideOnly(Side.CLIENT)
//	public static void init()
//	{
//		FB1 = GL30.glGenFramebuffers();
//		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, FB1);
//
//		FBT1 = GL11.glGenTextures();
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, FBT1);
//		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, 1920, 1080, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
//		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
//		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, FBT1, 0);
//
//		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
//	}
}
