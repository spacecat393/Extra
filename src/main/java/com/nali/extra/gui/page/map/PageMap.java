package com.nali.extra.gui.page.map;

import com.nali.extra.ExtraColor;
import com.nali.gui.box.Box;
import com.nali.gui.box.BoxV;
import com.nali.gui.page.Page;
import com.nali.list.data.NaliData;
import com.nali.render.RenderHelper;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

@SideOnly(Side.CLIENT)
public class PageMap extends Page
{
//	public BoxImage[] boxcolor_array;
	public float[] vs_float_array = new float[2];
	public float[] cw_float_array = new float[]{1, 1, 1, 0.75F};
	public float[] cb_float_array = new float[]{0.5F, 0.5F, 0.5F, 0.75F};

//	public final static byte BS_ENTER_MODE = 1;
//	public byte state;
	public float[]
		float_array,
		w_float_array;

	public final static byte B_XZ = 0;
	public final static byte B_XY = 1;
	public final static byte B_ZY = 2;
	public byte xyz;

	public BlockPos blockpos;

	public PageMap()
	{
		this.c_float_array[3] = 0.5F;
		this.blockpos = Minecraft.getMinecraft().player.getPosition();
	}

	@Override
	public void init()
	{
//		float size = WIDTH / 16.0F;
		int min = Math.min(Box.WIDTH, Box.HEIGHT);
		float size = min / 16.0F;
		this.boxcolor_array = new BoxImage[16*16+1];
		float x, y;
		float fixx, fixy;
		if (min == Box.WIDTH)
		{
			fixy = (Box.HEIGHT - Box.WIDTH) / 2.0F;
			y = fixy;
			fixx = 0;
			x = 0;
		}
		else
		{
			fixy = 0;
			y = 0;
			fixx = (Box.WIDTH - Box.HEIGHT) / 2.0F;
			x = fixx;
		}

		for (int i = 0; i < 16*16; ++i)
		{
			if (i != 0 && i % 16 == 0)
			{
				x = fixx;
				y += size;
			}

			BoxImage boxcolor = new BoxImage();
			boxcolor.x0 = x;
			boxcolor.y0 = y;
			boxcolor.x1 = x + size;
			boxcolor.y1 = y + size;

			boxcolor.u1 = 32;
			boxcolor.v1 = 32;
			boxcolor.t_width = 32;
			boxcolor.t_height = 32;
			this.boxcolor_array[i] = boxcolor;

			x += size;
		}

		BoxImage boxcolor = new BoxImage();
		float h2_size = size / 4.0F;
		boxcolor.x0 = fixx + h2_size;
		boxcolor.y0 = fixy + h2_size;
		boxcolor.x1 = fixx + size - h2_size;
		boxcolor.y1 = fixy + size - h2_size;

		boxcolor.u1 = 32;
		boxcolor.v1 = 32;
		boxcolor.t_width = 32;
		boxcolor.t_height = 32;
		this.boxcolor_array[16*16] = boxcolor;
	}

	@Override
	public void gen()
	{
		this.clear();

		this.float_array[BoxV.B_FA_V_WIDTH] = Box.WIDTH;
		this.float_array[BoxV.B_FA_V_HEIGHT] = Box.HEIGHT;

		for (BoxImage boxcolor : this.boxcolor_array)
		{
			boxcolor.v_width = WIDTH;
			boxcolor.v_height = HEIGHT;
			boxcolor.gen();
		}
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

		MemoS rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP/* + 1*/);
		OpenGlHelper.glUseProgram(rs.program);
		int v = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v);
		byte b = 0;
		byte bb = 0;
//		byte bb = 0;

//		GL13.glActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));

		for (int i = 0; i < 16*16; ++i)
		{
			BoxImage boxcolor = this.boxcolor_array[i];
			boxcolor.draw(rs, this.v_float_array, (b++ + bb) % 2 == 0 ? this.cb_float_array : this.cw_float_array);
			if (b % 16 == 0)
			{
				bb ^= 1;
//				if ((bb & 1) == 1)
//				{
//					b = -1;
//				}
//				else
//				{
//					b = 1;
//				}
//				bb ^= 1;
			}
		}
		this.c_float_array[0] = ExtraColor.RED;
		this.c_float_array[1] = ExtraColor.GREEN;
		this.c_float_array[2] = ExtraColor.BLUE;
		this.boxcolor_array[16*16].draw(rs, this.vs_float_array, this.c_float_array);
		GL20.glDisableVertexAttribArray(v);

//		GL11.glColor4f(r, g, b, a);
	}

	@Override
	public void clear()
	{
		for (BoxImage boxcolor : this.boxcolor_array)
		{
			boxcolor.clear();
		}
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
			this.init();
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
