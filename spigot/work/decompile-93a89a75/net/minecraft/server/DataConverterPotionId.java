package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.Optional;

public class DataConverterPotionId extends DataFix {

    private static final String[] a = (String[]) DataFixUtils.make(new String[128], (astring) -> {
        astring[0] = "minecraft:water";
        astring[1] = "minecraft:regeneration";
        astring[2] = "minecraft:swiftness";
        astring[3] = "minecraft:fire_resistance";
        astring[4] = "minecraft:poison";
        astring[5] = "minecraft:healing";
        astring[6] = "minecraft:night_vision";
        astring[7] = null;
        astring[8] = "minecraft:weakness";
        astring[9] = "minecraft:strength";
        astring[10] = "minecraft:slowness";
        astring[11] = "minecraft:leaping";
        astring[12] = "minecraft:harming";
        astring[13] = "minecraft:water_breathing";
        astring[14] = "minecraft:invisibility";
        astring[15] = null;
        astring[16] = "minecraft:awkward";
        astring[17] = "minecraft:regeneration";
        astring[18] = "minecraft:swiftness";
        astring[19] = "minecraft:fire_resistance";
        astring[20] = "minecraft:poison";
        astring[21] = "minecraft:healing";
        astring[22] = "minecraft:night_vision";
        astring[23] = null;
        astring[24] = "minecraft:weakness";
        astring[25] = "minecraft:strength";
        astring[26] = "minecraft:slowness";
        astring[27] = "minecraft:leaping";
        astring[28] = "minecraft:harming";
        astring[29] = "minecraft:water_breathing";
        astring[30] = "minecraft:invisibility";
        astring[31] = null;
        astring[32] = "minecraft:thick";
        astring[33] = "minecraft:strong_regeneration";
        astring[34] = "minecraft:strong_swiftness";
        astring[35] = "minecraft:fire_resistance";
        astring[36] = "minecraft:strong_poison";
        astring[37] = "minecraft:strong_healing";
        astring[38] = "minecraft:night_vision";
        astring[39] = null;
        astring[40] = "minecraft:weakness";
        astring[41] = "minecraft:strong_strength";
        astring[42] = "minecraft:slowness";
        astring[43] = "minecraft:strong_leaping";
        astring[44] = "minecraft:strong_harming";
        astring[45] = "minecraft:water_breathing";
        astring[46] = "minecraft:invisibility";
        astring[47] = null;
        astring[48] = null;
        astring[49] = "minecraft:strong_regeneration";
        astring[50] = "minecraft:strong_swiftness";
        astring[51] = "minecraft:fire_resistance";
        astring[52] = "minecraft:strong_poison";
        astring[53] = "minecraft:strong_healing";
        astring[54] = "minecraft:night_vision";
        astring[55] = null;
        astring[56] = "minecraft:weakness";
        astring[57] = "minecraft:strong_strength";
        astring[58] = "minecraft:slowness";
        astring[59] = "minecraft:strong_leaping";
        astring[60] = "minecraft:strong_harming";
        astring[61] = "minecraft:water_breathing";
        astring[62] = "minecraft:invisibility";
        astring[63] = null;
        astring[64] = "minecraft:mundane";
        astring[65] = "minecraft:long_regeneration";
        astring[66] = "minecraft:long_swiftness";
        astring[67] = "minecraft:long_fire_resistance";
        astring[68] = "minecraft:long_poison";
        astring[69] = "minecraft:healing";
        astring[70] = "minecraft:long_night_vision";
        astring[71] = null;
        astring[72] = "minecraft:long_weakness";
        astring[73] = "minecraft:long_strength";
        astring[74] = "minecraft:long_slowness";
        astring[75] = "minecraft:long_leaping";
        astring[76] = "minecraft:harming";
        astring[77] = "minecraft:long_water_breathing";
        astring[78] = "minecraft:long_invisibility";
        astring[79] = null;
        astring[80] = "minecraft:awkward";
        astring[81] = "minecraft:long_regeneration";
        astring[82] = "minecraft:long_swiftness";
        astring[83] = "minecraft:long_fire_resistance";
        astring[84] = "minecraft:long_poison";
        astring[85] = "minecraft:healing";
        astring[86] = "minecraft:long_night_vision";
        astring[87] = null;
        astring[88] = "minecraft:long_weakness";
        astring[89] = "minecraft:long_strength";
        astring[90] = "minecraft:long_slowness";
        astring[91] = "minecraft:long_leaping";
        astring[92] = "minecraft:harming";
        astring[93] = "minecraft:long_water_breathing";
        astring[94] = "minecraft:long_invisibility";
        astring[95] = null;
        astring[96] = "minecraft:thick";
        astring[97] = "minecraft:regeneration";
        astring[98] = "minecraft:swiftness";
        astring[99] = "minecraft:long_fire_resistance";
        astring[100] = "minecraft:poison";
        astring[101] = "minecraft:strong_healing";
        astring[102] = "minecraft:long_night_vision";
        astring[103] = null;
        astring[104] = "minecraft:long_weakness";
        astring[105] = "minecraft:strength";
        astring[106] = "minecraft:long_slowness";
        astring[107] = "minecraft:leaping";
        astring[108] = "minecraft:strong_harming";
        astring[109] = "minecraft:long_water_breathing";
        astring[110] = "minecraft:long_invisibility";
        astring[111] = null;
        astring[112] = null;
        astring[113] = "minecraft:regeneration";
        astring[114] = "minecraft:swiftness";
        astring[115] = "minecraft:long_fire_resistance";
        astring[116] = "minecraft:poison";
        astring[117] = "minecraft:strong_healing";
        astring[118] = "minecraft:long_night_vision";
        astring[119] = null;
        astring[120] = "minecraft:long_weakness";
        astring[121] = "minecraft:strength";
        astring[122] = "minecraft:long_slowness";
        astring[123] = "minecraft:leaping";
        astring[124] = "minecraft:strong_harming";
        astring[125] = "minecraft:long_water_breathing";
        astring[126] = "minecraft:long_invisibility";
        astring[127] = null;
    });

    public DataConverterPotionId(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
        OpticFinder<?> opticfinder1 = type.findField("tag");

        return this.fixTypeEverywhereTyped("ItemPotionFix", type, (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent() && Objects.equals(((Pair) optional.get()).getSecond(), "minecraft:potion")) {
                Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());
                Optional<? extends Typed<?>> optional1 = typed.getOptionalTyped(opticfinder1);
                short short0 = dynamic.get("Damage").asShort((short) 0);

                if (optional1.isPresent()) {
                    Typed<?> typed1 = typed;
                    Dynamic<?> dynamic1 = (Dynamic) ((Typed) optional1.get()).get(DSL.remainderFinder());
                    Optional<String> optional2 = dynamic1.get("Potion").asString();

                    if (!optional2.isPresent()) {
                        String s = DataConverterPotionId.a[short0 & 127];
                        Typed<?> typed2 = ((Typed) optional1.get()).set(DSL.remainderFinder(), dynamic1.set("Potion", dynamic1.createString(s == null ? "minecraft:water" : s)));

                        typed1 = typed.set(opticfinder1, typed2);
                        if ((short0 & 16384) == 16384) {
                            typed1 = typed1.set(opticfinder, Pair.of(DataConverterTypes.r.typeName(), "minecraft:splash_potion"));
                        }
                    }

                    if (short0 != 0) {
                        dynamic = dynamic.set("Damage", dynamic.createShort((short) 0));
                    }

                    return typed1.set(DSL.remainderFinder(), dynamic);
                }
            }

            return typed;
        });
    }
}
