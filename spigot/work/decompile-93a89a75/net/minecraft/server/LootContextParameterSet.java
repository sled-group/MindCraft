package net.minecraft.server;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.Set;

public class LootContextParameterSet {

    private final Set<LootContextParameter<?>> a;
    private final Set<LootContextParameter<?>> b;

    private LootContextParameterSet(Set<LootContextParameter<?>> set, Set<LootContextParameter<?>> set1) {
        this.a = ImmutableSet.copyOf(set);
        this.b = ImmutableSet.copyOf(Sets.union(set, set1));
    }

    public Set<LootContextParameter<?>> a() {
        return this.a;
    }

    public Set<LootContextParameter<?>> b() {
        return this.b;
    }

    public String toString() {
        return "[" + Joiner.on(", ").join(this.b.stream().map((lootcontextparameter) -> {
            return (this.a.contains(lootcontextparameter) ? "!" : "") + lootcontextparameter.a();
        }).iterator()) + "]";
    }

    public void a(LootCollector lootcollector, LootItemUser lootitemuser) {
        Set<LootContextParameter<?>> set = lootitemuser.a();
        Set<LootContextParameter<?>> set1 = Sets.difference(set, this.b);

        if (!set1.isEmpty()) {
            lootcollector.a("Parameters " + set1 + " are not provided in this context");
        }

    }

    public static class a {

        private final Set<LootContextParameter<?>> a = Sets.newIdentityHashSet();
        private final Set<LootContextParameter<?>> b = Sets.newIdentityHashSet();

        public a() {}

        public LootContextParameterSet.a a(LootContextParameter<?> lootcontextparameter) {
            if (this.b.contains(lootcontextparameter)) {
                throw new IllegalArgumentException("Parameter " + lootcontextparameter.a() + " is already optional");
            } else {
                this.a.add(lootcontextparameter);
                return this;
            }
        }

        public LootContextParameterSet.a b(LootContextParameter<?> lootcontextparameter) {
            if (this.a.contains(lootcontextparameter)) {
                throw new IllegalArgumentException("Parameter " + lootcontextparameter.a() + " is already required");
            } else {
                this.b.add(lootcontextparameter);
                return this;
            }
        }

        public LootContextParameterSet a() {
            return new LootContextParameterSet(this.a, this.b);
        }
    }
}
