package net.minecraft.server;

import java.util.Random;

public final class BiomeFrozenOcean extends BiomeBase {

    protected static final NoiseGenerator3 u = new NoiseGenerator3(new Random(3456L), 3);

    public BiomeFrozenOcean() {
        super((new BiomeBase.a()).a(WorldGenSurface.P, WorldGenSurface.v).a(BiomeBase.Precipitation.SNOW).a(BiomeBase.Geography.OCEAN).a(-1.0F).b(0.1F).c(0.0F).d(0.5F).a(3750089).b(329011).a((String) null));
        this.a(WorldGenerator.OCEAN_RUIN, (WorldGenFeatureConfiguration) (new WorldGenFeatureOceanRuinConfiguration(WorldGenFeatureOceanRuin.Temperature.COLD, 0.3F, 0.9F)));
        this.a(WorldGenerator.MINESHAFT, (WorldGenFeatureConfiguration) (new WorldGenMineshaftConfiguration(0.004D, WorldGenMineshaft.Type.NORMAL)));
        this.a(WorldGenerator.SHIPWRECK, (WorldGenFeatureConfiguration) (new WorldGenFeatureShipwreckConfiguration(false)));
        BiomeDecoratorGroups.b(this);
        BiomeDecoratorGroups.c(this);
        BiomeDecoratorGroups.d(this);
        BiomeDecoratorGroups.an(this);
        BiomeDecoratorGroups.f(this);
        BiomeDecoratorGroups.ao(this);
        BiomeDecoratorGroups.g(this);
        BiomeDecoratorGroups.h(this);
        BiomeDecoratorGroups.l(this);
        BiomeDecoratorGroups.u(this);
        BiomeDecoratorGroups.U(this);
        BiomeDecoratorGroups.W(this);
        BiomeDecoratorGroups.Z(this);
        BiomeDecoratorGroups.aa(this);
        BiomeDecoratorGroups.am(this);
        BiomeDecoratorGroups.ap(this);
        this.a(EnumCreatureType.WATER_CREATURE, new BiomeBase.BiomeMeta(EntityTypes.SQUID, 1, 1, 4));
        this.a(EnumCreatureType.WATER_CREATURE, new BiomeBase.BiomeMeta(EntityTypes.SALMON, 15, 1, 5));
        this.a(EnumCreatureType.CREATURE, new BiomeBase.BiomeMeta(EntityTypes.POLAR_BEAR, 1, 1, 2));
        this.a(EnumCreatureType.AMBIENT, new BiomeBase.BiomeMeta(EntityTypes.BAT, 10, 8, 8));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SPIDER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE, 95, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.DROWNED, 5, 1, 1));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ZOMBIE_VILLAGER, 5, 1, 1));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SKELETON, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.CREEPER, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.SLIME, 100, 4, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 1, 4));
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.WITCH, 5, 1, 1));
    }

    @Override
    protected float c(BlockPosition blockposition) {
        float f = this.getTemperature();
        double d0 = BiomeFrozenOcean.u.a((double) blockposition.getX() * 0.05D, (double) blockposition.getZ() * 0.05D);
        double d1 = BiomeFrozenOcean.e.a((double) blockposition.getX() * 0.2D, (double) blockposition.getZ() * 0.2D);
        double d2 = d0 + d1;

        if (d2 < 0.3D) {
            double d3 = BiomeFrozenOcean.e.a((double) blockposition.getX() * 0.09D, (double) blockposition.getZ() * 0.09D);

            if (d3 < 0.8D) {
                f = 0.2F;
            }
        }

        if (blockposition.getY() > 64) {
            float f1 = (float) (BiomeFrozenOcean.d.a((double) ((float) blockposition.getX() / 8.0F), (double) ((float) blockposition.getZ() / 8.0F)) * 4.0D);

            return f - (f1 + (float) blockposition.getY() - 64.0F) * 0.05F / 30.0F;
        } else {
            return f;
        }
    }
}
