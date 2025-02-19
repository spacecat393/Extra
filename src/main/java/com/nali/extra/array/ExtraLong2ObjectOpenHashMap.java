//package com.nali.extra.array;
//
//import com.nali.Nali;
//import com.nali.extra.ExtraThread;
//import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
//
//public class ExtraLong2ObjectOpenHashMap<V> extends Long2ObjectOpenHashMap<V>
//{
//	public final static byte B_LOCK = 1;
//	public byte state;
//
//	public ExtraLong2ObjectOpenHashMap(int expected)
//	{
//		super(expected);
//	}
//
//	@Override
//	public V get(long k)
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
//		V v = super.get(k);
//		this.state &= 255 - B_LOCK;
//		return v;
//	}
//
//	@Override
//	public V put(long k, V v)
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
//	@Override
//	public V remove(long k)
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
//		V vv = super.remove(k);
//		this.state &= 255 - B_LOCK;
//		return vv;
//	}
//}
