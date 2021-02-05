package net.minecraft.server;

public class ContainerFurnaceFurnace extends ContainerFurnace {

    public ContainerFurnaceFurnace(int i, PlayerInventory playerinventory) {
        super(Containers.FURNACE, Recipes.SMELTING, i, playerinventory);
    }

    public ContainerFurnaceFurnace(int i, PlayerInventory playerinventory, IInventory iinventory, IContainerProperties icontainerproperties) {
        super(Containers.FURNACE, Recipes.SMELTING, i, playerinventory, iinventory, icontainerproperties);
    }
}
