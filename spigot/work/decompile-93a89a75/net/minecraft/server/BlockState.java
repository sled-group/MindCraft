package net.minecraft.server;

import com.google.common.base.MoreObjects;

public abstract class BlockState<T extends Comparable<T>> implements IBlockState<T> {

    private final Class<T> a;
    private final String b;
    private Integer c;

    protected BlockState(String s, Class<T> oclass) {
        this.a = oclass;
        this.b = s;
    }

    @Override
    public String a() {
        return this.b;
    }

    @Override
    public Class<T> b() {
        return this.a;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("name", this.b).add("clazz", this.a).add("values", this.getValues()).toString();
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (!(object instanceof BlockState)) {
            return false;
        } else {
            BlockState<?> blockstate = (BlockState) object;

            return this.a.equals(blockstate.a) && this.b.equals(blockstate.b);
        }
    }

    public final int hashCode() {
        if (this.c == null) {
            this.c = this.c();
        }

        return this.c;
    }

    public int c() {
        return 31 * this.a.hashCode() + this.b.hashCode();
    }
}
