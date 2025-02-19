//package com.nali.extra.array;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraThread;
//import net.minecraft.util.ClassInheritanceMultiMap;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//public class ExtraClassInheritanceMultiMap<T> extends ClassInheritanceMultiMap<T>
//{
//	public ExtraClassInheritanceMultiMap(Class<T> baseClassIn)
//	{
//		super(baseClassIn);
//	}
//
//	public final static byte B_LOCK = 1;
//	public byte state;
//
//	@Override
//	public boolean add(T t)
//	{
//		while ((this.state & B_LOCK) == B_LOCK)
//		{
//			try
//			{
//				Thread.sleep(ExtraThread.SLEEP);
//			}
//			catch (InterruptedException ex)
//			{
//				Nali.warn(ex);
//			}
//		}
//
//		this.state |= B_LOCK;
//		boolean b = super.add(t);
//		this.state &= 255 - B_LOCK;
//		return b;
//	}
//
////	@Override
////	public int size()
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		int i = super.size();
////		this.state &= 255 - B_LOCK;
////		return i;
////	}
//
//	@Override
//	public boolean remove(Object o)
//	{
//		while ((this.state & B_LOCK) == B_LOCK)
//		{
//			try
//			{
//				Thread.sleep(ExtraThread.SLEEP);
//			}
//			catch (InterruptedException ex)
//			{
//				Nali.warn(ex);
//			}
//		}
//
//		this.state |= B_LOCK;
//		boolean b = super.remove(o);
//		this.state &= 255 - B_LOCK;
//		return b;
//	}
//
////	@Override
////	public boolean isEmpty()
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		boolean b = super.isEmpty();
////		this.state &= 255 - B_LOCK;
////		return b;
////	}
//
//	@Override
//	public Iterator<T> iterator()
//	{
//		while ((this.state & B_LOCK) == B_LOCK)
//		{
//			try
//			{
//				Thread.sleep(ExtraThread.SLEEP);
//			}
//			catch (InterruptedException ex)
//			{
//				Nali.warn(ex);
//			}
//		}
//
//		this.state |= B_LOCK;
//		ArrayList arraylist = new ArrayList();
//		Iterator<T> it = super.iterator();
//		while (it.hasNext())
//		{
//			arraylist.add(it.next());
//		}
//		it = arraylist.iterator();
//		this.state &= 255 - B_LOCK;
//		return it;
//	}
//
////	@Override
////	public boolean contains(Object o)
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		boolean b = super.contains(o);
////		this.state &= 255 - B_LOCK;
////		return b;
////	}
//}
