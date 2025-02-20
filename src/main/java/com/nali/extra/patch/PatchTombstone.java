package com.nali.extra.patch;

import com.nali.Nali;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class PatchTombstone
{
	public static Field SPECIAL_EVENT_FIELD;
	public static Method SPECIAL_EVENT_VALUES_METHOD;

	public static void init()
	{
		try
		{
			Class clasz = Class.forName("ovh.corail.tombstone.helper.TimeHelper");
			SPECIAL_EVENT_FIELD = clasz.getDeclaredField("SPECIAL_EVENT");
			SPECIAL_EVENT_FIELD.setAccessible(true);
			clasz = Class.forName("ovh.corail.tombstone.helper.TimeHelper$SpecialEvent");
			SPECIAL_EVENT_VALUES_METHOD = clasz.getMethod("values");
		}
		catch (Exception e)
		{
			Nali.error(e);
		}
	}

	public static void forceEvent(long time, Random random)
	{
		if (time % 24000L == 0)
		{
	//		IMixinTimeHelper.SPECIAL_EVENT(TimeHelper.SpecialEvent.values()[random.nextInt(5)]);
	//		Nali.warn("TimeHelper.getSpecialEvent() " + TimeHelper.getSpecialEvent());
			try
			{
				SPECIAL_EVENT_FIELD.set(null, ((Object[])SPECIAL_EVENT_VALUES_METHOD.invoke(null))[random.nextInt(5)]);
//				Nali.warn("TimeHelper.getSpecialEvent() " + SPECIAL_EVENT_FIELD.get(null));
			}
			catch (IllegalAccessException | InvocationTargetException e)
			{
				Nali.error(e);
			}
		}
	}
}
