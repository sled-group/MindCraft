package net.minecraft.server;

public class BiomeTheEndHighIsland extends BiomeBase {

    public BiomeTheEndHighIsland() {
        super((new BiomeBase.a()).a(WorldGenSurface.G, WorldGenSurface.F).a(BiomeBase.Precipitation.NONE).a(BiomeBase.Geography.THEEND).a(0.1F).b(0.2F).c(0.5F).d(0.5F).a(4159204).b(329011).a((String) null));
        this.a(WorldGenerator.END_CITY, (WorldGenFeatureConfiguration) WorldGenFeatureConfiguration.e);
        this.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, a(WorldGenerator.END_GATEWAY, WorldGenEndGatewayConfiguration.a(WorldProviderTheEnd.f, true), WorldGenDecorator.L, WorldGenFeatureDecoratorConfiguration.e));
        BiomeDecoratorGroups.aq(this);
        this.a(WorldGenStage.Decoration.VEGETAL_DECORATION, a(WorldGenerator.CHORUS_PLANT, WorldGenFeatureConfiguration.e, WorldGenDecorator.K, WorldGenFeatureDecoratorConfiguration.e));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 4, 4));
    }
}
