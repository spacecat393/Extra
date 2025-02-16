package com.nali.extra.gui.page.map;

import com.nali.gui.box.BoxColor;
import com.nali.gui.page.Page;
import com.nali.list.data.NaliData;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class PageMap extends Page
{
	public BoxColor boxcolor = new BoxColor();

	@Override
	public void init()
	{
		this.boxcolor.x1 = WIDTH / 16;
		this.boxcolor.y1 = HEIGHT / 16;
	}

	@Override
	public void gen()
	{
		this.boxcolor.v_width = WIDTH;
		this.boxcolor.v_height = HEIGHT;
		this.boxcolor.gen();
	}

	@Override
	public void draw()
	{
//		RenderO.FLOATBUFFER.limit(16);
//		GL11.glGetFloat(GL11.GL_CURRENT_COLOR, RenderO.FLOATBUFFER);
//		float r = RenderO.FLOATBUFFER.get(0);
//		float g = RenderO.FLOATBUFFER.get(1);
//		float b = RenderO.FLOATBUFFER.get(2);
//		float a = RenderO.FLOATBUFFER.get(3);
//		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);
		this.boxcolor.draw(rs, new float[2], new float[]{1, 1, 1, 1});
		GL20.glDisableVertexAttribArray(v);

//		GL11.glColor4f(r, g, b, a);
	}

	@Override
	public void clear()
	{
		OpenGlHelper.glDeleteBuffers(this.boxcolor.array_buffer);
	}

	@Override
	public void render()
	{
		this.take();

		int width = Display.getWidth();
		int height = Display.getHeight();

		if (WIDTH != width || HEIGHT != height)
		{
			WIDTH = width;
			HEIGHT = height;
			GL11.glViewport(0, 0, width, height);
			this.gen();
		}

		this.draw();
		this.free();
	}

	@Override
	public void exit()
	{
	}
}
