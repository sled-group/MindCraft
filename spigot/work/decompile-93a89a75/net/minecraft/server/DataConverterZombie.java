package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Random;

public class DataConverterZombie extends DataConverterNamedEntity {

    private static final Random a = new Random();

    public DataConverterZombie(Schema schema, boolean flag) {
        super(schema, flag, "EntityZombieVillagerTypeFix", DataConverterTypes.ENTITY, "Zombie");
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        if (dynamic.get("IsVillager").asBoolean(false)) {
            if (!dynamic.get("ZombieType").get().isPresent()) {
                int i = this.a(dynamic.get("VillagerProfession").asInt(-1));

                if (i == -1) {
                    i = this.a(DataConverterZombie.a.nextInt(6));
                }

                dynamic = dynamic.set("ZombieType", dynamic.createInt(i));
            }

            dynamic = dynamic.remove("IsVillager");
        }

        return dynamic;
    }

    private int a(int i) {
        return i >= 0 && i < 6 ? i : -1;
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), this::a);
    }
}
