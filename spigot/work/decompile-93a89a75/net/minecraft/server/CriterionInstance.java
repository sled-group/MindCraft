package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

public interface CriterionInstance {

    MinecraftKey a();

    default JsonElement b() {
        return JsonNull.INSTANCE;
    }
}
