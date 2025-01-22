package com.nali.extra.mixin;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.util.FoodStats;
import org.spongepowered.asm.mixin.Mixin;

//show info gui
@Mixin(GuiInventory.class)
public abstract class MixinGuiInventory extends InventoryEffectRenderer
{
	public MixinGuiInventory(Container inventorySlotsIn)
	{
		super(inventorySlotsIn);
	}

	@Override
	protected void renderHoveredToolTip(int mouseX, int mouseY)
	{
		EntityPlayerSP entityplayersp = this.mc.player;
		FoodStats foodstats = entityplayersp.getFoodStats();
		this.drawHoveringText(new String[]
		{
			entityplayersp.getName(),
			"CHP " + entityplayersp.getHealth(),
			"MHP " + entityplayersp.getMaxHealth(),
			"CFL " + foodstats.getFoodLevel(),
			"CSL " + foodstats.getSaturationLevel()
		}, new int[]
		{
			0xFFF85A52,
			0xFFFFFFFF,
			0xFFFFFFFF,
			0xFFFFFFFF,
			0xFFFFFFFF
		}, mouseX, mouseY, true);
//			this.drawHoveringText(Arrays.asList(entityplayersp.getName(), "CHP " + entityplayersp.getHealth(), "MHP " + entityplayersp.getMaxHealth(), "FL" + foodstats.getFoodLevel(), "SL" + foodstats.getSaturationLevel()), mouseX, mouseY, this.fontRenderer);
		super.renderHoveredToolTip(mouseX, mouseY);
	}

	//should renew later
	private void drawHoveringText(String[] text_string_array, int[] color_int_array, int x, int y, boolean have_head)
	{
//        net.minecraftforge.fml.client.config.GuiUtils.drawHoveringText(textLines, x, y, width, height, -1, font);
//        if (false && !textLines.isEmpty())
//        {
		GlStateManager.disableRescaleNormal();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableLighting();
		GlStateManager.disableDepth();

		int i = 0;

		for (String s : text_string_array)
		{
			int j = this.fontRenderer.getStringWidth(s);

			if (j > i)
			{
				i = j;
			}
		}

		int l1 = x + 12;
		int i2 = y - 12;
		int k = 8;

		int length = text_string_array.length;
		if (length > 1)
		{
			if (have_head)
			{
				k += 2;
			}

			k += (length - 1) * 10;
		}

		if (l1 + i > this.width)
		{
			l1 -= 28 + i;
		}

		if (i2 + k + 6 > this.height)
		{
			i2 = this.height - k - 6;
		}

		this.zLevel = 300.0F;
		this.itemRender.zLevel = 300.0F;
		//AARRGGBB
		int l = 0x88000000;
		int i1 = 0xFFF85A52;
		int j1 = 0xFFFFFFFF;

		this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, l, l); //bg

		this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, i1, j1); //left
		this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, i1, j1); //right
		this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, i1, i1); //top
		this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, j1, j1); //bottom
		this.drawGradientRect(l1 - 3 + 1, i2 - 3 - 2, l1 - 3 + 3, i2 - 3, i1, i1); //top ribbon
		this.drawGradientRect(l1 - 3 - 2, i2 - 3 + 1, l1 - 3, i2 - 3 + 3, i1, i1); //left ribbon

		for (int k1 = 0; k1 < length; ++k1)
		{
			this.fontRenderer.drawStringWithShadow(text_string_array[k1], (float)l1, (float)i2, color_int_array[k1]/*(k1 + 1) % 2 == 0 ? 0xFFFFFFFF : 0xFFF85A52*/);

			if (have_head)
			{
				if (k1 == 0)
				{
					i2 += 2;
				}
			}

			i2 += 10;
		}

		this.zLevel = 0.0F;
		this.itemRender.zLevel = 0.0F;
		GlStateManager.enableLighting();
		GlStateManager.enableDepth();
		RenderHelper.enableStandardItemLighting();
		GlStateManager.enableRescaleNormal();
	}

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
}
