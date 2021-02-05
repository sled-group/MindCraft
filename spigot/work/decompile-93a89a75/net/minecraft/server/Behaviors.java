package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;

public class Behaviors {

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> a(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(0, new BehaviorSwim(0.4F, 0.8F)), Pair.of(0, new BehaviorInteractDoor()), Pair.of(0, new BehaviorLook(45, 90)), Pair.of(0, new BehaviorPanic()), Pair.of(0, new BehaviorWake()), Pair.of(0, new BehaviorBellAlert()), Pair.of(0, new BehaviorRaid()), Pair.of(1, new BehavorMove(200)), Pair.of(2, new BehaviorInteractPlayer(f)), Pair.of(5, new BehaviorPickupItem()), Pair.of(10, new BehaviorFindPosition(villagerprofession.b(), MemoryModuleType.JOB_SITE, true)), Pair.of(10, new BehaviorFindPosition(VillagePlaceType.q, MemoryModuleType.HOME, false)), new Pair[]{Pair.of(10, new BehaviorFindPosition(VillagePlaceType.r, MemoryModuleType.MEETING_POINT, true)), Pair.of(10, new BehaviorCareer()), Pair.of(10, new BehaviorProfession())});
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> b(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(b(), Pair.of(5, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorWork(), 7), Pair.of(new BehaviorStrollPosition(MemoryModuleType.JOB_SITE, 4), 2), Pair.of(new BehaviorStrollPlace(MemoryModuleType.JOB_SITE, 1, 10), 5), Pair.of(new BehaviorStrollPlaceList(MemoryModuleType.SECONDARY_JOB_SITE, 0.4F, 1, 6, MemoryModuleType.JOB_SITE), 5), Pair.of(new BehaviorFarm(), villagerprofession == VillagerProfession.FARMER ? 2 : 5)))), Pair.of(10, new BehaviorTradePlayer(400, 1600)), Pair.of(10, new BehaviorLookInteract(EntityTypes.PLAYER, 4)), Pair.of(2, new BehaviorWalkAwayBlock(MemoryModuleType.JOB_SITE, f, 9, 100, 1200)), Pair.of(3, new BehaviorVillageHeroGift(100)), Pair.of(3, new BehaviorPositionValidate(villagerprofession.b(), MemoryModuleType.JOB_SITE)), Pair.of(99, new BehaviorSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> a(float f) {
        return ImmutableList.of(Pair.of(0, new BehavorMove(100)), a(), Pair.of(5, new BehaviorPlay()), Pair.of(5, new BehaviorGateSingle<>(ImmutableMap.of(MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(BehaviorInteract.a(EntityTypes.VILLAGER, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 2), Pair.of(BehaviorInteract.a(EntityTypes.CAT, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 1), Pair.of(new BehaviorStrollRandom(f), 1), Pair.of(new BehaviorLookWalk(f, 2), 1), Pair.of(new BehaviorBedJump(f), 2), Pair.of(new BehaviorNop(20, 40), 2)))), Pair.of(99, new BehaviorSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> c(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(2, new BehaviorWalkAwayBlock(MemoryModuleType.HOME, f, 1, 150, 1200)), Pair.of(3, new BehaviorPositionValidate(VillagePlaceType.q, MemoryModuleType.HOME)), Pair.of(3, new BehaviorSleep()), Pair.of(5, new BehaviorGateSingle<>(ImmutableMap.of(MemoryModuleType.HOME, MemoryStatus.VALUE_ABSENT), ImmutableList.of(Pair.of(new BehaviorWalkHome(f), 1), Pair.of(new BehaviorStrollInside(f), 4), Pair.of(new BehaviorNearestVillage(f, 4), 2), Pair.of(new BehaviorNop(20, 40), 2)))), b(), Pair.of(99, new BehaviorSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> d(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(2, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorStrollPosition(MemoryModuleType.MEETING_POINT, 40), 2), Pair.of(new BehaviorBell(), 2)))), Pair.of(10, new BehaviorTradePlayer(400, 1600)), Pair.of(10, new BehaviorLookInteract(EntityTypes.PLAYER, 4)), Pair.of(2, new BehaviorWalkAwayBlock(MemoryModuleType.MEETING_POINT, f, 6, 100, 200)), Pair.of(3, new BehaviorVillageHeroGift(100)), Pair.of(3, new BehaviorPositionValidate(VillagePlaceType.r, MemoryModuleType.MEETING_POINT)), Pair.of(3, new BehaviorGate<>(ImmutableMap.of(), ImmutableSet.of(MemoryModuleType.INTERACTION_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, ImmutableList.of(Pair.of(new BehaviorTradeVillager(), 1)))), a(), Pair.of(99, new BehaviorSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> e(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(2, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(BehaviorInteract.a(EntityTypes.VILLAGER, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 2), Pair.of(new BehaviorInteract<>(EntityTypes.VILLAGER, 8, EntityVillager::canBreed, EntityVillager::canBreed, MemoryModuleType.BREED_TARGET, f, 2), 1), Pair.of(BehaviorInteract.a(EntityTypes.CAT, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 1), Pair.of(new BehaviorStrollRandom(f), 1), Pair.of(new BehaviorLookWalk(f, 2), 1), Pair.of(new BehaviorBedJump(f), 1), Pair.of(new BehaviorNop(30, 60), 1)))), Pair.of(3, new BehaviorVillageHeroGift(100)), Pair.of(3, new BehaviorLookInteract(EntityTypes.PLAYER, 4)), Pair.of(3, new BehaviorTradePlayer(400, 1600)), Pair.of(3, new BehaviorGate<>(ImmutableMap.of(), ImmutableSet.of(MemoryModuleType.INTERACTION_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, ImmutableList.of(Pair.of(new BehaviorTradeVillager(), 1)))), Pair.of(3, new BehaviorGate<>(ImmutableMap.of(), ImmutableSet.of(MemoryModuleType.BREED_TARGET), BehaviorGate.Order.ORDERED, BehaviorGate.Execution.RUN_ONE, ImmutableList.of(Pair.of(new BehaviorMakeLove(), 1)))), a(), Pair.of(99, new BehaviorSchedule()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> f(VillagerProfession villagerprofession, float f) {
        float f1 = f * 1.5F;

        return ImmutableList.of(Pair.of(0, new BehaviorCooldown()), Pair.of(1, new BehaviorWalkAwayEntity(MemoryModuleType.NEAREST_HOSTILE, f1)), Pair.of(1, new BehaviorWalkAwayEntity(MemoryModuleType.HURT_BY_ENTITY, f1)), Pair.of(3, new BehaviorStrollRandom(f1, 2, 2)), b());
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> g(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(0, new BehaviorBellRing()), Pair.of(0, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorWalkAwayBlock(MemoryModuleType.MEETING_POINT, f * 1.5F, 2, 150, 200), 6), Pair.of(new BehaviorStrollRandom(f * 1.5F), 2)))), b(), Pair.of(99, new BehaviorRaidReset()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> h(VillagerProfession villagerprofession, float f) {
        return ImmutableList.of(Pair.of(0, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorOutsideCelebrate(f), 5), Pair.of(new BehaviorVictory(f * 1.1F), 2)))), Pair.of(0, new BehaviorCelebrate(600, 600)), Pair.of(2, new BehaviorHomeRaid(24, f * 1.4F)), b(), Pair.of(99, new BehaviorRaidReset()));
    }

    public static ImmutableList<Pair<Integer, ? extends Behavior<? super EntityVillager>>> i(VillagerProfession villagerprofession, float f) {
        boolean flag = true;

        return ImmutableList.of(Pair.of(0, new BehaviorHide(15, 2)), Pair.of(1, new BehaviorHome(32, f * 1.25F, 2)), b());
    }

    private static Pair<Integer, Behavior<EntityLiving>> a() {
        return Pair.of(5, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorLookTarget(EntityTypes.CAT, 8.0F), 8), Pair.of(new BehaviorLookTarget(EntityTypes.VILLAGER, 8.0F), 2), Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), 2), Pair.of(new BehaviorLookTarget(EnumCreatureType.CREATURE, 8.0F), 1), Pair.of(new BehaviorLookTarget(EnumCreatureType.WATER_CREATURE, 8.0F), 1), Pair.of(new BehaviorLookTarget(EnumCreatureType.MONSTER, 8.0F), 1), Pair.of(new BehaviorNop(30, 60), 2))));
    }

    private static Pair<Integer, Behavior<EntityLiving>> b() {
        return Pair.of(5, new BehaviorGateSingle<>(ImmutableList.of(Pair.of(new BehaviorLookTarget(EntityTypes.VILLAGER, 8.0F), 2), Pair.of(new BehaviorLookTarget(EntityTypes.PLAYER, 8.0F), 2), Pair.of(new BehaviorNop(30, 60), 8))));
    }
}
