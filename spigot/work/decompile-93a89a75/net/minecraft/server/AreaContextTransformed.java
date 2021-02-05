package net.minecraft.server;

public interface AreaContextTransformed<R extends Area> extends WorldGenContext {

    void a(long i, long j);

    R a(AreaTransformer8 areatransformer8);

    default R a(AreaTransformer8 areatransformer8, R r0) {
        return this.a(areatransformer8);
    }

    default R a(AreaTransformer8 areatransformer8, R r0, R r1) {
        return this.a(areatransformer8);
    }

    default int a(int i, int j) {
        return this.a(2) == 0 ? i : j;
    }

    default int a(int i, int j, int k, int l) {
        int i1 = this.a(4);

        return i1 == 0 ? i : (i1 == 1 ? j : (i1 == 2 ? k : l));
    }
}
