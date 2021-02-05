package net.minecraft.server;

public class TileEntitySmoker extends TileEntityFurnace {

    public TileEntitySmoker() {
        super(TileEntityTypes.SMOKER, Recipes.SMOKING);
    }

    @Override
    protected IChatBaseComponent getContainerName() {
        return new ChatMessage("container.smoker", new Object[0]);
    }

    @Override
    protected int fuelTime(ItemStack itemstack) {
        return super.fuelTime(itemstack) / 2;
    }

    @Override
    protected Container createContainer(int i, PlayerInventory playerinventory) {
        return new ContainerSmoker(i, playerinventory, this, this.b);
    }
}
