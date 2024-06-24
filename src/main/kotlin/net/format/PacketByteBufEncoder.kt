package gay.pyrrha.mimic.net.format

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.minecraft.network.PacketByteBuf

@ExperimentalSerializationApi
public class PacketByteBufEncoder(private val buf: PacketByteBuf) : AbstractEncoder() {
    override val serializersModule: SerializersModule = MimicSerializersModule()

    override fun encodeBoolean(value: Boolean) { buf.writeBoolean(value) }
    override fun encodeByte(value: Byte) { buf.writeByte(value.toInt()) }
    override fun encodeChar(value: Char) { buf.writeChar(value.code) }
    override fun encodeDouble(value: Double) { buf.writeDouble(value) }
    override fun encodeFloat(value: Float) { buf.writeFloat(value) }
    override fun encodeInt(value: Int) { buf.writeInt(value) }
    override fun encodeLong(value: Long) { buf.writeLong(value) }
    override fun encodeShort(value: Short) { buf.writeShort(value.toInt()) }
    override fun encodeString(value: String) { buf.writeString(value) }
    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) { buf.writeInt(index) }

    override fun encodeNull(): Unit = encodeBoolean(false)
    override fun encodeNotNullMark(): Unit = encodeBoolean(true)

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        encodeInt(collectionSize)
        return this
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder = PacketByteBufEncoder(buf)
}

@ExperimentalSerializationApi
public fun <T> encodeTo(buf: PacketByteBuf, serializer: SerializationStrategy<T>, value: T) {
    val encoder = PacketByteBufEncoder(buf)
    encoder.encodeSerializableValue(serializer, value)
}

@ExperimentalSerializationApi
public inline fun <reified T> encodeTo(buf: PacketByteBuf, value: T): Unit = encodeTo(buf, serializer(), value)
