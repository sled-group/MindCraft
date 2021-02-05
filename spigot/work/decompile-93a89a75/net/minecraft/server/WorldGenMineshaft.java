package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WorldGenMineshaft extends StructureGenerator<WorldGenMineshaftConfiguration> {

    public WorldGenMineshaft(Function<Dynamic<?>, ? extends WorldGenMineshaftConfiguration> function) {
        super(function);
    }

    @Override
    public boolean a(ChunkGenerator<?> chunkgenerator, Random random, int i, int j) {
        ((SeededRandom) random).c(chunkgenerator.getSeed(), i, j);
        BiomeBase biomebase = chunkgenerator.getWorldChunkManager().getBiome(new BlockPosition((i << 4) + 9, 0, (j << 4) + 9));

        if (chunkgenerator.canSpawnStructure(biomebase, WorldGenerator.MINESHAFT)) {
            WorldGenMineshaftConfiguration worldgenmineshaftconfiguration = (WorldGenMineshaftConfiguration) chunkgenerator.getFeatureConfiguration(biomebase, WorldGenerator.MINESHAFT);
            double d0 = worldgenmineshaftconfiguration.a;

            return random.nextDouble() < d0;
        } else {
            return false;
        }
    }

    @Override
    public StructureGenerator.a a() {
        return WorldGenMineshaft.a::new;
    }

    @Override
    public String b() {
        return "Mineshaft";
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
            WorldGenMineshaftConfiguration worldgenmineshaftconfiguration = (WorldGenMineshaftConfiguration) chunkgenerator.getFeatureConfiguration(biomebase, WorldGenerator.MINESHAFT);
            WorldGenMineshaftPieces.WorldGenMineshaftRoom worldgenmineshaftpieces_worldgenmineshaftroom = new WorldGenMineshaftPieces.WorldGenMineshaftRoom(0, this.d, (i << 4) + 2, (j << 4) + 2, worldgenmineshaftconfiguration.b);

            this.b.add(worldgenmineshaftpieces_worldgenmineshaftroom);
            worldgenmineshaftpieces_worldgenmineshaftroom.a((StructurePiece) worldgenmineshaftpieces_worldgenmineshaftroom, this.b, (Random) this.d);
            this.b();
            if (worldgenmineshaftconfiguration.b == WorldGenMineshaft.Type.MESA) {
                boolean flag = true;
                int k = chunkgenerator.getSeaLevel() - this.c.e + this.c.d() / 2 - -5;

                this.c.a(0, k, 0);
                Iterator iterator = this.b.iterator();

                while (iterator.hasNext()) {
                    StructurePiece structurepiece = (StructurePiece) iterator.next();

                    structurepiece.a(0, k, 0);
                }
            } else {
                this.a(chunkgenerator.getSeaLevel(), this.d, 10);
            }

        }
    }

    public static enum Type {

        NORMAL("normal"), MESA("mesa");

        private static final Map<String, WorldGenMineshaft.Type> c = (Map) Arrays.stream(values()).collect(Collectors.toMap(WorldGenMineshaft.Type::a, (worldgenmineshaft_type) -> {
            return worldgenmineshaft_type;
        }));
        private final String d;

        private Type(String s) {
            this.d = s;
        }

        public String a() {
            return this.d;
        }

        public static WorldGenMineshaft.Type a(String s) {
            return (WorldGenMineshaft.Type) WorldGenMineshaft.Type.c.get(s);
        }

        public static WorldGenMineshaft.Type a(int i) {
            return i >= 0 && i < values().length ? values()[i] : WorldGenMineshaft.Type.NORMAL;
        }
    }
}
