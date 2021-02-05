package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class LootEntryChildrenAbstract extends LootEntryAbstract {

    protected final LootEntryAbstract[] c;
    private final LootEntryChildren e;

    protected LootEntryChildrenAbstract(LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition) {
        super(alootitemcondition);
        this.c = alootentryabstract;
        this.e = this.a((LootEntryChildren[]) alootentryabstract);
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        super.a(lootcollector, function, set, lootcontextparameterset);
        if (this.c.length == 0) {
            lootcollector.a("Empty children list");
        }

        for (int i = 0; i < this.c.length; ++i) {
            this.c[i].a(lootcollector.b(".entry[" + i + "]"), function, set, lootcontextparameterset);
        }

    }

    protected abstract LootEntryChildren a(LootEntryChildren[] alootentrychildren);

    @Override
    public final boolean expand(LootTableInfo loottableinfo, Consumer<LootEntry> consumer) {
        return !this.a(loottableinfo) ? false : this.e.expand(loottableinfo, consumer);
    }

    public static <T extends LootEntryChildrenAbstract> LootEntryChildrenAbstract.b<T> a(MinecraftKey minecraftkey, Class<T> oclass, final LootEntryChildrenAbstract.a<T> lootentrychildrenabstract_a) {
        return new LootEntryChildrenAbstract.b<T>(minecraftkey, oclass) {
            @Override
            protected T a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition) {
                return lootentrychildrenabstract_a.create(alootentryabstract, alootitemcondition);
            }
        };
    }

    public abstract static class b<T extends LootEntryChildrenAbstract> extends LootEntryAbstract.b<T> {

        public b(MinecraftKey minecraftkey, Class<T> oclass) {
            super(minecraftkey, oclass);
        }

        public void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("children", jsonserializationcontext.serialize(t0.c));
        }

        @Override
        public final T b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootEntryAbstract[] alootentryabstract = (LootEntryAbstract[]) ChatDeserializer.a(jsonobject, "children", jsondeserializationcontext, LootEntryAbstract[].class);

            return this.a(jsonobject, jsondeserializationcontext, alootentryabstract, alootitemcondition);
        }

        protected abstract T a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition);
    }

    @FunctionalInterface
    public interface a<T extends LootEntryChildrenAbstract> {

        T create(LootEntryAbstract[] alootentryabstract, LootItemCondition[] alootitemcondition);
    }
}
