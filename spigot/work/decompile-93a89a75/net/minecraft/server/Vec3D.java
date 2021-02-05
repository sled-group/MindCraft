package net.minecraft.server;

import java.util.EnumSet;

public class Vec3D implements IPosition {

    public static final Vec3D a = new Vec3D(0.0D, 0.0D, 0.0D);
    public final double x;
    public final double y;
    public final double z;

    public Vec3D(double d0, double d1, double d2) {
        this.x = d0;
        this.y = d1;
        this.z = d2;
    }

    public Vec3D(BaseBlockPosition baseblockposition) {
        this((double) baseblockposition.getX(), (double) baseblockposition.getY(), (double) baseblockposition.getZ());
    }

    public Vec3D a(Vec3D vec3d) {
        return new Vec3D(vec3d.x - this.x, vec3d.y - this.y, vec3d.z - this.z);
    }

    public Vec3D d() {
        double d0 = (double) MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);

        return d0 < 1.0E-4D ? Vec3D.a : new Vec3D(this.x / d0, this.y / d0, this.z / d0);
    }

    public double b(Vec3D vec3d) {
        return this.x * vec3d.x + this.y * vec3d.y + this.z * vec3d.z;
    }

    public Vec3D c(Vec3D vec3d) {
        return new Vec3D(this.y * vec3d.z - this.z * vec3d.y, this.z * vec3d.x - this.x * vec3d.z, this.x * vec3d.y - this.y * vec3d.x);
    }

    public Vec3D d(Vec3D vec3d) {
        return this.a(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3D a(double d0, double d1, double d2) {
        return this.add(-d0, -d1, -d2);
    }

    public Vec3D e(Vec3D vec3d) {
        return this.add(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3D add(double d0, double d1, double d2) {
        return new Vec3D(this.x + d0, this.y + d1, this.z + d2);
    }

    public double f(Vec3D vec3d) {
        double d0 = vec3d.x - this.x;
        double d1 = vec3d.y - this.y;
        double d2 = vec3d.z - this.z;

        return (double) MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
    }

    public double distanceSquared(Vec3D vec3d) {
        double d0 = vec3d.x - this.x;
        double d1 = vec3d.y - this.y;
        double d2 = vec3d.z - this.z;

        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public double c(double d0, double d1, double d2) {
        double d3 = d0 - this.x;
        double d4 = d1 - this.y;
        double d5 = d2 - this.z;

        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public Vec3D a(double d0) {
        return this.d(d0, d0, d0);
    }

    public Vec3D h(Vec3D vec3d) {
        return this.d(vec3d.x, vec3d.y, vec3d.z);
    }

    public Vec3D d(double d0, double d1, double d2) {
        return new Vec3D(this.x * d0, this.y * d1, this.z * d2);
    }

    public double f() {
        return (double) MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double g() {
        return this.x * this.x + this.y * this.y + this.z * this.z;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof Vec3D)) {
            return false;
        } else {
            Vec3D vec3d = (Vec3D) object;

            return Double.compare(vec3d.x, this.x) != 0 ? false : (Double.compare(vec3d.y, this.y) != 0 ? false : Double.compare(vec3d.z, this.z) == 0);
        }
    }

    public int hashCode() {
        long i = Double.doubleToLongBits(this.x);
        int j = (int) (i ^ i >>> 32);

        i = Double.doubleToLongBits(this.y);
        j = 31 * j + (int) (i ^ i >>> 32);
        i = Double.doubleToLongBits(this.z);
        j = 31 * j + (int) (i ^ i >>> 32);
        return j;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public Vec3D a(float f) {
        float f1 = MathHelper.cos(f);
        float f2 = MathHelper.sin(f);
        double d0 = this.x;
        double d1 = this.y * (double) f1 + this.z * (double) f2;
        double d2 = this.z * (double) f1 - this.y * (double) f2;

        return new Vec3D(d0, d1, d2);
    }

    public Vec3D b(float f) {
        float f1 = MathHelper.cos(f);
        float f2 = MathHelper.sin(f);
        double d0 = this.x * (double) f1 + this.z * (double) f2;
        double d1 = this.y;
        double d2 = this.z * (double) f1 - this.x * (double) f2;

        return new Vec3D(d0, d1, d2);
    }

    public Vec3D a(EnumSet<EnumDirection.EnumAxis> enumset) {
        double d0 = enumset.contains(EnumDirection.EnumAxis.X) ? (double) MathHelper.floor(this.x) : this.x;
        double d1 = enumset.contains(EnumDirection.EnumAxis.Y) ? (double) MathHelper.floor(this.y) : this.y;
        double d2 = enumset.contains(EnumDirection.EnumAxis.Z) ? (double) MathHelper.floor(this.z) : this.z;

        return new Vec3D(d0, d1, d2);
    }

    public double a(EnumDirection.EnumAxis enumdirection_enumaxis) {
        return enumdirection_enumaxis.a(this.x, this.y, this.z);
    }

    @Override
    public final double getX() {
        return this.x;
    }

    @Override
    public final double getY() {
        return this.y;
    }

    @Override
    public final double getZ() {
        return this.z;
    }
}
