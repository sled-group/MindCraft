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

public class DataConverterSpawnEgg extends DataFix {

    public static final String[] ID_MAPPING = (String[]) DataFixUtils.make(new String[256], (astring) -> {
        astring[1] = "Item";
        astring[2] = "XPOrb";
        astring[7] = "ThrownEgg";
        astring[8] = "LeashKnot";
        astring[9] = "Painting";
        astring[10] = "Arrow";
        astring[11] = "Snowball";
        astring[12] = "Fireball";
        astring[13] = "SmallFireball";
        astring[14] = "ThrownEnderpearl";
        astring[15] = "EyeOfEnderSignal";
        astring[16] = "ThrownPotion";
        astring[17] = "ThrownExpBottle";
        astring[18] = "ItemFrame";
        astring[19] = "WitherSkull";
        astring[20] = "PrimedTnt";
        astring[21] = "FallingSand";
        astring[22] = "FireworksRocketEntity";
        astring[23] = "TippedArrow";
        astring[24] = "SpectralArrow";
        astring[25] = "ShulkerBullet";
        astring[26] = "DragonFireball";
        astring[30] = "ArmorStand";
        astring[41] = "Boat";
        astring[42] = "MinecartRideable";
        astring[43] = "MinecartChest";
        astring[44] = "MinecartFurnace";
        astring[45] = "MinecartTNT";
        astring[46] = "MinecartHopper";
        astring[47] = "MinecartSpawner";
        astring[40] = "MinecartCommandBlock";
        astring[48] = "Mob";
        astring[49] = "Monster";
        astring[50] = "Creeper";
        astring[51] = "Skeleton";
        astring[52] = "Spider";
        astring[53] = "Giant";
        astring[54] = "Zombie";
        astring[55] = "Slime";
        astring[56] = "Ghast";
        astring[57] = "PigZombie";
        astring[58] = "Enderman";
        astring[59] = "CaveSpider";
        astring[60] = "Silverfish";
        astring[61] = "Blaze";
        astring[62] = "LavaSlime";
        astring[63] = "EnderDragon";
        astring[64] = "WitherBoss";
        astring[65] = "Bat";
        astring[66] = "Witch";
        astring[67] = "Endermite";
        astring[68] = "Guardian";
        astring[69] = "Shulker";
        astring[90] = "Pig";
        astring[91] = "Sheep";
        astring[92] = "Cow";
        astring[93] = "Chicken";
        astring[94] = "Squid";
        astring[95] = "Wolf";
        astring[96] = "MushroomCow";
        astring[97] = "SnowMan";
        astring[98] = "Ozelot";
        astring[99] = "VillagerGolem";
        astring[100] = "EntityHorse";
        astring[101] = "Rabbit";
        astring[120] = "Villager";
        astring[200] = "EnderCrystal";
    });

    public DataConverterSpawnEgg(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        Type<?> type = schema.getType(DataConverterTypes.ITEM_STACK);
        OpticFinder<Pair<String, String>> opticfinder = DSL.fieldFinder("id", DSL.named(DataConverterTypes.r.typeName(), DSL.namespacedString()));
        OpticFinder<String> opticfinder1 = DSL.fieldFinder("id", DSL.string());
        OpticFinder<?> opticfinder2 = type.findField("tag");
        OpticFinder<?> opticfinder3 = opticfinder2.type().findField("EntityTag");
        OpticFinder<?> opticfinder4 = DSL.typeFinder(schema.getTypeRaw(DataConverterTypes.ENTITY));

        return this.fixTypeEverywhereTyped("ItemSpawnEggFix", type, (typed) -> {
            Optional<Pair<String, String>> optional = typed.getOptional(opticfinder);

            if (optional.isPresent() && Objects.equals(((Pair) optional.get()).getSecond(), "minecraft:spawn_egg")) {
                Dynamic<?> dynamic = (Dynamic) typed.get(DSL.remainderFinder());
                short short0 = dynamic.get("Damage").asShort((short) 0);
                Optional<? extends Typed<?>> optional1 = typed.getOptionalTyped(opticfinder2);
                Optional<? extends Typed<?>> optional2 = optional1.flatMap((typed1) -> {
                    return typed1.getOptionalTyped(opticfinder3);
                });
                Optional<? extends Typed<?>> optional3 = optional2.flatMap((typed1) -> {
                    return typed1.getOptionalTyped(opticfinder4);
                });
                Optional<String> optional4 = optional3.flatMap((typed1) -> {
                    return typed1.getOptional(opticfinder1);
                });
                Typed<?> typed1 = typed;
                String s = DataConverterSpawnEgg.ID_MAPPING[short0 & 255];

                if (s != null && (!optional4.isPresent() || !Objects.equals(optional4.get(), s))) {
                    Typed<?> typed2 = typed.getOrCreateTyped(opticfinder2);
                    Typed<?> typed3 = typed2.getOrCreateTyped(opticfinder3);
                    Typed<?> typed4 = typed3.getOrCreateTyped(opticfinder4);
                    Dynamic<?> dynamic1 = typed4.write().set("id", dynamic.createString(s));
                    Typed<?> typed5 = (Typed) ((Optional) this.getOutputSchema().getTypeRaw(DataConverterTypes.ENTITY).readTyped(dynamic1).getSecond()).orElseThrow(() -> {
                        return new IllegalStateException("Could not parse new entity");
                    });

                    typed1 = typed.set(opticfinder2, typed2.set(opticfinder3, typed3.set(opticfinder4, typed5)));
                }

                if (short0 != 0) {
                    dynamic = dynamic.set("Damage", dynamic.createShort((short) 0));
                    typed1 = typed1.set(DSL.remainderFinder(), dynamic);
                }

                return typed1;
            } else {
                return typed;
            }
        });
    }
}
