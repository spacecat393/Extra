package com.nali.list.da;

import com.nali.da.IBothDaO;
import com.nali.list.data.ExtraData;

public class BothDaExtraGlowshape implements IBothDaO
{
	public static BothDaExtraGlowshape IDA;

	@Override
	public int O_StartPart()
	{
		return ExtraData.MODEL_STEP + 14;
	}

	@Override
	public int O_EndPart()
	{
		return ExtraData.MODEL_STEP + 15;
	}
}