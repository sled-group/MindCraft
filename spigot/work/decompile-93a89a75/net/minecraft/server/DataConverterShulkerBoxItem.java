package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.Optional;

public class DataConverterShulkerBoxItem extends DataFix {

    public static final String[] a = new String[]{"minecraft:white_shulker_box", "minecraft:orange_shulker_box", "minecraft:magenta_shulker_box", "minecraft:light_blue_shulker_box", "minecraft:yellow_shulker_box", "minecraft:lime_shulker_box", "minecraft:pink_shulker_box", "minecraft:gray_shulker_box", "minecraft:silver_shulker_box", "minecraft:cyan_shulker_box", "minecraft:purple_shulker_box", "minecraft:blue_shulker_box", "minecraft:brown_shulker_box", "minecraft:green_shulker_box", "minecraft:red_shulker_box", "minecraft:black_shulker_box"};

    public DataConverterShulkerBoxItem(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
        OpticFinder<?> opticfinder1 = type.findField("tag");
        OpticFinder<?> opticfinder2 = opticfinder1.type().findField("BlockEntityTag");

        return this.fixTypeEverywhereTyped("ItemShulkerBoxColorFix", type, (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent() && Objects.equals(((Pair) optional.get()).getSecond(), "minecraft:shulker_box")) {
                Optional<? extends Typed<?>> optional1 = typed.getOptionalTyped(opticfinder1);

                if (optional1.isPresent()) {
                    Typed<?> typed1 = (Typed) optional1.get();
                    Optional<? extends Typed<?>> optional2 = typed1.getOptionalTyped(opticfinder2);

                    if (optional2.isPresent()) {
                        Typed<?> typed2 = (Typed) optional2.get();
                        Dynamic<?> dynamic = (Dynamic) typed2.get(DSL.remainderFinder());
                        int i = dynamic.get("Color").asInt(0);

                        dynamic.remove("Color");
                        return typed.set(opticfinder1, typed1.set(opticfinder2, typed2.set(DSL.remainderFinder(), dynamic))).set(opticfinder, Pair.of(DataConverterTypes.r.typeName(), DataConverterShulkerBoxItem.a[i % 16]));
                    }
                }
            }

            return typed;
        });
    }
}
