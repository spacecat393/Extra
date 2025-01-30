package com.nali.extra.mixin;

import com.nali.extra.Extra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

//force player move
@Mixin(MovementInputFromOptions.class)
public abstract class MixinMovementInputFromOptions
{
	@Redirect(method = "updatePlayerMoveState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
	private boolean nali_extra_updatePlayerMoveState(KeyBinding instance)
	{
		boolean down = instance.isKeyDown();
		Minecraft minecraft = Minecraft.getMinecraft();
		GameSettings gamesettings = minecraft.gameSettings;
		if (instance != gamesettings.keyBindJump && instance != gamesettings.keyBindSneak && down && minecraft.gameSettings.thirdPersonView == 1)
		{
			EntityPlayerSP entityplayersp = minecraft.player;
			entityplayersp.rotationYaw = Extra.YAW;
			entityplayersp.prevRotationYaw = Extra.P_YAW;
		}
		return down;
	}
}
