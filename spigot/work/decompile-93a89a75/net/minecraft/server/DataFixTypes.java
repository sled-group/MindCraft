package net.minecraft.server;

import com.mojang.datafixers.DSL.TypeReference;

public enum DataFixTypes {

    LEVEL(DataConverterTypes.a), PLAYER(DataConverterTypes.PLAYER), CHUNK(DataConverterTypes.c), HOTBAR(DataConverterTypes.d), OPTIONS(DataConverterTypes.e), STRUCTURE(DataConverterTypes.f), STATS(DataConverterTypes.g), SAVED_DATA(DataConverterTypes.h), ADVANCEMENTS(DataConverterTypes.i), POI_CHUNK(DataConverterTypes.j);

    private final TypeReference k;

    private DataFixTypes(TypeReference typereference) {
        this.k = typereference;
    }

    public TypeReference a() {
        return this.k;
    }
}
