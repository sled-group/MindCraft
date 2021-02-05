package net.minecraft.server;

public class EntitySize {

    public final float width;
    public final float height;
    public final boolean c;

    public EntitySize(float f, float f1, boolean flag) {
        this.width = f;
        this.height = f1;
        this.c = flag;
    }

    public EntitySize a(float f) {
        return this.a(f, f);
    }

    public EntitySize a(float f, float f1) {
        return !this.c && (f != 1.0F || f1 != 1.0F) ? b(this.width * f, this.height * f1) : this;
    }

    public static EntitySize b(float f, float f1) {
        return new EntitySize(f, f1, false);
    }

    public static EntitySize c(float f, float f1) {
        return new EntitySize(f, f1, true);
    }

    public String toString() {
        return "EntityDimensions w=" + this.width + ", h=" + this.height + ", fixed=" + this.c;
    }
}
