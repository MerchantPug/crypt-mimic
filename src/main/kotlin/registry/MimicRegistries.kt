package gay.pyrrha.mimic.registry

import gay.pyrrha.mimic.dialog.DialogFrame
import gay.pyrrha.mimic.ident
import gay.pyrrha.mimic.npc.Npc
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey

public object MimicRegistries {
    @JvmStatic
    public val DIALOG: RegistryKey<Registry<DialogFrame>> = RegistryKey.ofRegistry(ident("dialog"))

    @JvmStatic
    public val NPC: RegistryKey<Registry<Npc>> = RegistryKey.ofRegistry(ident("npc"))

    public fun register() {
        DynamicRegistries.registerSynced(DIALOG, DialogFrame.CODEC)
        DynamicRegistries.registerSynced(NPC, Npc.CODEC)
    }
}
