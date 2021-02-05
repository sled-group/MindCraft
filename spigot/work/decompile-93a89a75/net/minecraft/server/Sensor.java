package net.minecraft.server;

import java.util.Random;
import java.util.Set;

public abstract class Sensor<E extends EntityLiving> {

    private static final Random a = new Random();
    private final int b;
    private long c;

    public Sensor(int i) {
        this.b = i;
        this.c = (long) Sensor.a.nextInt(i);
    }

    public Sensor() {
        this(20);
    }

    public final void b(WorldServer worldserver, E e0) {
        if (--this.c <= 0L) {
            this.c = (long) this.b;
            this.a(worldserver, e0);
        }

    }

    protected abstract void a(WorldServer worldserver, E e0);

    public abstract Set<MemoryModuleType<?>> a();
}
