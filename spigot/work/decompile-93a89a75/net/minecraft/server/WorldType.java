package net.minecraft.server;

import javax.annotation.Nullable;

public class WorldType {

    public static final WorldType[] types = new WorldType[16];
    public static final WorldType NORMAL = (new WorldType(0, "default", 1)).k();
    public static final WorldType FLAT = (new WorldType(1, "flat")).a(true);
    public static final WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
    public static final WorldType AMPLIFIED = (new WorldType(3, "amplified")).l();
    public static final WorldType CUSTOMIZED = (new WorldType(4, "customized", "normal", 0)).a(true).b(false);
    public static final WorldType g = (new WorldType(5, "buffet")).a(true);
    public static final WorldType DEBUG_ALL_BLOCK_STATES = new WorldType(6, "debug_all_block_states");
    public static final WorldType NORMAL_1_1 = (new WorldType(8, "default_1_1", 0)).b(false);
    private final int j;
    private final String name;
    private final String l;
    private final int version;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;

    private WorldType(int i, String s) {
        this(i, s, s, 0);
    }

    private WorldType(int i, String s, int j) {
        this(i, s, s, j);
    }

    private WorldType(int i, String s, String s1, int j) {
        this.name = s;
        this.l = s1;
        this.version = j;
        this.n = true;
        this.j = i;
        WorldType.types[i] = this;
    }

    public String name() {
        return this.name;
    }

    public String b() {
        return this.l;
    }

    public int getVersion() {
        return this.version;
    }

    public WorldType a(int i) {
        return this == WorldType.NORMAL && i == 0 ? WorldType.NORMAL_1_1 : this;
    }

    public WorldType a(boolean flag) {
        this.q = flag;
        return this;
    }

    private WorldType b(boolean flag) {
        this.n = flag;
        return this;
    }

    private WorldType k() {
        this.o = true;
        return this;
    }

    public boolean h() {
        return this.o;
    }

    @Nullable
    public static WorldType getType(String s) {
        WorldType[] aworldtype = WorldType.types;
        int i = aworldtype.length;

        for (int j = 0; j < i; ++j) {
            WorldType worldtype = aworldtype[j];

            if (worldtype != null && worldtype.name.equalsIgnoreCase(s)) {
                return worldtype;
            }
        }

        return null;
    }

    public int i() {
        return this.j;
    }

    private WorldType l() {
        this.p = true;
        return this;
    }
}
