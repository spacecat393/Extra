//package com.nali.extra;
//
//import com.nali.Nali;
//import org.lwjgl.opengl.GL15;
//import org.lwjgl.opengl.GL33;
//
//import java.nio.ByteBuffer;
//import java.nio.ByteOrder;
//import java.nio.IntBuffer;
//
//public class ExtraGTime
//{
//	private static int QUERIES;
//	private static long START;
//	private static long ELAPSE;
//
//	private static int FRAME;
//	private static long ACCUMULATE;
//
//	private static IntBuffer INTBUFFER = ByteBuffer.allocateDirect(16 << 1).order(ByteOrder.nativeOrder()).asIntBuffer();
//
//	public static void init()
//	{
//		QUERIES = GL15.glGenQueries();
//	}
//
//	public static void start()
//	{
//		GL15.glBeginQuery(GL33.GL_TIME_ELAPSED, QUERIES);//nanoseconds
//		START = System.nanoTime();
//	}
//
//	public static void end()
//	{
//		GL15.glEndQuery(GL33.GL_TIME_ELAPSED);
//
////		int available = 0;
////		while (available == 0)
////		{
////			GL15.glGetQueryObject(QUERIES, GL15.GL_QUERY_RESULT_AVAILABLE, INTBUFFER);
////			available = INTBUFFER.get(0);
//////			Nali.warn("LOST");
////		}
//
//		GL15.glGetQueryObject(QUERIES, GL15.GL_QUERY_RESULT, INTBUFFER);
//		ELAPSE = INTBUFFER.get(0);
//
//		ACCUMULATE += ELAPSE;
//		FRAME++;
//
//		if (FRAME == 144)
//		{
//			Nali.warn("144 " + ACCUMULATE / 1_000_000_000.0D);
//
//			ACCUMULATE = 0;
//			FRAME = 0;
//		}
//
////		Nali.warn("ELAPSE " + ELAPSE);
////		Nali.warn("END " + ((System.nanoTime() - START) / 1_000_000_000.0D));
//	}
//}
