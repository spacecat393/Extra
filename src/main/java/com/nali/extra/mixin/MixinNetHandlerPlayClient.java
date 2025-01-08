package com.nali.extra.mixin;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.server.SPacketRecipeBook;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(NetHandlerPlayClient.class)
public abstract class MixinNetHandlerPlayClient
{
	//remove CreativeTabs
	@Overwrite
	public void handleRecipeBook(SPacketRecipeBook packetIn)
	{
	}
}
