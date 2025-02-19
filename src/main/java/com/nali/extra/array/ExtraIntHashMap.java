//package com.nali.extra.array;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraThread;
//import net.minecraft.util.IntHashMap;
//
//public class ExtraIntHashMap<E> extends IntHashMap<E>
//{
//	public final static byte B_LOCK = 1;
//	public byte state;
//
////	@Override
////	public boolean containsItem(int hashEntry)
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		boolean b = super.containsItem(hashEntry);
////		this.state &= 255 - B_LOCK;
////		return b;
////	}
//
//	@Override
//	public void addKey(int hashEntry, E valueEntry)
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
//		super.addKey(hashEntry, valueEntry);
//		this.state &= 255 - B_LOCK;
//	}
//
////	@Override
////	public E lookup(int hashEntry)
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		E e = super.lookup(hashEntry);
////		this.state &= 255 - B_LOCK;
////		return e;
////	}
//
//	@Override
//	public E removeObject(int o)
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
//		E e = super.removeObject(o);
//		this.state &= 255 - B_LOCK;
//		return e;
//	}
//}
