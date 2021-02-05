package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;

public class DataConverterSwimStats extends DataFix {

    public DataConverterSwimStats(Schema schema, boolean flag) {
        super(schema, flag);
    }

    protected TypeRewriteRule makeRule() {
        Type<?> type = this.getOutputSchema().getType(DataConverterTypes.g);
        Type<?> type1 = this.getInputSchema().getType(DataConverterTypes.g);
        OpticFinder<?> opticfinder = type1.findField("stats");
        OpticFinder<?> opticfinder1 = opticfinder.type().findField("minecraft:custom");
        OpticFinder<String> opticfinder2 = DSL.namespacedString().finder();

        return this.fixTypeEverywhereTyped("SwimStatsRenameFix", type1, type, (typed) -> {
            return typed.updateTyped(opticfinder, (typed1) -> {
                return typed1.updateTyped(opticfinder1, (typed2) -> {
                    return typed2.update(opticfinder2, (s) -> {
                        return s.equals("minecraft:swim_one_cm") ? "minecraft:walk_on_water_one_cm" : (s.equals("minecraft:dive_one_cm") ? "minecraft:walk_under_water_one_cm" : s);
                    });
                });
            });
        });
    }
}
