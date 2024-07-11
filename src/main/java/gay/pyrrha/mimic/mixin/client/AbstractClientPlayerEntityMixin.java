package gay.pyrrha.mimic.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import gay.pyrrha.mimic.client.entity.renderer.NPCEntityRenderer;
import gay.pyrrha.mimic.npc.Npc;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractClientPlayerEntity.class)
public class AbstractClientPlayerEntityMixin {
    @Unique
    private SkinTextures mimic$skinTextures;

    @ModifyReturnValue(method = "getSkinTextures", at = @At("RETURN"))
    private SkinTextures mimic$getSkinTextures(SkinTextures original) {
        if (NPCEntityRenderer.isNpc((AbstractClientPlayerEntity)(Object) this)) {
            if (mimic$skinTextures == null) {
                Npc npc = NPCEntityRenderer.getNpc((AbstractClientPlayerEntity)(Object) this);
                mimic$skinTextures = new SkinTextures(npc.skin().texture(), null, null, null, npc.skin().hasSlimArms() ? SkinTextures.Model.SLIM : SkinTextures.Model.WIDE, true);
            }
            return mimic$skinTextures;
        }
        return original;
    }
}
