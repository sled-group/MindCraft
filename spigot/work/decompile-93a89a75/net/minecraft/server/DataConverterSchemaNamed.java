package net.minecraft.server;

import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;

public class DataConverterSchemaNamed extends Schema {

    public DataConverterSchemaNamed(int i, Schema schema) {
        super(i, schema);
    }

    public static String a(String s) {
        MinecraftKey minecraftkey = MinecraftKey.a(s);

        return minecraftkey != null ? minecraftkey.toString() : s;
    }

    public Type<?> getChoiceType(TypeReference typereference, String s) {
        return super.getChoiceType(typereference, a(s));
    }
}
