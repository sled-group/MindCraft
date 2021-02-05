package net.minecraft.server;

import com.google.common.collect.Lists;

public class BiomeDecoratorGroups {

    public static void a(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Features.AIR, BiomeBase.a(WorldGenCarverAbstract.a, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.14285715F))));
        biomebase.a(WorldGenStage.Features.AIR, BiomeBase.a(WorldGenCarverAbstract.c, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.02F))));
    }

    public static void b(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Features.AIR, BiomeBase.a(WorldGenCarverAbstract.a, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.06666667F))));
        biomebase.a(WorldGenStage.Features.AIR, BiomeBase.a(WorldGenCarverAbstract.c, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.02F))));
        biomebase.a(WorldGenStage.Features.LIQUID, BiomeBase.a(WorldGenCarverAbstract.d, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.02F))));
        biomebase.a(WorldGenStage.Features.LIQUID, BiomeBase.a(WorldGenCarverAbstract.e, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.06666667F))));
    }

    public static void c(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeBase.a(WorldGenerator.MINESHAFT, new WorldGenMineshaftConfiguration(0.004000000189989805D, WorldGenMineshaft.Type.NORMAL), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.PILLAGER_OUTPOST, new WorldGenFeaturePillagerOutpostConfiguration(0.004D), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeBase.a(WorldGenerator.STRONGHOLD, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.SWAMP_HUT, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.DESERT_PYRAMID, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.JUNGLE_TEMPLE, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.IGLOO, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.SHIPWRECK, new WorldGenFeatureShipwreckConfiguration(false), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.OCEAN_MONUMENT, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.WOODLAND_MANSION, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.OCEAN_RUIN, new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.COLD, 0.3F, 0.9F), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeBase.a(WorldGenerator.BURIED_TREASURE, new WorldGenBuriedTreasureConfiguration(0.01F), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.VILLAGE, new WorldGenFeatureVillageConfiguration("village/plains/town_centers", 6), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
    }

    public static void d(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.LAKE, new WorldGenFeatureLakeConfiguration(Blocks.WATER.getBlockData()), WorldGenDecorator.E, new WorldGenDecoratorLakeChanceConfiguration(4)));
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.LAKE, new WorldGenFeatureLakeConfiguration(Blocks.LAVA.getBlockData()), WorldGenDecorator.D, new WorldGenDecoratorLakeChanceConfiguration(80)));
    }

    public static void e(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.LAKE, new WorldGenFeatureLakeConfiguration(Blocks.LAVA.getBlockData()), WorldGenDecorator.D, new WorldGenDecoratorLakeChanceConfiguration(80)));
    }

    public static void f(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_STRUCTURES, BiomeBase.a(WorldGenerator.MONSTER_ROOM, WorldGenFeatureConfiguration.e, WorldGenDecorator.F, new WorldGenDecoratorDungeonConfiguration(8)));
    }

    public static void g(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.DIRT.getBlockData(), 33), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(10, 0, 0, 256)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.GRAVEL.getBlockData(), 33), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(8, 0, 0, 256)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.GRANITE.getBlockData(), 33), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(10, 0, 0, 80)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.DIORITE.getBlockData(), 33), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(10, 0, 0, 80)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.ANDESITE.getBlockData(), 33), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(10, 0, 0, 80)));
    }

    public static void h(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.COAL_ORE.getBlockData(), 17), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 0, 0, 128)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.IRON_ORE.getBlockData(), 9), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 0, 0, 64)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.GOLD_ORE.getBlockData(), 9), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(2, 0, 0, 32)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.REDSTONE_ORE.getBlockData(), 8), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(8, 0, 0, 16)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.DIAMOND_ORE.getBlockData(), 8), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(1, 0, 0, 16)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.LAPIS_ORE.getBlockData(), 7), WorldGenDecorator.u, new WorldGenDecoratorHeightAverageConfiguration(1, 16, 16)));
    }

    public static void i(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.GOLD_ORE.getBlockData(), 9), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 32, 32, 80)));
    }

    public static void j(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.EMERALD_ORE, new WorldGenFeatureReplaceBlockConfiguration(Blocks.STONE.getBlockData(), Blocks.EMERALD_ORE.getBlockData()), WorldGenDecorator.C, WorldGenFeatureDecoratorConfiguration.e));
    }

    public static void k(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeBase.a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NATURAL_STONE, Blocks.INFESTED_STONE.getBlockData(), 9), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(7, 0, 0, 64)));
    }

    public static void l(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.DISK, new WorldGenFeatureCircleConfiguration(Blocks.SAND.getBlockData(), 7, 2, Lists.newArrayList(new IBlockData[]{Blocks.DIRT.getBlockData(), Blocks.GRASS_BLOCK.getBlockData()})), WorldGenDecorator.b, new WorldGenDecoratorFrequencyConfiguration(3)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.DISK, new WorldGenFeatureCircleConfiguration(Blocks.CLAY.getBlockData(), 4, 1, Lists.newArrayList(new IBlockData[]{Blocks.DIRT.getBlockData(), Blocks.CLAY.getBlockData()})), WorldGenDecorator.b, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.DISK, new WorldGenFeatureCircleConfiguration(Blocks.GRAVEL.getBlockData(), 6, 2, Lists.newArrayList(new IBlockData[]{Blocks.DIRT.getBlockData(), Blocks.GRASS_BLOCK.getBlockData()})), WorldGenDecorator.b, new WorldGenDecoratorFrequencyConfiguration(1)));
    }

    public static void m(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_ORES, BiomeBase.a(WorldGenerator.DISK, new WorldGenFeatureCircleConfiguration(Blocks.CLAY.getBlockData(), 4, 1, Lists.newArrayList(new IBlockData[]{Blocks.DIRT.getBlockData(), Blocks.CLAY.getBlockData()})), WorldGenDecorator.b, new WorldGenDecoratorFrequencyConfiguration(1)));
    }

    public static void n(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.FOREST_ROCK, new WorldGenFeatureBlockOffsetConfiguration(Blocks.MOSSY_COBBLESTONE.getBlockData(), 0), WorldGenDecorator.z, new WorldGenDecoratorFrequencyConfiguration(3)));
    }

    public static void o(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DOUBLE_PLANT, new WorldGenFeatureDoublePlantConfiguration(Blocks.LARGE_FERN.getBlockData()), WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(7)));
    }

    public static void p(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SWEET_BERRY_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(12)));
    }

    public static void q(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SWEET_BERRY_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
    }

    public static void r(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BAMBOO, new WorldGenFeatureConfigurationChance(0.0F), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(16)));
    }

    public static void s(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BAMBOO, new WorldGenFeatureConfigurationChance(0.2F), WorldGenDecorator.x, new WorldGenDecoratorNoiseConfiguration(160, 80.0D, 0.3D, HeightMap.Type.WORLD_SURFACE_WG)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.FANCY_TREE, WorldGenerator.JUNGLE_GROUND_BUSH, WorldGenerator.MEGA_JUNGLE_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.05F, 0.15F, 0.7F}, WorldGenerator.JUNGLE_GRASS, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(30, 0.1F, 1)));
    }

    public static void t(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.PINE_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.33333334F}, WorldGenerator.SPRUCE_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void u(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.1F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1)));
    }

    public static void v(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BIRCH_TREE, WorldGenFeatureConfiguration.e, WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void w(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.BIRCH_TREE, WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.2F, 0.1F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void x(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.SUPER_BIRCH_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.5F}, WorldGenerator.BIRCH_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void y(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.SAVANNA_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.8F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(1, 0.1F, 1)));
    }

    public static void z(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.SAVANNA_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.8F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1)));
    }

    public static void A(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.SPRUCE_TREE, WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.666F, 0.1F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1)));
    }

    public static void B(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.SPRUCE_TREE, WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.666F, 0.1F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(3, 0.1F, 1)));
    }

    public static void C(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.FANCY_TREE, WorldGenerator.JUNGLE_GROUND_BUSH, WorldGenerator.MEGA_JUNGLE_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.1F, 0.5F, 0.33333334F}, WorldGenerator.JUNGLE_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(50, 0.1F, 1)));
    }

    public static void D(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.FANCY_TREE, WorldGenerator.JUNGLE_GROUND_BUSH}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.1F, 0.5F}, WorldGenerator.JUNGLE_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1)));
    }

    public static void E(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e, WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(5, 0.1F, 1)));
    }

    public static void F(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SPRUCE_TREE, WorldGenFeatureConfiguration.e, WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.1F, 1)));
    }

    public static void G(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.MEGA_SPRUCE_TREE, WorldGenerator.PINE_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.33333334F, 0.33333334F}, WorldGenerator.SPRUCE_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void H(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.MEGA_SPRUCE_TREE, WorldGenerator.MEGA_PINE_TREE, WorldGenerator.PINE_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.025641026F, 0.30769232F, 0.33333334F}, WorldGenerator.SPRUCE_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(10, 0.1F, 1)));
    }

    public static void I(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.JUNGLE_GRASS, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(25)));
    }

    public static void J(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DOUBLE_PLANT, new WorldGenFeatureDoublePlantConfiguration(Blocks.TALL_GRASS.getBlockData()), WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(7)));
    }

    public static void K(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(5)));
    }

    public static void L(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(20)));
    }

    public static void M(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEAD_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(20)));
    }

    public static void N(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_RANDOM_SELECTOR, new WorldGenFeatureRandomConfiguration(new WorldGenerator[]{WorldGenerator.DOUBLE_PLANT, WorldGenerator.DOUBLE_PLANT, WorldGenerator.DOUBLE_PLANT, WorldGenerator.GENERAL_FOREST_FLOWER}, new WorldGenFeatureConfiguration[]{new WorldGenFeatureDoublePlantConfiguration(Blocks.LILAC.getBlockData()), new WorldGenFeatureDoublePlantConfiguration(Blocks.ROSE_BUSH.getBlockData()), new WorldGenFeatureDoublePlantConfiguration(Blocks.PEONY.getBlockData()), WorldGenFeatureConfiguration.e}, 0), WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(5)));
    }

    public static void O(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(2)));
    }

    public static void P(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SWAMP_TREE, WorldGenFeatureConfiguration.e, WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(2, 0.1F, 1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SWAMP_FLOWER, WorldGenFeatureConfiguration.e, WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(5)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEAD_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.WATERLILY, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(4)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.s, new WorldGenDecoratorFrequencyChanceConfiguration(8, 0.25F)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.t, new WorldGenDecoratorFrequencyChanceConfiguration(8, 0.125F)));
    }

    public static void Q(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_BOOLEAN_SELECTOR, new WorldGenFeatureChoiceConfiguration(WorldGenerator.HUGE_RED_MUSHROOM, new WorldGenHugeMushroomConfiguration(false), WorldGenerator.HUGE_BROWN_MUSHROOM, new WorldGenHugeMushroomConfiguration(false)), WorldGenDecorator.a, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.s, new WorldGenDecoratorFrequencyChanceConfiguration(1, 0.25F)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.t, new WorldGenDecoratorFrequencyChanceConfiguration(1, 0.125F)));
    }

    public static void R(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e}, new float[]{0.33333334F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(0, 0.05F, 1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.PLAIN_FLOWER, WorldGenFeatureConfiguration.e, WorldGenDecorator.f, new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 15, 4)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.g, new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 5, 10)));
    }

    public static void S(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEAD_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(2)));
    }

    public static void T(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.TAIGA_GRASS, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(7)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEAD_BUSH, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.s, new WorldGenDecoratorFrequencyChanceConfiguration(3, 0.25F)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.t, new WorldGenDecoratorFrequencyChanceConfiguration(3, 0.125F)));
    }

    public static void U(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEFAULT_FLOWER, WorldGenFeatureConfiguration.e, WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(2)));
    }

    public static void V(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DEFAULT_FLOWER, WorldGenFeatureConfiguration.e, WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(4)));
    }

    public static void W(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.GRASS, new WorldGenFeatureTallGrassConfiguration(Blocks.GRASS.getBlockData()), WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
    }

    public static void X(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.TAIGA_GRASS, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.s, new WorldGenDecoratorFrequencyChanceConfiguration(1, 0.25F)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.t, new WorldGenDecoratorFrequencyChanceConfiguration(1, 0.125F)));
    }

    public static void Y(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.DOUBLE_PLANT, new WorldGenFeatureDoublePlantConfiguration(Blocks.TALL_GRASS.getBlockData()), WorldGenDecorator.f, new WorldGenFeatureDecoratorNoiseConfiguration(-0.8D, 0, 7)));
    }

    public static void Z(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(4)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(8)));
    }

    public static void aa(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.REED, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(10)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.PUMPKIN, WorldGenFeatureConfiguration.e, WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(32)));
    }

    public static void ab(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.REED, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(13)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.PUMPKIN, WorldGenFeatureConfiguration.e, WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(32)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.CACTUS, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(5)));
    }

    public static void ac(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.MELON, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(1)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.VINES, WorldGenFeatureConfiguration.e, WorldGenDecorator.e, new WorldGenDecoratorFrequencyConfiguration(50)));
    }

    public static void ad(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.REED, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(60)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.PUMPKIN, WorldGenFeatureConfiguration.e, WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(32)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.CACTUS, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(10)));
    }

    public static void ae(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.REED, WorldGenFeatureConfiguration.e, WorldGenDecorator.d, new WorldGenDecoratorFrequencyConfiguration(20)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.PUMPKIN, WorldGenFeatureConfiguration.e, WorldGenDecorator.j, new WorldGenDecoratorChanceConfiguration(32)));
    }

    public static void af(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.DESERT_WELL, WorldGenFeatureConfiguration.e, WorldGenDecorator.i, new WorldGenDecoratorChanceConfiguration(1000)));
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeBase.a(WorldGenerator.FOSSIL, WorldGenFeatureConfiguration.e, WorldGenDecorator.k, new WorldGenDecoratorChanceConfiguration(64)));
    }

    public static void ag(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeBase.a(WorldGenerator.FOSSIL, WorldGenFeatureConfiguration.e, WorldGenDecorator.k, new WorldGenDecoratorChanceConfiguration(64)));
    }

    public static void ah(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.KELP, WorldGenFeatureConfiguration.e, WorldGenDecorator.x, new WorldGenDecoratorNoiseConfiguration(120, 80.0D, 0.0D, HeightMap.Type.OCEAN_FLOOR_WG)));
    }

    public static void ai(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SIMPLE_BLOCK, new WorldGenFeatureBlockConfiguration(Blocks.SEAGRASS.getBlockData(), new IBlockData[]{Blocks.STONE.getBlockData()}, new IBlockData[]{Blocks.WATER.getBlockData()}, new IBlockData[]{Blocks.WATER.getBlockData()}), WorldGenDecorator.y, new WorldGenDecoratorCarveMaskConfiguration(WorldGenStage.Features.LIQUID, 0.1F)));
    }

    public static void aj(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SEAGRASS, new WorldGenFeatureSeaGrassConfiguration(80, 0.3D), WorldGenDecorator.v, WorldGenFeatureDecoratorConfiguration.e));
    }

    public static void ak(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SEAGRASS, new WorldGenFeatureSeaGrassConfiguration(80, 0.8D), WorldGenDecorator.v, WorldGenFeatureDecoratorConfiguration.e));
    }

    public static void al(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.KELP, WorldGenFeatureConfiguration.e, WorldGenDecorator.x, new WorldGenDecoratorNoiseConfiguration(80, 80.0D, 0.0D, HeightMap.Type.OCEAN_FLOOR_WG)));
    }

    public static void am(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SPRING_FEATURE, new WorldGenFeatureFlowingConfiguration(FluidTypes.WATER.i()), WorldGenDecorator.o, new WorldGenFeatureChanceDecoratorCountConfiguration(50, 8, 8, 256)));
        biomebase.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeBase.a(WorldGenerator.SPRING_FEATURE, new WorldGenFeatureFlowingConfiguration(FluidTypes.LAVA.i()), WorldGenDecorator.p, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 8, 16, 256)));
    }

    public static void an(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.ICEBERG, new WorldGenFeatureIceburgConfiguration(Blocks.PACKED_ICE.getBlockData()), WorldGenDecorator.H, new WorldGenDecoratorChanceConfiguration(16)));
        biomebase.a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeBase.a(WorldGenerator.ICEBERG, new WorldGenFeatureIceburgConfiguration(Blocks.BLUE_ICE.getBlockData()), WorldGenDecorator.H, new WorldGenDecoratorChanceConfiguration(200)));
    }

    public static void ao(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.BLUE_ICE, WorldGenFeatureConfiguration.e, WorldGenDecorator.q, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 30, 32, 64)));
    }

    public static void ap(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.TOP_LAYER_MODIFICATION, BiomeBase.a(WorldGenerator.FREEZE_TOP_LAYER, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
    }

    public static void aq(BiomeBase biomebase) {
        biomebase.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeBase.a(WorldGenerator.END_CITY, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
    }
}
