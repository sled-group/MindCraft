package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;

public class DataConverterZombieType extends DataConverterEntityNameAbstract {

    public DataConverterZombieType(Schema schema, boolean flag) {
        super("EntityZombieSplitFix", schema, flag);
    }

    @Override
    protected Pair<String, Dynamic<?>> a(String s, Dynamic<?> dynamic) {
        if (Objects.equals("Zombie", s)) {
            String s1 = "Zombie";
            int i = dynamic.get("ZombieType").asInt(0);

            switch (i) {
                case 0:
                default:
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    s1 = "ZombieVillager";
                    dynamic = dynamic.set("Profession", dynamic.createInt(i - 1));
                    break;
                case 6:
                    s1 = "Husk";
            }

            dynamic = dynamic.remove("ZombieType");
            return Pair.of(s1, dynamic);
        } else {
            return Pair.of(s, dynamic);
        }
    }
}
