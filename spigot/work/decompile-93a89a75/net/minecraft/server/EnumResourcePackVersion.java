package net.minecraft.server;

public enum EnumResourcePackVersion {

    TOO_OLD("old"), TOO_NEW("new"), COMPATIBLE("compatible");

    private final IChatBaseComponent d;
    private final IChatBaseComponent e;

    private EnumResourcePackVersion(String s) {
        this.d = new ChatMessage("resourcePack.incompatible." + s, new Object[0]);
        this.e = new ChatMessage("resourcePack.incompatible.confirm." + s, new Object[0]);
    }

    public boolean a() {
        return this == EnumResourcePackVersion.COMPATIBLE;
    }

    public static EnumResourcePackVersion a(int i) {
        return i < SharedConstants.a().getPackVersion() ? EnumResourcePackVersion.TOO_OLD : (i > SharedConstants.a().getPackVersion() ? EnumResourcePackVersion.TOO_NEW : EnumResourcePackVersion.COMPATIBLE);
    }
}
