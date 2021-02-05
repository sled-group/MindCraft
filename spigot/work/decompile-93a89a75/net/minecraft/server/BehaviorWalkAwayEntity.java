package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorWalkAwayEntity extends Behavior<EntityCreature> {

    private final MemoryModuleType<? extends Entity> a;
    private final float b;

    public BehaviorWalkAwayEntity(MemoryModuleType<? extends Entity> memorymoduletype, float f) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, memorymoduletype, MemoryStatus.VALUE_PRESENT));
        this.a = memorymoduletype;
        this.b = f;
    }

    protected boolean a(WorldServer worldserver, EntityCreature entitycreature) {
        Entity entity = (Entity) entitycreature.getBehaviorController().getMemory(this.a).get();

        return entitycreature.h(entity) < 36.0D;
    }

    protected void a(WorldServer worldserver, EntityCreature entitycreature, long i) {
        Entity entity = (Entity) entitycreature.getBehaviorController().getMemory(this.a).get();

        a(entitycreature, entity, this.b);
    }

    public static void a(EntityCreature entitycreature, Entity entity, float f) {
        for (int i = 0; i < 10; ++i) {
            Vec3D vec3d = new Vec3D(entity.locX, entity.locY, entity.locZ);
            Vec3D vec3d1 = RandomPositionGenerator.b(entitycreature, 16, 7, vec3d);

            if (vec3d1 != null) {
                entitycreature.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(vec3d1, f, 0)));
                return;
            }
        }

    }
}
