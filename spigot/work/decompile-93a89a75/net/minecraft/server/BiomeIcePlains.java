package net.minecraft.server;

public final class BiomeIcePlains extends BiomeBase {

    public BiomeIcePlains() {
        super((new BiomeBase.a()).a(WorldGenSurface.G, WorldGenSurface.v).a(BiomeBase.Precipitation.SNOW).a(BiomeBase.Geography.ICY).a(0.125F).b(0.05F).c(0.0F).d(0.5F).a(4159204).b(329011).a((String) null));
        this.a(WorldGenerator.VILLAGE, (WorldGenFeatureConfiguration) (new WorldGenFeatureVillageConfiguration("village/snowy/town_centers", 6)));
        this.a(WorldGenerator.IGLOO, (WorldGenFeatureConfiguration) WorldGenFeatureConfiguration.e);
        this.a(WorldGenerator.MINESHAFT, (WorldGenFeatureConfiguration) (new WorldGenMineshaftConfiguration(0.004D, WorldGenMineshaft.Type.NORMAL)));
        this.a(WorldGenerator.STRONGHOLD, (WorldGenFeatureConfiguration) WorldGenFeatureConfiguration.e);
        this.a(WorldGenerator.PILLAGER_OUTPOST, (WorldGenFeatureConfiguration) (new WorldGenFeaturePillagerOutpostConfiguration(0.004D)));
        BiomeDecoratorGroups.a(this);
        BiomeDecoratorGroups.c(this);
        BiomeDecoratorGroups.d(this);
        BiomeDecoratorGroups.f(this);
        BiomeDecoratorGroups.g(this);
        BiomeDecoratorGroups.h(this);
        BiomeDecoratorGroups.l(this);
        BiomeDecoratorGroups.F(this);
        BiomeDecoratorGroups.U(this);
        BiomeDecoratorGroups.W(this);
        BiomeDecoratorGroups.Z(this);
        BiomeDecoratorGroups.aa(this);
        BiomeDecoratorGroups.am(this);
        BiomeDecoratorGroups.ap(this);
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.RABBIT, 10, 2, 3));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.POLAR_BEAR, 1, 1, 2));
        this.a(EnumCreatureType.AMBIENT, new BiomeBase.BiomeMeta(EntityTypes.BAT, 10, 8, 8));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SPIDER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE, 95, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE_VILLAGER, 5, 1, 1));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.CREEPER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SLIME, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 1, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.WITCH, 5, 1, 1));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SKELETON, 20, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.STRAY, 80, 4, 4));
    }

    @Override
    public float d() {
        return 0.07F;
    }
}
