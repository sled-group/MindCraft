package net.minecraft.server;

import com.google.common.collect.ImmutableList;

public final class BiomeTheEnd extends BiomeBase {

    public BiomeTheEnd() {
        super((new BiomeBase.a()).a(WorldGenSurface.G, WorldGenSurface.F).a(BiomeBase.Precipitation.NONE).a(BiomeBase.Geography.THEEND).a(0.1F).b(0.2F).c(0.5F).d(0.5F).a(4159204).b(329011).a((String) null));
        this.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, a(WorldGenerator.END_SPIKE, new WorldGenFeatureEndSpikeConfiguration(false, ImmutableList.of(), (BlockPosition) null), WorldGenDecorator.h, WorldGenFeatureDecoratorConfiguration.e));
        BiomeDecoratorGroups.aq(this);
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 4, 4));
    }
}
