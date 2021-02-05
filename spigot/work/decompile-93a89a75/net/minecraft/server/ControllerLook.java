package net.minecraft.server;

public class ControllerLook {

    protected final EntityInsentient a;
    protected float b;
    protected float c;
    protected boolean d;
    protected double e;
    protected double f;
    protected double g;

    public ControllerLook(EntityInsentient entityinsentient) {
        this.a = entityinsentient;
    }

    public void a(Vec3D vec3d) {
        this.a(vec3d.x, vec3d.y, vec3d.z);
    }

    public void a(Entity entity, float f, float f1) {
        this.a(entity.locX, b(entity), entity.locZ, f, f1);
    }

    public void a(double d0, double d1, double d2) {
        this.a(d0, d1, d2, (float) this.a.dB(), (float) this.a.M());
    }

    public void a(double d0, double d1, double d2, float f, float f1) {
        this.e = d0;
        this.f = d1;
        this.g = d2;
        this.b = f;
        this.c = f1;
        this.d = true;
    }

    public void a() {
        if (this.b()) {
            this.a.pitch = 0.0F;
        }

        if (this.d) {
            this.d = false;
            this.a.aM = this.a(this.a.aM, this.h(), this.b);
            this.a.pitch = this.a(this.a.pitch, this.g(), this.c);
        } else {
            this.a.aM = this.a(this.a.aM, this.a.aK, 10.0F);
        }

        if (!this.a.getNavigation().n()) {
            this.a.aM = MathHelper.b(this.a.aM, this.a.aK, (float) this.a.dA());
        }

    }

    protected boolean b() {
        return true;
    }

    public boolean c() {
        return this.d;
    }

    public double d() {
        return this.e;
    }

    public double e() {
        return this.f;
    }

    public double f() {
        return this.g;
    }

    protected float g() {
        double d0 = this.e - this.a.locX;
        double d1 = this.f - (this.a.locY + (double) this.a.getHeadHeight());
        double d2 = this.g - this.a.locZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);

        return (float) (-(MathHelper.d(d1, d3) * 57.2957763671875D));
    }

    protected float h() {
        double d0 = this.e - this.a.locX;
        double d1 = this.g - this.a.locZ;

        return (float) (MathHelper.d(d1, d0) * 57.2957763671875D) - 90.0F;
    }

    protected float a(float f, float f1, float f2) {
        float f3 = MathHelper.c(f, f1);
        float f4 = MathHelper.a(f3, -f2, f2);

        return f + f4;
    }

    private static double b(Entity entity) {
        return entity instanceof EntityLiving ? entity.locY + (double) entity.getHeadHeight() : (entity.getBoundingBox().minY + entity.getBoundingBox().maxY) / 2.0D;
    }
}
