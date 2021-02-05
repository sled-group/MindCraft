package net.minecraft.server;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ChunkProviderDebug extends ChunkGenerator<GeneratorSettingsDebug> {

    private static final List<IBlockData> g = (List) StreamSupport.stream(IRegistry.BLOCK.spliterator(), false).flatMap((block) -> {
        return block.getStates().a().stream();
    }).collect(Collectors.toList());
    private static final int h = MathHelper.f(MathHelper.c((float) ChunkProviderDebug.g.size()));
    private static final int i = MathHelper.f((float) ChunkProviderDebug.g.size() / (float) ChunkProviderDebug.h);
    protected static final IBlockData e = Blocks.AIR.getBlockData();
    protected static final IBlockData f = Blocks.BARRIER.getBlockData();

    public ChunkProviderDebug(GeneratorAccess generatoraccess, WorldChunkManager worldchunkmanager, GeneratorSettingsDebug generatorsettingsdebug) {
        super(generatoraccess, worldchunkmanager, generatorsettingsdebug);
    }

    @Override
    public void buildBase(IChunkAccess ichunkaccess) {}

    @Override
    public void doCarving(IChunkAccess ichunkaccess, WorldGenStage.Features worldgenstage_features) {}

    @Override
    public int getSpawnHeight() {
        return this.a.getSeaLevel() + 1;
    }

    @Override
    public void addDecorations(RegionLimitedWorldAccess regionlimitedworldaccess) {
        BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
        int i = regionlimitedworldaccess.a();
        int j = regionlimitedworldaccess.b();

        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                int i1 = (i << 4) + k;
                int j1 = (j << 4) + l;

                regionlimitedworldaccess.setTypeAndData(blockposition_mutableblockposition.d(i1, 60, j1), ChunkProviderDebug.f, 2);
                IBlockData iblockdata = a(i1, j1);

                if (iblockdata != null) {
                    regionlimitedworldaccess.setTypeAndData(blockposition_mutableblockposition.d(i1, 70, j1), iblockdata, 2);
                }
            }
        }

    }

    @Override
    public void buildNoise(GeneratorAccess generatoraccess, IChunkAccess ichunkaccess) {}

    @Override
    public int getBaseHeight(int i, int j, HeightMap.Type heightmap_type) {
        return 0;
    }

    public static IBlockData a(int i, int j) {
        IBlockData iblockdata = ChunkProviderDebug.e;

        if (i > 0 && j > 0 && i % 2 != 0 && j % 2 != 0) {
            i /= 2;
            j /= 2;
            if (i <= ChunkProviderDebug.h && j <= ChunkProviderDebug.i) {
                int k = MathHelper.a(i * ChunkProviderDebug.h + j);

                if (k < ChunkProviderDebug.g.size()) {
                    iblockdata = (IBlockData) ChunkProviderDebug.g.get(k);
                }
            }
        }

        return iblockdata;
    }
}
