package net.minecraft.server;

import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.stream.Stream;

public final class PlayerMap {

    private final Object2BooleanMap<EntityPlayer> a = new Object2BooleanOpenHashMap();

    public PlayerMap() {}

    public Stream<EntityPlayer> a(long i) {
        return this.a.keySet().stream();
    }

    public void a(long i, EntityPlayer entityplayer, boolean flag) {
        this.a.put(entityplayer, flag);
    }

    public void a(long i, EntityPlayer entityplayer) {
        this.a.removeBoolean(entityplayer);
    }

    public void a(EntityPlayer entityplayer) {
        this.a.replace(entityplayer, true);
    }

    public void b(EntityPlayer entityplayer) {
        this.a.replace(entityplayer, false);
    }

    public boolean c(EntityPlayer entityplayer) {
        return this.a.getOrDefault(entityplayer, true);
    }

    public boolean d(EntityPlayer entityplayer) {
        return this.a.getBoolean(entityplayer);
    }

    public void a(long i, long j, EntityPlayer entityplayer) {}
}
