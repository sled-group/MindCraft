package net.minecraft.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.function.Supplier;

public class LootCollector {

    private final Multimap<String, String> a;
    private final Supplier<String> b;
    private String c;

    public LootCollector() {
        this(HashMultimap.create(), () -> {
            return "";
        });
    }

    public LootCollector(Multimap<String, String> multimap, Supplier<String> supplier) {
        this.a = multimap;
        this.b = supplier;
    }

    private String b() {
        if (this.c == null) {
            this.c = (String) this.b.get();
        }

        return this.c;
    }

    public void a(String s) {
        this.a.put(this.b(), s);
    }

    public LootCollector b(String s) {
        return new LootCollector(this.a, () -> {
            return this.b() + s;
        });
    }

    public Multimap<String, String> a() {
        return ImmutableMultimap.copyOf(this.a);
    }
}
