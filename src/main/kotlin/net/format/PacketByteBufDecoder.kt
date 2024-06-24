package gay.pyrrha.mimic.net.format

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import net.minecraft.network.PacketByteBuf

@ExperimentalSerializationApi
public class PacketByteBufDecoder(
    private val buf: PacketByteBuf,
    private var elementCount: Int = 0
) : AbstractDecoder() {
    private var elementIndex: Int = 0
    override val serializersModule: SerializersModule = MimicSerializersModule()

    override fun decodeBoolean(): Boolean = buf.readBoolean()
    override fun decodeByte(): Byte = buf.readByte()
    override fun decodeChar(): Char = buf.readChar()
    override fun decodeDouble(): Double = buf.readDouble()
    override fun decodeFloat(): Float = buf.readFloat()
    override fun decodeInt(): Int = buf.readInt()
    override fun decodeLong(): Long = buf.readLong()
    override fun decodeShort(): Short = buf.readShort()
    override fun decodeString(): String = buf.readString()
    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int = buf.readInt()

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (elementIndex == elementCount) return CompositeDecoder.DECODE_DONE
        return elementIndex++
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
        PacketByteBufDecoder(buf, descriptor.elementsCount)

    override fun decodeSequentially(): Boolean = true
    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int = decodeInt().also { elementCount = it }
    override fun decodeNotNullMark(): Boolean = decodeBoolean()
}

@ExperimentalSerializationApi
public fun <T> decodeFrom(buf: PacketByteBuf, deserializer: DeserializationStrategy<T>): T {
    val decoder = PacketByteBufDecoder(buf)
    return decoder.decodeSerializableValue(deserializer)
}

@ExperimentalSerializationApi
public inline fun <reified T> decodeFrom(buf: PacketByteBuf): T = decodeFrom(buf, serializer())
