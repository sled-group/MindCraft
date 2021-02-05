package net.minecraft.server;

public class Vec2F {

    public static final Vec2F a = new Vec2F(0.0F, 0.0F);
    public static final Vec2F b = new Vec2F(1.0F, 1.0F);
    public static final Vec2F c = new Vec2F(1.0F, 0.0F);
    public static final Vec2F d = new Vec2F(-1.0F, 0.0F);
    public static final Vec2F e = new Vec2F(0.0F, 1.0F);
    public static final Vec2F f = new Vec2F(0.0F, -1.0F);
    public static final Vec2F g = new Vec2F(Float.MAX_VALUE, Float.MAX_VALUE);
    public static final Vec2F h = new Vec2F(Float.MIN_VALUE, Float.MIN_VALUE);
    public final float i;
    public final float j;

    public Vec2F(float f, float f1) {
        this.i = f;
        this.j = f1;
    }

    public boolean c(Vec2F vec2f) {
        return this.i == vec2f.i && this.j == vec2f.j;
    }
}
