package Parnapple.mistbornmod.block.custom;

import Parnapple.mistbornmod.block.entity.MetallurgyFurnaceBlockEntity;
import Parnapple.mistbornmod.block.entity.ModBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;




public class MetallurgyFurnaceBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public MetallurgyFurnaceBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }


    // I'm not sure if these next two methods are needed, but I copied them from the AbstractFurnaceBlock class anyway
    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }


    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, Random pRandom) {
        double d0 = (double)pPos.getX() + 0.5D;
        double d1 = (double)pPos.getY();
        double d2 = (double)pPos.getZ() + 0.5D;
        if (pRandom.nextDouble() < 0.1D) {
            pLevel.playLocalSound(d0, d1, d2, SoundEvents.BLASTFURNACE_FIRE_CRACKLE, SoundSource.BLOCKS, 1.0F, 1.0F, false);
        }
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if(!pLevel.isClientSide()) {
            if(pEntity instanceof LivingEntity) {
                LivingEntity entity = (LivingEntity) pEntity;
                if(!(entity instanceof Villager)) {
                    entity.hurt(DamageSource.HOT_FLOOR, 0.5f);
                }
            }
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof MetallurgyFurnaceBlockEntity) {
                NetworkHooks.openGui(((ServerPlayer)pPlayer), (MetallurgyFurnaceBlockEntity)entity, pPos);
            } else {
                throw new IllegalStateException("Our Container provider is missing!");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Override
    public void attack(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        if(!pLevel.isClientSide() && pLevel.getBlockEntity(pPos) instanceof final MetallurgyFurnaceBlockEntity furnace) {
           //pPlayer.displayClientMessage(new TextComponent("The "), true);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return ModBlockEntityTypes.METALLURGY_FURNACE_BLOCK_ENTITY.get().create(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
       //return pLevel.isClientSide ? null : (level0, pos, state0, blockEntity) -> ((MetallurgyFurnaceBlockEntity) blockEntity).tick();
        //return createTickerHelper(pBlockEntityType, ModBlockEntityTypes.METALLURGY_FURNACE_BLOCK_ENTITY.get(), MetallurgyFurnaceBlockEntity::craftingTick);
        return createTickerHelper(pBlockEntityType, ModBlockEntityTypes.METALLURGY_FURNACE_BLOCK_ENTITY.get(),
                MetallurgyFurnaceBlockEntity::tick);
    }

    /*
    @Nullable
    @Override
    public <T extends BlockEntity> GameEventListener getListener(Level pLevel, T pBlockEntity) {
        //return EntityBlock.super.getListener(pLevel, pBlockEntity);
    }
     */

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(pState.getBlock() != pNewState.getBlock()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if(entity instanceof MetallurgyFurnaceBlockEntity) {
                ((MetallurgyFurnaceBlockEntity) entity).drops();
            }
        }
    }
}
