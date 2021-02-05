package net.minecraft.server;

import java.util.Collections;

public abstract class Biomes {

    public static final BiomeBase OCEAN = a(0, "ocean", new BiomeOcean());
    public static final BiomeBase b = Biomes.OCEAN;
    public static final BiomeBase PLAINS = a(1, "plains", new BiomePlains());
    public static final BiomeBase DESERT = a(2, "desert", new BiomeDesert());
    public static final BiomeBase MOUNTAINS = a(3, "mountains", new BiomeBigHills());
    public static final BiomeBase FOREST = a(4, "forest", new BiomeForest());
    public static final BiomeBase TAIGA = a(5, "taiga", new BiomeTaiga());
    public static final BiomeBase SWAMP = a(6, "swamp", new BiomeSwamp());
    public static final BiomeBase RIVER = a(7, "river", new BiomeRiver());
    public static final BiomeBase NETHER = a(8, "nether", new BiomeHell());
    public static final BiomeBase THE_END = a(9, "the_end", new BiomeTheEnd());
    public static final BiomeBase FROZEN_OCEAN = a(10, "frozen_ocean", new BiomeFrozenOcean());
    public static final BiomeBase FROZEN_RIVER = a(11, "frozen_river", new BiomeFrozenRiver());
    public static final BiomeBase SNOWY_TUNDRA = a(12, "snowy_tundra", new BiomeIcePlains());
    public static final BiomeBase SNOWY_MOUNTAINS = a(13, "snowy_mountains", new BiomeIceMountains());
    public static final BiomeBase MUSHROOM_FIELDS = a(14, "mushroom_fields", new BiomeMushrooms());
    public static final BiomeBase MUSHROOM_FIELD_SHORE = a(15, "mushroom_field_shore", new BiomeMushroomIslandShore());
    public static final BiomeBase BEACH = a(16, "beach", new BiomeBeach());
    public static final BiomeBase DESERT_HILLS = a(17, "desert_hills", new BiomeDesertHills());
    public static final BiomeBase WOODED_HILLS = a(18, "wooded_hills", new BiomeForestHills());
    public static final BiomeBase TAIGA_HILLS = a(19, "taiga_hills", new BiomeTaigaHills());
    public static final BiomeBase MOUNTAIN_EDGE = a(20, "mountain_edge", new BiomeExtremeHillsEdge());
    public static final BiomeBase JUNGLE = a(21, "jungle", new BiomeJungle());
    public static final BiomeBase JUNGLE_HILLS = a(22, "jungle_hills", new BiomeJungleHills());
    public static final BiomeBase JUNGLE_EDGE = a(23, "jungle_edge", new BiomeJungleEdge());
    public static final BiomeBase DEEP_OCEAN = a(24, "deep_ocean", new BiomeDeepOcean());
    public static final BiomeBase STONE_SHORE = a(25, "stone_shore", new BiomeStoneBeach());
    public static final BiomeBase SNOWY_BEACH = a(26, "snowy_beach", new BiomeColdBeach());
    public static final BiomeBase BIRCH_FOREST = a(27, "birch_forest", new BiomeBirchForest());
    public static final BiomeBase BIRCH_FOREST_HILLS = a(28, "birch_forest_hills", new BiomeBirchForestHills());
    public static final BiomeBase DARK_FOREST = a(29, "dark_forest", new BiomeRoofedForest());
    public static final BiomeBase SNOWY_TAIGA = a(30, "snowy_taiga", new BiomeColdTaiga());
    public static final BiomeBase SNOWY_TAIGA_HILLS = a(31, "snowy_taiga_hills", new BiomeColdTaigaHills());
    public static final BiomeBase GIANT_TREE_TAIGA = a(32, "giant_tree_taiga", new BiomeMegaTaiga());
    public static final BiomeBase GIANT_TREE_TAIGA_HILLS = a(33, "giant_tree_taiga_hills", new BiomeMegaTaigaHills());
    public static final BiomeBase WOODED_MOUNTAINS = a(34, "wooded_mountains", new BiomeExtremeHillsWithTrees());
    public static final BiomeBase SAVANNA = a(35, "savanna", new BiomeSavanna());
    public static final BiomeBase SAVANNA_PLATEAU = a(36, "savanna_plateau", new BiomeSavannaPlateau());
    public static final BiomeBase BADLANDS = a(37, "badlands", new BiomeMesa());
    public static final BiomeBase WOODED_BADLANDS_PLATEAU = a(38, "wooded_badlands_plateau", new BiomeMesaPlataeu());
    public static final BiomeBase BADLANDS_PLATEAU = a(39, "badlands_plateau", new BiomeMesaPlataeuClear());
    public static final BiomeBase SMALL_END_ISLANDS = a(40, "small_end_islands", new BiomeTheEndFloatingIslands());
    public static final BiomeBase END_MIDLANDS = a(41, "end_midlands", new BiomeTheEndMediumIsland());
    public static final BiomeBase END_HIGHLANDS = a(42, "end_highlands", new BiomeTheEndHighIsland());
    public static final BiomeBase END_BARRENS = a(43, "end_barrens", new BiomeTheEndBarrenIsland());
    public static final BiomeBase WARM_OCEAN = a(44, "warm_ocean", new BiomeWarmOcean());
    public static final BiomeBase LUKEWARM_OCEAN = a(45, "lukewarm_ocean", new BiomeLukewarmOcean());
    public static final BiomeBase COLD_OCEAN = a(46, "cold_ocean", new BiomeColdOcean());
    public static final BiomeBase DEEP_WARM_OCEAN = a(47, "deep_warm_ocean", new BiomeWarmDeepOcean());
    public static final BiomeBase DEEP_LUKEWARM_OCEAN = a(48, "deep_lukewarm_ocean", new BiomeLukewarmDeepOcean());
    public static final BiomeBase DEEP_COLD_OCEAN = a(49, "deep_cold_ocean", new BiomeColdDeepOcean());
    public static final BiomeBase DEEP_FROZEN_OCEAN = a(50, "deep_frozen_ocean", new BiomeFrozenDeepOcean());
    public static final BiomeBase THE_VOID = a(127, "the_void", new BiomeVoid());
    public static final BiomeBase SUNFLOWER_PLAINS = a(129, "sunflower_plains", new BiomeSunflowerPlains());
    public static final BiomeBase DESERT_LAKES = a(130, "desert_lakes", new BiomeDesertMutated());
    public static final BiomeBase GRAVELLY_MOUNTAINS = a(131, "gravelly_mountains", new BiomeExtremeHillsMutated());
    public static final BiomeBase FLOWER_FOREST = a(132, "flower_forest", new BiomeFlowerForest());
    public static final BiomeBase TAIGA_MOUNTAINS = a(133, "taiga_mountains", new BiomeTaigaMutated());
    public static final BiomeBase SWAMP_HILLS = a(134, "swamp_hills", new BiomeSwamplandMutated());
    public static final BiomeBase ICE_SPIKES = a(140, "ice_spikes", new BiomeIcePlainsSpikes());
    public static final BiomeBase MODIFIED_JUNGLE = a(149, "modified_jungle", new BiomeJungleMutated());
    public static final BiomeBase MODIFIED_JUNGLE_EDGE = a(151, "modified_jungle_edge", new BiomeJungleEdgeMutated());
    public static final BiomeBase TALL_BIRCH_FOREST = a(155, "tall_birch_forest", new BiomeBirchForestMutated());
    public static final BiomeBase TALL_BIRCH_HILLS = a(156, "tall_birch_hills", new BiomeBirchForestHillsMutated());
    public static final BiomeBase DARK_FOREST_HILLS = a(157, "dark_forest_hills", new BiomeRoofedForestMutated());
    public static final BiomeBase SNOWY_TAIGA_MOUNTAINS = a(158, "snowy_taiga_mountains", new BiomeColdTaigaMutated());
    public static final BiomeBase GIANT_SPRUCE_TAIGA = a(160, "giant_spruce_taiga", new BiomeMegaSpruceTaiga());
    public static final BiomeBase GIANT_SPRUCE_TAIGA_HILLS = a(161, "giant_spruce_taiga_hills", new BiomeRedwoodTaigaHillsMutated());
    public static final BiomeBase MODIFIED_GRAVELLY_MOUNTAINS = a(162, "modified_gravelly_mountains", new BiomeExtremeHillsWithTreesMutated());
    public static final BiomeBase SHATTERED_SAVANNA = a(163, "shattered_savanna", new BiomeSavannaMutated());
    public static final BiomeBase SHATTERED_SAVANNA_PLATEAU = a(164, "shattered_savanna_plateau", new BiomeSavannaPlateauMutated());
    public static final BiomeBase ERODED_BADLANDS = a(165, "eroded_badlands", new BiomeMesaBryce());
    public static final BiomeBase MODIFIED_WOODED_BADLANDS_PLATEAU = a(166, "modified_wooded_badlands_plateau", new BiomeMesaPlateauMutated());
    public static final BiomeBase MODIFIED_BADLANDS_PLATEAU = a(167, "modified_badlands_plateau", new BiomeMesaPlateauClearMutated());
    public static final BiomeBase BAMBOO_JUNGLE = a(168, "bamboo_jungle", new BiomeBambooJungle());
    public static final BiomeBase BAMBOO_JUNGLE_HILLS = a(169, "bamboo_jungle_hills", new BiomeBambooJungleHills());

    private static BiomeBase a(int i, String s, BiomeBase biomebase) {
        IRegistry.a(IRegistry.BIOME, i, s, biomebase);
        if (biomebase.a()) {
            BiomeBase.c.a(biomebase, IRegistry.BIOME.a(IRegistry.BIOME.get(new MinecraftKey(biomebase.m))));
        }

        return biomebase;
    }

    static {
        Collections.addAll(BiomeBase.b, new BiomeBase[]{Biomes.OCEAN, Biomes.PLAINS, Biomes.DESERT, Biomes.MOUNTAINS, Biomes.FOREST, Biomes.TAIGA, Biomes.SWAMP, Biomes.RIVER, Biomes.FROZEN_RIVER, Biomes.SNOWY_TUNDRA, Biomes.SNOWY_MOUNTAINS, Biomes.MUSHROOM_FIELDS, Biomes.MUSHROOM_FIELD_SHORE, Biomes.BEACH, Biomes.DESERT_HILLS, Biomes.WOODED_HILLS, Biomes.TAIGA_HILLS, Biomes.JUNGLE, Biomes.JUNGLE_HILLS, Biomes.JUNGLE_EDGE, Biomes.DEEP_OCEAN, Biomes.STONE_SHORE, Biomes.SNOWY_BEACH, Biomes.BIRCH_FOREST, Biomes.BIRCH_FOREST_HILLS, Biomes.DARK_FOREST, Biomes.SNOWY_TAIGA, Biomes.SNOWY_TAIGA_HILLS, Biomes.GIANT_TREE_TAIGA, Biomes.GIANT_TREE_TAIGA_HILLS, Biomes.WOODED_MOUNTAINS, Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.BADLANDS, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.BADLANDS_PLATEAU});
    }
}
