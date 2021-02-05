package net.minecraft.server;

public class TileEntityBlastFurnace extends TileEntityFurnace {

    public TileEntityBlastFurnace() {
        super(TileEntityTypes.BLAST_FURNACE, Recipes.BLASTING);
    }

    @Override
    protected IChatBaseComponent getContainerName() {
        return new ChatMessage("container.blast_furnace", new Object[0]);
    }

    @Override
    protected int fuelTime(ItemStack itemstack) {
        return super.fuelTime(itemstack) / 2;
    }

    @Override
    protected Container createContainer(int i, PlayerInventory playerinventory) {
        return new ContainerBlastFurnace(i, playerinventory, this, this.b);
    }
}
