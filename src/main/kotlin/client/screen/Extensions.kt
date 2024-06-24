package gay.pyrrha.mimic.client.screen

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.widget.ClickableWidget
import net.minecraft.text.Text
import net.minecraft.util.Util
import net.minecraft.util.math.MathHelper
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sin

public fun ClickableWidget.drawScrollableText(
    drawContext: DrawContext,
    textRenderer: TextRenderer,
    xMargin: Int,
    color: Int,
    shadow: Boolean
) {
    val startX = this.x + xMargin
    val endX = this.x + this.width - xMargin
    drawContext.drawScrollableText(textRenderer, message, startX, y, endX, y + height, color, shadow)
}

public fun DrawContext.drawScrollableText(
    textRenderer: TextRenderer,
    text: Text,
    startX: Int,
    startY: Int,
    endX: Int,
    endY: Int,
    color: Int,
    shadow: Boolean
) {
    drawScrollableText(textRenderer, text, (startX + endX) / 2, startX, startY, endX, endY, color, shadow)
}

/**
 * just [net.minecraft.client.gui.widget.ClickableWidget.drawScrollableText] without the shadow
 */
public fun DrawContext.drawScrollableText(
    textRenderer: TextRenderer,
    text: Text,
    centerX: Int,
    startX: Int,
    startY: Int,
    endX: Int,
    endY: Int,
    color: Int,
    shadow: Boolean
) {
    val textWidth = textRenderer.getWidth(text)
    val y = (startY + endY - 9) / 2 + 1
    val width = endX - startX
    if (textWidth > width) {
        val rawOverflow = textWidth - width
        val seconds = Util.getMeasuringTimeMs() / 1000.0
        val overflow = max(rawOverflow * 0.5, 3.0)
        val delta = sin((PI / 2) * cos((PI * 2) * seconds / overflow))  + 0.5
        val offsetX = MathHelper.lerp(delta, 0.0, rawOverflow.toDouble())

        enableScissor(startX, startY, endX, endY)
        drawText(textRenderer, text, (startX - offsetX).toInt(), y, color, shadow)
        disableScissor()
    } else {
        val x = centerX.coerceIn(startX + textWidth / 2, endX - textWidth / 2)
        drawCenteredText(textRenderer, text, x, y, color, shadow)
    }
}

public fun DrawContext.drawCenteredText(textRenderer: TextRenderer, text: Text, centerX: Int, y: Int, color: Int, shadow: Boolean) {
    drawText(textRenderer, text, centerX - textRenderer.getWidth(text) / 2, y, color, shadow)
}
