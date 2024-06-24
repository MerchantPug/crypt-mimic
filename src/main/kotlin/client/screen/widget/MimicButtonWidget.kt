package gay.pyrrha.mimic.client.screen.widget

import com.mojang.blaze3d.systems.RenderSystem
import gay.pyrrha.mimic.client.screen.drawScrollableText
import gay.pyrrha.mimic.client.ModColours
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.tooltip.Tooltip
import net.minecraft.client.gui.widget.ButtonWidget
import net.minecraft.client.gui.widget.ButtonWidget.PressAction
import net.minecraft.text.MutableText
import net.minecraft.text.Text

public class MimicButtonWidget(
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    message: Text,
    onPress: PressAction,
    narrationSupplier: NarrationSupplier
) : ButtonWidget(x, y, width, height, message, onPress, narrationSupplier) {

    override fun renderWidget(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        context.setShaderColor(1f, 1f, 1f, this.alpha)
        RenderSystem.enableBlend()
        RenderSystem.enableDepthTest()
        context.fill(this.x, this.y, this.x + getWidth(), this.y + getHeight(), ModColours.active.surface0)
        context.drawBorder(
            this.x,
            this.y,
            getWidth(),
            getHeight(),
            if (this.hovered) ModColours.active.lavender else ModColours.active.overlay0
        )
        context.setShaderColor(1f, 1f, 1f, 1f)
        val textColor = if(this.active) ModColours.active.text else ModColours.active.subtext0
        drawScrollableText(context, MinecraftClient.getInstance().textRenderer, 4, textColor, false)
    }

    public companion object {
        public val DEFAULT_NARRATION_SUPPLIER: NarrationSupplier = NarrationSupplier { text -> text.get() as MutableText }
        public fun builder(message: Text, onPress: PressAction, buttonBuilder: CryptButtonBuilder.() -> Unit): MimicButtonWidget {
            val b = CryptButtonBuilder(message, onPress, buttonBuilder)
            buttonBuilder.invoke(b)
            return b.build()
        }
    }
}

public class CryptButtonBuilder(private val message: Text, private val onPress: PressAction, private val builder: CryptButtonBuilder.() -> Unit) {
    private var x: Int = 0
    private var y: Int = 0
    private var width: Int = 150
    private var height: Int = 20
    private var narrationSupplier: ButtonWidget.NarrationSupplier = MimicButtonWidget.DEFAULT_NARRATION_SUPPLIER
    private var tooltip: Tooltip? = null

    public fun pos(x: Int, y: Int): CryptButtonBuilder {
        this.x = x
        this.y = y
        return this
    }

    public fun width(width: Int): CryptButtonBuilder {
        this.width = width
        return this
    }

    public fun size(width: Int, height: Int): CryptButtonBuilder {
        this.width = width
        this.height = height
        return this
    }

    public fun tooltip(tooltip: Tooltip?): CryptButtonBuilder {
        this.tooltip = tooltip
        return this
    }

    public fun build(): MimicButtonWidget {
        builder(this)
        val widget = MimicButtonWidget(x, y, width, height, message, onPress, narrationSupplier)
        widget.tooltip = tooltip
        return widget
    }
}