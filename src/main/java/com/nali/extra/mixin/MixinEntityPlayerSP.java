//package com.nali.extra.mixin;
//
//import com.mojang.authlib.GameProfile;
//import com.nali.extra.Extra;
//import com.nali.extra.ExtraView;
//import net.minecraft.client.entity.AbstractClientPlayer;
//import net.minecraft.client.entity.EntityPlayerSP;
//import net.minecraft.world.World;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
////force player move
//@Mixin(EntityPlayerSP.class)
//public abstract class MixinEntityPlayerSP extends AbstractClientPlayer
//{
//	public MixinEntityPlayerSP(World worldIn, GameProfile playerProfile)
//	{
//		super(worldIn, playerProfile);
//	}
//
//	@Inject(method = "onUpdate", at = @At(value = "HEAD"), cancellable = true)
//	private void nali_extra_onUpdate(CallbackInfo ci)
//	{
//		if ((Extra.FP & 1) == 1)
//		{
//			this.rotationYaw = ExtraView.YAW;
//			this.prevRotationYaw = ExtraView.YAW;
//			this.rotationYawHead = ExtraView.YAW;
//			this.prevRotationYawHead = ExtraView.YAW;
//			this.rotationPitch = ExtraView.PITCH;
//			this.prevRotationPitch = ExtraView.PITCH;
//			ci.cancel();
//		}
////		Extra.UPDATE = 20;
//	}
////	@Redirect(method = "onUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isBlockLoaded(Lnet/minecraft/util/math/BlockPos;)Z"))
////	private boolean nali_extra_onUpdate(World instance, BlockPos pos)
////	{
////		return true;
////	}
////	@Shadow
////	protected Minecraft mc;
////
//////	@Overwrite
//////	private void onUpdateWalkingPlayer()
//////	{
//////
//////	}
//////
//////	@Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 7))
//////	private void nali_extra_onUpdateWalkingPlayer7(NetHandlerPlayClient instance, Packet<?> packetIn)
//////	{
//////		if (this.mc.gameSettings.thirdPersonView == 0)
//////		{
//////			instance.sendPacket(packetIn);
//////		}
//////	}
//////
//////	@Redirect(method = "onUpdateWalkingPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/NetHandlerPlayClient;sendPacket(Lnet/minecraft/network/Packet;)V", ordinal = 5))
//////	private void nali_extra_onUpdateWalkingPlayer5(NetHandlerPlayClient instance, Packet<?> packetIn)
//////	{
//////		if (this.mc.gameSettings.thirdPersonView == 0)
//////		{
//////			instance.sendPacket(packetIn);
//////		}
//////	}
////
////	@Redirect(method = "updateEntityActionState", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;moveForward:F"))
////	private void nali_extra_updateEntityActionStateF(EntityPlayerSP instance, float value)
////	{
////		if (this.mc.gameSettings.thirdPersonView == 0)
////		{
////			instance.moveForward = value;
////		}
////		else
////		{
////			instance.moveForward = 0;
////			instance.moveStrafing = 0;
////
////			double x = 0, z = 0;
////			if (Keyboard.isKeyDown(Keyboard.KEY_W))
////			{
////				double yaw = Math.toRadians(Extra.YAW);
////				x = -Math.sin(yaw) / 2.0F;
////				z = Math.cos(yaw) / 2.0F;
////			}
////			instance.move(MoverType.PLAYER, x, 0, z);
////		}
////	}
////
////	@Redirect(method = "updateEntityActionState", at = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/EntityPlayerSP;moveStrafing:F"))
////	private void nali_extra_updateEntityActionStateS(EntityPlayerSP instance, float value)
////	{
////		if (this.mc.gameSettings.thirdPersonView == 0)
////		{
////			instance.moveStrafing = value;
////		}
////	}
//}
