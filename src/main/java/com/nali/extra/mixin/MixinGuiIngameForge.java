package com.nali.extra.mixin;

import com.nali.small.Small;
import com.nali.small.SmallConfig;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.GuiIngameForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiIngameForge.class)
public abstract class MixinGuiIngameForge
{
	@Overwrite(remap = false)
	protected void renderToolHighlight(ScaledResolution res)
	{
	}

	@Inject(method = "renderHUDText", at = @At("HEAD"), cancellable = true, remap = false)
	private void nali_extra_renderHUDText(int width, int height, CallbackInfo ci)
	{
		if (SmallConfig.FAST_RAW_FPS)
		{
			if (Small.FLAG == 3/*4*/)
			{
				ci.cancel();
			}
		}
		else
		{
			Small.FLAG |= 2;
			if ((Small.FLAG & 1) == 1)
			{
				ci.cancel();
			}
		}
	}
}
