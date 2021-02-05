package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public final class MinecraftSerializableLong implements MinecraftSerializable {

    private final long a;

    private MinecraftSerializableLong(long i) {
        this.a = i;
    }

    public long a() {
        return this.a;
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createLong(this.a);
    }

    public static MinecraftSerializableLong a(Dynamic<?> dynamic) {
        return new MinecraftSerializableLong(dynamic.asNumber(0).longValue());
    }

    public static MinecraftSerializableLong a(long i) {
        return new MinecraftSerializableLong(i);
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            MinecraftSerializableLong minecraftserializablelong = (MinecraftSerializableLong) object;

            return this.a == minecraftserializablelong.a;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Long.hashCode(this.a);
    }

    public String toString() {
        return Long.toString(this.a);
    }
}
