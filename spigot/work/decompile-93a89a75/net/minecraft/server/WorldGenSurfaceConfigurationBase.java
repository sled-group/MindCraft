package net.minecraft.server;

import com.mojang.datafixers.Dynamic;

public class WorldGenSurfaceConfigurationBase implements WorldGenSurfaceConfiguration {

    private final IBlockData a;
    private final IBlockData b;
    private final IBlockData c;

    public WorldGenSurfaceConfigurationBase(IBlockData iblockdata, IBlockData iblockdata1, IBlockData iblockdata2) {
        this.a = iblockdata;
        this.b = iblockdata1;
        this.c = iblockdata2;
    }

    @Override
    public IBlockData a() {
        return this.a;
    }

    @Override
    public IBlockData b() {
        return this.b;
    }

    public IBlockData c() {
        return this.c;
    }

    public static WorldGenSurfaceConfigurationBase a(Dynamic<?> dynamic) {
        IBlockData iblockdata = (IBlockData) dynamic.get("top_material").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        IBlockData iblockdata1 = (IBlockData) dynamic.get("under_material").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());
        IBlockData iblockdata2 = (IBlockData) dynamic.get("underwater_material").map(IBlockData::a).orElse(Blocks.AIR.getBlockData());

        return new WorldGenSurfaceConfigurationBase(iblockdata, iblockdata1, iblockdata2);
    }
}
