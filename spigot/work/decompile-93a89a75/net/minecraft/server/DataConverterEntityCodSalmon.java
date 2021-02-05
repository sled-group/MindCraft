package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.schemas.Schema;
import java.util.Map;

public class DataConverterEntityCodSalmon extends DataConverterEntityRenameAbstract {

    public static final Map<String, String> a = ImmutableMap.builder().put("minecraft:salmon_mob", "minecraft:salmon").put("minecraft:cod_mob", "minecraft:cod").build();
    public static final Map<String, String> b = ImmutableMap.builder().put("minecraft:salmon_mob_spawn_egg", "minecraft:salmon_spawn_egg").put("minecraft:cod_mob_spawn_egg", "minecraft:cod_spawn_egg").build();

    public DataConverterEntityCodSalmon(Schema schema, boolean flag) {
        super("EntityCodSalmonFix", schema, flag);
    }

    @Override
    protected String a(String s) {
        return (String) DataConverterEntityCodSalmon.a.getOrDefault(s, s);
    }
}
