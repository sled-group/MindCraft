package net.minecraft.server;

public class Potions {

    public static final PotionRegistry EMPTY = a("empty", new PotionRegistry(new MobEffect[0]));
    public static final PotionRegistry WATER = a("water", new PotionRegistry(new MobEffect[0]));
    public static final PotionRegistry MUNDANE = a("mundane", new PotionRegistry(new MobEffect[0]));
    public static final PotionRegistry THICK = a("thick", new PotionRegistry(new MobEffect[0]));
    public static final PotionRegistry AWKWARD = a("awkward", new PotionRegistry(new MobEffect[0]));
    public static final PotionRegistry NIGHT_VISION = a("night_vision", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.NIGHT_VISION, 3600)}));
    public static final PotionRegistry LONG_NIGHT_VISION = a("long_night_vision", new PotionRegistry("night_vision", new MobEffect[]{new MobEffect(MobEffects.NIGHT_VISION, 9600)}));
    public static final PotionRegistry INVISIBILITY = a("invisibility", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.INVISIBILITY, 3600)}));
    public static final PotionRegistry LONG_INVISIBILITY = a("long_invisibility", new PotionRegistry("invisibility", new MobEffect[]{new MobEffect(MobEffects.INVISIBILITY, 9600)}));
    public static final PotionRegistry LEAPING = a("leaping", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.JUMP, 3600)}));
    public static final PotionRegistry LONG_LEAPING = a("long_leaping", new PotionRegistry("leaping", new MobEffect[]{new MobEffect(MobEffects.JUMP, 9600)}));
    public static final PotionRegistry STRONG_LEAPING = a("strong_leaping", new PotionRegistry("leaping", new MobEffect[]{new MobEffect(MobEffects.JUMP, 1800, 1)}));
    public static final PotionRegistry FIRE_RESISTANCE = a("fire_resistance", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.FIRE_RESISTANCE, 3600)}));
    public static final PotionRegistry LONG_FIRE_RESISTANCE = a("long_fire_resistance", new PotionRegistry("fire_resistance", new MobEffect[]{new MobEffect(MobEffects.FIRE_RESISTANCE, 9600)}));
    public static final PotionRegistry SWIFTNESS = a("swiftness", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.FASTER_MOVEMENT, 3600)}));
    public static final PotionRegistry LONG_SWIFTNESS = a("long_swiftness", new PotionRegistry("swiftness", new MobEffect[]{new MobEffect(MobEffects.FASTER_MOVEMENT, 9600)}));
    public static final PotionRegistry STRONG_SWIFTNESS = a("strong_swiftness", new PotionRegistry("swiftness", new MobEffect[]{new MobEffect(MobEffects.FASTER_MOVEMENT, 1800, 1)}));
    public static final PotionRegistry SLOWNESS = a("slowness", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 1800)}));
    public static final PotionRegistry LONG_SLOWNESS = a("long_slowness", new PotionRegistry("slowness", new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 4800)}));
    public static final PotionRegistry STRONG_SLOWNESS = a("strong_slowness", new PotionRegistry("slowness", new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 400, 3)}));
    public static final PotionRegistry TURTLE_MASTER = a("turtle_master", new PotionRegistry("turtle_master", new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 400, 3), new MobEffect(MobEffects.RESISTANCE, 400, 2)}));
    public static final PotionRegistry LONG_TURTLE_MASTER = a("long_turtle_master", new PotionRegistry("turtle_master", new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 800, 3), new MobEffect(MobEffects.RESISTANCE, 800, 2)}));
    public static final PotionRegistry STRONG_TURTLE_MASTER = a("strong_turtle_master", new PotionRegistry("turtle_master", new MobEffect[]{new MobEffect(MobEffects.SLOWER_MOVEMENT, 400, 5), new MobEffect(MobEffects.RESISTANCE, 400, 3)}));
    public static final PotionRegistry WATER_BREATHING = a("water_breathing", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.WATER_BREATHING, 3600)}));
    public static final PotionRegistry LONG_WATER_BREATHING = a("long_water_breathing", new PotionRegistry("water_breathing", new MobEffect[]{new MobEffect(MobEffects.WATER_BREATHING, 9600)}));
    public static final PotionRegistry HEALING = a("healing", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.HEAL, 1)}));
    public static final PotionRegistry STRONG_HEALING = a("strong_healing", new PotionRegistry("healing", new MobEffect[]{new MobEffect(MobEffects.HEAL, 1, 1)}));
    public static final PotionRegistry HARMING = a("harming", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.HARM, 1)}));
    public static final PotionRegistry STRONG_HARMING = a("strong_harming", new PotionRegistry("harming", new MobEffect[]{new MobEffect(MobEffects.HARM, 1, 1)}));
    public static final PotionRegistry POISON = a("poison", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.POISON, 900)}));
    public static final PotionRegistry LONG_POISON = a("long_poison", new PotionRegistry("poison", new MobEffect[]{new MobEffect(MobEffects.POISON, 1800)}));
    public static final PotionRegistry STRONG_POISON = a("strong_poison", new PotionRegistry("poison", new MobEffect[]{new MobEffect(MobEffects.POISON, 432, 1)}));
    public static final PotionRegistry REGENERATION = a("regeneration", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.REGENERATION, 900)}));
    public static final PotionRegistry LONG_REGENERATION = a("long_regeneration", new PotionRegistry("regeneration", new MobEffect[]{new MobEffect(MobEffects.REGENERATION, 1800)}));
    public static final PotionRegistry STRONG_REGENERATION = a("strong_regeneration", new PotionRegistry("regeneration", new MobEffect[]{new MobEffect(MobEffects.REGENERATION, 450, 1)}));
    public static final PotionRegistry STRENGTH = a("strength", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.INCREASE_DAMAGE, 3600)}));
    public static final PotionRegistry LONG_STRENGTH = a("long_strength", new PotionRegistry("strength", new MobEffect[]{new MobEffect(MobEffects.INCREASE_DAMAGE, 9600)}));
    public static final PotionRegistry STRONG_STRENGTH = a("strong_strength", new PotionRegistry("strength", new MobEffect[]{new MobEffect(MobEffects.INCREASE_DAMAGE, 1800, 1)}));
    public static final PotionRegistry WEAKNESS = a("weakness", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.WEAKNESS, 1800)}));
    public static final PotionRegistry LONG_WEAKNESS = a("long_weakness", new PotionRegistry("weakness", new MobEffect[]{new MobEffect(MobEffects.WEAKNESS, 4800)}));
    public static final PotionRegistry LUCK = a("luck", new PotionRegistry("luck", new MobEffect[]{new MobEffect(MobEffects.LUCK, 6000)}));
    public static final PotionRegistry SLOW_FALLING = a("slow_falling", new PotionRegistry(new MobEffect[]{new MobEffect(MobEffects.SLOW_FALLING, 1800)}));
    public static final PotionRegistry LONG_SLOW_FALLING = a("long_slow_falling", new PotionRegistry("slow_falling", new MobEffect[]{new MobEffect(MobEffects.SLOW_FALLING, 4800)}));

    private static PotionRegistry a(String s, PotionRegistry potionregistry) {
        return (PotionRegistry) IRegistry.a((IRegistry) IRegistry.POTION, s, (Object) potionregistry);
    }
}
