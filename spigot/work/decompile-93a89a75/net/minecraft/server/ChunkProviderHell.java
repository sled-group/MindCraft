package net.minecraft.server;

import java.util.List;

public class ChunkProviderHell extends ChunkGeneratorAbstract<GeneratorSettingsNether> {

    private final double[] h = this.j();

    public ChunkProviderHell(World world, WorldChunkManager worldchunkmanager, GeneratorSettingsNether generatorsettingsnether) {
        super(world, worldchunkmanager, 4, 8, 128, generatorsettingsnether, false);
    }

    @Override
    protected void a(double[] adouble, int i, int j) {
        double d0 = 684.412D;
        double d1 = 2053.236D;
        double d2 = 8.555150000000001D;
        double d3 = 34.2206D;
        boolean flag = true;
        boolean flag1 = true;

        this.a(adouble, i, j, 684.412D, 2053.236D, 8.555150000000001D, 34.2206D, 3, -10);
    }

    @Override
    protected double[] a(int i, int j) {
        return new double[]{0.0D, 0.0D};
    }

    @Override
    protected double a(double d0, double d1, int i) {
        return this.h[i];
    }

    private double[] j() {
        double[] adouble = new double[this.i()];

        for (int i = 0; i < this.i(); ++i) {
            adouble[i] = Math.cos((double) i * 3.141592653589793D * 6.0D / (double) this.i()) * 2.0D;
            double d0 = (double) i;

            if (i > this.i() / 2) {
                d0 = (double) (this.i() - 1 - i);
            }

            if (d0 < 4.0D) {
                d0 = 4.0D - d0;
                adouble[i] -= d0 * d0 * d0 * 10.0D;
            }
        }

        return adouble;
    }

    @Override
    public List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType enumcreaturetype, BlockPosition blockposition) {
        if (enumcreaturetype == EnumCreatureType.MONSTER) {
            if (WorldGenerator.NETHER_BRIDGE.b(this.a, blockposition)) {
                return WorldGenerator.NETHER_BRIDGE.e();
            }

            if (WorldGenerator.NETHER_BRIDGE.a(this.a, blockposition) && this.a.getType(blockposition.down()).getBlock() == Blocks.NETHER_BRICKS) {
                return WorldGenerator.NETHER_BRIDGE.e();
            }
        }

        return super.getMobsFor(enumcreaturetype, blockposition);
    }

    @Override
    public int getSpawnHeight() {
        return this.a.getSeaLevel() + 1;
    }

    @Override
    public int getGenerationDepth() {
        return 128;
    }

    @Override
    public int getSeaLevel() {
        return 32;
    }
}
