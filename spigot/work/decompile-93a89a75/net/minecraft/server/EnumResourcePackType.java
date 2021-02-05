package net.minecraft.server;

public enum EnumResourcePackType {

    CLIENT_RESOURCES("assets"), SERVER_DATA("data");

    private final String c;

    private EnumResourcePackType(String s) {
        this.c = s;
    }

    public String a() {
        return this.c;
    }
}
