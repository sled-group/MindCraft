package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BehaviorBell extends Behavior<EntityLiving> {

    public BehaviorBell() {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Optional<GlobalPos> optional = behaviorcontroller.getMemory(MemoryModuleType.MEETING_POINT);

        return worldserver.getRandom().nextInt(100) == 0 && optional.isPresent() && Objects.equals(worldserver.getWorldProvider().getDimensionManager(), ((GlobalPos) optional.get()).getDimensionManager()) && ((GlobalPos) optional.get()).getBlockPosition().a((IPosition) entityliving.getPositionVector(), 4.0D) && ((List) behaviorcontroller.getMemory(MemoryModuleType.VISIBLE_MOBS).get()).stream().anyMatch((entityliving1) -> {
            return EntityTypes.VILLAGER.equals(entityliving1.getEntityType());
        });
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();

        behaviorcontroller.getMemory(MemoryModuleType.VISIBLE_MOBS).ifPresent((list) -> {
            list.stream().filter((entityliving1) -> {
                return EntityTypes.VILLAGER.equals(entityliving1.getEntityType());
            }).filter((entityliving1) -> {
                return entityliving1.h((Entity) entityliving) <= 32.0D;
            }).findFirst().ifPresent((entityliving1) -> {
                behaviorcontroller.setMemory(MemoryModuleType.INTERACTION_TARGET, (Object) entityliving1);
                behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorPositionEntity(entityliving1)));
                behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(new BehaviorPositionEntity(entityliving1), 0.3F, 1)));
            });
        });
    }
}
