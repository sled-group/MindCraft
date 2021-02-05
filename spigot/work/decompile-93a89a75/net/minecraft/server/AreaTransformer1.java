package net.minecraft.server;

public interface AreaTransformer1 {

    default <R extends Area> AreaFactory<R> a(AreaContextTransformed<R> areacontexttransformed) {
        return () -> {
            return areacontexttransformed.a((i, j) -> {
                areacontexttransformed.a((long) i, (long) j);
                return this.a((WorldGenContext) areacontexttransformed, i, j);
            });
        };
    }

    int a(WorldGenContext worldgencontext, int i, int j);
}
