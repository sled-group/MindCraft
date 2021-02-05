package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenSurfaceSwamp extends WorldGenSurface<WorldGenSurfaceConfigurationBase> {

    public WorldGenSurfaceSwamp(Function<Dynamic<?>, ? extends WorldGenSurfaceConfigurationBase> function) {
        super(function);
    }

    public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, WorldGenSurfaceConfigurationBase worldgensurfaceconfigurationbase) {
        double d1 = BiomeBase.e.a((double) i * 0.25D, (double) j * 0.25D);

        if (d1 > 0.0D) {
            int j1 = i & 15;
            int k1 = j & 15;
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

            for (int l1 = k; l1 >= 0; --l1) {
                blockposition_mutableblockposition.d(j1, l1, k1);
                if (!ichunkaccess.getType(blockposition_mutableblockposition).isAir()) {
                    if (l1 == 62 && ichunkaccess.getType(blockposition_mutableblockposition).getBlock() != iblockdata1.getBlock()) {
                        ichunkaccess.setType(blockposition_mutableblockposition, iblockdata1, false);
                    }
                    break;
                }
            }
        }

        WorldGenSurface.G.a(random, ichunkaccess, biomebase, i, j, k, d0, iblockdata, iblockdata1, l, i1, worldgensurfaceconfigurationbase);
    }
}
