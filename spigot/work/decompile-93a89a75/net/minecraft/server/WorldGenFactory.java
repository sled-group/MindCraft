package net.minecraft.server;

import java.util.Locale;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenFactory {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final StructureGenerator<?> a = a("Mineshaft", WorldGenerator.MINESHAFT);
    public static final StructureGenerator<?> b = a("Pillager_Outpost", WorldGenerator.PILLAGER_OUTPOST);
    public static final StructureGenerator<?> c = a("Fortress", WorldGenerator.NETHER_BRIDGE);
    public static final StructureGenerator<?> d = a("Stronghold", WorldGenerator.STRONGHOLD);
    public static final StructureGenerator<?> e = a("Jungle_Pyramid", WorldGenerator.JUNGLE_TEMPLE);
    public static final StructureGenerator<?> f = a("Ocean_Ruin", WorldGenerator.OCEAN_RUIN);
    public static final StructureGenerator<?> g = a("Desert_Pyramid", WorldGenerator.DESERT_PYRAMID);
    public static final StructureGenerator<?> h = a("Igloo", WorldGenerator.IGLOO);
    public static final StructureGenerator<?> i = a("Swamp_Hut", WorldGenerator.SWAMP_HUT);
    public static final StructureGenerator<?> j = a("Monument", WorldGenerator.OCEAN_MONUMENT);
    public static final StructureGenerator<?> k = a("EndCity", WorldGenerator.END_CITY);
    public static final StructureGenerator<?> l = a("Mansion", WorldGenerator.WOODLAND_MANSION);
    public static final StructureGenerator<?> m = a("Buried_Treasure", WorldGenerator.BURIED_TREASURE);
    public static final StructureGenerator<?> n = a("Shipwreck", WorldGenerator.SHIPWRECK);
    public static final StructureGenerator<?> o = a("Village", WorldGenerator.VILLAGE);

    private static StructureGenerator<?> a(String s, StructureGenerator<?> structuregenerator) {
        return (StructureGenerator) IRegistry.a(IRegistry.STRUCTURE_FEATURE, s.toLowerCase(Locale.ROOT), (Object) structuregenerator);
    }

    public static void a() {}

    @Nullable
    public static StructureStart a(ChunkGenerator<?> chunkgenerator, DefinedStructureManager definedstructuremanager, WorldChunkManager worldchunkmanager, NBTTagCompound nbttagcompound) {
        String s = nbttagcompound.getString("id");

        if ("INVALID".equals(s)) {
            return StructureStart.a;
        } else {
            StructureGenerator<?> structuregenerator = (StructureGenerator) IRegistry.STRUCTURE_FEATURE.get(new MinecraftKey(s.toLowerCase(Locale.ROOT)));

            if (structuregenerator == null) {
                WorldGenFactory.LOGGER.error("Unknown feature id: {}", s);
                return null;
            } else {
                int i = nbttagcompound.getInt("ChunkX");
                int j = nbttagcompound.getInt("ChunkZ");
                BiomeBase biomebase = nbttagcompound.hasKey("biome") ? (BiomeBase) IRegistry.BIOME.get(new MinecraftKey(nbttagcompound.getString("biome"))) : worldchunkmanager.getBiome(new BlockPosition((i << 4) + 9, 0, (j << 4) + 9));
                StructureBoundingBox structureboundingbox = nbttagcompound.hasKey("BB") ? new StructureBoundingBox(nbttagcompound.getIntArray("BB")) : StructureBoundingBox.a();
                NBTTagList nbttaglist = nbttagcompound.getList("Children", 10);

                try {
                    StructureStart structurestart = structuregenerator.a().create(structuregenerator, i, j, biomebase, structureboundingbox, 0, chunkgenerator.getSeed());

                    for (int k = 0; k < nbttaglist.size(); ++k) {
                        NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(k);
                        String s1 = nbttagcompound1.getString("id");
                        WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype = (WorldGenFeatureStructurePieceType) IRegistry.STRUCTURE_PIECE.get(new MinecraftKey(s1.toLowerCase(Locale.ROOT)));

                        if (worldgenfeaturestructurepiecetype == null) {
                            WorldGenFactory.LOGGER.error("Unknown structure piece id: {}", s1);
                        } else {
                            try {
                                StructurePiece structurepiece = worldgenfeaturestructurepiecetype.load(definedstructuremanager, nbttagcompound1);

                                structurestart.b.add(structurepiece);
                            } catch (Exception exception) {
                                WorldGenFactory.LOGGER.error("Exception loading structure piece with id {}", s1, exception);
                            }
                        }
                    }

                    return structurestart;
                } catch (Exception exception1) {
                    WorldGenFactory.LOGGER.error("Failed Start with id {}", s, exception1);
                    return null;
                }
            }
        }
    }
}
