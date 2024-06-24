package gay.pyrrha.mimic.dialog

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.text.Text
import net.minecraft.text.TextCodecs
import java.util.function.BiFunction

@JvmRecord
public data class DialogFrame(
    public val text: Text,
    public val actions: MutableList<DialogAction>
) {
    public companion object {
        @JvmStatic
        public val CODEC: Codec<DialogFrame> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                TextCodecs.CODEC.stable().fieldOf("text").forGetter { it.text },
                Codec.list(DialogAction.CODEC.stable()).stable().fieldOf("actions").forGetter { it.actions }
            ).apply(instance, instance.stable(BiFunction(::of)))
        }

        @JvmStatic
        public fun of(text: Text, actions: MutableList<DialogAction>): DialogFrame =
            DialogFrame(text, actions)
    }
}
