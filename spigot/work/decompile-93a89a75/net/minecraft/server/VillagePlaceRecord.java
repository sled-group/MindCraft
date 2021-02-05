package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import java.util.Objects;

public class VillagePlaceRecord implements MinecraftSerializable {

    private final BlockPosition a;
    private final VillagePlaceType b;
    private int c;
    private final Runnable d;

    private VillagePlaceRecord(BlockPosition blockposition, VillagePlaceType villageplacetype, int i, Runnable runnable) {
        this.a = blockposition.immutableCopy();
        this.b = villageplacetype;
        this.c = i;
        this.d = runnable;
    }

    public VillagePlaceRecord(BlockPosition blockposition, VillagePlaceType villageplacetype, Runnable runnable) {
        this(blockposition, villageplacetype, villageplacetype.b(), runnable);
    }

    public <T> VillagePlaceRecord(Dynamic<T> dynamic, Runnable runnable) {
        this((BlockPosition) dynamic.get("pos").map(BlockPosition::a).orElse(new BlockPosition(0, 0, 0)), (VillagePlaceType) IRegistry.POINT_OF_INTEREST_TYPE.get(new MinecraftKey(dynamic.get("type").asString(""))), dynamic.get("free_tickets").asInt(0), runnable);
    }

    @Override
    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("pos"), this.a.a(dynamicops), dynamicops.createString("type"), dynamicops.createString(IRegistry.POINT_OF_INTEREST_TYPE.getKey(this.b).toString()), dynamicops.createString("free_tickets"), dynamicops.createInt(this.c)));
    }

    protected boolean b() {
        if (this.c <= 0) {
            return false;
        } else {
            --this.c;
            this.d.run();
            return true;
        }
    }

    protected boolean c() {
        if (this.c >= this.b.b()) {
            return false;
        } else {
            ++this.c;
            this.d.run();
            return true;
        }
    }

    public boolean d() {
        return this.c > 0;
    }

    public boolean e() {
        return this.c != this.b.b();
    }

    public BlockPosition f() {
        return this.a;
    }

    public VillagePlaceType g() {
        return this.b;
    }

    public boolean equals(Object object) {
        return this == object ? true : (object != null && this.getClass() == object.getClass() ? Objects.equals(this.a, ((VillagePlaceRecord) object).a) : false);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
