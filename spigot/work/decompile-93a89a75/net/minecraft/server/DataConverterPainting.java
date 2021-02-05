package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class DataConverterPainting extends DataConverterNamedEntity {

    private static final Map<String, String> a = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("donkeykong", "donkey_kong");
        hashmap.put("burningskull", "burning_skull");
        hashmap.put("skullandroses", "skull_and_roses");
    });

    public DataConverterPainting(Schema schema, boolean flag) {
        super(schema, flag, "EntityPaintingMotiveFix", DataConverterTypes.ENTITY, "minecraft:painting");
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<String> optional = dynamic.get("Motive").asString();

        if (optional.isPresent()) {
            String s = ((String) optional.get()).toLowerCase(Locale.ROOT);

            return dynamic.set("Motive", dynamic.createString((new MinecraftKey((String) DataConverterPainting.a.getOrDefault(s, s))).toString()));
        } else {
            return dynamic;
        }
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), this::a);
    }
}
