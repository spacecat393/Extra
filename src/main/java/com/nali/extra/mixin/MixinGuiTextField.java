package com.nali.extra.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

//clean gui
@Mixin(GuiTextField.class)
public abstract class MixinGuiTextField
{
	@Shadow public abstract boolean getVisible();

	@Shadow private boolean isEnabled;

	@Shadow private int enabledColor;

	@Shadow private int disabledColor;

	@Shadow private int cursorCounter;

	@Shadow private int lineScrollOffset;

	@Shadow private int cursorPosition;

	@Shadow private int selectionEnd;

	@Shadow @Final private FontRenderer fontRenderer;

	@Shadow private boolean isFocused;

	@Shadow private boolean enableBackgroundDrawing;

	@Shadow private String text;

	@Shadow public int height;

	@Shadow public int x;

	@Shadow public int y;

	@Shadow public abstract int getWidth();

	@Shadow public abstract int getMaxStringLength();

	@Shadow public int width;

	@Shadow protected abstract void drawSelectionBox(int startX, int startY, int endX, int endY);

	@Overwrite
	public void drawTextBox()
	{
		if (this.getVisible())
		{
			int i = this.isEnabled ? this.enabledColor : this.disabledColor;
			int j = this.cursorPosition - this.lineScrollOffset;
			int k = this.selectionEnd - this.lineScrollOffset;
			String s = this.fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), this.getWidth());
			boolean flag = j >= 0 && j <= s.length();
			boolean flag1 = this.isFocused && this.cursorCounter / 6 % 2 == 0 && flag;
			int l = this.enableBackgroundDrawing ? this.x + 4 : this.x;
			int i1 = this.enableBackgroundDrawing ? this.y + (this.height - 8) / 2 : this.y;
			int j1 = l;

			if (k > s.length())
			{
				k = s.length();
			}

			if (!s.isEmpty())
			{
				String s1 = flag ? s.substring(0, j) : s;
				j1 = this.fontRenderer.drawStringWithShadow(s1, (float)l, (float)i1, i);
			}

			boolean flag2 = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
			int k1 = j1;

			if (!flag)
			{
				k1 = j > 0 ? l + this.width : l;
			}
			else if (flag2)
			{
				k1 = j1 - 1;
				--j1;
			}

			if (!s.isEmpty() && flag && j < s.length())
			{
				this.fontRenderer.drawStringWithShadow(s.substring(j), (float)j1, (float)i1, i);
			}

			if (flag1)
			{
				this.fontRenderer.drawStringWithShadow("▌", (float)k1, (float)i1, i);
			}

			if (k != j)
			{
				int l1 = l + this.fontRenderer.getStringWidth(s.substring(0, k));
				this.drawSelectionBox(k1, i1 - 1, l1 - 1, i1 + 1 + this.fontRenderer.FONT_HEIGHT);
			}
		}
	}
}
