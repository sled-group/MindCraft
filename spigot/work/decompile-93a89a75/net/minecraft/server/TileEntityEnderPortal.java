package net.minecraft.server;

public class TileEntityEnderPortal extends TileEntity {

    public TileEntityEnderPortal(TileEntityTypes<?> tileentitytypes) {
        super(tileentitytypes);
    }

    public TileEntityEnderPortal() {
        this(TileEntityTypes.END_PORTAL);
    }
}
