package gay.pyrrha.mimic.net.format.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import java.util.UUID

public object UUIDSerializer : KSerializer<UUID> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(UUID::class.qualifiedName!!) {
        element<Long>("mostSigBits")
        element<Long>("leastSigBits")
    }

    override fun serialize(encoder: Encoder, value: UUID): Unit =
        encoder.encodeStructure(descriptor) {
            encodeLongElement(descriptor, 0, value.mostSignificantBits)
            encodeLongElement(descriptor, 1, value.leastSignificantBits)
        }

    override fun deserialize(decoder: Decoder): UUID =
        decoder.decodeStructure(descriptor) {
            var mostSigBits: Long? = null
            var leastSigBits: Long? = null
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> mostSigBits = decodeLongElement(descriptor, 0)
                    1 -> leastSigBits = decodeLongElement(descriptor, 1)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unknown index: $index")
                }
            }
            require(mostSigBits != null && leastSigBits != null)
            UUID(mostSigBits, leastSigBits)
        }
}
