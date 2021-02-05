package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class LootItemConditionLocationCheck implements LootItemCondition {

    private final CriterionConditionLocation a;

    private LootItemConditionLocationCheck(CriterionConditionLocation criterionconditionlocation) {
        this.a = criterionconditionlocation;
    }

    public boolean test(LootTableInfo loottableinfo) {
        BlockPosition blockposition = (BlockPosition) loottableinfo.getContextParameter(LootContextParameters.POSITION);

        return blockposition != null && this.a.a(loottableinfo.d(), (float) blockposition.getX(), (float) blockposition.getY(), (float) blockposition.getZ());
    }

    public static LootItemCondition.a a(CriterionConditionLocation.a criterionconditionlocation_a) {
        return () -> {
            return new LootItemConditionLocationCheck(criterionconditionlocation_a.a());
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionLocationCheck> {

        public a() {
            super(new MinecraftKey("location_check"), LootItemConditionLocationCheck.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionLocationCheck lootitemconditionlocationcheck, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("predicate", lootitemconditionlocationcheck.a.a());
        }

        @Override
        public LootItemConditionLocationCheck b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            CriterionConditionLocation criterionconditionlocation = CriterionConditionLocation.a(jsonobject.get("predicate"));

            return new LootItemConditionLocationCheck(criterionconditionlocation);
        }
    }
}
