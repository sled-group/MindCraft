package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.Optional;

public class DataConverterHorse extends DataConverterEntityName {

    public DataConverterHorse(Schema schema, boolean flag) {
        super("EntityHorseSplitFix", schema, flag);
    }

    @Override
    protected Pair<String, Typed<?>> a(String s, Typed<?> typed) {
        Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());

        if (Objects.equals("EntityHorse", s)) {
            int i = dynamic.get("Type").asInt(0);
            String s1;

            switch (i) {
                case 0:
                default:
                    s1 = "Horse";
                    break;
                case 1:
                    s1 = "Donkey";
                    break;
                case 2:
                    s1 = "Mule";
                    break;
                case 3:
                    s1 = "ZombieHorse";
                    break;
                case 4:
                    s1 = "SkeletonHorse";
            }

            dynamic.remove("Type");
            Type<?> type = (Type) this.getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(s1);

            return Pair.of(s1, ((Optional) type.readTyped(typed.write()).getSecond()).orElseThrow(() -> {
                return new IllegalStateException("Could not parse the new horse");
            }));
        } else {
            return Pair.of(s, typed);
        }
    }
}
