package gay.pyrrha.mimic.client

import net.minecraft.text.Text

public object ModColours {
    public var active: Palette = Latte
    public fun switch() {
        when (active) {
            Latte -> active = Frappe
            Frappe -> active = Macchiato
            Macchiato -> active = Mocha
            Mocha -> active = Latte
        }
    }
}

public interface Palette {
    public val name: Text
    public val rosewater: Int
    public val flamingo: Int
    public val pink: Int
    public val mauve: Int
    public val red: Int
    public val maroon: Int
    public val peach: Int
    public val yellow: Int
    public val green: Int
    public val teal: Int
    public val sky: Int
    public val sapphire: Int
    public val blue: Int
    public val lavender: Int
    public val text: Int
    public val subtext1: Int
    public val subtext0: Int
    public val overlay2: Int
    public val overlay1: Int
    public val overlay0: Int
    public val surface2: Int
    public val surface1: Int
    public val surface0: Int
    public val base: Int
    public val mantle: Int
    public val crust: Int
}

public object Latte: Palette {
    override val name: Text = Text.translatable("ui.crypt-mimic.theme.latte")
    override val rosewater: Int = 0xffdc8a78.toInt()
    override val flamingo: Int = 0xffdd7878.toInt()
    override val pink: Int = 0xffea76cb.toInt()
    override val mauve: Int = 0xff8839ef.toInt()
    override val red: Int = 0xffd20f39.toInt()
    override val maroon: Int = 0xffe64553.toInt()
    override val peach: Int = 0xfffe640b.toInt()
    override val yellow: Int = 0xffdf8e1d.toInt()
    override val green: Int = 0xff40a02b.toInt()
    override val teal: Int = 0xff179299.toInt()
    override val sky: Int = 0xff04a5e5.toInt()
    override val sapphire: Int = 0xff209fb5.toInt()
    override val blue: Int = 0xff1e66f5.toInt()
    override val lavender: Int = 0xff7287fd.toInt()
    override val text: Int = 0xff4c4f69.toInt()
    override val subtext1: Int = 0xff5c5f77.toInt()
    override val subtext0: Int = 0xff6c6f85.toInt()
    override val overlay2: Int = 0xff7c7f93.toInt()
    override val overlay1: Int = 0xff8c8fa1.toInt()
    override val overlay0: Int = 0xff9ca0b0.toInt()
    override val surface2: Int = 0xffacb0be.toInt()
    override val surface1: Int = 0xffbcc0cc.toInt()
    override val surface0: Int = 0xffccd0da.toInt()
    override val base: Int = 0xffeff1f5.toInt()
    override val mantle: Int = 0xffe6e9ef.toInt()
    override val crust: Int = 0xffdce0e8.toInt()
}

public object Frappe: Palette {
    override val name: Text = Text.translatable("ui.crypt-mimic.theme.frappe")
    override val rosewater: Int = 0xfff2d5cf.toInt()
    override val flamingo: Int = 0xffeebebe.toInt()
    override val pink: Int = 0xfff4b8e4.toInt()
    override val mauve: Int = 0xffca9ee6.toInt()
    override val red: Int = 0xffe78284.toInt()
    override val maroon: Int = 0xffea999c.toInt()
    override val peach: Int = 0xffef9f76.toInt()
    override val yellow: Int = 0xffe5c890.toInt()
    override val green: Int = 0xffa6d189.toInt()
    override val teal: Int = 0xff81c8be.toInt()
    override val sky: Int = 0xff99d1db.toInt()
    override val sapphire: Int = 0xff85c1dc.toInt()
    override val blue: Int = 0xff8caaee.toInt()
    override val lavender: Int = 0xffbabbf1.toInt()
    override val text: Int = 0xffc6d0f5.toInt()
    override val subtext1: Int = 0xffb5bfe2.toInt()
    override val subtext0: Int = 0xffa5adce.toInt()
    override val overlay2: Int = 0xff949cbb.toInt()
    override val overlay1: Int = 0xff838ba7.toInt()
    override val overlay0: Int = 0xff737994.toInt()
    override val surface2: Int = 0xff626880.toInt()
    override val surface1: Int = 0xff51576d.toInt()
    override val surface0: Int = 0xff414559.toInt()
    override val base: Int = 0xff303446.toInt()
    override val mantle: Int = 0xff292c3c.toInt()
    override val crust: Int = 0xff232634.toInt()
}

public object Macchiato: Palette {
    override val name: Text = Text.translatable("ui.crypt-mimic.theme.macchiato")
    override val rosewater: Int = 0xfff4dbd6.toInt()
    override val flamingo: Int = 0xfff0c6c6.toInt()
    override val pink: Int = 0xfff5bde6.toInt()
    override val mauve: Int = 0xffc6a0f6.toInt()
    override val red: Int = 0xffed8796.toInt()
    override val maroon: Int = 0xffee99a0.toInt()
    override val peach: Int = 0xfff5a97f.toInt()
    override val yellow: Int = 0xffeed49f.toInt()
    override val green: Int = 0xffa6da95.toInt()
    override val teal: Int = 0xff8bd5ca.toInt()
    override val sky: Int = 0xff91d7e3.toInt()
    override val sapphire: Int = 0xff7dc4e4.toInt()
    override val blue: Int = 0xff8aadf4.toInt()
    override val lavender: Int = 0xffb7bdf8.toInt()
    override val text: Int = 0xffcad3f5.toInt()
    override val subtext1: Int = 0xffb8c0e0.toInt()
    override val subtext0: Int = 0xffa5adcb.toInt()
    override val overlay2: Int = 0xff939ab7.toInt()
    override val overlay1: Int = 0xff8087a2.toInt()
    override val overlay0: Int = 0xff6e738d.toInt()
    override val surface2: Int = 0xff5b6078.toInt()
    override val surface1: Int = 0xff494d64.toInt()
    override val surface0: Int = 0xff363a4f.toInt()
    override val base: Int = 0xff24273a.toInt()
    override val mantle: Int = 0xff1e2030.toInt()
    override val crust: Int = 0xff181926.toInt()
}

public object Mocha: Palette {
    override val name: Text = Text.translatable("ui.crypt-mimic.theme.mocha")
    override val rosewater: Int = 0xfff5e0dc.toInt()
    override val flamingo: Int = 0xfff2cdcd.toInt()
    override val pink: Int = 0xfff5c2e7.toInt()
    override val mauve: Int = 0xffcba6f7.toInt()
    override val red: Int = 0xfff38ba8.toInt()
    override val maroon: Int = 0xffeba0ac.toInt()
    override val peach: Int = 0xfffab387.toInt()
    override val yellow: Int = 0xfff9e2af.toInt()
    override val green: Int = 0xffa6e3a1.toInt()
    override val teal: Int = 0xff94e2d5.toInt()
    override val sky: Int = 0xff89dceb.toInt()
    override val sapphire: Int = 0xff74c7ec.toInt()
    override val blue: Int = 0xff89b4fa.toInt()
    override val lavender: Int = 0xffb4befe.toInt()
    override val text: Int = 0xffcdd6f4.toInt()
    override val subtext1: Int = 0xffbac2de.toInt()
    override val subtext0: Int = 0xffa6adc8.toInt()
    override val overlay2: Int = 0xff9399b2.toInt()
    override val overlay1: Int = 0xff7f849c.toInt()
    override val overlay0: Int = 0xff6c7086.toInt()
    override val surface2: Int = 0xff585b70.toInt()
    override val surface1: Int = 0xff45475a.toInt()
    override val surface0: Int = 0xff313244.toInt()
    override val base: Int = 0xff1e1e2e.toInt()
    override val mantle: Int = 0xff181825.toInt()
    override val crust: Int = 0xff11111b.toInt()
}
