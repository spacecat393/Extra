//package com.nali.extra.mixin;
//
//import net.minecraft.client.gui.GuiButton;
//import net.minecraft.client.gui.inventory.GuiInventory;
//import net.minecraft.client.gui.recipebook.GuiRecipeBook;
//import net.minecraft.client.renderer.InventoryEffectRenderer;
//import net.minecraft.client.resources.I18n;
//import net.minecraft.inventory.ClickType;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.ContainerPlayer;
//import net.minecraft.inventory.Slot;
//import org.spongepowered.asm.mixin.*;
//
//import java.io.IOException;
//
////free gui
//@Mixin(GuiInventory.class)
//public abstract class MixinGuiInventory extends InventoryEffectRenderer
//{
////	@Mutable
//	@Shadow @Final private GuiRecipeBook recipeBookGui;
//
//	@Shadow private float oldMouseX;
//
//	@Shadow private float oldMouseY;
//
//	@Shadow private boolean widthTooNarrow;
//
//	public MixinGuiInventory(Container inventorySlotsIn)
//	{
//		super(inventorySlotsIn);
////		this.recipeBookGui = null;
//	}
//
//	@Overwrite
//	public void updateScreen()
//	{
//	}
//
//	@Overwrite
//	public void initGui()
//	{
////		this.buttonList.clear();
//
//		super.initGui();
//
//		this.widthTooNarrow = this.width < 379;
//		this.recipeBookGui.func_194303_a(this.width, this.height, this.mc, this.widthTooNarrow, ((ContainerPlayer)this.inventorySlots).craftMatrix);
////		this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
////		this.buttonList.add(this.recipeButton);
//	}
//
//	@Overwrite
//	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
//	{
//		this.fontRenderer.drawString(I18n.format("container.crafting"), 97, 8, 0xFFFFFFFF);
//	}
//
//	@Overwrite
//	public void drawScreen(int mouseX, int mouseY, float partialTicks)
//	{
//		this.drawDefaultBackground();
//		this.hasActivePotionEffects = true;
//
//		super.drawScreen(mouseX, mouseY, partialTicks);
//
//		this.renderHoveredToolTip(mouseX, mouseY);
//		this.oldMouseX = (float)mouseX;
//		this.oldMouseY = (float)mouseY;
//	}
//
//	@Overwrite
//	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
//	{
//	}
//
//	@Overwrite
//	protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY)
//	{
//		return super.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
//	}
//
//	@Overwrite
//	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
//	{
//		super.mouseClicked(mouseX, mouseY, mouseButton);
//	}
//
//	@Overwrite
//	protected boolean hasClickedOutside(int p_193983_1_, int p_193983_2_, int p_193983_3_, int p_193983_4_)
//	{
//		return p_193983_1_ < p_193983_3_ || p_193983_2_ < p_193983_4_ || p_193983_1_ >= p_193983_3_ + this.xSize || p_193983_2_ >= p_193983_4_ + this.ySize;
//	}
//
//	@Overwrite
//	protected void actionPerformed(GuiButton button)
//	{
//	}
//
//	@Overwrite
//	protected void keyTyped(char typedChar, int keyCode) throws IOException
//	{
//		super.keyTyped(typedChar, keyCode);
//	}
//
//	@Overwrite
//	protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type)
//	{
//		super.handleMouseClick(slotIn, slotId, mouseButton, type);
//	}
//
//	@Overwrite
//	public void recipesUpdated()
//	{
//	}
//
////	@Overwrite
////	public void onGuiClosed()
////	{
////		super.onGuiClosed();
////	}
//
////	@Overwrite
////	public GuiRecipeBook func_194310_f()
////	{
////		return null;
////	}
//}
