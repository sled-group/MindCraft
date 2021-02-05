package net.minecraft.server;

public enum GenLayerDeepOcean implements AreaTransformer7 {

    INSTANCE;

    private GenLayerDeepOcean() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1) {
        if (GenLayers.b(i1)) {
            int j1 = 0;

            if (GenLayers.b(i)) {
                ++j1;
            }

            if (GenLayers.b(j)) {
                ++j1;
            }

            if (GenLayers.b(l)) {
                ++j1;
            }

            if (GenLayers.b(k)) {
                ++j1;
            }

            if (j1 > 3) {
                if (i1 == GenLayers.a) {
                    return GenLayers.f;
                }

                if (i1 == GenLayers.b) {
                    return GenLayers.g;
                }

                if (i1 == GenLayers.c) {
                    return GenLayers.h;
                }

                if (i1 == GenLayers.d) {
                    return GenLayers.i;
                }

                if (i1 == GenLayers.e) {
                    return GenLayers.j;
                }

                return GenLayers.h;
            }
        }

        return i1;
    }
}
