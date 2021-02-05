package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;

public final class LootValueConstant implements LootValue {

    private final int d;

    public LootValueConstant(int i) {
        this.d = i;
    }

    @Override
    public int a(Random random) {
        return this.d;
    }

    @Override
    public MinecraftKey a() {
        return LootValueConstant.a;
    }

    public static LootValueConstant a(int i) {
        return new LootValueConstant(i);
    }

    public static class a implements JsonDeserializer<LootValueConstant>, JsonSerializer<LootValueConstant> {

        public a() {}

        public LootValueConstant deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            return new LootValueConstant(ChatDeserializer.g(jsonelement, "value"));
        }

        public JsonElement serialize(LootValueConstant lootvalueconstant, Type type, JsonSerializationContext jsonserializationcontext) {
            return new JsonPrimitive(lootvalueconstant.d);
        }
    }
}
