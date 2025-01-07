//package com.nali.extra.render;
//
//import com.nali.da.IBothDaO;
//import com.nali.da.IBothDaS;
//import com.nali.list.data.ExtraData;
//import com.nali.render.RenderS;
//import com.nali.system.opengl.memo.client.MemoG;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//@SideOnly(Side.CLIENT)
//public class ExtraRenderS
//<
//	BD extends IBothDaO & IBothDaS
//> extends RenderS<BD>
//{
//	public ExtraRenderS(BD bd)
//	{
//		super(bd);
//	}
//
//	@Override
//	public int getTextureID(MemoG rg)
//	{
//		return ExtraData.TEXTURE_STEP + super.getTextureID(rg);
//	}
//
//	@Override
//	public int getShaderID(MemoG rg)
//	{
//		return ExtraData.SHADER_STEP + super.getShaderID(rg);
//	}
//}
