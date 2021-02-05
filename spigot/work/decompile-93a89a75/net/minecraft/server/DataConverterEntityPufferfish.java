package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.schemas.Schema;
import java.util.Map;
import java.util.Objects;

public class DataConverterEntityPufferfish extends DataConverterEntityRenameAbstract {

    public static final Map<String, String> a = ImmutableMap.builder().put("minecraft:puffer_fish_spawn_egg", "minecraft:pufferfish_spawn_egg").build();

    public DataConverterEntityPufferfish(Schema schema, boolean flag) {
        super("EntityPufferfishRenameFix", schema, flag);
    }

    @Override
    protected String a(String s) {
        return Objects.equals("minecraft:puffer_fish", s) ? "minecraft:pufferfish" : s;
    }
}
