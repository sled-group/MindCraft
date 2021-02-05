package net.minecraft.server;

public class SourceBlock implements ISourceBlock {

    private final World a;
    private final BlockPosition b;

    public SourceBlock(World world, BlockPosition blockposition) {
        this.a = world;
        this.b = blockposition;
    }

    @Override
    public World getWorld() {
        return this.a;
    }

    @Override
    public double getX() {
        return (double) this.b.getX() + 0.5D;
    }

    @Override
    public double getY() {
        return (double) this.b.getY() + 0.5D;
    }

    @Override
    public double getZ() {
        return (double) this.b.getZ() + 0.5D;
    }

    @Override
    public BlockPosition getBlockPosition() {
        return this.b;
    }

    @Override
    public IBlockData getBlockData() {
        return this.a.getType(this.b);
    }

    @Override
    public <T extends TileEntity> T getTileEntity() {
        return this.a.getTileEntity(this.b);
    }
}
