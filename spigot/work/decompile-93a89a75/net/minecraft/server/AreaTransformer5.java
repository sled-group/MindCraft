package net.minecraft.server;

public interface AreaTransformer5 extends AreaTransformer2, AreaTransformerIdentity {

    int a(WorldGenContext worldgencontext, int i);

    @Override
    default int a(AreaContextTransformed<?> areacontexttransformed, Area area, int i, int j) {
        return this.a(areacontexttransformed, area.a(this.a(i), this.b(j)));
    }
}
