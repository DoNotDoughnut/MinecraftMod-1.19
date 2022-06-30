package io.github.donotdoughnut.wmod.vedant;

import io.github.donotdoughnut.wmod.Mod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeRegistry;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class VedantCube extends HostileEntity {

	public static final EntityType<VedantCube> VEDANT_CUBE = Registry.register(
			Registry.ENTITY_TYPE,
			Mod.identifier("vedant_cube"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, VedantCube::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
	);

	protected VedantCube(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
		this.moveControl = new VedantCube.VexMoveControl(this);
	}

	public void tick() {
		this.setNoGravity(true);
//		this.noClip = true;
		super.tick();
//		this.noClip = false;
	}

	public static void register() {
		var attributes = DefaultAttributeContainer.builder()
				.add(EntityAttributes.GENERIC_MAX_HEALTH)
				.add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6D)
				.add(EntityAttributes.GENERIC_ARMOR)
				.add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)
				.add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0D)
				.add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE);
		FabricDefaultAttributeRegistry.register(VEDANT_CUBE, attributes);
	}

	static class Attributes {

	}

	private class VexMoveControl extends MoveControl {
		public VexMoveControl(VedantCube vexEntity2) {
			super(vexEntity2);
		}

		public void tick() {
			if (this.state == State.MOVE_TO) {
				Vec3d vec3d = new Vec3d(this.targetX - VedantCube.this.getX(), this.targetY - VedantCube.this.getY(), this.targetZ - VedantCube.this.getZ());
				double d = vec3d.length();
				if (d < VedantCube.this.getBoundingBox().getAverageSideLength()) {
					this.state = State.WAIT;
					VedantCube.this.setVelocity(VedantCube.this.getVelocity().multiply(0.5D));
				} else {
					VedantCube.this.setVelocity(VedantCube.this.getVelocity().add(vec3d.multiply(this.speed * 0.05D / d)));
					if (VedantCube.this.getTarget() == null) {
						Vec3d vec3d2 = VedantCube.this.getVelocity();
						VedantCube.this.setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * 57.295776F);
						VedantCube.this.bodyYaw = VedantCube.this.getYaw();
					} else {
						double e = VedantCube.this.getTarget().getX() - VedantCube.this.getX();
						double f = VedantCube.this.getTarget().getZ() - VedantCube.this.getZ();
						VedantCube.this.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776F);
						VedantCube.this.bodyYaw = VedantCube.this.getYaw();
					}
				}

			}
		}
	}

}
