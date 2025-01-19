package com.nali.extra;

import com.nali.render.RenderO;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;

public class ExtraCubeLine
{
	public static int VBO;
	public final static int VERTEX_COUNT = 24;
	public final static int FLOATS_PER_VERTEX = 3;

	public static void init()
	{
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		VBO = OpenGlHelper.glGenBuffers();

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, VBO);

//		ARBVertexBufferObject.glBufferDataARB();
		GL15.glBufferData(OpenGlHelper.GL_ARRAY_BUFFER, VERTEX_COUNT * FLOATS_PER_VERTEX * Float.BYTES, GL15.GL_DYNAMIC_DRAW);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
	}

	public static void update(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)
	{
		float[] v3_float_array =
		{
			//b
			(float) minX, (float) minY, (float) minZ, (float) maxX, (float) minY, (float) minZ,
			(float) maxX, (float) minY, (float) minZ, (float) maxX, (float) minY, (float) maxZ,
			(float) maxX, (float) minY, (float) maxZ, (float) minX, (float) minY, (float) maxZ,
			(float) minX, (float) minY, (float) maxZ, (float) minX, (float) minY, (float) minZ,

			//t
			(float) minX, (float) maxY, (float) minZ, (float) maxX, (float) maxY, (float) minZ,
			(float) maxX, (float) maxY, (float) minZ, (float) maxX, (float) maxY, (float) maxZ,
			(float) maxX, (float) maxY, (float) maxZ, (float) minX, (float) maxY, (float) maxZ,
			(float) minX, (float) maxY, (float) maxZ, (float) minX, (float) maxY, (float) minZ,

			//l r
			(float) minX, (float) minY, (float) minZ, (float) minX, (float) maxY, (float) minZ,
			(float) maxX, (float) minY, (float) minZ, (float) maxX, (float) maxY, (float) minZ,
			(float) maxX, (float) minY, (float) maxZ, (float) maxX, (float) maxY, (float) maxZ,
			(float) minX, (float) minY, (float) maxZ, (float) minX, (float) maxY, (float) maxZ,
		};

		FloatBuffer floatbuffer = BufferUtils.createFloatBuffer(v3_float_array.length);
		floatbuffer.put(v3_float_array).flip();

		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, VBO);
		GL15.glBufferSubData(OpenGlHelper.GL_ARRAY_BUFFER, 0, floatbuffer);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
	}

	public static void draw()
	{
		GL11.glGetInteger(GL15.GL_ARRAY_BUFFER_BINDING, RenderO.INTBUFFER);
		int gl_array_buffer_binding = RenderO.INTBUFFER.get(0);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, VBO);

		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);

		GL11.glDrawArrays(GL11.GL_LINES, 0, VERTEX_COUNT);

		GL20.glDisableVertexAttribArray(0);

		OpenGlHelper.glBindBuffer(OpenGlHelper.GL_ARRAY_BUFFER, gl_array_buffer_binding);
	}
}
