package net.minecraft.server;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List.ListType;
import com.mojang.datafixers.util.Pair;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class DataConverterBedBlock extends DataFix {

    public DataConverterBedBlock(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getOutputSchema().getType(DataConverterTypes.c);
        Type<?> type1 = type.findFieldType("Level");
        Type<?> type2 = type1.findFieldType("TileEntities");

        if (!(type2 instanceof ListType)) {
            throw new IllegalStateException("Tile entity type is not a list type.");
        } else {
            ListType<?> listtype = (ListType) type2;

            return this.a(type1, listtype);
        }
    }

    private <TE> TypeRewriteRule a(Type<?> type, ListType<TE> listtype) {
        Type<TE> type1 = listtype.getElement();
        OpticFinder<?> opticfinder = DSL.fieldFinder("Level", type);
        OpticFinder<List<TE>> opticfinder1 = DSL.fieldFinder("TileEntities", listtype);
        boolean flag = true;

        return TypeRewriteRule.seq(this.fixTypeEverywhere("InjectBedBlockEntityType", this.getInputSchema().findChoiceType(DataConverterTypes.k), this.getOutputSchema().findChoiceType(DataConverterTypes.k), (dynamicops) -> {
            return (pair) -> {
                return pair;
            };
        }), this.fixTypeEverywhereTyped("BedBlockEntityInjecter", this.getOutputSchema().getType(DataConverterTypes.c), (typed) -> {
            Typed<?> typed1 = typed.getTyped(opticfinder);
            Dynamic<?> dynamic = (Dynamic) typed1.get(DSL.remainderFinder());
            int i = dynamic.get("xPos").asInt(0);
            int j = dynamic.get("zPos").asInt(0);
            List<TE> list = Lists.newArrayList((Iterable) typed1.getOrCreate(opticfinder1));
            List<? extends Dynamic<?>> list1 = dynamic.get("Sections").asList(Function.identity());

            for (int k = 0; k < list1.size(); ++k) {
                Dynamic<?> dynamic1 = (Dynamic) list1.get(k);
                int l = dynamic1.get("Y").asInt(0);
                Stream<Integer> stream = dynamic1.get("Blocks").asStream().map((dynamic2) -> {
                    return dynamic2.asInt(0);
                });
                int i1 = 0;

                stream.getClass();

                for (Iterator iterator = (stream::iterator).iterator(); iterator.hasNext(); ++i1) {
                    int j1 = (Integer) iterator.next();

                    if (416 == (j1 & 255) << 4) {
                        int k1 = i1 & 15;
                        int l1 = i1 >> 8 & 15;
                        int i2 = i1 >> 4 & 15;
                        Map<Dynamic<?>, Dynamic<?>> map = Maps.newHashMap();

                        map.put(dynamic1.createString("id"), dynamic1.createString("minecraft:bed"));
                        map.put(dynamic1.createString("x"), dynamic1.createInt(k1 + (i << 4)));
                        map.put(dynamic1.createString("y"), dynamic1.createInt(l1 + (l << 4)));
                        map.put(dynamic1.createString("z"), dynamic1.createInt(i2 + (j << 4)));
                        map.put(dynamic1.createString("color"), dynamic1.createShort((short) 14));
                        list.add(((Optional) type1.read(dynamic1.createMap(map)).getSecond()).orElseThrow(() -> {
                            return new IllegalStateException("Could not parse newly created bed block entity.");
                        }));
                    }
                }
            }

            if (!list.isEmpty()) {
                return typed.set(opticfinder, typed1.set(opticfinder1, list));
            } else {
                return typed;
            }
        }));
    }
}
