package net.minecraft.server;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum EnumCreatureType {

    MONSTER("monster", 70, false, false), CREATURE("creature", 10, true, true), AMBIENT("ambient", 15, true, false), WATER_CREATURE("water_creature", 15, true, false), MISC("misc", 15, true, false);

    private static final Map<String, EnumCreatureType> f = (Map) Arrays.stream(values()).collect(Collectors.toMap(EnumCreatureType::a, (enumcreaturetype) -> {
        return enumcreaturetype;
    }));
    private final int g;
    private final boolean h;
    private final boolean i;
    private final String j;

    private EnumCreatureType(String s, int i, boolean flag, boolean flag1) {
        this.j = s;
        this.g = i;
        this.h = flag;
        this.i = flag1;
    }

    public String a() {
        return this.j;
    }

    public int b() {
        return this.g;
    }

    public boolean c() {
        return this.h;
    }

    public boolean d() {
        return this.i;
    }
}
