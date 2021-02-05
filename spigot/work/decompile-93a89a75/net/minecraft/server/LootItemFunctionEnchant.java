package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LootItemFunctionEnchant extends LootItemFunctionConditional {

    private static final Logger LOGGER = LogManager.getLogger();
    private final List<Enchantment> c;

    private LootItemFunctionEnchant(LootItemCondition[] alootitemcondition, Collection<Enchantment> collection) {
        super(alootitemcondition);
        this.c = ImmutableList.copyOf(collection);
    }

    @Override
    public ItemStack a(ItemStack itemstack, LootTableInfo loottableinfo) {
        Random random = loottableinfo.b();
        Enchantment enchantment;

        if (this.c.isEmpty()) {
            List<Enchantment> list = Lists.newArrayList();
            Iterator iterator = IRegistry.ENCHANTMENT.iterator();

            while (iterator.hasNext()) {
                Enchantment enchantment1 = (Enchantment) iterator.next();

                if (itemstack.getItem() == Items.BOOK || enchantment1.canEnchant(itemstack)) {
                    list.add(enchantment1);
                }
            }

            if (list.isEmpty()) {
                LootItemFunctionEnchant.LOGGER.warn("Couldn't find a compatible enchantment for {}", itemstack);
                return itemstack;
            }

            enchantment = (Enchantment) list.get(random.nextInt(list.size()));
        } else {
            enchantment = (Enchantment) this.c.get(random.nextInt(this.c.size()));
        }

        int i = MathHelper.nextInt(random, enchantment.getStartLevel(), enchantment.getMaxLevel());

        if (itemstack.getItem() == Items.BOOK) {
            itemstack = new ItemStack(Items.ENCHANTED_BOOK);
            ItemEnchantedBook.a(itemstack, new WeightedRandomEnchant(enchantment, i));
        } else {
            itemstack.addEnchantment(enchantment, i);
        }

        return itemstack;
    }

    public static LootItemFunctionConditional.a<?> c() {
        return a((alootitemcondition) -> {
            return new LootItemFunctionEnchant(alootitemcondition, ImmutableList.of());
        });
    }

    public static class b extends LootItemFunctionConditional.c<LootItemFunctionEnchant> {

        public b() {
            super(new MinecraftKey("enchant_randomly"), LootItemFunctionEnchant.class);
        }

        public void a(JsonObject jsonobject, LootItemFunctionEnchant lootitemfunctionenchant, JsonSerializationContext jsonserializationcontext) {
            super.a(jsonobject, (LootItemFunctionConditional) lootitemfunctionenchant, jsonserializationcontext);
            if (!lootitemfunctionenchant.c.isEmpty()) {
                JsonArray jsonarray = new JsonArray();
                Iterator iterator = lootitemfunctionenchant.c.iterator();

                while (iterator.hasNext()) {
                    Enchantment enchantment = (Enchantment) iterator.next();
                    MinecraftKey minecraftkey = IRegistry.ENCHANTMENT.getKey(enchantment);

                    if (minecraftkey == null) {
                        throw new IllegalArgumentException("Don't know how to serialize enchantment " + enchantment);
                    }

                    jsonarray.add(new JsonPrimitive(minecraftkey.toString()));
                }

                jsonobject.add("enchantments", jsonarray);
            }

        }

        @Override
        public LootItemFunctionEnchant b(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
            List<Enchantment> list = Lists.newArrayList();

            if (jsonobject.has("enchantments")) {
                JsonArray jsonarray = ChatDeserializer.u(jsonobject, "enchantments");
                Iterator iterator = jsonarray.iterator();

                while (iterator.hasNext()) {
                    JsonElement jsonelement = (JsonElement) iterator.next();
                    String s = ChatDeserializer.a(jsonelement, "enchantment");
                    Enchantment enchantment = (Enchantment) IRegistry.ENCHANTMENT.getOptional(new MinecraftKey(s)).orElseThrow(() -> {
                        return new JsonSyntaxException("Unknown enchantment '" + s + "'");
                    });

                    list.add(enchantment);
                }
            }

            return new LootItemFunctionEnchant(alootitemcondition, list);
        }
    }
}
