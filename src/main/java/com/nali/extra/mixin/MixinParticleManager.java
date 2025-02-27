package com.nali.extra.mixin;

import com.nali.extra.ExtraView;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public abstract class MixinParticleManager
{
	//tr particle
//	@ModifyConstant(method = "renderParticles", constant = @Constant(intValue = 3))
//	private int nali_extra_renderParticles3(int constant)
//	{
//		return 1;
//	}

//	@ModifyConstant(method = "renderParticles", constant = @Constant(intValue = 2))
//	private int nali_extra_renderParticles2(int constant)
//	{
//		return 1;
//	}

	//cull particle
	@Inject(method = "addEffect", at = @At("HEAD"), cancellable = true)
	private void nali_extra_addEffect(Particle effect, CallbackInfo ci)
	{
		//avoid aether crash
		if (effect != null)
		{
			AxisAlignedBB axisalignedbb = effect.getBoundingBox();
			if (!ExtraView.check(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ))
			{
				ci.cancel();
			}
		}
	}

//	@Redirect(method = "renderParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/Particle;renderParticle(Lnet/minecraft/client/renderer/BufferBuilder;Lnet/minecraft/entity/Entity;FFFFFF)V"))
//	private void nali_extra_renderParticles(Particle instance, BufferBuilder f8, Entity f9, float f10, float f11, float f12, float vec3d, float v, float buffer)
//	{
//		AxisAlignedBB axisalignedbb = instance.getBoundingBox();
//		if (ExtraView.check(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ))
//		{
//			instance.renderParticle(f8, f9, f10, f11, f12, vec3d, v, buffer);
//		}
//	}
}
