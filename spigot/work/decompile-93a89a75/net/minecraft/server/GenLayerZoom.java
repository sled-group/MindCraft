package net.minecraft.server;

public enum GenLayerZoom implements AreaTransformer2 {

    NORMAL, FUZZY {
        @Override
        protected int a(AreaContextTransformed<?> areacontexttransformed, int i, int j, int k, int l) {
            return areacontexttransformed.a(i, j, k, l);
        }
    };

    private GenLayerZoom() {}

    @Override
    public int a(int i) {
        return i >> 1;
    }

    @Override
    public int b(int i) {
        return i >> 1;
    }

    @Override
    public int a(AreaContextTransformed<?> areacontexttransformed, Area area, int i, int j) {
        int k = area.a(this.a(i), this.b(j));

        areacontexttransformed.a((long) (i >> 1 << 1), (long) (j >> 1 << 1));
        int l = i & 1;
        int i1 = j & 1;

        if (l == 0 && i1 == 0) {
            return k;
        } else {
            int j1 = area.a(this.a(i), this.b(j + 1));
            int k1 = areacontexttransformed.a(k, j1);

            if (l == 0 && i1 == 1) {
                return k1;
            } else {
                int l1 = area.a(this.a(i + 1), this.b(j));
                int i2 = areacontexttransformed.a(k, l1);

                if (l == 1 && i1 == 0) {
                    return i2;
                } else {
                    int j2 = area.a(this.a(i + 1), this.b(j + 1));

                    return this.a(areacontexttransformed, k, l1, j1, j2);
                }
            }
        }
    }

    protected int a(AreaContextTransformed<?> areacontexttransformed, int i, int j, int k, int l) {
        return j == k && k == l ? j : (i == j && i == k ? i : (i == j && i == l ? i : (i == k && i == l ? i : (i == j && k != l ? i : (i == k && j != l ? i : (i == l && j != k ? i : (j == k && i != l ? j : (j == l && i != k ? j : (k == l && i != j ? k : areacontexttransformed.a(i, j, k, l))))))))));
    }
}
