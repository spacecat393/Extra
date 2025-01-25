package com.nali.extra;

import com.nali.list.data.ExtraData;
import com.nali.render.RenderO;
import com.nali.system.ClientLoader;
import com.nali.system.opengl.memo.client.MemoA;
import com.nali.system.opengl.memo.client.MemoS;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.*;

import java.nio.ByteBuffer;

@SideOnly(Side.CLIENT)
public class ExtraFBO
{
	public static int
		VBO,

		FBO0,
		FBO1,

		FBCT0,
		FBCT1,

		FBDST0,
		FBDST1,

		WIDTH, HEIGHT;

//	public static byte FRAME;

	public static void init()
	{
		WIDTH = Display.getWidth();
		HEIGHT = Display.getHeight();
		GL11.glViewport(0, 0, WIDTH, HEIGHT);

		//s0-vbo
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		VBO = MemoA.genBuffer(MemoA.createFloatByteBuffer(new float[]
		{
			-1, 1, 0.0F, 1.0F,
			-1, -1, 0.0F, 0.0F,
			1, -1, 1.0F, 0.0F,

			-1, 1, 0.0F, 1.0F,
			1, -1, 1.0F, 0.0F,
			1, 1, 1.0F, 1.0F
		}));

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
		//e0-vbo

//		GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, RenderO.INTBUFFER);
//		int gl_draw_framebuffer_binding = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, RenderO.INTBUFFER);
//		int gl_read_framebuffer_binding = RenderO.INTBUFFER.get(0);

		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);

		FBO0 = GL30.glGenFramebuffers();
		FBO1 = GL30.glGenFramebuffers();

		FBCT0 = GL11.glGenTextures();
		FBCT1 = GL11.glGenTextures();

		FBDST0 = GL11.glGenTextures();
		FBDST1 = GL11.glGenTextures();

		setFBO(FBO1, FBCT1, FBDST1);
		setFBO(FBO0, FBCT0, FBDST0);

//		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
//		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, RenderO.INTBUFFER.get(0));

//		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, FBO0);
	}

	public static void update()
	{
		int width = Display.getWidth();
		int height = Display.getHeight();

		if (WIDTH != width || HEIGHT != height)
		{
			WIDTH = width;
			HEIGHT = height;

			GL11.glGetInteger(GL30.GL_DRAW_FRAMEBUFFER_BINDING, RenderO.INTBUFFER);
			int gl_draw_framebuffer_binding = RenderO.INTBUFFER.get(0);
			GL11.glGetInteger(GL30.GL_READ_FRAMEBUFFER_BINDING, RenderO.INTBUFFER);
			int gl_read_framebuffer_binding = RenderO.INTBUFFER.get(0);

			GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);

			setFBO(FBO0, FBCT0, FBDST0);
			setFBO(FBO1, FBCT1, FBDST1);

			GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, gl_draw_framebuffer_binding);
			GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, gl_read_framebuffer_binding);

			GL11.glBindTexture(GL11.GL_TEXTURE_2D, RenderO.INTBUFFER.get(0));
		}
	}

	public static void setFBO(int fbo, int fbct, int fbdst)
	{
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbct);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, WIDTH, HEIGHT, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbdst);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_DEPTH24_STENCIL8, WIDTH, HEIGHT, 0, GL30.GL_DEPTH_STENCIL, GL30.GL_UNSIGNED_INT_24_8, (ByteBuffer)null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, fbct, 0);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, GL11.GL_TEXTURE_2D, fbdst, 0);
	}

	public static void mix()
	{
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
//		GL11.glClearColor(0.0F, 0.0F, 1.0F, 1.0F);
//		GL11.glDisable(GL11.GL_DEPTH_TEST);
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		GL11.glDisable(GL11.GL_STENCIL_TEST);
//		GL11.glDisable(GL11.GL_BLEND);
//		GL11.glDisable(GL11.GL_ALPHA_TEST);
//		GL11.glDisable(GL11.GL_SCISSOR_TEST);

		GL11.glGetInteger(GL13.GL_ACTIVE_TEXTURE, RenderO.INTBUFFER);
		int gl_active_texture = RenderO.INTBUFFER.get(0);
		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
		int gl_texture_binding_2d_0 = RenderO.INTBUFFER.get(0);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, FBCT0);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);

		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
		GL11.glGetInteger(GL11.GL_TEXTURE_BINDING_2D, RenderO.INTBUFFER);
		int gl_texture_binding_2d_1 = RenderO.INTBUFFER.get(0);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, FBCT1);
//		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
		int gl_current_program = RenderO.INTBUFFER.get(0);
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		MemoS rs = ClientLoader.S_LIST.get(ExtraData.SHADER_STEP + 1);
		OpenGlHelper.glUseProgram(rs.program);
		int v0 = rs.attriblocation_int_array[0];
		GL20.glEnableVertexAttribArray(v0);
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, VBO);
		GL20.glVertexAttribPointer(rs.attriblocation_int_array[0], 4, GL11.GL_FLOAT, false, 0, 0);

//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);

		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);

		GL20.glDisableVertexAttribArray(v0);

		OpenGlHelper.glUseProgram(gl_current_program);
		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);

//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_1);
		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
		OpenGlHelper.setActiveTexture(gl_active_texture);

//		//s0-drawColor
//
//		//s1-take
//		GL11.glGetInteger(GL20.GL_CURRENT_PROGRAM, RenderO.INTBUFFER);
//		gl_current_program = RenderO.INTBUFFER.get(0);
//		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
//		gl_array_buffer_binding = RenderO.INTBUFFER.get(0);
//		//e1-take
//
//		rs = ClientLoader.S_LIST.get(NaliData.SHADER_STEP + 1);
//		OpenGlHelper.glUseProgram(rs.program);
//		int v = rs.attriblocation_int_array[0];
//		GL20.glEnableVertexAttribArray(v);
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, Page.QUAD2D_ARRAY_BUFFER);
//		GL20.glVertexAttribPointer(v, 2, GL11.GL_FLOAT, false, 0, 0);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(new float[2]);
//		RenderO.FLOATBUFFER.flip();
//		OpenGlHelper.glUniform2(rs.uniformlocation_int_array[0], RenderO.FLOATBUFFER);
//
//		RenderO.FLOATBUFFER.clear();
//		RenderO.FLOATBUFFER.put(new float[]{0.0F, 1.0F, 0.0F, 0.5F});
//		RenderO.FLOATBUFFER.flip();
////		OpenGlHelper.glUniform4(rs.uniformlocation_int_array[1], RenderO.FLOATBUFFER);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//		GL20.glDisableVertexAttribArray(v);
//
//		//s1-free
//		OpenGlHelper.glUseProgram(gl_current_program);
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//		//e1-free
//
//		//e0-drawColor

//		//draw again
//		rs = ClientLoader.S_LIST.get(ExtraData.SHADER_STEP + 1);
//		v0 = rs.attriblocation_int_array[0];
//		OpenGlHelper.glUseProgram(rs.program);
//		GL20.glEnableVertexAttribArray(v0);
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, VBO);
//		GL20.glVertexAttribPointer(v0, 4, GL11.GL_FLOAT, false, 0, 0);
//
////		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[0], 0);
//		OpenGlHelper.glUniform1i(rs.uniformlocation_int_array[1], 1);
//
//		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 6);
//
//		GL20.glDisableVertexAttribArray(v0);
//
//		OpenGlHelper.glUseProgram(gl_current_program);
//		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
//
////		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE1);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_1);
//		OpenGlHelper.setActiveTexture(GL13.GL_TEXTURE0);
//		GL11.glBindTexture(GL11.GL_TEXTURE_2D, gl_texture_binding_2d_0);
//		OpenGlHelper.setActiveTexture(gl_active_texture);
	}
}
