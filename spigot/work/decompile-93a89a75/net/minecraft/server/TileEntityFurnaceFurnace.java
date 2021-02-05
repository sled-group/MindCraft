package net.minecraft.server;

public class TileEntityFurnaceFurnace extends TileEntityFurnace {

    public TileEntityFurnaceFurnace() {
        super(TileEntityTypes.FURNACE, Recipes.SMELTING);
    }

    @Override
    protected IChatBaseComponent getContainerName() {
        return new ChatMessage("container.furnace", new Object[0]);
    }

    @Override
    protected Container createContainer(int i, PlayerInventory playerinventory) {
        return new ContainerFurnaceFurnace(i, playerinventory, this, this.b);
    }
}
