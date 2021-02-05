package net.minecraft.server;

public enum GenLayerOcean implements AreaTransformer3, AreaTransformerIdentity {

    INSTANCE;

    private GenLayerOcean() {}

    @Override
    public int a(WorldGenContext worldgencontext, Area area, Area area1, int i, int j) {
        int k = area.a(this.a(i), this.b(j));
        int l = area1.a(this.a(i), this.b(j));

        if (!GenLayers.a(k)) {
            return k;
        } else {
            boolean flag = true;
            boolean flag1 = true;

            for (int i1 = -8; i1 <= 8; i1 += 4) {
                for (int j1 = -8; j1 <= 8; j1 += 4) {
                    int k1 = area.a(this.a(i + i1), this.b(j + j1));

                    if (!GenLayers.a(k1)) {
                        if (l == GenLayers.a) {
                            return GenLayers.b;
                        }

                        if (l == GenLayers.e) {
                            return GenLayers.d;
                        }
                    }
                }
            }

            if (k == GenLayers.h) {
                if (l == GenLayers.b) {
                    return GenLayers.g;
                }

                if (l == GenLayers.c) {
                    return GenLayers.h;
                }

                if (l == GenLayers.d) {
                    return GenLayers.i;
                }

                if (l == GenLayers.e) {
                    return GenLayers.j;
                }
            }

            return l;
        }
    }
}
