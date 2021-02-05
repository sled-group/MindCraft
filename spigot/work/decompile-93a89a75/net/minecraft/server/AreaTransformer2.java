package net.minecraft.server;

public interface AreaTransformer2 extends AreaTransformer {

    default <R extends Area> AreaFactory<R> a(AreaContextTransformed<R> areacontexttransformed, AreaFactory<R> areafactory) {
        return () -> {
            R r0 = areafactory.make();

            return areacontexttransformed.a((i, j) -> {
                areacontexttransformed.a((long) i, (long) j);
                return this.a(areacontexttransformed, r0, i, j);
            }, r0);
        };
    }

    int a(AreaContextTransformed<?> areacontexttransformed, Area area, int i, int j);
}
