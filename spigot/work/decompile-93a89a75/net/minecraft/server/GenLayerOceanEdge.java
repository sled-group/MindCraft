package net.minecraft.server;

public enum GenLayerOceanEdge implements AreaTransformer1 {

    INSTANCE;

    private GenLayerOceanEdge() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i, int j) {
        NoiseGeneratorPerlin noisegeneratorperlin = worldgencontext.a();
        double d0 = noisegeneratorperlin.a((double) i / 8.0D, (double) j / 8.0D, 0.0D, 0.0D, 0.0D);

        return d0 > 0.4D ? GenLayers.a : (d0 > 0.2D ? GenLayers.b : (d0 < -0.4D ? GenLayers.e : (d0 < -0.2D ? GenLayers.d : GenLayers.c)));
    }
}
