package gay.pyrrha.mimic.client.entity.renderer

import gay.pyrrha.mimic.entity.NPCEntity.Companion.NPC_ID
import gay.pyrrha.mimic.entity.NPCEntity.Companion.TRACKED_IS_SMALL
import gay.pyrrha.mimic.npc.Npc
import gay.pyrrha.mimic.registry.MimicRegistries
import net.minecraft.client.network.AbstractClientPlayerEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.PlayerEntityRenderer
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityPose
import net.minecraft.text.Text
import net.minecraft.util.Identifier

public class NPCEntityRenderer(ctx: EntityRendererFactory.Context, slimArms: Boolean = false) :
    PlayerEntityRenderer(
        ctx,
        slimArms
    ) {

    public companion object NpcGetter {
        @JvmStatic
        public fun isNpc(entity: AbstractClientPlayerEntity) : Boolean {
            return entity.gameProfile.name.equals("[Mimic NPC]")
        }
        @JvmStatic
        public fun getId(entity: AbstractClientPlayerEntity) : Identifier {
            return Identifier.of(entity.dataTracker[NPC_ID])
        }
        @JvmStatic
        public fun getNpc(entity: AbstractClientPlayerEntity) : Npc? {
            return entity.registryManager.get(MimicRegistries.NPC).get(getId(entity));
        }
    }


    override fun render(
        livingEntity: AbstractClientPlayerEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack?,
        vertexConsumerProvider: VertexConsumerProvider?,
        i: Int
    ) {
        setModelPose(getModel())
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: AbstractClientPlayerEntity): Identifier =
        getNpc(entity)?.skin?.texture
            ?: Identifier.ofVanilla("textures/entity/player/wide/steve.png")

    public override fun scale(entity: AbstractClientPlayerEntity, matrices: MatrixStack, amount: Float) {
        val scale = 0.9375f
        matrices.scale(scale, scale, scale)
    }

    private fun setModelPose(model: PlayerEntityModel<AbstractClientPlayerEntity>) {
        model.setVisible(true)
        model.sneaking = false
        model.leftArmPose = BipedEntityModel.ArmPose.EMPTY
        model.rightArmPose = BipedEntityModel.ArmPose.EMPTY
    }

    override fun renderLabelIfPresent(
        entity: AbstractClientPlayerEntity,
        text: Text,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        tickDelta: Float
    ) {
        val npc = getNpc(entity);
        if (npc == null) {
            super.renderLabelIfPresent(entity, text, matrices, vertexConsumers, light, tickDelta)
            return;
        }
        val distance = dispatcher.getSquaredDistanceToCamera(entity)
        val titleText = npc.title
        matrices.push()
        if (entity.dataTracker[TRACKED_IS_SMALL]) {
            matrices.translate(0f, -(entity.getBaseDimensions(EntityPose.STANDING).height - 0.1f), 0f)
        }
        if (distance < 100 && titleText != null) {
            super.renderLabelIfPresent(entity, text, matrices, vertexConsumers, light, tickDelta)
            matrices.translate(0.0f, 0.25875f, 0.0f)
            super.renderLabelIfPresent(entity, titleText, matrices, vertexConsumers, light, tickDelta)
        } else {
            super.renderLabelIfPresent(entity, text, matrices, vertexConsumers, light, tickDelta)
        }

        matrices.pop()
    }
}
