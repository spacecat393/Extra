package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraGlowshapeside;
import com.nali.list.data.SmallData;
import com.nali.render.RenderHelper;
import com.nali.render.RenderO;
import com.nali.small.draw.DrawDa;
import com.nali.small.render.IRenderO;
import com.nali.system.ClientLoader;
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
	BD extends IBothDaO,
	R extends RenderO<BD> & IRenderO<BD, R>
> extends RenderO<BD> implements IRenderO<BD, R>
{
	public static Map<Integer, Integer> TEXTURE_MAP = new HashMap();

	public EnumFacing enumfacing;

	public RenderExtraGlowshapeside()
	{
		super((BD)BothDaExtraGlowshapeside.IDA);
	}

	public static void setTextureMap()
	{
		TEXTURE_MAP.clear();
		TEXTURE_MAP.put(ClientLoader.G_LIST.get(BothDaExtraGlowshapeside.IDA.O_StartPart()).ebo, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));
	}

	@Override
	public int getTextureBuffer()
	{
		return TEXTURE_MAP.get(this.rg.ebo);
	}

	@Override
	public byte getExtraBit()
	{
		return (byte)(IRenderO.super.getExtraBit() | 16);
	}

	@Override
	public int getShaderID()
	{
		return SmallData.SHADER_STEP + super.getShaderID();
	}

	@Override
	public void startDrawLater(DrawDa drawda)
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
		IRenderO.super.startDrawLater(drawda);
		GL11.glPopMatrix();
	}

	@Override
	public R getR()
	{
		return (R)this;
	}
}