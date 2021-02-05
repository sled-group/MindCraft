package net.minecraft.server;

public enum GenLayerZoomVoronoi implements AreaTransformer2 {

    INSTANCE;

    private GenLayerZoomVoronoi() {}

    @Override
    public int a(AreaContextTransformed<?> areacontexttransformed, Area area, int i, int j) {
        int k = i - 2;
        int l = j - 2;
        int i1 = k >> 2;
        int j1 = l >> 2;
        int k1 = i1 << 2;
        int l1 = j1 << 2;

        areacontexttransformed.a((long) k1, (long) l1);
        double d0 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D;
        double d1 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D;

        areacontexttransformed.a((long) (k1 + 4), (long) l1);
        double d2 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        double d3 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D;

        areacontexttransformed.a((long) k1, (long) (l1 + 4));
        double d4 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D;
        double d5 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;

        areacontexttransformed.a((long) (k1 + 4), (long) (l1 + 4));
        double d6 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        double d7 = ((double) areacontexttransformed.a(1024) / 1024.0D - 0.5D) * 3.6D + 4.0D;
        int i2 = k & 3;
        int j2 = l & 3;
        double d8 = ((double) j2 - d1) * ((double) j2 - d1) + ((double) i2 - d0) * ((double) i2 - d0);
        double d9 = ((double) j2 - d3) * ((double) j2 - d3) + ((double) i2 - d2) * ((double) i2 - d2);
        double d10 = ((double) j2 - d5) * ((double) j2 - d5) + ((double) i2 - d4) * ((double) i2 - d4);
        double d11 = ((double) j2 - d7) * ((double) j2 - d7) + ((double) i2 - d6) * ((double) i2 - d6);

        return d8 < d9 && d8 < d10 && d8 < d11 ? area.a(this.a(k1), this.b(l1)) : (d9 < d8 && d9 < d10 && d9 < d11 ? area.a(this.a(k1 + 4), this.b(l1)) & 255 : (d10 < d8 && d10 < d9 && d10 < d11 ? area.a(this.a(k1), this.b(l1 + 4)) : area.a(this.a(k1 + 4), this.b(l1 + 4)) & 255));
    }

    @Override
    public int a(int i) {
        return i >> 2;
    }

    @Override
    public int b(int i) {
        return i >> 2;
    }
}
