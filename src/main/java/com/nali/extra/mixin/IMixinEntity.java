package com.nali.extra.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

//*extra
@Mixin(Entity.class)
public interface IMixinEntity
{
	@Invoker("getFlag")
	boolean GOgetFlag(int flag);
}
