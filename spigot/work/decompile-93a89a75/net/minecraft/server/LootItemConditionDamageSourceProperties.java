package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;

public class LootItemConditionDamageSourceProperties implements LootItemCondition {

    private final CriterionConditionDamageSource a;

    private LootItemConditionDamageSourceProperties(CriterionConditionDamageSource criterionconditiondamagesource) {
        this.a = criterionconditiondamagesource;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.POSITION, LootContextParameters.DAMAGE_SOURCE);
    }

    public boolean test(LootTableInfo loottableinfo) {
        DamageSource damagesource = (DamageSource) loottableinfo.getContextParameter(LootContextParameters.DAMAGE_SOURCE);
        BlockPosition blockposition = (BlockPosition) loottableinfo.getContextParameter(LootContextParameters.POSITION);

        return blockposition != null && damagesource != null && this.a.a(loottableinfo.d(), new Vec3D(blockposition), damagesource);
    }

    public static LootItemCondition.a a(CriterionConditionDamageSource.a criterionconditiondamagesource_a) {
        return () -> {
            return new LootItemConditionDamageSourceProperties(criterionconditiondamagesource_a.b());
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionDamageSourceProperties> {

        protected a() {
            super(new MinecraftKey("damage_source_properties"), LootItemConditionDamageSourceProperties.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionDamageSourceProperties lootitemconditiondamagesourceproperties, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("predicate", lootitemconditiondamagesourceproperties.a.a());
        }

        @Override
        public LootItemConditionDamageSourceProperties b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            CriterionConditionDamageSource criterionconditiondamagesource = CriterionConditionDamageSource.a(jsonobject.get("predicate"));

            return new LootItemConditionDamageSourceProperties(criterionconditiondamagesource);
        }
    }
}
