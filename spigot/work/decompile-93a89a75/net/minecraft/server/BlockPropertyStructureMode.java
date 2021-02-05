package net.minecraft.server;

public enum BlockPropertyStructureMode implements INamable {

    SAVE("save"), LOAD("load"), CORNER("corner"), DATA("data");

    private final String e;

    private BlockPropertyStructureMode(String s) {
        this.e = s;
    }

    @Override
    public String getName() {
        return this.e;
    }
}
