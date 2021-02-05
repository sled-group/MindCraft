package net.minecraft.server;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;

public class DataConverterZombieVillagerLevelXp extends DataConverterNamedEntity {

    public DataConverterZombieVillagerLevelXp(Schema schema, boolean flag) {
        super(schema, flag, "Zombie Villager XP rebuild", DataConverterTypes.ENTITY, "minecraft:zombie_villager");
    }

    @Override
    protected Typed<?> a(Typed<?> typed) {
        return typed.update(DSL.remainderFinder(), (dynamic) -> {
            Optional<Number> optional = dynamic.get("Xp").asNumber();

            if (!optional.isPresent()) {
                int i = ((Number) dynamic.get("VillagerData").get("level").asNumber().orElse(1)).intValue();

                return dynamic.set("Xp", dynamic.createInt(DataConverterVillagerLevelXp.a(i)));
            } else {
                return dynamic;
            }
        });
    }
}
