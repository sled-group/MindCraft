package net.minecraft.server;

public final class BiomeHell extends BiomeBase {

    protected BiomeHell() {
        super((new BiomeBase.a()).a(WorldGenSurface.Q, WorldGenSurface.E).a(BiomeBase.Precipitation.NONE).a(BiomeBase.Geography.NETHER).a(0.1F).b(0.2F).c(2.0F).d(0.0F).a(4159204).b(329011).a((String) null));
        this.a(WorldGenerator.NETHER_BRIDGE, (WorldGenFeatureConfiguration) WorldGenFeatureConfiguration.e);
        this.a(WorldGenStage.Features.AIR, a(WorldGenCarverAbstract.b, (WorldGenCarverConfiguration) (new WorldGenFeatureConfigurationChance(0.2F))));
        this.a(WorldGenStage.Decoration.VEGETAL_DECORATION, a(WorldGenerator.SPRING_FEATURE, new WorldGenFeatureFlowingConfiguration(FluidTypes.LAVA.i()), WorldGenDecorator.p, new WorldGenFeatureChanceDecoratorCountConfiguration(20, 8, 16, 256)));
        BiomeDecoratorGroups.Z(this);
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.NETHER_BRIDGE, WorldGenFeatureConfiguration.e, WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.NETHER_SPRING, new WorldGenFeatureHellFlowingLavaConfiguration(false), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(8, 4, 8, 128)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.HELL_FIRE, WorldGenFeatureConfiguration.e, WorldGenDecorator.A, new WorldGenDecoratorFrequencyConfiguration(10)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.GLOWSTONE_BLOB, WorldGenFeatureConfiguration.e, WorldGenDecorator.I, new WorldGenDecoratorFrequencyConfiguration(10)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.GLOWSTONE_BLOB, WorldGenFeatureConfiguration.e, WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(10, 0, 0, 128)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.BROWN_MUSHROOM.getBlockData()), WorldGenDecorator.r, new WorldGenFeatureChanceDecoratorRangeConfiguration(0.5F, 0, 0, 128)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.BUSH, new WorldGenFeatureMushroomConfiguration(Blocks.RED_MUSHROOM.getBlockData()), WorldGenDecorator.r, new WorldGenFeatureChanceDecoratorRangeConfiguration(0.5F, 0, 0, 128)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, Blocks.NETHER_QUARTZ_ORE.getBlockData(), 14), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(16, 10, 20, 128)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.ORE, new WorldGenFeatureOreConfiguration(WorldGenFeatureOreConfiguration.Target.NETHERRACK, Blocks.MAGMA_BLOCK.getBlockData(), 33), WorldGenDecorator.B, new WorldGenDecoratorFrequencyConfiguration(4)));
        this.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, a(WorldGenerator.NETHER_SPRING, new WorldGenFeatureHellFlowingLavaConfiguration(true), WorldGenDecorator.n, new WorldGenFeatureChanceDecoratorCountConfiguration(16, 10, 20, 128)));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.GHAST, 50, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE_PIGMAN, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.MAGMA_CUBE, 2, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 1, 4, 4));
    }
}
