package net.minecraft.server;

import java.util.Arrays;

public final class Quaternion {

    private final float[] a;

    public Quaternion() {
        this.a = new float[4];
        this.a[4] = 1.0F;
    }

    public Quaternion(float f, float f1, float f2, float f3) {
        this.a = new float[4];
        this.a[0] = f;
        this.a[1] = f1;
        this.a[2] = f2;
        this.a[3] = f3;
    }

    public Quaternion(Vector3fa vector3fa, float f, boolean flag) {
        if (flag) {
            f *= 0.017453292F;
        }

        float f1 = b(f / 2.0F);

        this.a = new float[4];
        this.a[0] = vector3fa.a() * f1;
        this.a[1] = vector3fa.b() * f1;
        this.a[2] = vector3fa.c() * f1;
        this.a[3] = a(f / 2.0F);
    }

    public Quaternion(Quaternion quaternion) {
        this.a = Arrays.copyOf(quaternion.a, 4);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Quaternion quaternion = (Quaternion) object;

            return Arrays.equals(this.a, quaternion.a);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append("Quaternion[").append(this.d()).append(" + ");
        stringbuilder.append(this.a()).append("i + ");
        stringbuilder.append(this.b()).append("j + ");
        stringbuilder.append(this.c()).append("k]");
        return stringbuilder.toString();
    }

    public float a() {
        return this.a[0];
    }

    public float b() {
        return this.a[1];
    }

    public float c() {
        return this.a[2];
    }

    public float d() {
        return this.a[3];
    }

    public void a(Quaternion quaternion) {
        float f = this.a();
        float f1 = this.b();
        float f2 = this.c();
        float f3 = this.d();
        float f4 = quaternion.a();
        float f5 = quaternion.b();
        float f6 = quaternion.c();
        float f7 = quaternion.d();

        this.a[0] = f3 * f4 + f * f7 + f1 * f6 - f2 * f5;
        this.a[1] = f3 * f5 - f * f6 + f1 * f7 + f2 * f4;
        this.a[2] = f3 * f6 + f * f5 - f1 * f4 + f2 * f7;
        this.a[3] = f3 * f7 - f * f4 - f1 * f5 - f2 * f6;
    }

    public void e() {
        this.a[0] = -this.a[0];
        this.a[1] = -this.a[1];
        this.a[2] = -this.a[2];
    }

    private static float a(float f) {
        return (float) Math.cos((double) f);
    }

    private static float b(float f) {
        return (float) Math.sin((double) f);
    }
}
