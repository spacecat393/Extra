package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshapedown;
import com.nali.list.data.SmallData;
import com.nali.render.RenderHelper;
import com.nali.render.RenderO;
import com.nali.small.render.IRenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderExtraGlowshapedown
<
	BD extends IBothDaO
> extends RenderO<BD> implements IRenderO<BD, RenderExtraGlowshapedown<BD>>
{
	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

	public static void setTextureMap()
	{
		TEXTURE_MAP.clear();
		TEXTURE_MAP.put(ClientLoader.G_LIST.get(BothDaExtraGlowshapedown.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));
	}

	@Override
	public int getTextureBuffer(MemoG rg)
	{
		return TEXTURE_MAP.get(rg.ebo);
	}

//	@Override
//	public byte getExtraBit(MemoG rg)
//	{
//		return (byte)(super.getExtraBit(rg) | 16);
//	}

	@Override
	public int getShaderID(MemoG rg)
	{
		return SmallData.SHADER_STEP + super.getShaderID(rg);
	}
}