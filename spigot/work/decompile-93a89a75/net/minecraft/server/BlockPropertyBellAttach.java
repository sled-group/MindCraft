package net.minecraft.server;

public enum BlockPropertyBellAttach implements INamable {

    FLOOR("floor"), CEILING("ceiling"), SINGLE_WALL("single_wall"), DOUBLE_WALL("double_wall");

    private final String e;

    private BlockPropertyBellAttach(String s) {
        this.e = s;
    }

    @Override
    public String getName() {
        return this.e;
    }
}
