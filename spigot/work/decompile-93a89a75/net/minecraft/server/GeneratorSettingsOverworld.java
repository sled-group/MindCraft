package net.minecraft.server;

public class GeneratorSettingsOverworld extends GeneratorSettingsDefault {

    private final int t = 4;
    private final int u = 4;
    private final int v = -1;
    private final int w = 63;

    public GeneratorSettingsOverworld() {}

    public int v() {
        return 4;
    }

    public int w() {
        return 4;
    }

    public int x() {
        return -1;
    }

    @Override
    public int u() {
        return 0;
    }
}
