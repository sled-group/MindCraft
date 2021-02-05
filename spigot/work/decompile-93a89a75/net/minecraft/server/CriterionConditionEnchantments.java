package net.minecraft.server;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class CriterionConditionEnchantments {

    public static final CriterionConditionEnchantments a = new CriterionConditionEnchantments();
    private final Enchantment b;
    private final CriterionConditionValue.IntegerRange c;

    public CriterionConditionEnchantments() {
        this.b = null;
        this.c = CriterionConditionValue.IntegerRange.e;
    }

    public CriterionConditionEnchantments(@Nullable Enchantment enchantment, CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange) {
        this.b = enchantment;
        this.c = criterionconditionvalue_integerrange;
    }

    public boolean a(Map<Enchantment, Integer> map) {
        if (this.b != null) {
            if (!map.containsKey(this.b)) {
                return false;
            }

            int i = (Integer) map.get(this.b);

            if (this.c != null && !this.c.d(i)) {
                return false;
            }
        } else if (this.c != null) {
            Iterator iterator = map.values().iterator();

            Integer integer;

            do {
                if (!iterator.hasNext()) {
                    return false;
                }

                integer = (Integer) iterator.next();
            } while (!this.c.d(integer));

            return true;
        }

        return true;
    }

    public JsonElement a() {
        if (this == CriterionConditionEnchantments.a) {
            return JsonNull.INSTANCE;
        } else {
            JsonObject jsonobject = new JsonObject();

            if (this.b != null) {
                jsonobject.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(this.b).toString());
            }

            jsonobject.add("levels", this.c.d());
            return jsonobject;
        }
    }

    public static CriterionConditionEnchantments a(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "enchantment");
            Enchantment enchantment = null;

            if (jsonobject.has("enchantment")) {
                MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "enchantment"));

                enchantment = (Enchantment) IRegistry.ENCHANTMENT.getOptional(minecraftkey).orElseThrow(() -> {
                    return new JsonSyntaxException("Unknown enchantment '" + minecraftkey + "'");
                });
            }

            CriterionConditionValue.IntegerRange criterionconditionvalue_integerrange = CriterionConditionValue.IntegerRange.a(jsonobject.get("levels"));

            return new CriterionConditionEnchantments(enchantment, criterionconditionvalue_integerrange);
        } else {
            return CriterionConditionEnchantments.a;
        }
    }

    public static CriterionConditionEnchantments[] b(@Nullable JsonElement jsonelement) {
        if (jsonelement != null && !jsonelement.isJsonNull()) {
            JsonArray jsonarray = ChatDeserializer.n(jsonelement, "enchantments");
            CriterionConditionEnchantments[] acriterionconditionenchantments = new CriterionConditionEnchantments[jsonarray.size()];

            for (int i = 0; i < acriterionconditionenchantments.length; ++i) {
                acriterionconditionenchantments[i] = a(jsonarray.get(i));
            }

            return acriterionconditionenchantments;
        } else {
            return new CriterionConditionEnchantments[0];
        }
    }
}
