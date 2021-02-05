package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public class WorldGenFossils extends WorldGenerator<WorldGenFeatureEmptyConfiguration> {

    private static final MinecraftKey a = new MinecraftKey("fossil/spine_1");
    private static final MinecraftKey aS = new MinecraftKey("fossil/spine_2");
    private static final MinecraftKey aT = new MinecraftKey("fossil/spine_3");
    private static final MinecraftKey aU = new MinecraftKey("fossil/spine_4");
    private static final MinecraftKey aV = new MinecraftKey("fossil/spine_1_coal");
    private static final MinecraftKey aW = new MinecraftKey("fossil/spine_2_coal");
    private static final MinecraftKey aX = new MinecraftKey("fossil/spine_3_coal");
    private static final MinecraftKey aY = new MinecraftKey("fossil/spine_4_coal");
    private static final MinecraftKey aZ = new MinecraftKey("fossil/skull_1");
    private static final MinecraftKey ba = new MinecraftKey("fossil/skull_2");
    private static final MinecraftKey bb = new MinecraftKey("fossil/skull_3");
    private static final MinecraftKey bc = new MinecraftKey("fossil/skull_4");
    private static final MinecraftKey bd = new MinecraftKey("fossil/skull_1_coal");
    private static final MinecraftKey be = new MinecraftKey("fossil/skull_2_coal");
    private static final MinecraftKey bf = new MinecraftKey("fossil/skull_3_coal");
    private static final MinecraftKey bg = new MinecraftKey("fossil/skull_4_coal");
    private static final MinecraftKey[] bh = new MinecraftKey[]{WorldGenFossils.a, WorldGenFossils.aS, WorldGenFossils.aT, WorldGenFossils.aU, WorldGenFossils.aZ, WorldGenFossils.ba, WorldGenFossils.bb, WorldGenFossils.bc};
    private static final MinecraftKey[] bi = new MinecraftKey[]{WorldGenFossils.aV, WorldGenFossils.aW, WorldGenFossils.aX, WorldGenFossils.aY, WorldGenFossils.bd, WorldGenFossils.be, WorldGenFossils.bf, WorldGenFossils.bg};

    public WorldGenFossils(Function<Dynamic<?>, ? extends WorldGenFeatureEmptyConfiguration> function) {
        super(function);
    }

    public boolean a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, BlockPosition blockposition, WorldGenFeatureEmptyConfiguration worldgenfeatureemptyconfiguration) {
        Random random1 = generatoraccess.getRandom();
        EnumBlockRotation[] aenumblockrotation = EnumBlockRotation.values();
        EnumBlockRotation enumblockrotation = aenumblockrotation[random1.nextInt(aenumblockrotation.length)];
        int i = random1.nextInt(WorldGenFossils.bh.length);
        DefinedStructureManager definedstructuremanager = ((WorldServer) generatoraccess.getMinecraftWorld()).getDataManager().f();
        DefinedStructure definedstructure = definedstructuremanager.a(WorldGenFossils.bh[i]);
        DefinedStructure definedstructure1 = definedstructuremanager.a(WorldGenFossils.bi[i]);
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(blockposition);
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(chunkcoordintpair.d(), 0, chunkcoordintpair.e(), chunkcoordintpair.f(), 256, chunkcoordintpair.g());
        DefinedStructureInfo definedstructureinfo = (new DefinedStructureInfo()).a(enumblockrotation).a(structureboundingbox).a(random1).a((DefinedStructureProcessor) DefinedStructureProcessorBlockIgnore.c);
        BlockPosition blockposition1 = definedstructure.a(enumblockrotation);
        int j = random1.nextInt(16 - blockposition1.getX());
        int k = random1.nextInt(16 - blockposition1.getZ());
        int l = 256;

        int i1;

        for (i1 = 0; i1 < blockposition1.getX(); ++i1) {
            for (int j1 = 0; j1 < blockposition1.getZ(); ++j1) {
                l = Math.min(l, generatoraccess.a(HeightMap.Type.OCEAN_FLOOR_WG, blockposition.getX() + i1 + j, blockposition.getZ() + j1 + k));
            }
        }

        i1 = Math.max(l - 15 - random1.nextInt(10), 10);
        BlockPosition blockposition2 = definedstructure.a(blockposition.b(j, i1, k), EnumBlockMirror.NONE, enumblockrotation);
        DefinedStructureProcessorRotation definedstructureprocessorrotation = new DefinedStructureProcessorRotation(0.9F);

        definedstructureinfo.b().a((DefinedStructureProcessor) definedstructureprocessorrotation);
        definedstructure.a(generatoraccess, blockposition2, definedstructureinfo, 4);
        definedstructureinfo.b((DefinedStructureProcessor) definedstructureprocessorrotation);
        DefinedStructureProcessorRotation definedstructureprocessorrotation1 = new DefinedStructureProcessorRotation(0.1F);

        definedstructureinfo.b().a((DefinedStructureProcessor) definedstructureprocessorrotation1);
        definedstructure1.a(generatoraccess, blockposition2, definedstructureinfo, 4);
        return true;
    }
}
