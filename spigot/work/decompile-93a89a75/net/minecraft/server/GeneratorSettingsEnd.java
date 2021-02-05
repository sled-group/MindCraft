package net.minecraft.server;

public class GeneratorSettingsEnd extends GeneratorSettingsDefault {

    private BlockPosition t;

    public GeneratorSettingsEnd() {}

    public GeneratorSettingsEnd a(BlockPosition blockposition) {
        this.t = blockposition;
        return this;
    }

    public BlockPosition v() {
        return this.t;
    }
}
