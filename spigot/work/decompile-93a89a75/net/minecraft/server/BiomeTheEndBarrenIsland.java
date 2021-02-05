package net.minecraft.server;

public class BiomeTheEndBarrenIsland extends BiomeBase {

    public BiomeTheEndBarrenIsland() {
        super((new BiomeBase.a()).a(WorldGenSurface.G, WorldGenSurface.F).a(BiomeBase.Precipitation.NONE).a(BiomeBase.Geography.THEEND).a(0.1F).b(0.2F).c(0.5F).d(0.5F).a(4159204).b(329011).a((String) null));
        BiomeDecoratorGroups.aq(this);
        this.a(EnumCreatureType.MONSTER, new BiomeBase.BiomeMeta(EntityTypes.ENDERMAN, 10, 4, 4));
    }
}
