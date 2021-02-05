package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;

public final class LootValueBinomial implements LootValue {

    private final int d;
    private final float e;

    public LootValueBinomial(int i, float f) {
        this.d = i;
        this.e = f;
    }

    @Override
    public int a(Random random) {
        int i = 0;

        for (int j = 0; j < this.d; ++j) {
            if (random.nextFloat() < this.e) {
                ++i;
            }
        }

        return i;
    }

    public static LootValueBinomial a(int i, float f) {
        return new LootValueBinomial(i, f);
    }

    @Override
    public MinecraftKey a() {
        return LootValueBinomial.c;
    }

    public static class a implements JsonDeserializer<LootValueBinomial>, JsonSerializer<LootValueBinomial> {

        public a() {}

        public LootValueBinomial deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "value");
            int i = ChatDeserializer.n(jsonobject, "n");
            float f = ChatDeserializer.l(jsonobject, "p");

            return new LootValueBinomial(i, f);
        }

        public JsonElement serialize(LootValueBinomial lootvaluebinomial, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            jsonobject.addProperty("n", lootvaluebinomial.d);
            jsonobject.addProperty("p", lootvaluebinomial.e);
            return jsonobject;
        }
    }
}
