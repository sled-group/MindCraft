package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootItemFunctionSetDamage extends LootItemFunctionConditional {

    private static final Logger LOGGER = LogManager.getLogger();
    private final LootValueBounds c;

    private LootItemFunctionSetDamage(LootItemCondition[] alootitemcondition, LootValueBounds lootvaluebounds) {
        super(alootitemcondition);
        this.c = lootvaluebounds;
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        if (itemstack.e()) {
            float f = 1.0F - this.c.b(loottableinfo.b());

            itemstack.setDamage(MathHelper.d(f * (float) itemstack.h()));
        } else {
            LootItemFunctionSetDamage.LOGGER.warn("Couldn't set damage of loot item {}", itemstack);
        }

        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> a(LootValueBounds lootvaluebounds) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionSetDamage(alootitemcondition, lootvaluebounds);
        });
    }

    public static class a extends LootItemFunctionConditional.c<LootItemFunctionSetDamage> {

        protected a() {
            super(new MinecraftKey("set_damage"), LootItemFunctionSetDamage.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionSetDamage lootitemfunctionsetdamage, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionsetdamage, jsonserializationcontext);
            jsonobject.add("damage", jsonserializationcontext.serialize(lootitemfunctionsetdamage.c));
        }

        @Override
        public LootItemFunctionSetDamage b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            return new LootItemFunctionSetDamage(alootitemcondition, (LootValueBounds) ChatDeserializer.a(jsonobject, "damage", jsondeserializationcontext, LootValueBounds.class));
        }
    }
}
