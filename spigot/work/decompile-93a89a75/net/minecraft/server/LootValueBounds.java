package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Random;

public class LootValueBounds implements LootValue {

    private final float d;
    private final float e;

    public LootValueBounds(float f, float f1) {
        this.d = f;
        this.e = f1;
    }

    public LootValueBounds(float f) {
        this.d = f;
        this.e = f;
    }

    public static LootValueBounds a(float f, float f1) {
        return new LootValueBounds(f, f1);
    }

    public float b() {
        return this.d;
    }

    public float c() {
        return this.e;
    }

    @Override
    public int a(Random random) {
        return MathHelper.nextInt(random, MathHelper.d(this.d), MathHelper.d(this.e));
    }

    public float b(Random random) {
        return MathHelper.a(random, this.d, this.e);
    }

    public boolean a(int i) {
        return (float) i <= this.e && (float) i >= this.d;
    }

    @Override
    public MinecraftKey a() {
        return LootValueBounds.b;
    }

    public static class a implements JsonDeserializer<LootValueBounds>, JsonSerializer<LootValueBounds> {

        public a() {}

        public LootValueBounds deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            if (ChatDeserializer.b(jsonelement)) {
                return new LootValueBounds(ChatDeserializer.e(jsonelement, "value"));
            } else {
                JsonObject jsonobject = ChatDeserializer.m(jsonelement, "value");
                float f = ChatDeserializer.l(jsonobject, "min");
                float f1 = ChatDeserializer.l(jsonobject, "max");

                return new LootValueBounds(f, f1);
            }
        }

        public JsonElement serialize(LootValueBounds lootvaluebounds, Type type, JsonSerializationContext jsonserializationcontext) {
            if (lootvaluebounds.d == lootvaluebounds.e) {
                return new JsonPrimitive(lootvaluebounds.d);
            } else {
                JsonObject jsonobject = new JsonObject();

                jsonobject.addProperty("min", lootvaluebounds.d);
                jsonobject.addProperty("max", lootvaluebounds.e);
                return jsonobject;
            }
        }
    }
}
