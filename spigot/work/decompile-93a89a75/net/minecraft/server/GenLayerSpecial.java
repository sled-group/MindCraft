package net.minecraft.server;

public class GenLayerSpecial {
    public static enum Special3 implements AreaTransformer5 {

        INSTANCE;

        private Special3() {}

        @Override
        public int a(WorldGenContext worldgencontext, int i) {
            if (!GenLayers.b(i) && worldgencontext.a(13) == 0) {
                i |= 1 + worldgencontext.a(15) << 8 & 3840;
            }

            return i;
        }
    }

    public static enum Special2 implements AreaTransformer7 {

        INSTANCE;

        private Special2() {}

        @Override
        public int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1) {
            return i1 == 4 && (i == 1 || j == 1 || l == 1 || k == 1 || i == 2 || j == 2 || l == 2 || k == 2) ? 3 : i1;
        }
    }

    public static enum Special1 implements AreaTransformer7 {

        INSTANCE;

        private Special1() {}

        @Override
        public int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1) {
            return i1 == 1 && (i == 3 || j == 3 || l == 3 || k == 3 || i == 4 || j == 4 || l == 4 || k == 4) ? 2 : i1;
        }
    }
}
