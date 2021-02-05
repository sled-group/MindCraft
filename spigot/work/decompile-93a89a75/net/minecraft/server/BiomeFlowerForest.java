package net.minecraft.server;

public final class BiomeFlowerForest extends BiomeBase {

    public BiomeFlowerForest() {
        super((new BiomeBase.a()).a(WorldGenSurface.G, WorldGenSurface.v).a(BiomeBase.Precipitation.RAIN).a(BiomeBase.Geography.FOREST).a(0.1F).b(0.4F).c(0.7F).d(0.8F).a(4159204).b(329011).a("forest"));
        this.a(WorldGenerator.MINESHAFT, (WorldGenFeatureConfiguration) (new WorldGenMineshaftConfiguration(0.004D, WorldGenMineshaft.Type.NORMAL)));
        this.a(WorldGenerator.STRONGHOLD, (WorldGenFeatureConfiguration) WorldGenFeatureConfiguration.e);
        BiomeDecoratorGroups.a(this);
        BiomeDecoratorGroups.c(this);
        BiomeDecoratorGroups.d(this);
        BiomeDecoratorGroups.f(this);
        this.a(WorldGenStage.Decoration.VEGETAL_DECORATION, a(WorldGenerator.RANDOM_RANDOM_SELECTOR, new WorldGenFeatureRandomConfiguration(new WorldGenerator[]{WorldGenerator.DOUBLE_PLANT, WorldGenerator.DOUBLE_PLANT, WorldGenerator.DOUBLE_PLANT, WorldGenerator.GENERAL_FOREST_FLOWER}, new WorldGenFeatureConfiguration[]{new WorldGenFeatureDoublePlantConfiguration(Blocks.LILAC.getBlockData()), new WorldGenFeatureDoublePlantConfiguration(Blocks.ROSE_BUSH.getBlockData()), new WorldGenFeatureDoublePlantConfiguration(Blocks.PEONY.getBlockData()), WorldGenFeatureConfiguration.e}, 2), WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(5)));
        BiomeDecoratorGroups.g(this);
        BiomeDecoratorGroups.h(this);
        BiomeDecoratorGroups.l(this);
        this.a(WorldGenStage.Decoration.VEGETAL_DECORATION, a(WorldGenerator.RANDOM_SELECTOR, new WorldGenFeatureRandomChoiceConfiguration(new WorldGenerator[]{WorldGenerator.BIRCH_TREE, WorldGenerator.FANCY_TREE}, new WorldGenFeatureConfiguration[]{WorldGenFeatureConfiguration.e, WorldGenFeatureConfiguration.e}, new float[]{0.2F, 0.1F}, WorldGenerator.NORMAL_TREE, WorldGenFeatureConfiguration.e), WorldGenDecorator.m, new WorldGenDecoratorFrequencyExtraChanceConfiguration(6, 0.1F, 1)));
        this.a(WorldGenStage.Decoration.VEGETAL_DECORATION, a(WorldGenerator.FOREST_FLOWER, WorldGenFeatureConfiguration.e, WorldGenDecorator.c, new WorldGenDecoratorFrequencyConfiguration(100)));
        BiomeDecoratorGroups.W(this);
        BiomeDecoratorGroups.Z(this);
        BiomeDecoratorGroups.aa(this);
        BiomeDecoratorGroups.am(this);
        BiomeDecoratorGroups.ap(this);
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.SHEEP, 12, 4, 4));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.PIG, 10, 4, 4));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.CHICKEN, 10, 4, 4));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.COW, 8, 4, 4));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.RABBIT, 4, 2, 3));
        this.a(EnumCreatureType.AMBIENT, new BiomeBase.BiomeMeta(EntityTypes.BAT, 10, 8, 8));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SPIDER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE, 95, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE_VILLAGER, 5, 1, 1));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SKELETON, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.CREEPER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SLIME, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 1, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.WITCH, 5, 1, 1));
    }
}
