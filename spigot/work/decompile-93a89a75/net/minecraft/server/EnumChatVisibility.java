package net.minecraft.server;

import java.util.Arrays;
import java.util.Comparator;

public enum EnumChatVisibility {

    FULL(0, "options.chat.visibility.full"), SYSTEM(1, "options.chat.visibility.system"), HIDDEN(2, "options.chat.visibility.hidden");

    private static final EnumChatVisibility[] d = (EnumChatVisibility[]) Arrays.stream(values()).sorted(Comparator.comparingInt(EnumChatVisibility::a)).toArray((i) -> {
        return new EnumChatVisibility[i];
    });
    private final int e;
    private final String f;

    private EnumChatVisibility(int i, String s) {
        this.e = i;
        this.f = s;
    }

    public int a() {
        return this.e;
    }
}
