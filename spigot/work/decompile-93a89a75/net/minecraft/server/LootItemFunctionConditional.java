package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.commons.lang3.ArrayUtils;

public abstract class LootItemFunctionConditional implements LootItemFunction {

    protected final LootItemCondition[] b;
    private final Predicate<LootTableInfo> a;

    protected LootItemFunctionConditional(LootItemCondition[] alootitemcondition) {
        this.b = alootitemcondition;
        this.a = LootItemConditions.a((Predicate[]) alootitemcondition);
    }

    public final ItemStack apply(ItemStack itemstack, LootTableInfo loottableinfo) {
        return this.a.test(loottableinfo) ? this.a(itemstack, loottableinfo) : itemstack;
    }

    protected abstract ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo);

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        LootItemFunction.super.a(lootcollector, function, set, lootcontextparameterset);

        for (int i = 0; i < this.b.length; ++i) {
            this.b[i].a(lootcollector.b(".conditions[" + i + "]"), function, set, lootcontextparameterset);
        }

    }

    protected static LootItemFunctionConditional.a<?> a(Function<LootItemCondition[], LootItemFunction> function) {
        return new LootItemFunctionConditional.b(function);
    }

    public abstract static class c<T extends LootItemFunctionConditional> extends LootItemFunction.b<T> {

        public c(MinecraftKey minecraftkey, Class<T> oclass) {
            super(minecraftkey, oclass);
        }

        public void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext) {
            if (!ArrayUtils.isEmpty(t0.b)) {
                jsonobject.add("conditions", jsonserializationcontext.serialize(t0.b));
            }

        }

        @Override
        public final T b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            LootItemCondition[] alootitemcondition = (LootItemCondition[]) ChatDeserializer.a(jsonobject, "conditions", new LootItemCondition[0], jsondeserializationcontext, LootItemCondition[].class);

            return this.b(jsonobject, jsondeserializationcontext, alootitemcondition);
        }

        public abstract T b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition);
    }

    static final class b extends LootItemFunctionConditional.a<LootItemFunctionConditional.b> {

        private final Function<LootItemCondition[], LootItemFunction> a;

        public b(Function<LootItemCondition[], LootItemFunction> function) {
            this.a = function;
        }

        @Override
        protected LootItemFunctionConditional.b d() {
            return this;
        }

        @Override
        public LootItemFunction b() {
            return (LootItemFunction) this.a.apply(this.g());
        }
    }

    public abstract static class a<T extends LootItemFunctionConditional.a<T>> implements LootItemFunction.a, LootItemConditionUser<T> {

        private final List<LootItemCondition> a = Lists.newArrayList();

        public a() {}

        @Override
        public T b(LootItemCondition.a lootitemcondition_a) {
            this.a.add(lootitemcondition_a.build());
            return this.d();
        }

        @Override
        public final T c() {
            return this.d();
        }

        protected abstract T d();

        protected LootItemCondition[] g() {
            return (LootItemCondition[]) this.a.toArray(new LootItemCondition[0]);
        }
    }
}
