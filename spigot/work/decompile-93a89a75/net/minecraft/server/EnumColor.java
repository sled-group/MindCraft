package net.minecraft.server;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public enum EnumColor implements INamable {

    WHITE(0, "white", 16383998, MaterialMapColor.j, 15790320, 16777215), ORANGE(1, "orange", 16351261, MaterialMapColor.q, 15435844, 16738335), MAGENTA(2, "magenta", 13061821, MaterialMapColor.r, 12801229, 16711935), LIGHT_BLUE(3, "light_blue", 3847130, MaterialMapColor.s, 6719955, 10141901), YELLOW(4, "yellow", 16701501, MaterialMapColor.t, 14602026, 16776960), LIME(5, "lime", 8439583, MaterialMapColor.u, 4312372, 12582656), PINK(6, "pink", 15961002, MaterialMapColor.v, 14188952, 16738740), GRAY(7, "gray", 4673362, MaterialMapColor.w, 4408131, 8421504), LIGHT_GRAY(8, "light_gray", 10329495, MaterialMapColor.x, 11250603, 13882323), CYAN(9, "cyan", 1481884, MaterialMapColor.y, 2651799, 65535), PURPLE(10, "purple", 8991416, MaterialMapColor.z, 8073150, 10494192), BLUE(11, "blue", 3949738, MaterialMapColor.A, 2437522, 255), BROWN(12, "brown", 8606770, MaterialMapColor.B, 5320730, 9127187), GREEN(13, "green", 6192150, MaterialMapColor.C, 3887386, 65280), RED(14, "red", 11546150, MaterialMapColor.D, 11743532, 16711680), BLACK(15, "black", 1908001, MaterialMapColor.E, 1973019, 0);

    private static final EnumColor[] q = (EnumColor[]) Arrays.stream(values()).sorted(Comparator.comparingInt(EnumColor::getColorIndex)).toArray((i) -> {
        return new EnumColor[i];
    });
    private static final Int2ObjectOpenHashMap<EnumColor> r = new Int2ObjectOpenHashMap((Map) Arrays.stream(values()).collect(Collectors.toMap((enumcolor) -> {
        return enumcolor.y;
    }, (enumcolor) -> {
        return enumcolor;
    })));
    private final int s;
    private final String t;
    private final MaterialMapColor u;
    private final int v;
    private final int w;
    private final float[] x;
    private final int y;
    private final int z;

    private EnumColor(int i, String s, int j, MaterialMapColor materialmapcolor, int k, int l) {
        this.s = i;
        this.t = s;
        this.v = j;
        this.u = materialmapcolor;
        this.z = l;
        int i1 = (j & 16711680) >> 16;
        int j1 = (j & '\uff00') >> 8;
        int k1 = (j & 255) >> 0;

        this.w = k1 << 16 | j1 << 8 | i1 << 0;
        this.x = new float[]{(float) i1 / 255.0F, (float) j1 / 255.0F, (float) k1 / 255.0F};
        this.y = k;
    }

    public int getColorIndex() {
        return this.s;
    }

    public String b() {
        return this.t;
    }

    public float[] d() {
        return this.x;
    }

    public MaterialMapColor e() {
        return this.u;
    }

    public int f() {
        return this.y;
    }

    public static EnumColor fromColorIndex(int i) {
        if (i < 0 || i >= EnumColor.q.length) {
            i = 0;
        }

        return EnumColor.q[i];
    }

    public static EnumColor a(String s, EnumColor enumcolor) {
        EnumColor[] aenumcolor = values();
        int i = aenumcolor.length;

        for (int j = 0; j < i; ++j) {
            EnumColor enumcolor1 = aenumcolor[j];

            if (enumcolor1.t.equals(s)) {
                return enumcolor1;
            }
        }

        return enumcolor;
    }

    public String toString() {
        return this.t;
    }

    @Override
    public String getName() {
        return this.t;
    }
}
