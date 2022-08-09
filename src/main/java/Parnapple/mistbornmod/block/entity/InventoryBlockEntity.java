package Parnapple.mistbornmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryBlockEntity extends BlockEntity {
    public final int size;
    protected int timer;
    protected boolean requiresUpdate;

    public final ItemStackHandler inventory;
    protected LazyOptional<ItemStackHandler> handler;

    public InventoryBlockEntity(BlockEntityType<?> pType, BlockPos pWorldPosition, BlockState pBlockState, int size) {
        super(pType, pWorldPosition, pBlockState);
        if(size <= 0) {
            this.size = 1;
        } else {
            this.size = size;
        }

        this.inventory = createInventory();
        this.handler = LazyOptional.of(() -> this.inventory);
    }

    private ItemStackHandler createInventory() {
        return new ItemStackHandler(this.size) {
            @Override
            public ItemStack extractItem(int slot, int amount, boolean simulate) {
                InventoryBlockEntity.this.update();

                return super.extractItem(slot, amount, simulate);
            }

            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                InventoryBlockEntity.this.update();

                return super.insertItem(slot, stack, simulate);
            }
        };

    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? this.handler.cast() : super.getCapability(cap, side);
    }

    public LazyOptional<ItemStackHandler> getHandler() {
        return handler;
    }

    //not sure if this'll work right
    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return new ClientboundBlockEntityDataPacket(this.worldPosition, -1, save(new CompoundTag()));
    }

    @Override
    public CompoundTag getUpdateTag() {
        return serializeNBT();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        load(tag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public CompoundTag save(CompoundTag pTag) {
        super.save(pTag);
        pTag.put("inventory", this.inventory.serializeNBT());

        return pTag;
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.inventory.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        this.handler.invalidate();
    }

    public void update() {
        requestModelDataUpdate();
        setChanged();
        if(this.level != null) {
            this.level.setBlockAndUpdate(worldPosition, getBlockState());
        }
    }

    public void tick() {
        this.timer++;
        if(this.requiresUpdate && this.level != null) {
            update();
            this.requiresUpdate = false;
        }
    }

    public ItemStack extractItem(int slot) {
        final int count = getItemInSlot(slot).getCount();
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.extractItem(slot, count, false)).orElse(ItemStack.EMPTY);
    }

    public ItemStack getItemInSlot(int slot) {
        return this.handler.map(inv -> inv.getStackInSlot(slot)).orElse(ItemStack.EMPTY);
    }

    public ItemStack insertItem(int slot, ItemStack stack) {
        ItemStack copy = stack.copy();
        stack.shrink(copy.getCount());
        this.requiresUpdate = true;
        return this.handler.map(inv -> inv.insertItem(slot, copy, false)).orElse(ItemStack.EMPTY);
    }


}
