package net.minecraft.server;

import java.util.Arrays;

public final class Vector3fa {

    private final float[] a;

    public Vector3fa() {
        this.a = new float[3];
    }

    public Vector3fa(Vec3D vec3d) {
        this.a = new float[]{(float) vec3d.x, (float) vec3d.y, (float) vec3d.z};
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            Vector3fa vector3fa = (Vector3fa) object;

            return Arrays.equals(this.a, vector3fa.a);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Arrays.hashCode(this.a);
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

    public void a(float f, float f1, float f2) {
        this.a[0] = f;
        this.a[1] = f1;
        this.a[2] = f2;
    }

    public void a(Quaternion quaternion) {
        Quaternion quaternion1 = new Quaternion(quaternion);

        quaternion1.a(new Quaternion(this.a(), this.b(), this.c(), 0.0F));
        Quaternion quaternion2 = new Quaternion(quaternion);

        quaternion2.e();
        quaternion1.a(quaternion2);
        this.a(quaternion1.a(), quaternion1.b(), quaternion1.c());
    }
}
