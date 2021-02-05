package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;

public abstract class WorldGenSurface<C extends WorldGenSurfaceConfiguration> {

    public static final IBlockData f = Blocks.AIR.getBlockData();
    public static final IBlockData g = Blocks.DIRT.getBlockData();
    public static final IBlockData h = Blocks.GRASS_BLOCK.getBlockData();
    public static final IBlockData i = Blocks.PODZOL.getBlockData();
    public static final IBlockData j = Blocks.GRAVEL.getBlockData();
    public static final IBlockData k = Blocks.STONE.getBlockData();
    public static final IBlockData l = Blocks.COARSE_DIRT.getBlockData();
    public static final IBlockData m = Blocks.SAND.getBlockData();
    public static final IBlockData n = Blocks.RED_SAND.getBlockData();
    public static final IBlockData o = Blocks.WHITE_TERRACOTTA.getBlockData();
    public static final IBlockData p = Blocks.MYCELIUM.getBlockData();
    public static final IBlockData q = Blocks.NETHERRACK.getBlockData();
    public static final IBlockData r = Blocks.END_STONE.getBlockData();
    public static final WorldGenSurfaceConfigurationBase s = new WorldGenSurfaceConfigurationBase(WorldGenSurface.f, WorldGenSurface.f, WorldGenSurface.f);
    public static final WorldGenSurfaceConfigurationBase t = new WorldGenSurfaceConfigurationBase(WorldGenSurface.i, WorldGenSurface.g, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase u = new WorldGenSurfaceConfigurationBase(WorldGenSurface.j, WorldGenSurface.j, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase v = new WorldGenSurfaceConfigurationBase(WorldGenSurface.h, WorldGenSurface.g, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase w = new WorldGenSurfaceConfigurationBase(WorldGenSurface.g, WorldGenSurface.g, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase x = new WorldGenSurfaceConfigurationBase(WorldGenSurface.k, WorldGenSurface.k, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase y = new WorldGenSurfaceConfigurationBase(WorldGenSurface.l, WorldGenSurface.g, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase z = new WorldGenSurfaceConfigurationBase(WorldGenSurface.m, WorldGenSurface.m, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase A = new WorldGenSurfaceConfigurationBase(WorldGenSurface.h, WorldGenSurface.g, WorldGenSurface.m);
    public static final WorldGenSurfaceConfigurationBase B = new WorldGenSurfaceConfigurationBase(WorldGenSurface.m, WorldGenSurface.m, WorldGenSurface.m);
    public static final WorldGenSurfaceConfigurationBase C = new WorldGenSurfaceConfigurationBase(WorldGenSurface.n, WorldGenSurface.o, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase D = new WorldGenSurfaceConfigurationBase(WorldGenSurface.p, WorldGenSurface.g, WorldGenSurface.j);
    public static final WorldGenSurfaceConfigurationBase E = new WorldGenSurfaceConfigurationBase(WorldGenSurface.q, WorldGenSurface.q, WorldGenSurface.q);
    public static final WorldGenSurfaceConfigurationBase F = new WorldGenSurfaceConfigurationBase(WorldGenSurface.r, WorldGenSurface.r, WorldGenSurface.r);
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> G = a("default", new WorldGenSurfaceDefaultBlock(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> H = a("mountain", new WorldGenSurfaceExtremeHills(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> I = a("shattered_savanna", new WorldGenSurfaceSavannaMutated(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> J = a("gravelly_mountain", new WorldGenSurfaceExtremeHillMutated(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> K = a("giant_tree_taiga", new WorldGenSurfaceTaigaMega(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> L = a("swamp", new WorldGenSurfaceSwamp(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> M = a("badlands", new WorldGenSurfaceMesa(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> N = a("wooded_badlands", new WorldGenSurfaceMesaForest(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> O = a("eroded_badlands", new WorldGenSurfaceMesaBryce(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> P = a("frozen_ocean", new WorldGenSurfaceFrozenOcean(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> Q = a("nether", new WorldGenSurfaceNether(WorldGenSurfaceConfigurationBase::a));
    public static final WorldGenSurface<WorldGenSurfaceConfigurationBase> R = a("nope", new WorldGenSurfaceEmpty(WorldGenSurfaceConfigurationBase::a));
    private final Function<Dynamic<?>, ? extends C> a;

    private static <C extends WorldGenSurfaceConfiguration, F extends WorldGenSurface<C>> F a(String s, F f0) {
        return (WorldGenSurface) IRegistry.a(IRegistry.SURFACE_BUILDER, s, (Object) f0);
    }

    public WorldGenSurface(Function<Dynamic<?>, ? extends C> function) {
        this.a = function;
    }

    public abstract void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, C c0);

    public void a(long i) {}
}
