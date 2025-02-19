//package com.nali.extra.array;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraThread;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.TreeSet;
//
//public class ExtraTreeSet<E> extends TreeSet<E>
//{
//	public final static byte B_LOCK = 1;
//	public byte state;
//
//	@Override
//	public boolean add(E e)
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
//		boolean b = super.add(e);
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
//	public Iterator<E> iterator()
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
////		TreeSet treeset = new TreeSet();
//		ArrayList arraylist = new ArrayList();
//		Iterator<E> ie = super.iterator();
//		while (ie.hasNext())
//		{
////			treeset.add(ie.next());
//			arraylist.add(ie.next());
//		}
////		ie = treeset.iterator();
//		ie = arraylist.iterator();
//		this.state &= 255 - B_LOCK;
//		return ie;
//	}
//
//	@Override
//	public E first()
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
//		E e = super.first();
//		this.state &= 255 - B_LOCK;
//		return e;
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
