package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Random;

public class LootEnchantLevel extends LootItemFunctionConditional {

    private final LootValue a;
    private final boolean c;

    private LootEnchantLevel(LootItemCondition[] alootitemcondition, LootValue lootvalue, boolean flag) {
        super(alootitemcondition);
        this.a = lootvalue;
        this.c = flag;
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        Random random = loottableinfo.b();

        return EnchantmentManager.a(random, itemstack, this.a.a(random), this.c);
    }

    public static LootEnchantLevel.a a(LootValue lootvalue) {
        return new LootEnchantLevel.a(lootvalue);
    }

    public static class b extends LootItemFunctionConditional.c<LootEnchantLevel> {

        public b() {
            super(new MinecraftKey("enchant_with_levels"), LootEnchantLevel.class);
        }

        public void a(JsonObject jsonobject, LootEnchantLevel lootenchantlevel, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootenchantlevel, jsonserializationcontext);
            jsonobject.add("levels", LootValueGenerators.a(lootenchantlevel.a, jsonserializationcontext));
            jsonobject.addProperty("treasure", lootenchantlevel.c);
        }

        @Override
        public LootEnchantLevel b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            LootValue lootvalue = LootValueGenerators.a(jsonobject.get("levels"), jsondeserializationcontext);
            boolean flag = ChatDeserializer.a(jsonobject, "treasure", false);

            return new LootEnchantLevel(alootitemcondition, lootvalue, flag);
        }
    }

    public static class a extends LootItemFunctionConditional.a<LootEnchantLevel.a> {

        private final LootValue a;
        private boolean b;

        public a(LootValue lootvalue) {
            this.a = lootvalue;
        }

        @Override
        protected LootEnchantLevel.a d() {
            return this;
        }

        public LootEnchantLevel.a e() {
            this.b = true;
            return this;
        }

        @Override
        public LootItemFunction b() {
            return new LootEnchantLevel(this.g(), this.a, this.b);
        }
    }
}
