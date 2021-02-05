package net.minecraft.server;

import java.util.function.Predicate;

public interface VirtualLevelReadable {

    boolean a(BlockPosition blockposition, Predicate<IBlockData> predicate);

    BlockPosition getHighestBlockYAt(HeightMap.Type heightmap_type, BlockPosition blockposition);
}
