package net.minecraft.server;

public enum AdvancementFrameType {

    TASK("task", 0, EnumChatFormat.GREEN), CHALLENGE("challenge", 26, EnumChatFormat.DARK_PURPLE), GOAL("goal", 52, EnumChatFormat.GREEN);

    private final String d;
    private final int e;
    private final EnumChatFormat f;

    private AdvancementFrameType(String s, int i, EnumChatFormat enumchatformat) {
        this.d = s;
        this.e = i;
        this.f = enumchatformat;
    }

    public String a() {
        return this.d;
    }

    public static AdvancementFrameType a(String s) {
        AdvancementFrameType[] aadvancementframetype = values();
        int i = aadvancementframetype.length;

        for (int j = 0; j < i; ++j) {
            AdvancementFrameType advancementframetype = aadvancementframetype[j];

            if (advancementframetype.d.equals(s)) {
                return advancementframetype;
            }
        }

        throw new IllegalArgumentException("Unknown frame type '" + s + "'");
    }

    public EnumChatFormat c() {
        return this.f;
    }
}
