package net.minecraft.server;

public class ChunkProviderTheEnd extends ChunkGeneratorAbstract<GeneratorSettingsEnd> {

    private final BlockPosition h;

    public ChunkProviderTheEnd(GeneratorAccess generatoraccess, WorldChunkManager worldchunkmanager, GeneratorSettingsEnd generatorsettingsend) {
        super(generatoraccess, worldchunkmanager, 8, 4, 128, generatorsettingsend, true);
        this.h = generatorsettingsend.v();
    }

    @Override
    protected void a(double[] adouble, int i, int j) {
        double d0 = 1368.824D;
        double d1 = 684.412D;
        double d2 = 17.110300000000002D;
        double d3 = 4.277575000000001D;
        boolean flag = true;
        boolean flag1 = true;

        this.a(adouble, i, j, 1368.824D, 684.412D, 17.110300000000002D, 4.277575000000001D, 64, -3000);
    }

    @Override
    protected double[] a(int i, int j) {
        return new double[]{(double) this.c.c(i, j), 0.0D};
    }

    @Override
    protected double a(double d0, double d1, int i) {
        return 8.0D - d0;
    }

    @Override
    protected double g() {
        return (double) ((int) super.g() / 2);
    }

    @Override
    protected double h() {
        return 8.0D;
    }

    @Override
    public int getSpawnHeight() {
        return 50;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }
}
