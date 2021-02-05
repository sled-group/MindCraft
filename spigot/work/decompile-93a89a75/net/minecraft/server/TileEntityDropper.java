package net.minecraft.server;

public class TileEntityDropper extends TileEntityDispenser {

    public TileEntityDropper() {
        super(TileEntityTypes.DROPPER);
    }

    @Override
    protected IChatBaseComponent getContainerName() {
        return new ChatMessage("container.dropper", new Object[0]);
    }
}
