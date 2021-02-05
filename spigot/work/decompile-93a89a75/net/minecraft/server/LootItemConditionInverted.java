package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;
import java.util.function.Function;

public class LootItemConditionInverted implements LootItemCondition {

    private final LootItemCondition a;

    private LootItemConditionInverted(LootItemCondition lootitemcondition) {
        this.a = lootitemcondition;
    }

    public final boolean test(LootTableInfo loottableinfo) {
        return !this.a.test(loottableinfo);
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return this.a.a();
    }

    @Override
    public void a(LootCollector lootcollector, Function<MinecraftKey, LootTable> function, Set<MinecraftKey> set, LootContextParameterSet lootcontextparameterset) {
        LootItemCondition.super.a(lootcollector, function, set, lootcontextparameterset);
        this.a.a(lootcollector, function, set, lootcontextparameterset);
    }

    public static LootItemCondition.a a(LootItemCondition.a lootitemcondition_a) {
        LootItemConditionInverted lootitemconditioninverted = new LootItemConditionInverted(lootitemcondition_a.build());

        return () -> {
            return lootitemconditioninverted;
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionInverted> {

        public a() {
            super(new MinecraftKey("inverted"), LootItemConditionInverted.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionInverted lootitemconditioninverted, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("term", jsonserializationcontext.serialize(lootitemconditioninverted.a));
        }

        @Override
        public LootItemConditionInverted b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            LootItemCondition lootitemcondition = (LootItemCondition) ChatDeserializer.a(jsonobject, "term", jsondeserializationcontext, LootItemCondition.class);

            return new LootItemConditionInverted(lootitemcondition);
        }
    }
}
