package gay.pyrrha.mimic.net.format

import gay.pyrrha.mimic.net.format.serializer.IdentifierSerializer
import gay.pyrrha.mimic.net.format.serializer.UUIDSerializer
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

private val module: SerializersModule = SerializersModule {
    include(EmptySerializersModule())

    contextual(IdentifierSerializer)
    contextual(UUIDSerializer)
}

@Suppress("FunctionName")
public fun MimicSerializersModule(): SerializersModule = module
