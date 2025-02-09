package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraBox;
import com.nali.list.data.ExtraData;
import com.nali.list.data.SmallData;
import com.nali.render.RenderO;
import com.nali.small.draw.DrawMap;
import com.nali.small.render.IRenderO;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

import static com.nali.system.ClientLoader.G_LIST;

@SideOnly(Side.CLIENT)
public class RenderExtraBox
<
	BD extends IBothDaO,
	R extends RenderO<BD> & IRenderO<BD, R>
> extends RenderO<BD> implements IRenderO<BD, R>
{
	public static Map<Integer, Integer> COLOR_MAP = new HashMap();//ebo hex
	public byte extra_bit;

	public RenderExtraBox()
	{
		super((BD)BothDaExtraBox.IDA);
	}

	public static void setTextureMap()
	{
		COLOR_MAP.clear();
		COLOR_MAP.put((G_LIST.get(BothDaExtraBox.IDA.O_StartPart() + 1)).ebo, 0xFFFFFFFF);
		COLOR_MAP.put((G_LIST.get(BothDaExtraBox.IDA.O_StartPart() + 2)).ebo, 0xFFffc196);
		COLOR_MAP.put((G_LIST.get(BothDaExtraBox.IDA.O_StartPart() + 3)).ebo, 0xFFABB265);
	}

	@Override
	public void setTextureUniform()
	{
		Integer integer = COLOR_MAP.get(this.rg.ebo);
		if (integer == null)
		{
			this.extra_bit = 0;
			super.setTextureUniform();
		}
		else
		{
			this.extra_bit = DrawMap.B_COLOR;
			int color = this.getTextureID();
			FLOATBUFFER.clear();
			FLOATBUFFER.put(((color >> 16) & 0xFF) / 255.0F);
			FLOATBUFFER.put(((color >> 8) & 0xFF) / 255.0F);
			FLOATBUFFER.put((color & 0xFF) / 255.0F);
			FLOATBUFFER.put(((color >> 24) & 0xFF) / 255.0F);
			FLOATBUFFER.flip();
			OpenGlHelper.glUniform4(rs.uniformlocation_int_array[5], FLOATBUFFER);
		}
	}

	@Override
	public int getTextureID()
	{
		Integer integer = COLOR_MAP.get(this.rg.ebo);
		if (integer == null)
		{
			this.extra_bit = 0;
			return ExtraData.TEXTURE_STEP + super.getTextureID();
		}
		else
		{
			this.extra_bit = DrawMap.B_COLOR;
			return integer;
		}
	}

	@Override
	public byte getExtraBit()
	{
		return this.extra_bit;
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
