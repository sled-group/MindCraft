package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorInteractPlayer extends Behavior<EntityVillager> {

    private final float a;

    public BehaviorInteractPlayer(float f) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), Integer.MAX_VALUE);
        this.a = f;
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        EntityHuman entityhuman = entityvillager.getTrader();

        return entityvillager.isAlive() && entityhuman != null && !entityvillager.isInWater() && !entityvillager.velocityChanged && entityvillager.h((Entity) entityhuman) <= 16.0D && entityhuman.activeContainer != null;
    }

    protected boolean g(WorldServer worldserver, EntityVillager entityvillager, long i) {
        return this.a(worldserver, entityvillager);
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        this.a(entityvillager);
    }

    protected void f(WorldServer worldserver, EntityVillager entityvillager, long i) {
        BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

        behaviorcontroller.removeMemory(MemoryModuleType.WALK_TARGET);
        behaviorcontroller.removeMemory(MemoryModuleType.LOOK_TARGET);
    }

    protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
        this.a(entityvillager);
    }

    @Override
    protected boolean a(long i) {
        return false;
    }

    private void a(EntityVillager entityvillager) {
        BehaviorPositionEntity behaviorpositionentity = new BehaviorPositionEntity(entityvillager.getTrader());
        BehaviorController<?> behaviorcontroller = entityvillager.getBehaviorController();

        behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(behaviorpositionentity, this.a, 2)));
        behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) behaviorpositionentity);
    }
}
