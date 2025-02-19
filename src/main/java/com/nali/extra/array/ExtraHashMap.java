//package com.nali.extra.array;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraThread;
//
//import java.util.*;
//
//public class ExtraHashMap<K, V> extends HashMap<K, V>
//{
//	public final static byte B_LOCK = 1;
//	public byte state;
//
//	@Override
//	public V put(K k, V v)
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
//		V vv = super.put(k, v);
//		this.state &= 255 - B_LOCK;
//		return vv;
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
//	public V remove(Object o)
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
//		V v = super.remove(o);
//		this.state &= 255 - B_LOCK;
//		return v;
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
////	@Override
////	public boolean containsKey(Object o)
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		boolean b = super.containsKey(o);
////		this.state &= 255 - B_LOCK;
////		return b;
////	}
////
////	@Override
////	public boolean containsValue(Object o)
////	{
////		while ((this.state & B_LOCK) == B_LOCK)
////		{
////		}
////
////		this.state |= B_LOCK;
////		boolean b = super.containsValue(o);
////		this.state &= 255 - B_LOCK;
////		return b;
////	}
//
//	@Override
//	public Collection<V> values()
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
//		Collection<V> cv = new ArrayList(super.values());
//		this.state &= 255 - B_LOCK;
//		return cv;
//	}
//
//	@Override
//	public Set<K> keySet()
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
//		Set<K> sk = new HashSet(super.keySet());
//		this.state &= 255 - B_LOCK;
//		return sk;
//	}
//}
