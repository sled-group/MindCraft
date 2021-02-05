package net.minecraft.server;

public abstract class StructureAbstract extends StructureStart {

    public StructureAbstract(StructureGenerator<?> structuregenerator, int i, int j, BiomeBase biomebase, StructureBoundingBox structureboundingbox, int k, long l) {
        super(structuregenerator, i, j, biomebase, structureboundingbox, k, l);
    }

    @Override
    protected void b() {
        super.b();
        boolean flag = true;

        this.c.a -= 12;
        this.c.b -= 12;
        this.c.c -= 12;
        this.c.d += 12;
        this.c.e += 12;
        this.c.f += 12;
    }
}
