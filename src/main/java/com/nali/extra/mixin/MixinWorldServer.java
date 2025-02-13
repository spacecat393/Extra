package com.nali.extra.mixin;

import com.nali.Nali;
import com.nali.list.gui.da.server.SDaInvSelect;
import com.nali.list.gui.da.server.SDaInvSelectAdd;
import net.minecraft.world.WorldServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//clean sleep
@Mixin(WorldServer.class)
public abstract class MixinWorldServer
{
	@Overwrite
	public boolean areAllPlayersAsleep()
	{
		return false;
	}

	@Inject(method = "updateEntities", at = @At("HEAD"))
	public void nali_extra_updateEntities(CallbackInfo ci)
	{
		try
		{
			SDaInvSelect.move();
			SDaInvSelectAdd.add();
		}
		catch (Exception e)
		{
			Nali.warn(e);
		}
	}
}
