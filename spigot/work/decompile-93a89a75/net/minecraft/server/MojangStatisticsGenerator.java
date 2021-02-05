package net.minecraft.server;

import com.google.common.collect.Maps;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;

public class MojangStatisticsGenerator {

    private final Map<String, Object> a = Maps.newHashMap();
    private final Map<String, Object> b = Maps.newHashMap();
    private final String c = UUID.randomUUID().toString();
    private final URL d;
    private final IMojangStatistics e;
    private final Timer f = new Timer("Snooper Timer", true);
    private final Object g = new Object();
    private final long h;
    private boolean i;

    public MojangStatisticsGenerator(String s, IMojangStatistics imojangstatistics, long i) {
        try {
            this.d = new URL("http://snoop.minecraft.net/" + s + "?version=" + 2);
        } catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }

        this.e = imojangstatistics;
        this.h = i;
    }

    public void a() {
        if (!this.i) {
            ;
        }

    }

    public void b() {
        this.b("memory_total", Runtime.getRuntime().totalMemory());
        this.b("memory_max", Runtime.getRuntime().maxMemory());
        this.b("memory_free", Runtime.getRuntime().freeMemory());
        this.b("cpu_cores", Runtime.getRuntime().availableProcessors());
        this.e.a(this);
    }

    public void a(String s, Object object) {
        Object object1 = this.g;

        synchronized (this.g) {
            this.b.put(s, object);
        }
    }

    public void b(String s, Object object) {
        Object object1 = this.g;

        synchronized (this.g) {
            this.a.put(s, object);
        }
    }

    public boolean d() {
        return this.i;
    }

    public void e() {
        this.f.cancel();
    }

    public long g() {
        return this.h;
    }
}
