package net.minecraft.server;

import javax.annotation.Nullable;

public class LightEngine implements ILightEngine {

    @Nullable
    private final LightEngineLayer<?, ?> a;
    @Nullable
    private final LightEngineLayer<?, ?> b;

    public LightEngine(ILightAccess ilightaccess, boolean flag, boolean flag1) {
        this.a = flag ? new LightEngineBlock(ilightaccess) : null;
        this.b = flag1 ? new LightEngineSky(ilightaccess) : null;
    }

    public void a(BlockPosition blockposition) {
        if (this.a != null) {
            this.a.a(blockposition);
        }

        if (this.b != null) {
            this.b.a(blockposition);
        }

    }

    public void a(BlockPosition blockposition, int i) {
        if (this.a != null) {
            this.a.a(blockposition, i);
        }

    }

    public boolean a() {
        return this.b != null && this.b.a() ? true : this.a != null && this.a.a();
    }

    public int a(int i, boolean flag, boolean flag1) {
        if (this.a != null && this.b != null) {
            int j = i / 2;
            int k = this.a.a(j, flag, flag1);
            int l = i - j + k;
            int i1 = this.b.a(l, flag, flag1);

            return k == 0 && i1 > 0 ? this.a.a(i1, flag, flag1) : i1;
        } else {
            return this.a != null ? this.a.a(i, flag, flag1) : (this.b != null ? this.b.a(i, flag, flag1) : i);
        }
    }

    @Override
    public void a(SectionPosition sectionposition, boolean flag) {
        if (this.a != null) {
            this.a.a(sectionposition, flag);
        }

        if (this.b != null) {
            this.b.a(sectionposition, flag);
        }

    }

    public void a(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
        if (this.a != null) {
            this.a.a(chunkcoordintpair, flag);
        }

        if (this.b != null) {
            this.b.a(chunkcoordintpair, flag);
        }

    }

    public LightEngineLayerEventListener a(EnumSkyBlock enumskyblock) {
        return (LightEngineLayerEventListener) (enumskyblock == EnumSkyBlock.BLOCK ? (this.a == null ? LightEngineLayerEventListener.Void.INSTANCE : this.a) : (this.b == null ? LightEngineLayerEventListener.Void.INSTANCE : this.b));
    }

    public void a(EnumSkyBlock enumskyblock, SectionPosition sectionposition, @Nullable NibbleArray nibblearray) {
        if (enumskyblock == EnumSkyBlock.BLOCK) {
            if (this.a != null) {
                this.a.a(sectionposition.v(), nibblearray);
            }
        } else if (this.b != null) {
            this.b.a(sectionposition.v(), nibblearray);
        }

    }

    public void b(ChunkCoordIntPair chunkcoordintpair, boolean flag) {
        if (this.a != null) {
            this.a.b(chunkcoordintpair, flag);
        }

        if (this.b != null) {
            this.b.b(chunkcoordintpair, flag);
        }

    }
}
