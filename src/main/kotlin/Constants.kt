package gay.pyrrha.mimic

import net.minecraft.util.Identifier

public const val TAG: String = "[Mimic]"
public const val CLIENT_TAG: String = "[Mimic|Client]"
public const val SERVER_TAG: String = "[Mimic|Server]"

public const val MOD_ID: String = "crypt-mimic"
public fun ident(path: String): Identifier = Identifier.of(MOD_ID, path)
