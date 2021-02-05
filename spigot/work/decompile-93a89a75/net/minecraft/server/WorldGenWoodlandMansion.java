package net.minecraft.server;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;

public class WorldGenWoodlandMansion extends StructureGenerator<WorldGenFeatureEmptyConfiguration> {

    public WorldGenWoodlandMansion(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    @Override
    protected ChunkCoordIntPair a(ChunkGenerator<?> chunkgenerator, Random random, int i, int j, int k, int l) {
        int i1 = chunkgenerator.getSettings().p();
        int j1 = chunkgenerator.getSettings().q();
        int k1 = i + i1 * k;
        int l1 = j + i1 * l;
        int i2 = k1 < 0 ? k1 - i1 + 1 : k1;
        int j2 = l1 < 0 ? l1 - i1 + 1 : l1;
        int k2 = i2 / i1;
        int l2 = j2 / i1;

        ((SeededRandom) random).a(chunkgenerator.getSeed(), k2, l2, 10387319);
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
            Set<BiomeBase> set = chunkgenerator.getWorldChunkManager().a(i * 16 + 9, j * 16 + 9, 32);
            Iterator iterator = set.iterator();

            BiomeBase biomebase;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                biomebase = (BiomeBase) iterator.next();
            } while (chunkgenerator.canSpawnStructure(biomebase, WorldGenerator.WOODLAND_MANSION));

            return false;
        } else {
            return false;
        }
    }

    @Override
    public StructureGenerator.a a() {
        return WorldGenWoodlandMansion.a::new;
    }

    @Override
    public String b() {
        return "Mansion";
    }

    @Override
    public int c() {
        return 8;
    }

    public static class a extends StructureStart {

        public a(StructureGenerator<?> structuregenerator, int i, int j, BiomeBase biomebase, StructureBoundingBox structureboundingbox, int k, long l) {
            super(structuregenerator, i, j, biomebase, structureboundingbox, k, l);
        }

        @Override
        public void a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, int i, int j, BiomeBase biomebase) {
            EnumBlockRotation enumblockrotation = EnumBlockRotation.values()[this.d.nextInt(EnumBlockRotation.values().length)];
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
            int i2 = Math.min(Math.min(i1, j1), Math.min(k1, l1));

            if (i2 >= 60) {
                BlockPosition blockposition = new BlockPosition(i * 16 + 8, i2 + 1, j * 16 + 8);
                List<WorldGenWoodlandMansionPieces.i> list = Lists.newLinkedList();

                WorldGenWoodlandMansionPieces.a(definedstructuremanager, blockposition, enumblockrotation, list, this.d);
                this.b.addAll(list);
                this.b();
            }
        }

        @Override
        public void a(GeneratorAccess generatoraccess, Random random, StructureBoundingBox structureboundingbox, ChunkCoordIntPair chunkcoordintpair) {
            super.a(generatoraccess, random, structureboundingbox, chunkcoordintpair);
            int i = this.c.b;

            for (int j = structureboundingbox.a; j <= structureboundingbox.d; ++j) {
                for (int k = structureboundingbox.c; k <= structureboundingbox.f; ++k) {
                    BlockPosition blockposition = new BlockPosition(j, i, k);

                    if (!generatoraccess.isEmpty(blockposition) && this.c.b((BaseBlockPosition) blockposition)) {
                        boolean flag = false;
                        Iterator iterator = this.b.iterator();

                        while (iterator.hasNext()) {
                            StructurePiece structurepiece = (StructurePiece) iterator.next();

                            if (structurepiece.g().b((BaseBlockPosition) blockposition)) {
                                flag = true;
                                break;
                            }
                        }

                        if (flag) {
                            for (int l = i - 1; l > 1; --l) {
                                BlockPosition blockposition1 = new BlockPosition(j, l, k);

                                if (!generatoraccess.isEmpty(blockposition1) && !generatoraccess.getType(blockposition1).getMaterial().isLiquid()) {
                                    break;
                                }

                                generatoraccess.setTypeAndData(blockposition1, Blocks.COBBLESTONE.getBlockData(), 2);
                            }
                        }
                    }
                }
            }

        }
    }
}
