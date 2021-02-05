package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.function.BiFunction;

public class LootItemFunctions {

    private static final Map<MinecraftKey, LootItemFunction.b<?>> b = Maps.newHashMap();
    private static final Map<Class<? extends LootItemFunction>, LootItemFunction.b<?>> c = Maps.newHashMap();
    public static final BiFunction<ItemStack, LootTableInfo, ItemStack> a = (itemstack, loottableinfo) -> {
        return itemstack;
    };

    public static <T extends LootItemFunction> void a(LootItemFunction.b<? extends T> lootitemfunction_b) {
        MinecraftKey minecraftkey = lootitemfunction_b.a();
        Class<T> oclass = lootitemfunction_b.b();

        if (LootItemFunctions.b.containsKey(minecraftkey)) {
            throw new IllegalArgumentException("Can't re-register item function name " + minecraftkey);
        } else if (LootItemFunctions.c.containsKey(oclass)) {
            throw new IllegalArgumentException("Can't re-register item function class " + oclass.getName());
        } else {
            LootItemFunctions.b.put(minecraftkey, lootitemfunction_b);
            LootItemFunctions.c.put(oclass, lootitemfunction_b);
        }
    }

    public static LootItemFunction.b<?> a(MinecraftKey minecraftkey) {
        LootItemFunction.b<?> lootitemfunction_b = (LootItemFunction.b) LootItemFunctions.b.get(minecraftkey);

        if (lootitemfunction_b == null) {
            throw new IllegalArgumentException("Unknown loot item function '" + minecraftkey + "'");
        } else {
            return lootitemfunction_b;
        }
    }

    public static <T extends LootItemFunction> LootItemFunction.b<T> a(T t0) {
        LootItemFunction.b<T> lootitemfunction_b = (LootItemFunction.b) LootItemFunctions.c.get(t0.getClass());

        if (lootitemfunction_b == null) {
            throw new IllegalArgumentException("Unknown loot item function " + t0);
        } else {
            return lootitemfunction_b;
        }
    }

    public static BiFunction<ItemStack, LootTableInfo, ItemStack> a(BiFunction<ItemStack, LootTableInfo, ItemStack>[] abifunction) {
        switch (abifunction.length) {
            case 0:
                return LootItemFunctions.a;
            case 1:
                return abifunction[0];
            case 2:
                BiFunction<ItemStack, LootTableInfo, ItemStack> bifunction = abifunction[0];
                BiFunction<ItemStack, LootTableInfo, ItemStack> bifunction1 = abifunction[1];

                return (itemstack, loottableinfo) -> {
                    return (ItemStack) bifunction1.apply(bifunction.apply(itemstack, loottableinfo), loottableinfo);
                };
            default:
                return (itemstack, loottableinfo) -> {
                    BiFunction[] abifunction1 = abifunction;
                    int i = abifunction.length;

                    for (int j = 0; j < i; ++j) {
                        BiFunction<ItemStack, LootTableInfo, ItemStack> bifunction2 = abifunction1[j];

                        itemstack = (ItemStack) bifunction2.apply(itemstack, loottableinfo);
                    }

                    return itemstack;
                };
        }
    }

    static {
        a((LootItemFunction.b) (new LootItemFunctionSetCount.a()));
        a((LootItemFunction.b) (new LootEnchantLevel.b()));
        a((LootItemFunction.b) (new LootItemFunctionEnchant.b()));
        a((LootItemFunction.b) (new LootItemFunctionSetTag.a()));
        a((LootItemFunction.b) (new LootItemFunctionSmelt.a()));
        a((LootItemFunction.b) (new LootEnchantFunction.b()));
        a((LootItemFunction.b) (new LootItemFunctionSetDamage.a()));
        a((LootItemFunction.b) (new LootItemFunctionSetAttribute.d()));
        a((LootItemFunction.b) (new LootItemFunctionSetName.a()));
        a((LootItemFunction.b) (new LootItemFunctionExplorationMap.b()));
        a((LootItemFunction.b) (new LootItemFunctionSetStewEffect.b()));
        a((LootItemFunction.b) (new LootItemFunctionCopyName.b()));
        a((LootItemFunction.b) (new LootItemFunctionSetContents.b()));
        a((LootItemFunction.b) (new LootItemFunctionLimitCount.a()));
        a((LootItemFunction.b) (new LootItemFunctionApplyBonus.e()));
        a((LootItemFunction.b) (new LootItemFunctionSetTable.a()));
        a((LootItemFunction.b) (new LootItemFunctionExplosionDecay.a()));
        a((LootItemFunction.b) (new LootItemFunctionSetLore.b()));
        a((LootItemFunction.b) (new LootItemFunctionFillPlayerHead.a()));
        a((LootItemFunction.b) (new LootItemFunctionCopyNBT.e()));
    }

    public static class a implements JsonDeserializer<LootItemFunction>, JsonSerializer<LootItemFunction> {

        public a() {}

        public LootItemFunction deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "function");
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "function"));

            LootItemFunction.b lootitemfunction_b;

            try {
                lootitemfunction_b = LootItemFunctions.a(minecraftkey);
            } catch (IllegalArgumentException illegalargumentexception) {
                throw new JsonSyntaxException("Unknown function '" + minecraftkey + "'");
            }

            return lootitemfunction_b.b(jsonobject, jsondeserializationcontext);
        }

        public JsonElement serialize(LootItemFunction lootitemfunction, Type type, JsonSerializationContext jsonserializationcontext) {
            LootItemFunction.b<LootItemFunction> lootitemfunction_b = LootItemFunctions.a(lootitemfunction);
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("function", lootitemfunction_b.a().toString());
            lootitemfunction_b.a(jsonobject, lootitemfunction, jsonserializationcontext);
            return jsonobject;
        }
    }
}
