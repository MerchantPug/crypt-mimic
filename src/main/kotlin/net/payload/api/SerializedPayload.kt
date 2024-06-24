package gay.pyrrha.mimic.net.payload.api

import gay.pyrrha.mimic.net.format.decodeFrom
import gay.pyrrha.mimic.net.format.encodeTo
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import net.minecraft.network.RegistryByteBuf
import net.minecraft.network.codec.PacketCodec
import net.minecraft.network.packet.CustomPayload

@Serializable
@ExperimentalSerializationApi
public abstract class SerializedPayload<T : SerializedPayload<T>>(
) : CustomPayload {
    public abstract fun codec(): PacketCodec<RegistryByteBuf, T>

    public companion object {
        public inline fun <reified T : SerializedPayload<T>> codec(): PacketCodec<RegistryByteBuf, T> =
            CustomPayload.codecOf(
                { value, buf -> encodeTo<T>(buf, value) },
                { buf -> decodeFrom<T>(buf) }
            )
    }
}
