package com.bigdious.sp;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class SittingPillowItem extends Item {

	public SittingPillowItem(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		if (!(level instanceof ServerLevel)) {
			return InteractionResult.SUCCESS;
		} else {
			ItemStack itemstack = context.getItemInHand();
			BlockPos blockpos = context.getClickedPos();
			Direction direction = context.getClickedFace();
			BlockState blockstate = level.getBlockState(blockpos);
			BlockPos blockpos1;
			if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
				blockpos1 = blockpos;
			} else {
				blockpos1 = blockpos.relative(direction);
			}

			SittingPillow sittingPillow = new SittingPillow(SPEntities.SITTING_PILLOW.get(), level);
			level.addFreshEntity(sittingPillow);
			sittingPillow.setColor(itemstack.get(DataComponents.DYED_COLOR) == null ? -1 : itemstack.get(DataComponents.DYED_COLOR).rgb());
			if (context.getPlayer().isCrouching()) {
				sittingPillow.moveTo(context.getClickLocation());
			} else {
				sittingPillow.moveTo(blockpos1.getX() + 0.5, blockpos1.getY(), blockpos1.getZ() + 0.5);
			}
			itemstack.shrink(1);
			level.playSound(null, blockpos1, SoundEvents.WOOL_PLACE, SoundSource.BLOCKS);
			level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
			return InteractionResult.CONSUME;
		}
	}
}
