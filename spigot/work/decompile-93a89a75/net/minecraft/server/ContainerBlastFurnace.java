package net.minecraft.server;

public class ContainerBlastFurnace extends ContainerFurnace {

    public ContainerBlastFurnace(int i, PlayerInventory playerinventory) {
        super(Containers.BLAST_FURNACE, Recipes.BLASTING, i, playerinventory);
    }

    public ContainerBlastFurnace(int i, PlayerInventory playerinventory, IInventory iinventory, IContainerProperties icontainerproperties) {
        super(Containers.BLAST_FURNACE, Recipes.BLASTING, i, playerinventory, iinventory, icontainerproperties);
    }
}
