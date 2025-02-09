package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.da.BothDaExtraSky;
import com.nali.list.data.ExtraData;
import com.nali.render.RenderO;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderExtraSky
<
	BD extends IBothDaO
> extends RenderO<BD>
{
	public static RenderExtraSky RENDEREXTRASKY;
	public static float X_ANGLE/* = 359.0F*/, Z_ANGLE/* = 359.0F*/;

	public RenderExtraSky()
	{
		super((BD)BothDaExtraSky.IDA);
	}

	@Override
	public void setUniform()
	{
		FLOATBUFFER.limit(16);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, FLOATBUFFER);
//		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, FLOATBUFFER);
//		FLOATBUFFER.limit(4);
//		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], FLOATBUFFER);
		this.setTextureUniform();
	}

	@Override
	public int getTextureID()
	{
		return ExtraData.TEXTURE_STEP + super.getTextureID();
	}

	@Override
	public int getShaderID()
	{
		return ExtraData.SHADER_STEP + super.getShaderID();
	}
}
