package Parnapple.mistbornmod.block.entity;

import Parnapple.mistbornmod.MistbornBaseMod;
import Parnapple.mistbornmod.block.container.MetallurgyFurnaceContainer;
import Parnapple.mistbornmod.crafting.MetallurgyRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class MetallurgyFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    public static Component TITLE = new TranslatableComponent("container." + MistbornBaseMod.MOD_ID + ".metallurgy_furnace");

    private final ItemStackHandler itemHandler = new ItemStackHandler(11) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72 * 5;
    private int fuelTime = 0;
    private int maxFuelTime = 0;

    public MetallurgyFurnaceBlockEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntityTypes.METALLURGY_FURNACE_BLOCK_ENTITY.get(), pWorldPosition, pBlockState);

        this.data = new ContainerData() {
            public int get(int index) {
                switch (index) {
                    case 0: return MetallurgyFurnaceBlockEntity.this.progress;
                    case 1: return MetallurgyFurnaceBlockEntity.this.maxProgress;
                    case 2: return MetallurgyFurnaceBlockEntity.this.fuelTime;
                    case 3: return MetallurgyFurnaceBlockEntity.this.maxFuelTime;
                    default: return 0;
                }
            }

            public void set(int index, int value) {
                switch(index) {
                    case 0: MetallurgyFurnaceBlockEntity.this.progress = value; break;
                    case 1: MetallurgyFurnaceBlockEntity.this.maxProgress = value; break;
                    case 2: MetallurgyFurnaceBlockEntity.this.fuelTime = value; break;
                    case 3: MetallurgyFurnaceBlockEntity.this.maxFuelTime = value; break;
                }
            }

            public int getCount() {
                return 4;
            }
        };

    }
    @Override
    public Component getDisplayName() {
        return TITLE;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new MetallurgyFurnaceContainer(pContainerId, pInventory, this, this.data);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
       if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
           return lazyItemHandler.cast();
       }

       return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Override
    public void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", this.itemHandler.serializeNBT());
        pTag.putInt("metallurgy_furnace.progress", progress);
        pTag.putInt("metallurgy_furnace.fuelTime", fuelTime);
        pTag.putInt("metallurgy_furnace.maxFuelTime", maxFuelTime);
        super.saveAdditional(pTag);
    }




    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("metallurgy_furnace.progress");
        fuelTime = nbt.getInt("metallurgy_furnace.fuelTime");
        maxFuelTime = nbt.getInt("metallurgy_furnace.maxFuelTime");
    }




    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, MetallurgyFurnaceBlockEntity pBlockEntity) {
        if(isConsumingFuel(pBlockEntity)) {
            pBlockEntity.fuelTime--;
        }


        if(hasRecipe(pBlockEntity)) {
            if(hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
                pBlockEntity.consumeFuel();
                setChanged(pLevel, pPos, pState);
            }


            if(isConsumingFuel(pBlockEntity)) {
                pBlockEntity.progress++;
                setChanged(pLevel, pPos, pState);
                if(pBlockEntity.progress > pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity);
                }
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private static boolean hasRecipe(MetallurgyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MetallurgyRecipe> match = level.getRecipeManager()
                .getRecipeFor(MetallurgyRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getResultItem());

    }

    private static void craftItem(MetallurgyFurnaceBlockEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        Optional<MetallurgyRecipe> match = level.getRecipeManager()
                .getRecipeFor(MetallurgyRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            for(int i = 0; i < 9; i++) {
                entity.itemHandler.extractItem(i,1, false);
            }

            entity.itemHandler.setStackInSlot(10, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(10).getCount() + 9));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void consumeFuel() {
        if(!itemHandler.getStackInSlot(0).isEmpty()) {
            this.fuelTime = ForgeHooks.getBurnTime(this.itemHandler.extractItem(9, 1, false),
                    RecipeType.SMELTING)/8;
            this.maxFuelTime = this.fuelTime;
        }
    }

    private static boolean hasFuelInFuelSlot(MetallurgyFurnaceBlockEntity entity) {
        return !entity.itemHandler.getStackInSlot(0).isEmpty();
    }

    private static boolean isConsumingFuel(MetallurgyFurnaceBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inv, ItemStack output) {
        return inv.getItem(10).getItem() == output.getItem() || inv.getItem(10).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inv) {
        return inv.getItem(10).getMaxStackSize() >= inv.getItem(10).getCount()+9;
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


}
