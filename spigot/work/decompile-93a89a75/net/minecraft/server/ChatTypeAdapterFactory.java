package net.minecraft.server;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class ChatTypeAdapterFactory implements TypeAdapterFactory {

    public ChatTypeAdapterFactory() {}

    @Nullable
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typetoken) {
        Class<T> oclass = typetoken.getRawType();

        if (!oclass.isEnum()) {
            return null;
        } else {
            final Map<String, T> map = Maps.newHashMap();
            Object[] aobject = oclass.getEnumConstants();
            int i = aobject.length;

            for (int j = 0; j < i; ++j) {
                T t0 = aobject[j];

                map.put(this.a(t0), t0);
            }

            return new TypeAdapter<T>() {
                public void write(JsonWriter jsonwriter, T t1) throws IOException {
                    if (t1 == null) {
                        jsonwriter.nullValue();
                    } else {
                        jsonwriter.value(ChatTypeAdapterFactory.this.a(t1));
                    }

                }

                @Nullable
                public T read(JsonReader jsonreader) throws IOException {
                    if (jsonreader.peek() == JsonToken.NULL) {
                        jsonreader.nextNull();
                        return null;
                    } else {
                        return map.get(jsonreader.nextString());
                    }
                }
            };
        }
    }

    private String a(Object object) {
        return object instanceof Enum ? ((Enum) object).name().toLowerCase(Locale.ROOT) : object.toString().toLowerCase(Locale.ROOT);
    }
}
