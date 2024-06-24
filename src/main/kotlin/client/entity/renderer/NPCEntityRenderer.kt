package gay.pyrrha.mimic.client.entity.renderer


import gay.pyrrha.mimic.entity.NPCEntity
import gay.pyrrha.mimic.registry.MimicRegistries
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.LivingEntityRenderer
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer
import net.minecraft.client.render.entity.model.ArmorEntityModel
import net.minecraft.client.render.entity.model.BipedEntityModel
import net.minecraft.client.render.entity.model.EntityModelLayers
import net.minecraft.client.render.entity.model.PlayerEntityModel
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityPose
import net.minecraft.text.Text
import net.minecraft.util.Identifier

public class NPCEntityRenderer(ctx: EntityRendererFactory.Context, slimArms: Boolean = false) :
    LivingEntityRenderer<NPCEntity, PlayerEntityModel<NPCEntity>>(
        ctx,
        PlayerEntityModel<NPCEntity>(
            ctx.getPart(if (slimArms) EntityModelLayers.PLAYER_SLIM else EntityModelLayers.PLAYER),
            slimArms
        ),
        0.5f
    ) {

    init {
        addFeature(
            ArmorFeatureRenderer(
                this,
                ArmorEntityModel(
                    ctx.getPart(if (slimArms) EntityModelLayers.PLAYER_SLIM_INNER_ARMOR else EntityModelLayers.PLAYER_INNER_ARMOR)
                ),
                ArmorEntityModel(
                    ctx.getPart(if (slimArms) EntityModelLayers.PLAYER_SLIM_OUTER_ARMOR else EntityModelLayers.PLAYER_OUTER_ARMOR)
                ),
                ctx.modelManager
            )
        )

        addFeature(HeldItemFeatureRenderer(this, ctx.heldItemRenderer))
        addFeature(ElytraFeatureRenderer(this, ctx.modelLoader))
        addFeature(HeadFeatureRenderer(this, ctx.modelLoader, ctx.heldItemRenderer))
    }

    override fun render(
        livingEntity: NPCEntity,
        f: Float,
        g: Float,
        matrixStack: MatrixStack?,
        vertexConsumerProvider: VertexConsumerProvider?,
        i: Int
    ) {
        setModelPose(getModel())
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: NPCEntity): Identifier =
        entity.world.registryManager[MimicRegistries.NPC][entity.getNpcId()]?.skin?.texture
            ?: Identifier.ofVanilla("textures/entity/player/wide/steve.png")

    override fun scale(entity: NPCEntity, matrices: MatrixStack, amount: Float) {
        val scale = 0.9375f
        matrices.scale(scale, scale, scale)
    }

    private fun setModelPose(model: PlayerEntityModel<NPCEntity>) {
        model.setVisible(true)
        model.sneaking = false
        model.leftArmPose = BipedEntityModel.ArmPose.EMPTY
        model.rightArmPose = BipedEntityModel.ArmPose.EMPTY
    }

    override fun renderLabelIfPresent(
        entity: NPCEntity,
        text: Text,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        tickDelta: Float
    ) {
        val distance = dispatcher.getSquaredDistanceToCamera(entity)
        val titleText = entity.getTitle()
        matrices.push()
        if (entity.isSmall) {
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
