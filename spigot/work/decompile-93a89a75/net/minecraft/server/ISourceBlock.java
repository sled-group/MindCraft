package net.minecraft.server;

public interface ISourceBlock extends ILocationSource {

    @Override
    double getX();

    @Override
    double getY();

    @Override
    double getZ();

    BlockPosition getBlockPosition();

    IBlockData getBlockData();

    <T extends TileEntity> T getTileEntity();
}
