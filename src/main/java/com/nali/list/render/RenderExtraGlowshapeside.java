package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshapeside;
import com.nali.list.data.SmallData;
import com.nali.render.RenderHelper;
import com.nali.render.RenderO;
import com.nali.small.draw.DrawDa;
import com.nali.small.render.IRenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoG;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class RenderExtraGlowshapeside
<
	BD extends IBothDaO
> extends RenderO<BD> implements IRenderO<BD, RenderExtraGlowshapeside<BD>>
{
	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

	public EnumFacing enumfacing;

	public static void setTextureMap()
	{
		TEXTURE_MAP.clear();
		TEXTURE_MAP.put(ClientLoader.G_LIST.get(BothDaExtraGlowshapeside.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));
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

	@Override
	public void startDrawLater(BD bd, RenderExtraGlowshapeside<BD> r, DrawDa drawda)
	{
		GL11.glPushMatrix();
		switch (this.enumfacing)
		{
			//North
			case NORTH:
				GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
				break;
			//West
			case WEST:
				GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
				break;
			//South
			case SOUTH:
				GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
				break;
			//EAST
		}
		IRenderO.super.startDrawLater(bd, r, drawda);
		GL11.glPopMatrix();
	}
}