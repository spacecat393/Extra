package com.nali.list.da;

import com.nali.da.IBothDaO;
import com.nali.list.data.ExtraData;

public class BothDaExtraGlowshapeside implements IBothDaO
{
	public static BothDaExtraGlowshapeside IDA;

	@Override
	public int O_StartPart()
	{
		return ExtraData.MODEL_STEP + 16;
	}

	@Override
	public int O_EndPart()
	{
		return ExtraData.MODEL_STEP + 17;
	}
}