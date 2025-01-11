package com.nali.list.da;

import com.nali.da.IBothDaO;
import com.nali.list.data.ExtraData;

public class BothDaExtraGlowshapedown implements IBothDaO
{
	public static BothDaExtraGlowshapedown IDA;

	@Override
	public int O_StartPart()
	{
		return ExtraData.MODEL_STEP + 15;
	}

	@Override
	public int O_EndPart()
	{
		return ExtraData.MODEL_STEP + 16;
	}
}