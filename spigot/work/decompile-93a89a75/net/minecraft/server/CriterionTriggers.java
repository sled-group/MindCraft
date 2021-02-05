package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;

public class CriterionTriggers {

    private static final Map<MinecraftKey, CriterionTrigger<?>> J = Maps.newHashMap();
    public static final CriterionTriggerImpossible a = (CriterionTriggerImpossible) a((CriterionTrigger) (new CriterionTriggerImpossible()));
    public static final CriterionTriggerKilled b = (CriterionTriggerKilled) a((CriterionTrigger) (new CriterionTriggerKilled(new MinecraftKey("player_killed_entity"))));
    public static final CriterionTriggerKilled c = (CriterionTriggerKilled) a((CriterionTrigger) (new CriterionTriggerKilled(new MinecraftKey("entity_killed_player"))));
    public static final CriterionTriggerEnterBlock d = (CriterionTriggerEnterBlock) a((CriterionTrigger) (new CriterionTriggerEnterBlock()));
    public static final CriterionTriggerInventoryChanged e = (CriterionTriggerInventoryChanged) a((CriterionTrigger) (new CriterionTriggerInventoryChanged()));
    public static final CriterionTriggerRecipeUnlocked f = (CriterionTriggerRecipeUnlocked) a((CriterionTrigger) (new CriterionTriggerRecipeUnlocked()));
    public static final CriterionTriggerPlayerHurtEntity g = (CriterionTriggerPlayerHurtEntity) a((CriterionTrigger) (new CriterionTriggerPlayerHurtEntity()));
    public static final CriterionTriggerEntityHurtPlayer h = (CriterionTriggerEntityHurtPlayer) a((CriterionTrigger) (new CriterionTriggerEntityHurtPlayer()));
    public static final CriterionTriggerEnchantedItem i = (CriterionTriggerEnchantedItem) a((CriterionTrigger) (new CriterionTriggerEnchantedItem()));
    public static final CriterionTriggerFilledBucket j = (CriterionTriggerFilledBucket) a((CriterionTrigger) (new CriterionTriggerFilledBucket()));
    public static final CriterionTriggerBrewedPotion k = (CriterionTriggerBrewedPotion) a((CriterionTrigger) (new CriterionTriggerBrewedPotion()));
    public static final CriterionTriggerConstructBeacon l = (CriterionTriggerConstructBeacon) a((CriterionTrigger) (new CriterionTriggerConstructBeacon()));
    public static final CriterionTriggerUsedEnderEye m = (CriterionTriggerUsedEnderEye) a((CriterionTrigger) (new CriterionTriggerUsedEnderEye()));
    public static final CriterionTriggerSummonedEntity n = (CriterionTriggerSummonedEntity) a((CriterionTrigger) (new CriterionTriggerSummonedEntity()));
    public static final CriterionTriggerBredAnimals o = (CriterionTriggerBredAnimals) a((CriterionTrigger) (new CriterionTriggerBredAnimals()));
    public static final CriterionTriggerLocation p = (CriterionTriggerLocation) a((CriterionTrigger) (new CriterionTriggerLocation(new MinecraftKey("location"))));
    public static final CriterionTriggerLocation q = (CriterionTriggerLocation) a((CriterionTrigger) (new CriterionTriggerLocation(new MinecraftKey("slept_in_bed"))));
    public static final CriterionTriggerCuredZombieVillager r = (CriterionTriggerCuredZombieVillager) a((CriterionTrigger) (new CriterionTriggerCuredZombieVillager()));
    public static final CriterionTriggerVillagerTrade s = (CriterionTriggerVillagerTrade) a((CriterionTrigger) (new CriterionTriggerVillagerTrade()));
    public static final CriterionTriggerItemDurabilityChanged t = (CriterionTriggerItemDurabilityChanged) a((CriterionTrigger) (new CriterionTriggerItemDurabilityChanged()));
    public static final CriterionTriggerLevitation u = (CriterionTriggerLevitation) a((CriterionTrigger) (new CriterionTriggerLevitation()));
    public static final CriterionTriggerChangedDimension v = (CriterionTriggerChangedDimension) a((CriterionTrigger) (new CriterionTriggerChangedDimension()));
    public static final CriterionTriggerTick w = (CriterionTriggerTick) a((CriterionTrigger) (new CriterionTriggerTick()));
    public static final CriterionTriggerTamedAnimal x = (CriterionTriggerTamedAnimal) a((CriterionTrigger) (new CriterionTriggerTamedAnimal()));
    public static final CriterionTriggerPlacedBlock y = (CriterionTriggerPlacedBlock) a((CriterionTrigger) (new CriterionTriggerPlacedBlock()));
    public static final CriterionTriggerConsumeItem z = (CriterionTriggerConsumeItem) a((CriterionTrigger) (new CriterionTriggerConsumeItem()));
    public static final CriterionTriggerEffectsChanged A = (CriterionTriggerEffectsChanged) a((CriterionTrigger) (new CriterionTriggerEffectsChanged()));
    public static final CriterionTriggerUsedTotem B = (CriterionTriggerUsedTotem) a((CriterionTrigger) (new CriterionTriggerUsedTotem()));
    public static final CriterionTriggerNetherTravel C = (CriterionTriggerNetherTravel) a((CriterionTrigger) (new CriterionTriggerNetherTravel()));
    public static final CriterionTriggerFishingRodHooked D = (CriterionTriggerFishingRodHooked) a((CriterionTrigger) (new CriterionTriggerFishingRodHooked()));
    public static final CriterionTriggerChanneledLightning E = (CriterionTriggerChanneledLightning) a((CriterionTrigger) (new CriterionTriggerChanneledLightning()));
    public static final CriterionTriggerShotCrossbow F = (CriterionTriggerShotCrossbow) a((CriterionTrigger) (new CriterionTriggerShotCrossbow()));
    public static final CriterionTriggerKilledByCrossbow G = (CriterionTriggerKilledByCrossbow) a((CriterionTrigger) (new CriterionTriggerKilledByCrossbow()));
    public static final CriterionTriggerLocation H = (CriterionTriggerLocation) a((CriterionTrigger) (new CriterionTriggerLocation(new MinecraftKey("hero_of_the_village"))));
    public static final CriterionTriggerLocation I = (CriterionTriggerLocation) a((CriterionTrigger) (new CriterionTriggerLocation(new MinecraftKey("voluntary_exile"))));

    private static <T extends CriterionTrigger<?>> T a(T t0) {
        if (CriterionTriggers.J.containsKey(t0.a())) {
            throw new IllegalArgumentException("Duplicate criterion id " + t0.a());
        } else {
            CriterionTriggers.J.put(t0.a(), t0);
            return t0;
        }
    }

    @Nullable
    public static <T extends CriterionInstance> CriterionTrigger<T> a(MinecraftKey minecraftkey) {
        return (CriterionTrigger) CriterionTriggers.J.get(minecraftkey);
    }

    public static Iterable<? extends CriterionTrigger<?>> a() {
        return CriterionTriggers.J.values();
    }
}
