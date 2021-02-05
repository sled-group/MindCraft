package net.minecraft.server;

public interface AreaTransformer4 extends AreaTransformer2, AreaTransformerOffset1 {

    int a(WorldGenContext worldgencontext, int i, int j, int k, int l, int i1);

    @Override
    default int a(AreaContextTransformed<?> areacontexttransformed, Area area, int i, int j) {
        return this.a(areacontexttransformed, area.a(this.a(i + 0), this.b(j + 2)), area.a(this.a(i + 2), this.b(j + 2)), area.a(this.a(i + 2), this.b(j + 0)), area.a(this.a(i + 0), this.b(j + 0)), area.a(this.a(i + 1), this.b(j + 1)));
    }
}
