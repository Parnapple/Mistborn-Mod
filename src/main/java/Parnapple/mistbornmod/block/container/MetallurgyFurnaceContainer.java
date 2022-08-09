package Parnapple.mistbornmod.block.container;

import Parnapple.mistbornmod.block.ModBlocks;
import Parnapple.mistbornmod.block.entity.MetallurgyFurnaceBlockEntity;
import Parnapple.mistbornmod.screen.slot.ModResultSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class MetallurgyFurnaceContainer extends AbstractContainerMenu {
   // private final ContainerLevelAccess containerAccess;
    private final MetallurgyFurnaceBlockEntity blockEntity;
    private final Level level;
    public final ContainerData data;

    //Client constructor
    public MetallurgyFurnaceContainer(int id, Inventory playerInv, FriendlyByteBuf extraData) {
        this(id, playerInv, playerInv.player.level.getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(4));
    }

    //Server constructor
    public MetallurgyFurnaceContainer(int id, Inventory playerInv, BlockEntity entity, ContainerData data) {
        super(ModContainers.METALLURGY_FURNACE_CONTAINER.get(), id);
        checkContainerSize(playerInv, 11);
        blockEntity = ((MetallurgyFurnaceBlockEntity) entity);
        this.level = playerInv.player.level;
        this.data = data;

        final int slotSizePlus2 = 18, startX = 8, startY = 24, hotbarY = 179, inventoryY = 121, gridStartX = 38;

        addPlayerInventory(playerInv);
        addPlayerHotbar(playerInv);

        //furnace inventory
        this.blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
            for(int column = 0; column < 3; column++) {
                for(int row = 0; row < 3; row++) {
                    addSlot(new SlotItemHandler(handler,row * 3 + column,gridStartX + column * slotSizePlus2, startY + row * slotSizePlus2));
                }
            }

            addSlot(new SlotItemHandler(handler, 9,gridStartX + 1 * slotSizePlus2, 96));
            addSlot(new ModResultSlot(handler, 10,116, 79));
        });

        addDataSlots(data);


    }

    public boolean isCrafting() {
        return data.get(0) > 0;
    }

    public boolean hasFuel() {
        return data.get(2) > 0;
    }

    public int getScaledProgress() {
        int progress = this.data.get(0);
        int maxProgress = this.data.get(1);  // Max Progress
        int progressArrowSize = 22; // This is the height in pixels of your arrow

        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    public int getScaledFuelProgress() {
        int fuelProgress = this.data.get(2);
        int maxFuelProgress = this.data.get(3);
        int fuelProgressSize = 14;

        return maxFuelProgress != 0 ? (int)(((float)fuelProgress / (float)maxFuelProgress) * fuelProgressSize) : 0;
    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                pPlayer, ModBlocks.METALLURGY_FURNACE_BLOCK.get());
    }

    /*
    public static MenuConstructor getServerContainer(MetallurgyFurnaceBlockEntity furnace, BlockPos pos) {
        return (id, playerInv, player) -> new MetallurgyFurnaceContainer(id, playerInv, furnace.inventory, pos);
    }
     */

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        var retStack = ItemStack.EMPTY;

        final Slot slot = getSlot(pIndex);
        if(slot.hasItem()) {
            final ItemStack item = slot.getItem();
            retStack = item.copy();
            if(pIndex < 11) {
                if(!moveItemStackTo(item, 11, this.slots.size(), true))
                    return ItemStack.EMPTY;
            } else if(!moveItemStackTo(item, 0, 11, false))
                return ItemStack.EMPTY;

            if(item.isEmpty()) slot.set(ItemStack.EMPTY);
                else slot.setChanged();

        }


        return retStack;
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 121 + i * 18));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 179));
        }
    }

}
