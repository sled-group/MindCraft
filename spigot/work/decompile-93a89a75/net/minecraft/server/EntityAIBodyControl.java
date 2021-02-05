package net.minecraft.server;

public class EntityAIBodyControl {

    private final EntityInsentient a;
    private int b;
    private float c;

    public EntityAIBodyControl(EntityInsentient entityinsentient) {
        this.a = entityinsentient;
    }

    public void a() {
        if (this.f()) {
            this.a.aK = this.a.yaw;
            this.c();
            this.c = this.a.aM;
            this.b = 0;
        } else {
            if (this.e()) {
                if (Math.abs(this.a.aM - this.c) > 15.0F) {
                    this.b = 0;
                    this.c = this.a.aM;
                    this.b();
                } else {
                    ++this.b;
                    if (this.b > 10) {
                        this.d();
                    }
                }
            }

        }
    }

    private void b() {
        this.a.aK = MathHelper.b(this.a.aK, this.a.aM, (float) this.a.dA());
    }

    private void c() {
        this.a.aM = MathHelper.b(this.a.aM, this.a.aK, (float) this.a.dA());
    }

    private void d() {
        int i = this.b - 10;
        float f = MathHelper.a((float) i / 10.0F, 0.0F, 1.0F);
        float f1 = (float) this.a.dA() * (1.0F - f);

        this.a.aK = MathHelper.b(this.a.aK, this.a.aM, f1);
    }

    private boolean e() {
        return this.a.getPassengers().isEmpty() || !(this.a.getPassengers().get(0) instanceof EntityInsentient);
    }

    private boolean f() {
        double d0 = this.a.locX - this.a.lastX;
        double d1 = this.a.locZ - this.a.lastZ;

        return d0 * d0 + d1 * d1 > 2.500000277905201E-7D;
    }
}
