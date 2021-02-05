package net.minecraft.server;

public class Vector3f {

    protected final float x;
    protected final float y;
    protected final float z;

    public Vector3f(float f, float f1, float f2) {
        this.x = !Float.isInfinite(f) && !Float.isNaN(f) ? f % 360.0F : 0.0F;
        this.y = !Float.isInfinite(f1) && !Float.isNaN(f1) ? f1 % 360.0F : 0.0F;
        this.z = !Float.isInfinite(f2) && !Float.isNaN(f2) ? f2 % 360.0F : 0.0F;
    }

    public Vector3f(NBTTagList nbttaglist) {
        this(nbttaglist.i(0), nbttaglist.i(1), nbttaglist.i(2));
    }

    public NBTTagList a() {
        NBTTagList nbttaglist = new NBTTagList();

        nbttaglist.add(new NBTTagFloat(this.x));
        nbttaglist.add(new NBTTagFloat(this.y));
        nbttaglist.add(new NBTTagFloat(this.z));
        return nbttaglist;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Vector3f)) {
            return false;
        } else {
            Vector3f vector3f = (Vector3f) object;

            return this.x == vector3f.x && this.y == vector3f.y && this.z == vector3f.z;
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }
}
