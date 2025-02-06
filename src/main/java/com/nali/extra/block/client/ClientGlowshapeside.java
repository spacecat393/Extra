package com.nali.extra.block.client;

import com.nali.da.IBothDaO;
import com.nali.list.block.ExtraGlowshapeside;
import com.nali.list.render.RenderExtraGlowshapeside;
import com.nali.small.mix.IMixN;
import com.nali.small.mix.memo.client.ClientB;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientGlowshapeside
<
	BD extends IBothDaO,
	I extends IMixN<BD, ?, E>,
	E extends ExtraGlowshapeside,
	T extends TileEntity,
	R extends RenderExtraGlowshapeside<BD, R>
> extends ClientB<BD, R, I, E, T>
{
	public ClientGlowshapeside(R r, I i)
	{
		super(r, i);
	}

	@Override
	public void render(T t, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
	{
//		ExtraGlowshapeside e = this.i.getE();
		IBlockState iblockstate = t.getWorld().getBlockState(t.getPos());
		EnumFacing enumfacing = iblockstate.getValue(ExtraGlowshapeside.FACING);
		this.r.enumfacing = enumfacing;
		super.render(t, x, y, z, partialTicks, destroyStage, alpha);
	}
}
