package net.minecraft.server;

import com.google.gson.JsonObject;

public interface ResourcePackMetaParser<T> {

    String a();

    T a(JsonObject jsonobject);
}
