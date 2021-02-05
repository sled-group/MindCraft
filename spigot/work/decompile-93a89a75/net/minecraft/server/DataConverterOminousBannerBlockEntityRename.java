package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;

public class DataConverterOminousBannerBlockEntityRename extends DataConverterNamedEntity {

    public DataConverterOminousBannerBlockEntityRename(Schema schema, boolean flag) {
        super(schema, flag, "OminousBannerBlockEntityRenameFix", DataConverterTypes.k, "minecraft:banner");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), this::a);
    }

    private Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<String> optional = dynamic.get("CustomName").asString();

        if (optional.isPresent()) {
            String s = (String) optional.get();

            s = s.replace("\"translate\":\"block.minecraft.illager_banner\"", "\"translate\":\"block.minecraft.ominous_banner\"");
            return dynamic.set("CustomName", dynamic.createString(s));
        } else {
            return dynamic;
        }
    }
}
