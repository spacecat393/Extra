package com.nali.list.block.tile;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public class ExtraGlowshapeside extends TileEntity implements ITickable
{
	public byte tick = 120;

	@Override
	public void update()
	{
		if (!this.world.isRemote && this.tick-- == 0)
		{
			this.world.destroyBlock(this.pos, true);
		}
	}
}
