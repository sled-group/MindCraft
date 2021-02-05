package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import java.util.Map;

public class LootValueGenerators {

    private static final Map<MinecraftKey, Class<? extends LootValue>> a = Maps.newHashMap();

    public static LootValue a(JsonElement jsonelement, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
        if (jsonelement.isJsonPrimitive()) {
            return (LootValue) jsondeserializationcontext.deserialize(jsonelement, LootValueConstant.class);
        } else {
            JsonObject jsonobject = jsonelement.getAsJsonObject();
            String s = ChatDeserializer.a(jsonobject, "type", LootValue.b.toString());
            Class<? extends LootValue> oclass = (Class) LootValueGenerators.a.get(new MinecraftKey(s));

            if (oclass == null) {
                throw new JsonParseException("Unknown generator: " + s);
            } else {
                return (LootValue) jsondeserializationcontext.deserialize(jsonobject, oclass);
            }
        }
    }

    public static JsonElement a(LootValue lootvalue, JsonSerializationContext jsonserializationcontext) {
        JsonElement jsonelement = jsonserializationcontext.serialize(lootvalue);

        if (jsonelement.isJsonObject()) {
            jsonelement.getAsJsonObject().addProperty("type", lootvalue.a().toString());
        }

        return jsonelement;
    }

    static {
        LootValueGenerators.a.put(LootValue.b, LootValueBounds.class);
        LootValueGenerators.a.put(LootValue.c, LootValueBinomial.class);
        LootValueGenerators.a.put(LootValue.a, LootValueConstant.class);
    }
}
