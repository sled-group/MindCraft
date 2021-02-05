package net.minecraft.server;

public enum GenLayerCleaner implements AreaTransformer5 {

    INSTANCE;

    private GenLayerCleaner() {}

    @Override
    public int a(WorldGenContext worldgencontext, int i) {
        return GenLayers.b(i) ? i : worldgencontext.a(299999) + 2;
    }
}
