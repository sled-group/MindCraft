package net.minecraft.server;

public class Paintings {

    public static final Paintings a = a("kebab", 16, 16);
    public static final Paintings b = a("aztec", 16, 16);
    public static final Paintings c = a("alban", 16, 16);
    public static final Paintings d = a("aztec2", 16, 16);
    public static final Paintings e = a("bomb", 16, 16);
    public static final Paintings f = a("plant", 16, 16);
    public static final Paintings g = a("wasteland", 16, 16);
    public static final Paintings h = a("pool", 32, 16);
    public static final Paintings i = a("courbet", 32, 16);
    public static final Paintings j = a("sea", 32, 16);
    public static final Paintings k = a("sunset", 32, 16);
    public static final Paintings l = a("creebet", 32, 16);
    public static final Paintings m = a("wanderer", 16, 32);
    public static final Paintings n = a("graham", 16, 32);
    public static final Paintings o = a("match", 32, 32);
    public static final Paintings p = a("bust", 32, 32);
    public static final Paintings q = a("stage", 32, 32);
    public static final Paintings r = a("void", 32, 32);
    public static final Paintings s = a("skull_and_roses", 32, 32);
    public static final Paintings t = a("wither", 32, 32);
    public static final Paintings u = a("fighters", 64, 32);
    public static final Paintings v = a("pointer", 64, 64);
    public static final Paintings w = a("pigscene", 64, 64);
    public static final Paintings x = a("burning_skull", 64, 64);
    public static final Paintings y = a("skeleton", 64, 48);
    public static final Paintings z = a("donkey_kong", 64, 48);
    private final int A;
    private final int B;

    private static Paintings a(String s, int i, int j) {
        return (Paintings) IRegistry.a((IRegistry) IRegistry.MOTIVE, s, (Object) (new Paintings(i, j)));
    }

    public Paintings(int i, int j) {
        this.A = i;
        this.B = j;
    }

    public int getWidth() {
        return this.A;
    }

    public int getHeight() {
        return this.B;
    }
}
