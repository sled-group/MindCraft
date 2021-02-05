package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface DynamicDeserializer<T> {

    Logger LOGGER = LogManager.getLogger();

    T deserialize(Dynamic<?> dynamic);

    static <T, V, U extends DynamicDeserializer<V>> V a(Dynamic<T> dynamic, IRegistry<U> iregistry, String s, V v0) {
        U u0 = (DynamicDeserializer) iregistry.get(new MinecraftKey(dynamic.get(s).asString("")));
        Object object;

        if (u0 != null) {
            object = u0.deserialize(dynamic);
        } else {
            DynamicDeserializer.LOGGER.error("Unknown type {}, replacing with {}", dynamic.get(s).asString(""), v0);
            object = v0;
        }

        return object;
    }
}
