package com.nali.extra.gui.page.map;

import com.nali.extra.ExtraColor;
import com.nali.gui.box.Box;
import com.nali.gui.box.BoxV;
import com.nali.gui.box.BoxVT;
import com.nali.gui.page.Page;
import com.nali.list.data.NaliData;
import com.nali.render.RenderHelper;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.util.HashMap;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class PageMap extends Page
{
//	public BoxImage[] boxcolor_array;
	public float[] vs_float_array = new float[2];
//	public float[] cw_float_array = new float[]{1, 1, 1, 0.75F};
//	public float[] cb_float_array = new float[]{0.5F, 0.5F, 0.5F, 0.75F};

//	public final static byte BS_ENTER_MODE = 1;
//	public byte state;
	public float[]
		float_array,
		w_float_array;
	public int[]
//		texture_id_int_array,
		texture_int_array;
	public int
		size,
		half,
		array_buffer = -1;

	public final static byte B_XZ = 0;
	public final static byte B_XY = 1;
	public final static byte B_ZY = 2;
	public byte xyz;

	public BlockPos blockpos;

	public PageMap()
	{
		this.c_float_array[3] = 0.5F;
		this.blockpos = Minecraft.getMinecraft().player.getPosition();

		this.float_array = new float[BoxV.B_FAL + BoxVT.B_FAL];
		this.half = 16;
		this.size = this.half * this.half;
//		this.texture_id_int_array = new int[this.size];
		this.texture_int_array = new int[this.size];
		this.w_float_array = new float[(this.size + 1) * BoxVT.B_WFAL];

		for (int i = 0; i < this.size; ++i)
		{
			this.float_array[BoxVT.B_FA_U1] = 32;
			this.float_array[BoxVT.B_FA_V1] = 32;

			this.float_array[BoxVT.B_FA_T_WIDTH] = 32;
			this.float_array[BoxVT.B_FA_T_HEIGHT] = 32;
		}
	}

	@Override
	public void init()
	{
		EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;

		int z = -1;
//		int string_map_index = 0;
		Map<String, Integer> string_map = new HashMap();
		if (this.xyz == B_XZ)
		{
			//need check
			for (int i = 0; i < this.size; ++i)
			{
				int x = i % this.half;
				if (x == 0)
				{
					++z;
				}
//				Nali.warn("this.blockpos.add(x, 0, z) " + this.blockpos.add(x, 0, z));
				String string = "textures/blocks/" + entityplayersp.world.getBlockState(this.blockpos.add(x, 0, z)).getBlock().getRegistryName().getPath() + ".png";
//				Integer integer = string_map.get(string);
//				if (integer == null)
//				{
//					integer = string_map_index;
//					string_map.put(string, string_map_index);
//					++string_map_index;
//				}
//				this.texture_id_int_array[i] = integer;
//				Nali.warn(entityplayersp.world.getBlockState(this.blockpos.add(x, 0, 0)).getBlock().getRegistryName().getPath());
				Integer texture = string_map.get(string);
				if (texture == null)
				{
					texture = RenderHelper.getTextureBuffer(new ResourceLocation(string));
					if (texture == -1)
					{
						texture = TextureUtil.MISSING_TEXTURE.getGlTextureId();
					}
					string_map.put(string, texture);
				}
				this.texture_int_array[i] = texture;
			}
		}
//		int i = 0;
//		Set<String> set = string_map.keySet();
//		this.texture_int_array = new int[set.size()];
//		for (String string : set)
//		{
//			int texture = RenderHelper.getTextureBuffer(new ResourceLocation(string));
////			Nali.warn("texture " + texture);
//			if (texture == -1)
//			{
//				texture = TextureUtil.MISSING_TEXTURE.getGlTextureId();
////				Nali.warn("ntexture " + texture);
//			}
//			this.texture_int_array[i++] = texture;
//		}
	}

	@Override
	public void gen()
	{
		this.clear();

		this.float_array[BoxV.B_FA_V_WIDTH] = Box.WIDTH;
		this.float_array[BoxV.B_FA_V_HEIGHT] = Box.HEIGHT;

		//		float size = WIDTH / 16.0F;
		int min = Math.min(Box.WIDTH, Box.HEIGHT);
		float size = min / 16.0F;
//		this.boxcolor_array = new BoxImage[16*16+1];
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

		for (int i = 0; i < this.size; ++i)
		{
			if (i != 0 && i % this.half == 0)
			{
				x = fixx;
				y += size;
			}

//			BoxImage boxcolor = new BoxImage();
//			boxcolor.x0 = x;
//			boxcolor.y0 = y;
//			boxcolor.x1 = x + size;
//			boxcolor.y1 = y + size;
//
//			boxcolor.u1 = 32;
//			boxcolor.v1 = 32;
//			boxcolor.t_width = 32;
//			boxcolor.t_height = 32;
//			this.boxcolor_array[i] = boxcolor;
			this.float_array[BoxV.B_FA_X0] = x;
			this.float_array[BoxV.B_FA_Y0] = y;
			this.float_array[BoxV.B_FA_X1] = x + size;
			this.float_array[BoxV.B_FA_Y1] = y + size;
			BoxVT.set(this.float_array, this.w_float_array, i * BoxVT.B_WFAL);

			x += size;
		}

//		BoxImage boxcolor = new BoxImage();
//		float h2_size = size / 4.0F;
//		boxcolor.x0 = fixx + h2_size;
//		boxcolor.y0 = fixy + h2_size;
//		boxcolor.x1 = fixx + size - h2_size;
//		boxcolor.y1 = fixy + size - h2_size;
//
//		boxcolor.u1 = 32;
//		boxcolor.v1 = 32;
//		boxcolor.t_width = 32;
//		boxcolor.t_height = 32;
//		this.boxcolor_array[16*16] = boxcolor;
//
//		for (BoxImage boxcolor : this.boxcolor_array)
//		{
//			boxcolor.v_width = WIDTH;
//			boxcolor.v_height = HEIGHT;
//			boxcolor.gen();
//		}
		float h2_size = size / 4.0F;
//		EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
//		entityplayersp.rotationYaw;
		Vec3d vec3d = Minecraft.getMinecraft().player.getLookVec();
		float xx = (float)(h2_size * vec3d.x);
		float zz = (float)(h2_size * vec3d.z);

		this.float_array[BoxV.B_FA_X0] = fixx + h2_size + xx;
		this.float_array[BoxV.B_FA_Y0] = fixy + h2_size + zz;
		this.float_array[BoxV.B_FA_X1] = fixx + size - h2_size + xx;
		this.float_array[BoxV.B_FA_Y1] = fixy + size - h2_size + zz;
//		EnumFacing enumfacing = Minecraft.getMinecraft().player.getHorizontalFacing();
//		switch (enumfacing)
//		{
//			case NORTH:
//				this.float_array[BoxV.B_FA_Y0] -= h2_size;
//				this.float_array[BoxV.B_FA_Y1] -= h2_size;
//				break;
//			case SOUTH:
//				this.float_array[BoxV.B_FA_Y0] += h2_size;
//				this.float_array[BoxV.B_FA_Y1] += h2_size;
//				break;
//			case WEST:
//				this.float_array[BoxV.B_FA_X0] -= h2_size;
//				this.float_array[BoxV.B_FA_X1] -= h2_size;
//				break;
//			case EAST:
//				this.float_array[BoxV.B_FA_X0] += h2_size;
//				this.float_array[BoxV.B_FA_X1] += h2_size;
//		}
		BoxVT.set(this.float_array, this.w_float_array, this.size * BoxVT.B_WFAL);
		this.array_buffer = MemoA.genBuffer(MemoA.createFloatByteBuffer(this.w_float_array));
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

//		GL13.glActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, RenderHelper.getTextureBuffer(new ResourceLocation("textures/blocks/glowstone.png")));

//		byte b = 0;
//		byte bb = 0;
////		byte bb = 0;
//		for (int i = 0; i < 16*16; ++i)
//		{
//			BoxImage boxcolor = this.boxcolor_array[i];
//			boxcolor.draw(rs, this.v_float_array, (b++ + bb) % 2 == 0 ? this.cb_float_array : this.cw_float_array);
//			if (b % 16 == 0)
//			{
//				bb ^= 1;
////				if ((bb & 1) == 1)
////				{
////					b = -1;
////				}
////				else
////				{
////					b = 1;
////				}
////				bb ^= 1;
//			}
//		}
		for (int i = 0; i < this.size; ++i)
		{
//			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture_int_array[this.texture_id_int_array[i]]);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture_int_array[i]);
			Box.draw(rs, this.v_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, i * 6, 6);
		}

//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, );
//		Box.draw(rs, this.v_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, 0, this.size * 6);
		this.c_float_array[0] = ExtraColor.RED;
		this.c_float_array[1] = ExtraColor.GREEN;
		this.c_float_array[2] = ExtraColor.BLUE;
//		this.boxcolor_array[16*16].draw(rs, this.vs_float_array, this.c_float_array);
		Box.draw(rs, this.vs_float_array, this.c_float_array, this.array_buffer, BoxVT.B_AP_SIZE, this.size * 6, 6);
		this.c_float_array[0] = 1;
		this.c_float_array[1] = 1;
		this.c_float_array[2] = 1;
		GL20.glDisableVertexAttribArray(v);

//		GL11.glColor4f(r, g, b, a);
	}

	@Override
	public void clear()
	{
		if (this.array_buffer != -1)
		{
			OpenGlHelper.glDeleteBuffers(this.array_buffer);
		}
	}

	@Override
	public void render()
	{
		this.take();

		int width = Display.getWidth();
		int height = Display.getHeight();

		if (Box.WIDTH != width || Box.HEIGHT != height)
		{
			Box.WIDTH = width;
			Box.HEIGHT = height;
			GL11.glViewport(0, 0, width, height);
//			this.init();
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
