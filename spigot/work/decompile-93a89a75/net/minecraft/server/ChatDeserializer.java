package net.minecraft.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class ChatDeserializer {

    private static final Gson a = (new GsonBuilder()).create();

    public static boolean a(JsonObject jsonobject, String s) {
        return !f(jsonobject, s) ? false : jsonobject.getAsJsonPrimitive(s).isString();
    }

    public static boolean b(JsonElement jsonelement) {
        return !jsonelement.isJsonPrimitive() ? false : jsonelement.getAsJsonPrimitive().isNumber();
    }

    public static boolean d(JsonObject jsonobject, String s) {
        return !g(jsonobject, s) ? false : jsonobject.get(s).isJsonArray();
    }

    public static boolean f(JsonObject jsonobject, String s) {
        return !g(jsonobject, s) ? false : jsonobject.get(s).isJsonPrimitive();
    }

    public static boolean g(JsonObject jsonobject, String s) {
        return jsonobject == null ? false : jsonobject.get(s) != null;
    }

    public static String a(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive()) {
            return jsonelement.getAsString();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a string, was " + d(jsonelement));
        }
    }

    public static String h(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return a(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a string");
        }
    }

    public static String a(JsonObject jsonobject, String s, String s1) {
        return jsonobject.has(s) ? a(jsonobject.get(s), s) : s1;
    }

    public static Item b(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive()) {
            String s1 = jsonelement.getAsString();

            return (Item) IRegistry.ITEM.getOptional(new MinecraftKey(s1)).orElseThrow(() -> {
                return new JsonSyntaxException("Expected " + s + " to be an item, was unknown string '" + s1 + "'");
            });
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be an item, was " + d(jsonelement));
        }
    }

    public static Item i(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return b(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find an item");
        }
    }

    public static boolean c(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive()) {
            return jsonelement.getAsBoolean();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Boolean, was " + d(jsonelement));
        }
    }

    public static boolean j(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return c(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a Boolean");
        }
    }

    public static boolean a(JsonObject jsonobject, String s, boolean flag) {
        return jsonobject.has(s) ? c(jsonobject.get(s), s) : flag;
    }

    public static float e(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsFloat();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Float, was " + d(jsonelement));
        }
    }

    public static float l(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return e(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a Float");
        }
    }

    public static float a(JsonObject jsonobject, String s, float f) {
        return jsonobject.has(s) ? e(jsonobject.get(s), s) : f;
    }

    public static long f(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsLong();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Long, was " + d(jsonelement));
        }
    }

    public static long a(JsonObject jsonobject, String s, long i) {
        return jsonobject.has(s) ? f(jsonobject.get(s), s) : i;
    }

    public static int g(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsInt();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Int, was " + d(jsonelement));
        }
    }

    public static int n(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return g(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a Int");
        }
    }

    public static int a(JsonObject jsonobject, String s, int i) {
        return jsonobject.has(s) ? g(jsonobject.get(s), s) : i;
    }

    public static byte h(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonPrimitive() && jsonelement.getAsJsonPrimitive().isNumber()) {
            return jsonelement.getAsByte();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a Byte, was " + d(jsonelement));
        }
    }

    public static byte a(JsonObject jsonobject, String s, byte b0) {
        return jsonobject.has(s) ? h(jsonobject.get(s), s) : b0;
    }

    public static JsonObject m(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonObject()) {
            return jsonelement.getAsJsonObject();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a JsonObject, was " + d(jsonelement));
        }
    }

    public static JsonObject t(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return m(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a JsonObject");
        }
    }

    public static JsonObject a(JsonObject jsonobject, String s, JsonObject jsonobject1) {
        return jsonobject.has(s) ? m(jsonobject.get(s), s) : jsonobject1;
    }

    public static JsonArray n(JsonElement jsonelement, String s) {
        if (jsonelement.isJsonArray()) {
            return jsonelement.getAsJsonArray();
        } else {
            throw new JsonSyntaxException("Expected " + s + " to be a JsonArray, was " + d(jsonelement));
        }
    }

    public static JsonArray u(JsonObject jsonobject, String s) {
        if (jsonobject.has(s)) {
            return n(jsonobject.get(s), s);
        } else {
            throw new JsonSyntaxException("Missing " + s + ", expected to find a JsonArray");
        }
    }

    public static JsonArray a(JsonObject jsonobject, String s, @Nullable JsonArray jsonarray) {
        return jsonobject.has(s) ? n(jsonobject.get(s), s) : jsonarray;
    }

    public static <T> T a(@Nullable JsonElement jsonelement, String s, JsonDeserializationContext jsondeserializationcontext, Class<? extends T> oclass) {
        if (jsonelement != null) {
            return jsondeserializationcontext.deserialize(jsonelement, oclass);
        } else {
            throw new JsonSyntaxException("Missing " + s);
        }
    }

    public static <T> T a(JsonObject jsonobject, String s, JsonDeserializationContext jsondeserializationcontext, Class<? extends T> oclass) {
        if (jsonobject.has(s)) {
            return a(jsonobject.get(s), s, jsondeserializationcontext, oclass);
        } else {
            throw new JsonSyntaxException("Missing " + s);
        }
    }

    public static <T> T a(JsonObject jsonobject, String s, T t0, JsonDeserializationContext jsondeserializationcontext, Class<? extends T> oclass) {
        return jsonobject.has(s) ? a(jsonobject.get(s), s, jsondeserializationcontext, oclass) : t0;
    }

    public static String d(JsonElement jsonelement) {
        String s = StringUtils.abbreviateMiddle(String.valueOf(jsonelement), "...", 10);

        if (jsonelement == null) {
            return "null (missing)";
        } else if (jsonelement.isJsonNull()) {
            return "null (json)";
        } else if (jsonelement.isJsonArray()) {
            return "an array (" + s + ")";
        } else if (jsonelement.isJsonObject()) {
            return "an object (" + s + ")";
        } else {
            if (jsonelement.isJsonPrimitive()) {
                JsonPrimitive jsonprimitive = jsonelement.getAsJsonPrimitive();

                if (jsonprimitive.isNumber()) {
                    return "a number (" + s + ")";
                }

                if (jsonprimitive.isBoolean()) {
                    return "a boolean (" + s + ")";
                }
            }

            return s;
        }
    }

    @Nullable
    public static <T> T a(Gson gson, Reader reader, Class<T> oclass, boolean flag) {
        try {
            JsonReader jsonreader = new JsonReader(reader);

            jsonreader.setLenient(flag);
            return gson.getAdapter(oclass).read(jsonreader);
        } catch (IOException ioexception) {
            throw new JsonParseException(ioexception);
        }
    }

    @Nullable
    public static <T> T a(Gson gson, Reader reader, Type type, boolean flag) {
        try {
            JsonReader jsonreader = new JsonReader(reader);

            jsonreader.setLenient(flag);
            return gson.getAdapter(TypeToken.get(type)).read(jsonreader);
        } catch (IOException ioexception) {
            throw new JsonParseException(ioexception);
        }
    }

    @Nullable
    public static <T> T a(Gson gson, String s, Class<T> oclass, boolean flag) {
        return a(gson, (Reader) (new StringReader(s)), oclass, flag);
    }

    @Nullable
    public static <T> T a(Gson gson, Reader reader, Type type) {
        return a(gson, reader, type, false);
    }

    @Nullable
    public static <T> T a(Gson gson, Reader reader, Class<T> oclass) {
        return a(gson, reader, oclass, false);
    }

    @Nullable
    public static <T> T a(Gson gson, String s, Class<T> oclass) {
        return a(gson, s, oclass, false);
    }

    public static JsonObject a(String s, boolean flag) {
        return a((Reader) (new StringReader(s)), flag);
    }

    public static JsonObject a(Reader reader, boolean flag) {
        return (JsonObject) a(ChatDeserializer.a, reader, JsonObject.class, flag);
    }

    public static JsonObject a(String s) {
        return a(s, false);
    }

    public static JsonObject a(Reader reader) {
        return a(reader, false);
    }
}
