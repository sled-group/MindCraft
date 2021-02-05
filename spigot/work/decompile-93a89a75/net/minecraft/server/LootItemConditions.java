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
import java.util.function.Predicate;

public class LootItemConditions {

    private static final Map<MinecraftKey, LootItemCondition.b<?>> a = Maps.newHashMap();
    private static final Map<Class<? extends LootItemCondition>, LootItemCondition.b<?>> b = Maps.newHashMap();

    public static <T extends LootItemCondition> void a(LootItemCondition.b<? extends T> lootitemcondition_b) {
        MinecraftKey minecraftkey = lootitemcondition_b.a();
        Class<T> oclass = lootitemcondition_b.b();

        if (LootItemConditions.a.containsKey(minecraftkey)) {
            throw new IllegalArgumentException("Can't re-register item condition name " + minecraftkey);
        } else if (LootItemConditions.b.containsKey(oclass)) {
            throw new IllegalArgumentException("Can't re-register item condition class " + oclass.getName());
        } else {
            LootItemConditions.a.put(minecraftkey, lootitemcondition_b);
            LootItemConditions.b.put(oclass, lootitemcondition_b);
        }
    }

    public static LootItemCondition.b<?> a(MinecraftKey minecraftkey) {
        LootItemCondition.b<?> lootitemcondition_b = (LootItemCondition.b) LootItemConditions.a.get(minecraftkey);

        if (lootitemcondition_b == null) {
            throw new IllegalArgumentException("Unknown loot item condition '" + minecraftkey + "'");
        } else {
            return lootitemcondition_b;
        }
    }

    public static <T extends LootItemCondition> LootItemCondition.b<T> a(T t0) {
        LootItemCondition.b<T> lootitemcondition_b = (LootItemCondition.b) LootItemConditions.b.get(t0.getClass());

        if (lootitemcondition_b == null) {
            throw new IllegalArgumentException("Unknown loot item condition " + t0);
        } else {
            return lootitemcondition_b;
        }
    }

    public static <T> Predicate<T> a(Predicate<T>[] apredicate) {
        switch (apredicate.length) {
            case 0:
                return (object) -> {
                    return true;
                };
            case 1:
                return apredicate[0];
            case 2:
                return apredicate[0].and(apredicate[1]);
            default:
                return (object) -> {
                    Predicate[] apredicate1 = apredicate;
                    int i = apredicate.length;

                    for (int j = 0; j < i; ++j) {
                        Predicate<T> predicate = apredicate1[j];

                        if (!predicate.test(object)) {
                            return false;
                        }
                    }

                    return true;
                };
        }
    }

    public static <T> Predicate<T> b(Predicate<T>[] apredicate) {
        switch (apredicate.length) {
            case 0:
                return (object) -> {
                    return false;
                };
            case 1:
                return apredicate[0];
            case 2:
                return apredicate[0].or(apredicate[1]);
            default:
                return (object) -> {
                    Predicate[] apredicate1 = apredicate;
                    int i = apredicate.length;

                    for (int j = 0; j < i; ++j) {
                        Predicate<T> predicate = apredicate1[j];

                        if (predicate.test(object)) {
                            return true;
                        }
                    }

                    return false;
                };
        }
    }

    static {
        a((LootItemCondition.b) (new LootItemConditionInverted.a()));
        a((LootItemCondition.b) (new LootItemConditionAlternative.b()));
        a((LootItemCondition.b) (new LootItemConditionRandomChance.a()));
        a((LootItemCondition.b) (new LootItemConditionRandomChanceWithLooting.a()));
        a((LootItemCondition.b) (new LootItemConditionEntityProperty.a()));
        a((LootItemCondition.b) (new LootItemConditionKilledByPlayer.a()));
        a((LootItemCondition.b) (new LootItemConditionEntityScore.b()));
        a((LootItemCondition.b) (new LootItemConditionBlockStateProperty.b()));
        a((LootItemCondition.b) (new LootItemConditionMatchTool.a()));
        a((LootItemCondition.b) (new LootItemConditionTableBonus.a()));
        a((LootItemCondition.b) (new LootItemConditionSurvivesExplosion.a()));
        a((LootItemCondition.b) (new LootItemConditionDamageSourceProperties.a()));
        a((LootItemCondition.b) (new LootItemConditionLocationCheck.a()));
        a((LootItemCondition.b) (new LootItemConditionWeatherCheck.b()));
    }

    public static class a implements JsonDeserializer<LootItemCondition>, JsonSerializer<LootItemCondition> {

        public a() {}

        public LootItemCondition deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "condition");
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "condition"));

            LootItemCondition.b lootitemcondition_b;

            try {
                lootitemcondition_b = LootItemConditions.a(minecraftkey);
            } catch (IllegalArgumentException illegalargumentexception) {
                throw new JsonSyntaxException("Unknown condition '" + minecraftkey + "'");
            }

            return lootitemcondition_b.b(jsonobject, jsondeserializationcontext);
        }

        public JsonElement serialize(LootItemCondition lootitemcondition, Type type, JsonSerializationContext jsonserializationcontext) {
            LootItemCondition.b<LootItemCondition> lootitemcondition_b = LootItemConditions.a(lootitemcondition);
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("condition", lootitemcondition_b.a().toString());
            lootitemcondition_b.a(jsonobject, lootitemcondition, jsonserializationcontext);
            return jsonobject;
        }
    }
}
