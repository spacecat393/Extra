package com.nali.list.da;

import com.nali.da.IBothDaO;
import com.nali.list.data.ExtraData;

public class BothDaExtraBox implements IBothDaO
{
	public static BothDaExtraBox IDA;

	@Override
	public int O_StartPart()
	{
		return ExtraData.MODEL_STEP + 11;
	}

	@Override
	public int O_EndPart()
	{
		return ExtraData.MODEL_STEP + 14;
	}
}