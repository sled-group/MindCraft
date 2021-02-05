package net.minecraft.server;

import com.google.common.collect.Sets;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;
import java.util.Set;

public class DataConverterHealth extends DataFix {

    private static final Set<String> a = Sets.newHashSet(new String[]{"ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie"});

    public DataConverterHealth(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public Dynamic<?> a(Dynamic<?> dynamic) {
        Optional<Number> optional = dynamic.get("HealF").asNumber();
        Optional<Number> optional1 = dynamic.get("Health").asNumber();
        float f;

        if (optional.isPresent()) {
            f = ((Number) optional.get()).floatValue();
            dynamic = dynamic.remove("HealF");
        } else {
            if (!optional1.isPresent()) {
                return dynamic;
            }

            f = ((Number) optional1.get()).floatValue();
        }

        return dynamic.set("Health", dynamic.createFloat(f));
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityHealthFix", this.getInputSchema().getType(DataConverterTypes.ENTITY), (typed) -> {
            return typed.update(DSL.remainderFinder(), this::a);
        });
    }
}
