package net.minecraft.server;

public enum MobEffectInfo {

    BENEFICIAL(EnumChatFormat.BLUE), HARMFUL(EnumChatFormat.RED), NEUTRAL(EnumChatFormat.BLUE);

    private final EnumChatFormat d;

    private MobEffectInfo(EnumChatFormat enumchatformat) {
        this.d = enumchatformat;
    }
}
