package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Set;

public class LootItemConditionMatchTool implements LootItemCondition {

    private final CriterionConditionItem a;

    public LootItemConditionMatchTool(CriterionConditionItem criterionconditionitem) {
        this.a = criterionconditionitem;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.TOOL);
    }

    public boolean test(LootTableInfo loottableinfo) {
        ItemStack itemstack = (ItemStack) loottableinfo.getContextParameter(LootContextParameters.TOOL);

        return itemstack != null && this.a.a(itemstack);
    }

    public static LootItemCondition.a a(CriterionConditionItem.a criterionconditionitem_a) {
        return () -> {
            return new LootItemConditionMatchTool(criterionconditionitem_a.b());
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionMatchTool> {

        protected a() {
            super(new MinecraftKey("match_tool"), LootItemConditionMatchTool.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionMatchTool lootitemconditionmatchtool, JsonSerializationContext jsonserializationcontext) {
            jsonobject.add("predicate", lootitemconditionmatchtool.a.a());
        }

        @Override
        public LootItemConditionMatchTool b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            CriterionConditionItem criterionconditionitem = CriterionConditionItem.a(jsonobject.get("predicate"));

            return new LootItemConditionMatchTool(criterionconditionitem);
        }
    }
}
