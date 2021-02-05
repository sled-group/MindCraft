package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Set;

public class LootItemConditionTableBonus implements LootItemCondition {

    private final Enchantment a;
    private final float[] b;

    private LootItemConditionTableBonus(Enchantment enchantment, float[] afloat) {
        this.a = enchantment;
        this.b = afloat;
    }

    @Override
    public Set<LootContextParameter<?>> a() {
        return ImmutableSet.of(LootContextParameters.TOOL);
    }

    public boolean test(LootTableInfo loottableinfo) {
        ItemStack itemstack = (ItemStack) loottableinfo.getContextParameter(LootContextParameters.TOOL);
        int i = itemstack != null ? EnchantmentManager.getEnchantmentLevel(this.a, itemstack) : 0;
        float f = this.b[Math.min(i, this.b.length - 1)];

        return loottableinfo.b().nextFloat() < f;
    }

    public static LootItemCondition.a a(Enchantment enchantment, float... afloat) {
        return () -> {
            return new LootItemConditionTableBonus(enchantment, afloat);
        };
    }

    public static class a extends LootItemCondition.b<LootItemConditionTableBonus> {

        public a() {
            super(new MinecraftKey("table_bonus"), LootItemConditionTableBonus.class);
        }

        public void a(JsonObject jsonobject, LootItemConditionTableBonus lootitemconditiontablebonus, JsonSerializationContext jsonserializationcontext) {
            jsonobject.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(lootitemconditiontablebonus.a).toString());
            jsonobject.add("chances", jsonserializationcontext.serialize(lootitemconditiontablebonus.b));
        }

        @Override
        public LootItemConditionTableBonus b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "enchantment"));
            Enchantment enchantment = (Enchantment) IRegistry.ENCHANTMENT.getOptional(minecraftkey).orElseThrow(() -> {
                return new JsonParseException("Invalid enchantment id: " + minecraftkey);
            });
            float[] afloat = (float[]) ChatDeserializer.a(jsonobject, "chances", jsondeserializationcontext, float[].class);

            return new LootItemConditionTableBonus(enchantment, afloat);
        }
    }
}
