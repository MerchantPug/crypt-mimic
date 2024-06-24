package gay.pyrrha.mimic.server.command

import com.mojang.brigadier.suggestion.SuggestionProvider
import gay.pyrrha.mimic.ident
import gay.pyrrha.mimic.npc.Npc
import gay.pyrrha.mimic.registry.MimicRegistries
import net.minecraft.command.CommandSource
import net.minecraft.command.suggestion.SuggestionProviders
import net.minecraft.server.command.ServerCommandSource

public object MimicSuggestionProviders {
    @JvmStatic
    public val NPCs: SuggestionProvider<ServerCommandSource> by lazy {
        SuggestionProviders.register(ident("npcs")) { ctx, builder ->
            CommandSource.suggestFromIdentifier(
                ctx.source.registryManager[MimicRegistries.NPC].ids,
                builder,
                { id -> ctx.source.registryManager[MimicRegistries.NPC].ids.first { it == id } },
            ) { ctx.source.registryManager[MimicRegistries.NPC].get(it)!!.name }
        }
    }

    @JvmStatic
    public fun register() {}
}
