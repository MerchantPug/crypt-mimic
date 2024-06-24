package gay.pyrrha.mimic.datagen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import gay.pyrrha.mimic.datagen.provider.AmericanEnglishProvider

public object MimicDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::AmericanEnglishProvider)
    }
}