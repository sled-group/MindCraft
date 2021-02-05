package net.minecraft.server;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SoundCategory {

    MASTER("master"), MUSIC("music"), RECORDS("record"), WEATHER("weather"), BLOCKS("block"), HOSTILE("hostile"), NEUTRAL("neutral"), PLAYERS("player"), AMBIENT("ambient"), VOICE("voice");

    private static final Map<String, SoundCategory> k = (Map) Arrays.stream(values()).collect(Collectors.toMap(SoundCategory::a, Function.identity()));
    private final String l;

    private SoundCategory(String s) {
        this.l = s;
    }

    public String a() {
        return this.l;
    }
}
