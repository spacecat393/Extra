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
//		Nali.warn("x " + (maxx - minx));
//		Nali.warn("y " + (maxy - miny));
//		Nali.warn("z " + (maxz - minz));
		if
		(
//			(this.x >= minx && this.x <= maxx) &&
//			(this.y >= miny && this.y <= maxy) &&
//			(this.z >= minz && this.z <= maxz)
			(ExtraView.X >= minx && ExtraView.X <= maxx) &&
			(ExtraView.Y >= miny && ExtraView.Y <= maxy) &&
			(ExtraView.Z >= minz && ExtraView.Z <= maxz)
		)
		{
//			Nali.warn("1");
			return true;
		}

//		float epsilon = 45.0F;

//		minx -= this.x;
//		miny -= this.y;
//		minz -= this.z;
//		maxx -= this.x;
//		maxy -= this.y;
//		maxz -= this.z;
		minx -= ExtraView.X;
		miny -= ExtraView.Y;
		minz -= ExtraView.Z;
		maxx -= ExtraView.X;
		maxy -= ExtraView.Y;
		maxz -= ExtraView.Z;

//		double x_scalar = maxx - minx;
//		double y_scalar = maxy - miny;
//		double z_scalar = maxz - minz;
//
//		double box_center_x = minx + x_scalar / 2;
//		double box_center_y = miny + y_scalar / 2;
//		double box_center_z = minz + z_scalar / 2;
//
//		double x = box_center_x - this.x;
//		double y = box_center_y - this.y;
//		double z = box_center_z - this.z;

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}

		if (ExtraView.check(minx, miny, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, miny, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, miny, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, miny, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, maxy, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, maxy, minz))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(maxx, maxy, maxz))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (ExtraView.check(minx, maxy, maxz))
		{
			return true;
		}
		return false;
//		return this.clippingHelper.isBoxInFrustum(minx, miny, minz, maxx, maxy, maxz);
//		return true;
	}

//	@Overwrite
//	public boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb)
//	{
//		return this.isBoxInFrustum(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
//	}
}
