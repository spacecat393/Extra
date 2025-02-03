package com.nali.extra.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.culling.Frustum;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//force chunk render
@Mixin(Frustum.class)
public abstract class MixinFrustum
{
	@Shadow private double x;

	@Shadow private double y;

	@Shadow private double z;

	@Inject(method = "<init>(Lnet/minecraft/client/renderer/culling/ClippingHelper;)V", at = @At("TAIL"))
	private void nali_extra_init(CallbackInfo ci)
	{
////		Nali.warn("1");
//		ExtraView.X = this.x;
//		ExtraView.Y = this.y;
//		ExtraView.Z = this.z;
		this.x += 0.5D;
		this.y += Minecraft.getMinecraft().player.getEyeHeight();
		this.z += 0.5D;
	}

	@Overwrite
	public boolean isBoxInFrustum(double minx, double miny, double minz, double maxx, double maxy, double maxz)
	{
		if
		(
			(this.x >= minx && this.x <= maxx) &&
			(this.y >= miny && this.y <= maxy) &&
			(this.z >= minz && this.z <= maxz)
		)
		{
//			Nali.warn("1");
			return true;
		}

//		float epsilon = 45.0F;

		minx -= this.x;
		miny -= this.y;
		minz -= this.z;
		maxx -= this.x;
		maxy -= this.y;
		maxz -= this.z;

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

		EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
		float yaw = ((entityplayersp.rotationYaw + 180) % 360 + 360) % 360 - 180;
		float pitch = ((entityplayersp.rotationPitch + 90) % 180 + 180) % 180 - 90;

		if (check(minx, miny, minz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(maxx, miny, minz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(maxx, miny, maxz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(miny - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(minx, miny, maxz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(minx, maxy, minz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(minz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(maxx, maxy, minz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(maxx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(maxx, maxy, maxz, yaw, pitch))
		{
			return true;
		}

//		if (Math.abs(minx - this.x) <= epsilon && Math.abs(maxy - this.y) <= epsilon && Math.abs(maxz - this.z) <= epsilon)
//		{
//			return true;
//		}
		if (check(minx, maxy, maxz, yaw, pitch))
		{
			return true;
		}
		return false;
//		return true;
	}

	private static boolean check(double x, double y, double z, float entityplayersp_rotationyaw, float entityplayersp_rotationpitch)
	{
		float epsilon = 45.0F;

		double yaw = Math.toDegrees(Math.atan2(-x, z));
		double pitch = Math.toDegrees(Math.atan2(-y, Math.sqrt(x * x + z * z)));

		x = Math.abs(entityplayersp_rotationyaw - yaw);
		y = Math.abs(entityplayersp_rotationpitch - pitch);

		if (x > 180)
		{
			x = 360 - x;
		}

//		if (y > 90)
//		{
//			y = 180 - y;
//		}

		return x <= epsilon && y <= epsilon;
	}

//	@Overwrite
//	public boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb)
//	{
//		return this.isBoxInFrustum(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
//	}
}
