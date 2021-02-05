package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LootItemFunctionApplyBonus extends LootItemFunctionConditional {

    private static final Map<MinecraftKey, LootItemFunctionApplyBonus.c> a = Maps.newHashMap();
    private final Enchantment c;
    private final LootItemFunctionApplyBonus.b d;

    private LootItemFunctionApplyBonus(LootItemCondition[] alootitemcondition, Enchantment enchantment, LootItemFunctionApplyBonus.b lootitemfunctionapplybonus_b) {
        super(alootitemcondition);
        this.c = enchantment;
        this.d = lootitemfunctionapplybonus_b;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.TOOL);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        ItemStack itemstack1 = (ItemStack) loottableinfo.getContextParameter(LootContextParameters.TOOL);

        if (itemstack1 != null) {
            int i = EnchantmentManager.getEnchantmentLevel(this.c, itemstack1);
            int j = this.d.a(loottableinfo.b(), itemstack.getCount(), i);

            itemstack.setCount(j);
        }

        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> a(Enchantment enchantment, float f, int i) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionApplyBonus(alootitemcondition, enchantment, new LootItemFunctionApplyBonus.a(i, f));
        });
    }

    public static LootItemFunctionConditional.a<?> a(Enchantment enchantment) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionApplyBonus(alootitemcondition, enchantment, new LootItemFunctionApplyBonus.d());
        });
    }

    public static LootItemFunctionConditional.a<?> b(Enchantment enchantment) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionApplyBonus(alootitemcondition, enchantment, new LootItemFunctionApplyBonus.f(1));
        });
    }

    public static LootItemFunctionConditional.a<?> a(Enchantment enchantment, int i) {
        return a((alootitemcondition) -> {
            return new LootItemFunctionApplyBonus(alootitemcondition, enchantment, new LootItemFunctionApplyBonus.f(i));
        });
    }

    static {
        LootItemFunctionApplyBonus.a.put(LootItemFunctionApplyBonus.a.a, LootItemFunctionApplyBonus.a::a);
        LootItemFunctionApplyBonus.a.put(LootItemFunctionApplyBonus.d.a, LootItemFunctionApplyBonus.d::a);
        LootItemFunctionApplyBonus.a.put(LootItemFunctionApplyBonus.f.a, LootItemFunctionApplyBonus.f::a);
    }

    public static class e extends LootItemFunctionConditional.c<LootItemFunctionApplyBonus> {

        public e() {
            super(new MinecraftKey("apply_bonus"), LootItemFunctionApplyBonus.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionApplyBonus lootitemfunctionapplybonus, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionapplybonus, jsonserializationcontext);
            jsonobject.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(lootitemfunctionapplybonus.c).toString());
            jsonobject.addProperty("formula", lootitemfunctionapplybonus.d.a().toString());
            JsonObject jsonobject1 = new JsonObject();

            lootitemfunctionapplybonus.d.a(jsonobject1, jsonserializationcontext);
            if (jsonobject1.size() > 0) {
                jsonobject.add("parameters", jsonobject1);
            }

        }

        @Override
        public LootItemFunctionApplyBonus b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "enchantment"));
            Enchantment enchantment = (Enchantment) IRegistry.ENCHANTMENT.getOptional(minecraftkey).orElseThrow(() -> {
                return new JsonParseException("Invalid enchantment id: " + minecraftkey);
            });
            MinecraftKey minecraftkey1 = new MinecraftKey(ChatDeserializer.h(jsonobject, "formula"));
            LootItemFunctionApplyBonus.c lootitemfunctionapplybonus_c = (LootItemFunctionApplyBonus.c) LootItemFunctionApplyBonus.a.get(minecraftkey1);

            if (lootitemfunctionapplybonus_c == null) {
                throw new JsonParseException("Invalid formula id: " + minecraftkey1);
            } else {
                LootItemFunctionApplyBonus.b lootitemfunctionapplybonus_b;

                if (jsonobject.has("parameters")) {
                    lootitemfunctionapplybonus_b = lootitemfunctionapplybonus_c.deserialize(ChatDeserializer.t(jsonobject, "parameters"), jsondeserializationcontext);
                } else {
                    lootitemfunctionapplybonus_b = lootitemfunctionapplybonus_c.deserialize(new JsonObject(), jsondeserializationcontext);
                }

                return new LootItemFunctionApplyBonus(alootitemcondition, enchantment, lootitemfunctionapplybonus_b);
            }
        }
    }

    static final class d implements LootItemFunctionApplyBonus.b {

        public static final MinecraftKey a = new MinecraftKey("ore_drops");

        private d() {}

        @Override
        public int a(Random random, int i, int j) {
            if (j > 0) {
                int k = random.nextInt(j + 2) - 1;

                if (k < 0) {
                    k = 0;
                }

                return i * (k + 1);
            } else {
                return i;
            }
        }

        @Override
        public void a(JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {}

        public static LootItemFunctionApplyBonus.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            return new LootItemFunctionApplyBonus.d();
        }

        @Override
        public MinecraftKey a() {
            return LootItemFunctionApplyBonus.d.a;
        }
    }

    static final class f implements LootItemFunctionApplyBonus.b {

        public static final MinecraftKey a = new MinecraftKey("uniform_bonus_count");
        private final int b;

        public f(int i) {
            this.b = i;
        }

        @Override
        public int a(Random random, int i, int j) {
            return i + random.nextInt(this.b * j + 1);
        }

        @Override
        public void a(JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("bonusMultiplier", this.b);
        }

        public static LootItemFunctionApplyBonus.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            int i = ChatDeserializer.n(jsonobject, "bonusMultiplier");

            return new LootItemFunctionApplyBonus.f(i);
        }

        @Override
        public MinecraftKey a() {
            return LootItemFunctionApplyBonus.f.a;
        }
    }

    static final class a implements LootItemFunctionApplyBonus.b {

        public static final MinecraftKey a = new MinecraftKey("binomial_with_bonus_count");
        private final int b;
        private final float c;

        public a(int i, float f) {
            this.b = i;
            this.c = f;
        }

        @Override
        public int a(Random random, int i, int j) {
            for (int k = 0; k < j + this.b; ++k) {
                if (random.nextFloat() < this.c) {
                    ++i;
                }
            }

            return i;
        }

        @Override
        public void a(JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("extra", this.b);
            jsonobject.addProperty("probability", this.c);
        }

        public static LootItemFunctionApplyBonus.b a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            int i = ChatDeserializer.n(jsonobject, "extra");
            float f = ChatDeserializer.l(jsonobject, "probability");

            return new LootItemFunctionApplyBonus.a(i, f);
        }

        @Override
        public MinecraftKey a() {
            return LootItemFunctionApplyBonus.a.a;
        }
    }

    interface c {

        LootItemFunctionApplyBonus.b deserialize(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext);
    }

    interface b {

        int a(Random random, int i, int j);

        void a(JsonObject jsonobject, JsonSerializationContext jsonserializationcontext);

        MinecraftKey a();
    }
}
