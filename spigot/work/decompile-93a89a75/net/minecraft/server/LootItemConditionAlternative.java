package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class LootItemConditionAlternative implements LootItemCondition {

    private final LootItemCondition[] a;
    private final Predicate<LootTableInfo> b;

    private LootItemConditionAlternative(LootItemCondition[] alootitemcondition) {
        this.a = alootitemcondition;
        this.b = LootItemConditions.b((Predicate[]) alootitemcondition);
    }

    public final boolean test(LootTableInfo loottableinfo) {
        return this.b.test(loottableinfo);
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        LootItemCondition.super.a(lootcollector, function, set, lootcontextparameterset);

        for (int i = 0; i < this.a.length; ++i) {
            this.a[i].a(lootcollector.b(".term[" + i + "]"), function, set, lootcontextparameterset);
        }

    }

    public static LootItemConditionAlternative.a a(LootItemCondition.a... alootitemcondition_a) {
        return new LootItemConditionAlternative.a(alootitemcondition_a);
    }

    public static class b extends LootItemCondition.b<LootItemConditionAlternative> {

        public b() {
            super(new MinecraftKey("alternative"), LootItemConditionAlternative.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionAlternative lootitemconditionalternative, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("terms", jsonserializationcontext.serialize(lootitemconditionalternative.a));
        }

        @Override
        public LootItemConditionAlternative b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            LootItemCondition[] alootitemcondition = (LootItemCondition[]) ChatDeserializer.a(jsonobject, "terms", jsondeserializationcontext, LootItemCondition[].class);

            return new LootItemConditionAlternative(alootitemcondition);
        }
    }

    public static class a implements LootItemCondition.a {

        private final List<LootItemCondition> a = Lists.newArrayList();

        public a(LootItemCondition.a... alootitemcondition_a) {
            LootItemCondition.a[] alootitemcondition_a1 = alootitemcondition_a;
            int i = alootitemcondition_a.length;

            for (int j = 0; j < i; ++j) {
                LootItemCondition.a lootitemcondition_a = alootitemcondition_a1[j];

                this.a.add(lootitemcondition_a.build());
            }

        }

        @Override
        public LootItemConditionAlternative.a a(LootItemCondition.a lootitemcondition_a) {
            this.a.add(lootitemcondition_a.build());
            return this;
        }

        @Override
        public LootItemCondition build() {
            return new LootItemConditionAlternative((LootItemCondition[]) this.a.toArray(new LootItemCondition[0]));
        }
    }
}
