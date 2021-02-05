package net.minecraft.server;

public class GeneratorSettingsDefault {

    protected int a = 32;
    protected final int b = 8;
    protected int c = 32;
    protected int d = 5;
    protected int e = 32;
    protected int f = 128;
    protected int g = 3;
    protected int h = 32;
    protected final int i = 8;
    protected final int j = 16;
    protected final int k = 8;
    protected int l = 20;
    protected final int m = 11;
    protected final int n = 16;
    protected final int o = 8;
    protected int p = 80;
    protected final int q = 20;
    protected IBlockData r;
    protected IBlockData s;

    public GeneratorSettingsDefault() {
        this.r = Blocks.STONE.getBlockData();
        this.s = Blocks.WATER.getBlockData();
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return 8;
    }

    public int c() {
        return this.c;
    }

    public int d() {
        return this.d;
    }

    public int e() {
        return this.e;
    }

    public int f() {
        return this.f;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.h;
    }

    public int i() {
        return 8;
    }

    public int j() {
        return 16;
    }

    public int k() {
        return 8;
    }

    public int l() {
        return 16;
    }

    public int m() {
        return 8;
    }

    public int n() {
        return this.l;
    }

    public int o() {
        return 11;
    }

    public int p() {
        return this.p;
    }

    public int q() {
        return 20;
    }

    public IBlockData r() {
        return this.r;
    }

    public IBlockData s() {
        return this.s;
    }

    public void a(IBlockData iblockdata) {
        this.r = iblockdata;
    }

    public void b(IBlockData iblockdata) {
        this.s = iblockdata;
    }

    public int t() {
        return 0;
    }

    public int u() {
        return 256;
    }
}
