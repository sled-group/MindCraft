package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class LootEntryAbstract implements LootEntryChildren {

    protected final LootItemCondition[] d;
    private final Predicate<LootTableInfo> c;

    protected LootEntryAbstract(LootItemCondition[] alootitemcondition) {
        this.d = alootitemcondition;
        this.c = LootItemConditions.a((Predicate[]) alootitemcondition);
    }

    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].a(lootcollector.b(".condition[" + i + "]"), function, set, lootcontextparameterset);
        }

    }

    protected final boolean a(LootTableInfo loottableinfo) {
        return this.c.test(loottableinfo);
    }

    public abstract static class b<T extends LootEntryAbstract> {

        private final MinecraftKey a;
        private final Class<T> b;

        protected b(MinecraftKey minecraftkey, Class<T> oclass) {
            this.a = minecraftkey;
            this.b = oclass;
        }

        public MinecraftKey a() {
            return this.a;
        }

        public Class<T> b() {
            return this.b;
        }

        public abstract void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext);

        public abstract T b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition);
    }

    public abstract static class a<T extends LootEntryAbstract.a<T>> implements LootItemConditionUser<T> {

        private final List<LootItemCondition> a = Lists.newArrayList();

        public a() {}

        protected abstract T d();

        @Override
        public T b(LootItemCondition.a lootitemcondition_a) {
            this.a.add(lootitemcondition_a.build());
            return this.d();
        }

        @Override
        public final T c() {
            return this.d();
        }

        protected LootItemCondition[] f() {
            return (LootItemCondition[]) this.a.toArray(new LootItemCondition[0]);
        }

        public LootEntryAlternatives.a a(LootEntryAbstract.a<?> lootentryabstract_a) {
            return new LootEntryAlternatives.a(new LootEntryAbstract.a[]{this, lootentryabstract_a});
        }

        public abstract LootEntryAbstract b();
    }
}
