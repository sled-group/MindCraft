package net.minecraft.server;

public class ActivityFrame {

    private final int a;
    private final float b;

    public ActivityFrame(int i, float f) {
        this.a = i;
        this.b = f;
    }

    public int a() {
        return this.a;
    }

    public float b() {
        return this.b;
    }
}
