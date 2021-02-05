package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice.TaggedChoiceType;
import com.mojang.datafixers.util.Pair;
import java.util.HashMap;
import java.util.Map;

public class DataConverterEntity extends DataFix {

    private static final Map<String, String> a = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("AreaEffectCloud", "minecraft:area_effect_cloud");
        hashmap.put("ArmorStand", "minecraft:armor_stand");
        hashmap.put("Arrow", "minecraft:arrow");
        hashmap.put("Bat", "minecraft:bat");
        hashmap.put("Blaze", "minecraft:blaze");
        hashmap.put("Boat", "minecraft:boat");
        hashmap.put("CaveSpider", "minecraft:cave_spider");
        hashmap.put("Chicken", "minecraft:chicken");
        hashmap.put("Cow", "minecraft:cow");
        hashmap.put("Creeper", "minecraft:creeper");
        hashmap.put("Donkey", "minecraft:donkey");
        hashmap.put("DragonFireball", "minecraft:dragon_fireball");
        hashmap.put("ElderGuardian", "minecraft:elder_guardian");
        hashmap.put("EnderCrystal", "minecraft:ender_crystal");
        hashmap.put("EnderDragon", "minecraft:ender_dragon");
        hashmap.put("Enderman", "minecraft:enderman");
        hashmap.put("Endermite", "minecraft:endermite");
        hashmap.put("EyeOfEnderSignal", "minecraft:eye_of_ender_signal");
        hashmap.put("FallingSand", "minecraft:falling_block");
        hashmap.put("Fireball", "minecraft:fireball");
        hashmap.put("FireworksRocketEntity", "minecraft:fireworks_rocket");
        hashmap.put("Ghast", "minecraft:ghast");
        hashmap.put("Giant", "minecraft:giant");
        hashmap.put("Guardian", "minecraft:guardian");
        hashmap.put("Horse", "minecraft:horse");
        hashmap.put("Husk", "minecraft:husk");
        hashmap.put("Item", "minecraft:item");
        hashmap.put("ItemFrame", "minecraft:item_frame");
        hashmap.put("LavaSlime", "minecraft:magma_cube");
        hashmap.put("LeashKnot", "minecraft:leash_knot");
        hashmap.put("MinecartChest", "minecraft:chest_minecart");
        hashmap.put("MinecartCommandBlock", "minecraft:commandblock_minecart");
        hashmap.put("MinecartFurnace", "minecraft:furnace_minecart");
        hashmap.put("MinecartHopper", "minecraft:hopper_minecart");
        hashmap.put("MinecartRideable", "minecraft:minecart");
        hashmap.put("MinecartSpawner", "minecraft:spawner_minecart");
        hashmap.put("MinecartTNT", "minecraft:tnt_minecart");
        hashmap.put("Mule", "minecraft:mule");
        hashmap.put("MushroomCow", "minecraft:mooshroom");
        hashmap.put("Ozelot", "minecraft:ocelot");
        hashmap.put("Painting", "minecraft:painting");
        hashmap.put("Pig", "minecraft:pig");
        hashmap.put("PigZombie", "minecraft:zombie_pigman");
        hashmap.put("PolarBear", "minecraft:polar_bear");
        hashmap.put("PrimedTnt", "minecraft:tnt");
        hashmap.put("Rabbit", "minecraft:rabbit");
        hashmap.put("Sheep", "minecraft:sheep");
        hashmap.put("Shulker", "minecraft:shulker");
        hashmap.put("ShulkerBullet", "minecraft:shulker_bullet");
        hashmap.put("Silverfish", "minecraft:silverfish");
        hashmap.put("Skeleton", "minecraft:skeleton");
        hashmap.put("SkeletonHorse", "minecraft:skeleton_horse");
        hashmap.put("Slime", "minecraft:slime");
        hashmap.put("SmallFireball", "minecraft:small_fireball");
        hashmap.put("SnowMan", "minecraft:snowman");
        hashmap.put("Snowball", "minecraft:snowball");
        hashmap.put("SpectralArrow", "minecraft:spectral_arrow");
        hashmap.put("Spider", "minecraft:spider");
        hashmap.put("Squid", "minecraft:squid");
        hashmap.put("Stray", "minecraft:stray");
        hashmap.put("ThrownEgg", "minecraft:egg");
        hashmap.put("ThrownEnderpearl", "minecraft:ender_pearl");
        hashmap.put("ThrownExpBottle", "minecraft:xp_bottle");
        hashmap.put("ThrownPotion", "minecraft:potion");
        hashmap.put("Villager", "minecraft:villager");
        hashmap.put("VillagerGolem", "minecraft:villager_golem");
        hashmap.put("Witch", "minecraft:witch");
        hashmap.put("WitherBoss", "minecraft:wither");
        hashmap.put("WitherSkeleton", "minecraft:wither_skeleton");
        hashmap.put("WitherSkull", "minecraft:wither_skull");
        hashmap.put("Wolf", "minecraft:wolf");
        hashmap.put("XPOrb", "minecraft:xp_orb");
        hashmap.put("Zombie", "minecraft:zombie");
        hashmap.put("ZombieHorse", "minecraft:zombie_horse");
        hashmap.put("ZombieVillager", "minecraft:zombie_villager");
    });

    public DataConverterEntity(Schema schema, boolean flag) {
        super(schema, flag);
    }

    public TypeRewriteRule makeRule() {
        TaggedChoiceType<String> taggedchoicetype = this.getInputSchema().findChoiceType(DataConverterTypes.ENTITY);
        TaggedChoiceType<String> taggedchoicetype1 = this.getOutputSchema().findChoiceType(DataConverterTypes.ENTITY);
        Type<?> type = this.getInputSchema().getType(DataConverterTypes.ITEM_STACK);
        Type<?> type1 = this.getOutputSchema().getType(DataConverterTypes.ITEM_STACK);

        return TypeRewriteRule.seq(this.convertUnchecked("item stack entity name hook converter", type, type1), this.fixTypeEverywhere("EntityIdFix", taggedchoicetype, taggedchoicetype1, (dynamicops) -> {
            return (pair) -> {
                return pair.mapFirst((s) -> {
                    return (String) DataConverterEntity.a.getOrDefault(s, s);
                });
            };
        }));
    }
}
