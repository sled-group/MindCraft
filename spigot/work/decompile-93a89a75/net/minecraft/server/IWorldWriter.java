package net.minecraft.server;

public interface IWorldWriter {

    boolean setTypeAndData(BlockPosition blockposition, IBlockData iblockdata, int i);

    boolean a(BlockPosition blockposition, boolean flag);

    boolean b(BlockPosition blockposition, boolean flag);

    default boolean addEntity(Entity entity) {
        return false;
    }
}
