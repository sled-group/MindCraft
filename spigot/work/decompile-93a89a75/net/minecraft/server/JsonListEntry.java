package net.minecraft.server;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public class JsonListEntry<T> {

    @Nullable
    private final T a;

    public JsonListEntry(T t0) {
        this.a = t0;
    }

    protected JsonListEntry(@Nullable T t0, JsonObject jsonobject) {
        this.a = t0;
    }

    @Nullable
    public T getKey() {
        return this.a;
    }

    boolean hasExpired() {
        return false;
    }

    protected void a(JsonObject jsonobject) {}
}
