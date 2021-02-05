package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Optional;

public class DataConverterHeightmapRenaming extends DataFix {

    public DataConverterHeightmapRenaming(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.c);
        OpticFinder<?> opticfinder = type.findField("Level");

        return this.fixTypeEverywhereTyped("HeightmapRenamingFix", type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), this::a);
            });
        });
    }

    private Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<? extends Dynamic<?>> optional = dynamic.get("Heightmaps").get();

        if (!optional.isPresent()) {
            return dynamic;
        } else {
            Dynamic<?> dynamic1 = (Dynamic) optional.get();
            Optional<? extends Dynamic<?>> optional1 = dynamic1.get("LIQUID").get();

            if (optional1.isPresent()) {
                dynamic1 = dynamic1.remove("LIQUID");
                dynamic1 = dynamic1.set("WORLD_SURFACE_WG", (Dynamic) optional1.get());
            }

            Optional<? extends Dynamic<?>> optional2 = dynamic1.get("SOLID").get();

            if (optional2.isPresent()) {
                dynamic1 = dynamic1.remove("SOLID");
                dynamic1 = dynamic1.set("OCEAN_FLOOR_WG", (Dynamic) optional2.get());
                dynamic1 = dynamic1.set("OCEAN_FLOOR", (Dynamic) optional2.get());
            }

            Optional<? extends Dynamic<?>> optional3 = dynamic1.get("LIGHT").get();

            if (optional3.isPresent()) {
                dynamic1 = dynamic1.remove("LIGHT");
                dynamic1 = dynamic1.set("LIGHT_BLOCKING", (Dynamic) optional3.get());
            }

            Optional<? extends Dynamic<?>> optional4 = dynamic1.get("RAIN").get();

            if (optional4.isPresent()) {
                dynamic1 = dynamic1.remove("RAIN");
                dynamic1 = dynamic1.set("MOTION_BLOCKING", (Dynamic) optional4.get());
                dynamic1 = dynamic1.set("MOTION_BLOCKING_NO_LEAVES", (Dynamic) optional4.get());
            }

            return dynamic.set("Heightmaps", dynamic1);
        }
    }
}
