package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshape;
import com.nali.list.data.ExtraData;
import com.nali.list.data.SmallData;
import com.nali.render.RenderHelper;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderExtraGlowshape
<
	BD extends IBothDaO
> extends RenderO<BD>
{
	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

	public static void setTextureMap()
	{
		TEXTURE_MAP.clear();
		TEXTURE_MAP.put(ClientLoader.G_LIST.get(BothDaExtraGlowshape.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));
	}

	@Override
	public int getTextureBuffer(MemoG rg)
	{
		return TEXTURE_MAP.get(rg.ebo);
	}

	@Override
	public byte getExtraBit(MemoG rg)
	{
		return (byte)(super.getExtraBit(rg) | 16);
	}

	@Override
	public int getShaderID(MemoG rg)
	{
		return SmallData.SHADER_STEP + super.getShaderID(rg);
	}
}