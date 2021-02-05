package net.minecraft.server;

import javax.annotation.Nullable;

public interface LightEngineLayerEventListener extends ILightEngine {

    @Nullable
    NibbleArray a(SectionPosition sectionposition);

    int b(BlockPosition blockposition);

    public static enum Void implements LightEngineLayerEventListener {

        INSTANCE;

        private Void() {}

        @Nullable
        @Override
        public NibbleArray a(SectionPosition sectionposition) {
            return null;
        }

        @Override
        public int b(BlockPosition blockposition) {
            return 0;
        }

        @Override
        public void a(SectionPosition sectionposition, boolean flag) {}
    }
}
