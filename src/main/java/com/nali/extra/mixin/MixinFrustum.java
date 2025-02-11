package com.nali.extra.mixin;

import com.nali.extra.ExtraView;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//force chunk render
@Mixin(Frustum.class)
public abstract class MixinFrustum
{
//	@Mutable
//	@Shadow @Final private ClippingHelper clippingHelper;
//
//	@Inject(method = "<init>(Lnet/minecraft/client/renderer/culling/ClippingHelper;)V", at = @At("TAIL"))
//	private void nali_extra_init(CallbackInfo ci)
//	{
////////		Nali.warn("1");
//////		ExtraView.X = this.x;
//////		ExtraView.Y = this.y;
//////		ExtraView.Z = this.z;
////		this.x += 0.5D;
////		this.y += Minecraft.getMinecraft().player.getEyeHeight();
////		this.z += 0.5D;
//		this.clippingHelper = null;
//	}

	@Inject(method = "setPosition", at = @At("TAIL"))
	private void nali_extra_setPosition(double xIn, double yIn, double zIn, CallbackInfo ci)
//	@Overwrite
//	public void setPosition(double xIn, double yIn, double zIn)
	{
		ExtraView.X = xIn + 0.5D;
		ExtraView.Y = yIn + Minecraft.getMinecraft().player.getEyeHeight();
		ExtraView.Z = zIn + 0.5D;
	}

	@Overwrite
	public boolean isBoxInFrustum(double minx, double miny, double minz, double maxx, double maxy, double maxz)
	{
		return ExtraView.check(minx, miny, minz, maxx, maxy, maxz);
	}

//	@Overwrite
//	public boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb)
//	{
//		return this.isBoxInFrustum(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
//	}
}
