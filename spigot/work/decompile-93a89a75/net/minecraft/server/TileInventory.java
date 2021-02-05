package net.minecraft.server;

public final class TileInventory implements ITileInventory {

    private final IChatBaseComponent a;
    private final ITileEntityContainer b;

    public TileInventory(ITileEntityContainer itileentitycontainer, IChatBaseComponent ichatbasecomponent) {
        this.b = itileentitycontainer;
        this.a = ichatbasecomponent;
    }

    @Override
    public IChatBaseComponent getScoreboardDisplayName() {
        return this.a;
    }

    @Override
    public Container createMenu(int i, PlayerInventory playerinventory, EntityHuman entityhuman) {
        return this.b.createMenu(i, playerinventory, entityhuman);
    }
}
