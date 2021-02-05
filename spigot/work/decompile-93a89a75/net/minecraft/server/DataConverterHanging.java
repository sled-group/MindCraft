package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;

public class DataConverterHanging extends DataFix {

    private static final int[][] a = new int[][]{{0, 0, 1}, {-1, 0, 0}, {0, 0, -1}, {1, 0, 0}};

    public DataConverterHanging(Schema schema, boolean flag) {
        super(schema, flag);
    }

    private Dynamic<?> a(Dynamic<?> dynamic, boolean flag, boolean flag1) {
        if ((flag || flag1) && !dynamic.get("Facing").asNumber().isPresent()) {
            int i;

            if (dynamic.get("Direction").asNumber().isPresent()) {
                i = dynamic.get("Direction").asByte((byte) 0) % DataConverterHanging.a.length;
                int[] aint = DataConverterHanging.a[i];

                dynamic = dynamic.set("TileX", dynamic.createInt(dynamic.get("TileX").asInt(0) + aint[0]));
                dynamic = dynamic.set("TileY", dynamic.createInt(dynamic.get("TileY").asInt(0) + aint[1]));
                dynamic = dynamic.set("TileZ", dynamic.createInt(dynamic.get("TileZ").asInt(0) + aint[2]));
                dynamic = dynamic.remove("Direction");
                if (flag1 && dynamic.get("ItemRotation").asNumber().isPresent()) {
                    dynamic = dynamic.set("ItemRotation", dynamic.createByte((byte) (dynamic.get("ItemRotation").asByte((byte) 0) * 2)));
                }
            } else {
                i = dynamic.get("Dir").asByte((byte) 0) % DataConverterHanging.a.length;
                dynamic = dynamic.remove("Dir");
            }

            dynamic = dynamic.set("Facing", dynamic.createByte((byte) i));
        }

        return dynamic;
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "Painting");
        OpticFinder<?> opticfinder = DSL.namedChoice("Painting", type);
        Type<?> type1 = this.getInputSchema().getChoiceType(DataConverterTypes.ENTITY, "ItemFrame");
        OpticFinder<?> opticfinder1 = DSL.namedChoice("ItemFrame", type1);
        Type<?> type2 = this.getInputSchema().getType(DataConverterTypes.ENTITY);
        TypeRewriteRule typerewriterule = this.fixTypeEverywhereTyped("EntityPaintingFix", type2, (typed) -> {
            return typed.updateTyped(opticfinder, type, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), (dynamic) -> {
                    return this.a(dynamic, true, false);
                });
            });
        });
        TypeRewriteRule typerewriterule1 = this.fixTypeEverywhereTyped("EntityItemFrameFix", type2, (typed) -> {
            return typed.updateTyped(opticfinder1, type1, (typed1) -> {
                return typed1.update(DSL.remainderFinder(), (dynamic) -> {
                    return this.a(dynamic, false, true);
                });
            });
        });

        return TypeRewriteRule.seq(typerewriterule, typerewriterule1);
    }
}
