package com.bigdious.sp;

import com.bigdious.sp.config.SPConfig;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SittingPillow extends Entity {
	protected static final EntityDataAccessor<Integer> DATA_COLOR = SynchedEntityData.defineId(SittingPillow.class, EntityDataSerializers.INT);
	public SittingPillow(EntityType<?> entityType, Level level) {
		super(entityType, level);
		this.entityData.set(DATA_COLOR, -1);
	}

	protected double getDefaultGravity() {
		return 0.04;
	}

	public void setColor(int color) {
		this.getEntityData().set(DATA_COLOR, color);
	}

	public int getColor() {
		return this.entityData.get(DATA_COLOR);
	}

	public void tick() {
		if (!SPConfig.killPillowsTicking) {
			this.applyGravity();
			this.move(MoverType.SELF, this.getDeltaMovement());
			if (!this.level().isClientSide() && this.getPassengers().isEmpty()) {
				List<Entity> list = this.level().getEntities(this, this.getBoundingBox(), EntitySelector.pushableBy(this));
				if (!list.isEmpty()) {
					for (Entity entity1 : list) {
						//added redundancy
						if (!(entity1 instanceof Player)
							&& !(entity1 instanceof IronGolem)
							&& !(entity1 instanceof AbstractMinecart)
							&& !this.isVehicle()
							&& !entity1.isPassenger()
							&& this.getPassengers().isEmpty()) {
							entity1.startRiding(this);
						}
					}
				}
			}
		}
	}

@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		if (!this.isRemoved()) {
			if (player.isCrouching()) {
				this.kill();
				ItemStack pillow = new ItemStack(SPItems.SITTING_PILLOW.get());
				if (this.getColor() != -1) {
					pillow.set(DataComponents.DYED_COLOR, new DyedItemColor(this.getColor(), true));
				}
				player.addItem(pillow);
				player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS);
			} else {
				if (!this.getPassengers().isEmpty()) {
					this.ejectPassengers();
				}
				player.startRiding(this);
			}
			this.gameEvent(GameEvent.ENTITY_INTERACT, player);
			return InteractionResult.sidedSuccess(this.level().isClientSide);
		}
		return InteractionResult.PASS;
	}

	//THIS FUCKER BELOW IS NEEDED FOR INTERACTIONS
	@Override
	public boolean isPickable() {
		return true;
	}

	@Override
	protected void defineSynchedData(SynchedEntityData.Builder builder) {
		builder.define(DATA_COLOR, 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		if (tag.contains("color")) {
			this.setColor(tag.getInt("color"));
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		tag.putInt("color", this.getColor());
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		return false;
	}
}
