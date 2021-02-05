package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

public class LootEntries {

    private static final Map<MinecraftKey, LootEntryAbstract.b<?>> a = Maps.newHashMap();
    private static final Map<Class<?>, LootEntryAbstract.b<?>> b = Maps.newHashMap();

    private static void a(LootEntryAbstract.b<?> lootentryabstract_b) {
        LootEntries.a.put(lootentryabstract_b.a(), lootentryabstract_b);
        LootEntries.b.put(lootentryabstract_b.b(), lootentryabstract_b);
    }

    static {
        a(LootEntryChildrenAbstract.a(new MinecraftKey("alternatives"), LootEntryAlternatives.class, LootEntryAlternatives::new));
        a(LootEntryChildrenAbstract.a(new MinecraftKey("sequence"), LootEntrySequence.class, LootEntrySequence::new));
        a(LootEntryChildrenAbstract.a(new MinecraftKey("group"), LootEntryGroup.class, LootEntryGroup::new));
        a(new LootSelectorEmpty.a());
        a(new LootItem.a());
        a(new LootSelectorLootTable.a());
        a(new LootSelectorDynamic.a());
        a(new LootSelectorTag.a());
    }

    public static class a implements JsonDeserializer<LootEntryAbstract>, JsonSerializer<LootEntryAbstract> {

        public a() {}

        public LootEntryAbstract deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "entry");
            MinecraftKey minecraftkey = new MinecraftKey(ChatDeserializer.h(jsonobject, "type"));
            LootEntryAbstract.b<?> lootentryabstract_b = (LootEntryAbstract.b) LootEntries.a.get(minecraftkey);

            if (lootentryabstract_b == null) {
                throw new JsonParseException("Unknown item type: " + minecraftkey);
            } else {
                LootItemCondition[] alootitemcondition = (LootItemCondition[]) ChatDeserializer.a(jsonobject, "conditions", new LootItemCondition[0], jsondeserializationcontext, LootItemCondition[].class);

                return lootentryabstract_b.b(jsonobject, jsondeserializationcontext, alootitemcondition);
            }
        }

        public JsonElement serialize(LootEntryAbstract lootentryabstract, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();
            LootEntryAbstract.b<LootEntryAbstract> lootentryabstract_b = a(lootentryabstract.getClass());

            jsonobject.addProperty("type", lootentryabstract_b.a().toString());
            if (!ArrayUtils.isEmpty(lootentryabstract.d)) {
                jsonobject.add("conditions", jsonserializationcontext.serialize(lootentryabstract.d));
            }

            lootentryabstract_b.a(jsonobject, lootentryabstract, jsonserializationcontext);
            return jsonobject;
        }

        private static LootEntryAbstract.b<LootEntryAbstract> a(Class<?> oclass) {
            LootEntryAbstract.b<?> lootentryabstract_b = (LootEntryAbstract.b) LootEntries.b.get(oclass);

            if (lootentryabstract_b == null) {
                throw new JsonParseException("Unknown item type: " + oclass);
            } else {
                return lootentryabstract_b;
            }
        }
    }
}
