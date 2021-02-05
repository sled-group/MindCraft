package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenEndCity extends StructureGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenEndCity(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected ChunkCoordIntPair a(ChunkGenerator<?> chunkgenerator, Random random, int i, int j, int k, int l) {
        int i1 = chunkgenerator.getSettings().n();
        int j1 = chunkgenerator.getSettings().o();
        int k1 = i + i1 * k;
        int l1 = j + i1 * l;
        int i2 = k1 < 0 ? k1 - i1 + 1 : k1;
        int j2 = l1 < 0 ? l1 - i1 + 1 : l1;
        int k2 = i2 / i1;
        int l2 = j2 / i1;

        ((SeededRandom) random).a(chunkgenerator.getSeed(), k2, l2, 10387313);
        k2 *= i1;
        l2 *= i1;
        k2 += (random.nextInt(i1 - j1) + random.nextInt(i1 - j1)) / 2;
        l2 += (random.nextInt(i1 - j1) + random.nextInt(i1 - j1)) / 2;
        return new ChunkCoordIntPair(k2, l2);
    }

    @Override
    public boolean a(ChunkGenerator<?> chunkgenerator, Random random, int i, int j) {
        ChunkCoordIntPair chunkcoordintpair = this.a(chunkgenerator, random, i, j, 0, 0);

        if (i == chunkcoordintpair.x && j == chunkcoordintpair.z) {
            BiomeBase biomebase = chunkgenerator.getWorldChunkManager().getBiome(new BlockPosition((i << 4) + 9, 0, (j << 4) + 9));

            if (!chunkgenerator.canSpawnStructure(biomebase, WorldGenerator.END_CITY)) {
                return false;
            } else {
                int k = b(i, j, chunkgenerator);

                return k >= 60;
            }
        } else {
            return false;
        }
    }

    @Override
    public StructureGenerator.a a() {
        return WorldGenEndCity.a::new;
    }

    @Override
    public String b() {
        return "EndCity";
    }

    @Override
    public int c() {
        return 8;
    }

    private static int b(int i, int j, ChunkGenerator<?> chunkgenerator) {
        Random random = new Random((long) (i + j * 10387313));
        EnumBlockRotation enumblockrotation = EnumBlockRotation.values()[random.nextInt(EnumBlockRotation.values().length)];
        byte b0 = 5;
        byte b1 = 5;

        if (enumblockrotation == EnumBlockRotation.CLOCKWISE_90) {
            b0 = -5;
        } else if (enumblockrotation == EnumBlockRotation.CLOCKWISE_180) {
            b0 = -5;
            b1 = -5;
        } else if (enumblockrotation == EnumBlockRotation.COUNTERCLOCKWISE_90) {
            b1 = -5;
        }

        int k = (i << 4) + 7;
        int l = (j << 4) + 7;
        int i1 = chunkgenerator.c(k, l, HeightMap.Type.WORLD_SURFACE_WG);
        int j1 = chunkgenerator.c(k, l + b1, HeightMap.Type.WORLD_SURFACE_WG);
        int k1 = chunkgenerator.c(k + b0, l, HeightMap.Type.WORLD_SURFACE_WG);
        int l1 = chunkgenerator.c(k + b0, l + b1, HeightMap.Type.WORLD_SURFACE_WG);

        return Math.min(Math.min(i1, j1), Math.min(k1, l1));
    }

    public static class a extends StructureStart {

        public a(StructureGenerator<?> structuregenerator, int i, int j, BiomeBase biomebase, StructureBoundingBox structureboundingbox, int k, long l) {
            super(structuregenerator, i, j, biomebase, structureboundingbox, k, l);
        }

        @Override
        public void a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, int i, int j, BiomeBase biomebase) {
            EnumBlockRotation enumblockrotation = EnumBlockRotation.values()[this.d.nextInt(EnumBlockRotation.values().length)];
            int k = WorldGenEndCity.b(i, j, chunkgenerator);

            if (k >= 60) {
                BlockPosition blockposition = new BlockPosition(i * 16 + 8, k, j * 16 + 8);

                WorldGenEndCityPieces.a(definedstructuremanager, blockposition, enumblockrotation, this.b, this.d);
                this.b();
            }
        }
    }
}
