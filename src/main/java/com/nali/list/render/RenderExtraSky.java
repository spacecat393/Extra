package com.nali.list.render;

import com.nali.da.IBothDaO;
import com.nali.list.data.ExtraData;
import com.nali.render.RenderO;
import com.nali.system.opengl.memo.client.MemoG;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderExtraSky
<
	BD extends IBothDaO
> extends RenderO<BD>
{
	@Override
	public void setUniform(MemoG rg, MemoS rs, int index)
	{
		FLOATBUFFER.limit(16);
		GL11.glGetFloat(GL11.GL_PROJECTION_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[0], false, FLOATBUFFER);
		GL11.glGetFloat(GL11.GL_MODELVIEW_MATRIX, FLOATBUFFER);
		OpenGlHelper.glUniformMatrix4(rs.uniformlocation_int_array[1], false, FLOATBUFFER);
//		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, FLOATBUFFER);
//		FLOATBUFFER.limit(4);
//		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[2], FLOATBUFFER);
		this.setTextureUniform(rg, rs);
	}

	@Override
	public int getTextureID(MemoG rg)
	{
		return ExtraData.TEXTURE_STEP + super.getTextureID(rg);
	}

	@Override
	public int getShaderID(MemoG rg)
	{
		return ExtraData.SHADER_STEP + super.getShaderID(rg);
	}
}
