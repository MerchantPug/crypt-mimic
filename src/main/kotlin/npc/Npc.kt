package gay.pyrrha.mimic.npc

import com.mojang.datafixers.util.Function4
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.PrimitiveCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import gay.pyrrha.mimic.ident
import net.minecraft.text.Text
import net.minecraft.text.TextCodecs
import net.minecraft.util.Identifier
import java.util.Optional
import java.util.function.BiFunction
import kotlin.jvm.optionals.getOrElse
import kotlin.jvm.optionals.getOrNull

@JvmRecord
public data class Npc(
    val name: Text,
    val skin: NpcSkin,
    val title: Text?,
    val action: NpcAction?
) {
    public companion object {
        @JvmStatic
        public val CODEC: Codec<Npc> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                TextCodecs.CODEC.stable().fieldOf("name").forGetter { it.name },
                NpcSkin.CODEC.stable().fieldOf("skin").forGetter { it.skin },
                TextCodecs.CODEC.stable().optionalFieldOf("title").forGetter { Optional.ofNullable(it.title) },
                NpcAction.CODEC.stable().optionalFieldOf("action").forGetter { Optional.ofNullable(it.action) }
            ).apply(instance, instance.stable(Function4(::of)))
        }

        @JvmStatic
        public val DEFAULT: Identifier = ident("default")

        @JvmStatic
        public fun of(name: Text, skin: NpcSkin, title: Optional<Text>, action: Optional<NpcAction>): Npc =
            Npc(name, skin, title.getOrNull(), action.getOrNull())
    }
}

@JvmRecord
public data class NpcSkin(
    val texture: Identifier,
    val hasSlimArms: Boolean
) {
    public companion object {
        @JvmStatic
        public val CODEC: Codec<NpcSkin> = RecordCodecBuilder.create { instance ->
            return@create instance.group(
                Identifier.CODEC.stable().fieldOf("texture").forGetter { it.texture },
                PrimitiveCodec.BOOL.stable().optionalFieldOf("hasSlimArms")
                    .forGetter { Optional.ofNullable(it.hasSlimArms) }
            ).apply(instance, instance.stable(BiFunction(::of)))
        }

        @JvmStatic
        public fun of(texture: Identifier, hasSlimArms: Optional<Boolean>): NpcSkin =
            NpcSkin(texture, hasSlimArms.getOrElse { false })
    }
}