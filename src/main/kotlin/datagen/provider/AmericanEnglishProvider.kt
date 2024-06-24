package gay.pyrrha.mimic.datagen.provider

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

public class AmericanEnglishProvider(
    output: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(output, registryLookup) {
    override fun generateTranslations(
        registryLookup: RegistryWrapper.WrapperLookup,
        builder: TranslationBuilder
    ) {
        // Theme
        builder.add("ui.crypt-mimic.theme", "UI Theme: %s")
        builder.add("ui.crypt-mimic.theme.latte", "Latte")
        builder.add("ui.crypt-mimic.theme.frappe", "Frappé")
        builder.add("ui.crypt-mimic.theme.macchiato", "Macchiato")
        builder.add("ui.crypt-mimic.theme.mocha", "Mocha")

        // NPCs
        builder.add("npc.crypt-mimic.default.name", "Py")
        builder.add("npc.crypt-mimic.default.title", "⮜goofy lil dork⮞")

        // Dialog
        builder.add("npc.crypt-mimic.default.dialog.start", "Heya, welcome to the Mimic demo!")
        builder.add("npc.crypt-mimic.default.dialog.next", "Mimic adds these NPCs and their dialog, all via datapacks. Theres also an API for custom dialog actions like the button below. Hope you enjoy <3")

        // Dialog actions
        builder.add("action.crypt-mimic.show_dialog", "Next")
        builder.add("action.crypt-mimic.close", "Done")
    }
}