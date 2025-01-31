//package com.nali.extra.mixin;
//
//import net.minecraft.client.gui.GuiChat;
//import net.minecraft.client.gui.GuiTextField;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
////clean gui
//@Mixin(GuiChat.class)
//public abstract class MixinGuiChat
//{
//	@Shadow protected GuiTextField inputField;
//
//	@Inject(method = "initGui", at = @At(value = "TAIL"))
//	private void nali_extra_initGui(CallbackInfo ci)
//	{
//		this.inputField.y = 12 + 9;
//	}
//}
