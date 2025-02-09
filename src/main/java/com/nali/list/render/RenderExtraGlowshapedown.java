package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshapedown;
import com.nali.list.data.SmallData;
import com.nali.render.RenderHelper;
import com.nali.render.RenderO;
import com.nali.small.draw.DrawMap;
import com.nali.small.render.IRenderO;
import com.nali.system.ClientLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderExtraGlowshapedown
<
	BD extends IBothDaO,
	R extends RenderO<BD> & IRenderO<BD, R>
> extends RenderO<BD> implements IRenderO<BD, R>
{
	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

	public RenderExtraGlowshapedown()
	{
		super((BD)BothDaExtraGlowshapedown.IDA);
	}

	public static void setTextureMap()
	{
		TEXTURE_MAP.clear();
		TEXTURE_MAP.put(ClientLoader.G_LIST.get(BothDaExtraGlowshapedown.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));
	}

	@Override
	public int getTextureBuffer()
	{
		return TEXTURE_MAP.get(this.rg.ebo);
	}

	@Override
	public byte getExtraBit()
	{
		return (byte)(IRenderO.super.getExtraBit() | DrawMap.B_TILE_ENTITY);
	}

	@Override
	public int getShaderID()
	{
		return SmallData.SHADER_STEP + super.getShaderID();
	}

	@Override
	public R getR()
	{
		return (R)this;
	}
}