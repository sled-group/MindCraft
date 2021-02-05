package net.minecraft.server;

import javax.annotation.Nullable;

public abstract class AttributeBase implements IAttribute {

    private final IAttribute a;
    private final String b;
    private final double c;
    private boolean d;

    protected AttributeBase(@Nullable IAttribute iattribute, String s, double d0) {
        this.a = iattribute;
        this.b = s;
        this.c = d0;
        if (s == null) {
            throw new IllegalArgumentException("Name cannot be null!");
        }
    }

    @Override
    public String getName() {
        return this.b;
    }

    @Override
    public double getDefault() {
        return this.c;
    }

    @Override
    public boolean c() {
        return this.d;
    }

    public AttributeBase a(boolean flag) {
        this.d = flag;
        return this;
    }

    @Nullable
    @Override
    public IAttribute d() {
        return this.a;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public boolean equals(Object object) {
        return object instanceof IAttribute && this.b.equals(((IAttribute) object).getName());
    }
}
