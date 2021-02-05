package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.function.IntUnaryOperator;
import javax.annotation.Nullable;

public class LootIntegerLimit implements IntUnaryOperator {

    private final Integer a;
    private final Integer b;
    private final IntUnaryOperator c;

    private LootIntegerLimit(@Nullable Integer integer, @Nullable Integer integer1) {
        this.a = integer;
        this.b = integer1;
        int i;

        if (integer == null) {
            if (integer1 == null) {
                this.c = (j) -> {
                    return j;
                };
            } else {
                i = integer1;
                this.c = (j) -> {
                    return Math.min(i, j);
                };
            }
        } else {
            i = integer;
            if (integer1 == null) {
                this.c = (j) -> {
                    return Math.max(i, j);
                };
            } else {
                int j = integer1;

                this.c = (k) -> {
                    return MathHelper.clamp(k, i, j);
                };
            }
        }

    }

    public static LootIntegerLimit a(int i, int j) {
        return new LootIntegerLimit(i, j);
    }

    public static LootIntegerLimit a(int i) {
        return new LootIntegerLimit(i, (Integer) null);
    }

    public static LootIntegerLimit b(int i) {
        return new LootIntegerLimit((Integer) null, i);
    }

    public int applyAsInt(int i) {
        return this.c.applyAsInt(i);
    }

    public static class a implements JsonDeserializer<LootIntegerLimit>, JsonSerializer<LootIntegerLimit> {

        public a() {}

        public LootIntegerLimit deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
            JsonObject jsonobject = ChatDeserializer.m(jsonelement, "value");
            Integer integer = jsonobject.has("min") ? ChatDeserializer.n(jsonobject, "min") : null;
            Integer integer1 = jsonobject.has("max") ? ChatDeserializer.n(jsonobject, "max") : null;

            return new LootIntegerLimit(integer, integer1);
        }

        public JsonElement serialize(LootIntegerLimit lootintegerlimit, Type type, JsonSerializationContext jsonserializationcontext) {
            JsonObject jsonobject = new JsonObject();

            if (lootintegerlimit.b != null) {
                jsonobject.addProperty("max", lootintegerlimit.b);
            }

            if (lootintegerlimit.a != null) {
                jsonobject.addProperty("min", lootintegerlimit.a);
            }

            return jsonobject;
        }
    }
}
