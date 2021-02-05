package net.minecraft.server;

public enum LayerIsland implements AreaTransformer1 {

    INSTANCE;

    private LayerIsland() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i, int j) {
        return i == 0 && j == 0 ? 1 : (worldgencontext.a(10) == 0 ? 1 : GenLayers.c);
    }
}
