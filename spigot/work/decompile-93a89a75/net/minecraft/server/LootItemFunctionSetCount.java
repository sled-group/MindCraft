package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

public class LootItemFunctionSetCount extends LootItemFunctionConditional {

    private final LootValue a;

    private LootItemFunctionSetCount(LootItemCondition[] alootitemcondition, LootValue lootvalue) {
        super(alootitemcondition);
        this.a = lootvalue;
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        itemstack.setCount(this.a.a(loottableinfo.b()));
        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> a(LootValue lootvalue) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionSetCount(alootitemcondition, lootvalue);
        });
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionSetCount> {

        protected a() {
            super(new MinecraftKey("set_count"), LootItemFunctionSetCount.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetCount lootitemfunctionsetcount, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetcount, jsonserializationcontext);
            jsonobject.add("count", LootValueGenerators.a(lootitemfunctionsetcount.a, jsonserializationcontext));
        }

        @Override
        public LootItemFunctionSetCount b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootValue lootvalue = LootValueGenerators.a(jsonobject.get("count"), jsondeserializationcontext);

            return new LootItemFunctionSetCount(alootitemcondition, lootvalue);
        }
    }
}
