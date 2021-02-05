package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Objects;
import java.util.Optional;

public class BehaviorStrollPosition extends Behavior<EntityCreature> {

    private final MemoryModuleType<GlobalPos> a;
    private long b;
    private final int c;

    public BehaviorStrollPosition(MemoryModuleType<GlobalPos> memorymoduletype, int i) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, memorymoduletype, MemoryStatus.VALUE_PRESENT));
        this.a = memorymoduletype;
        this.c = i;
    }

    protected boolean a(WorldServer worldserver, EntityCreature entitycreature) {
        Optional<GlobalPos> optional = entitycreature.getBehaviorController().getMemory(this.a);

        return optional.isPresent() && Objects.equals(worldserver.getWorldProvider().getDimensionManager(), ((GlobalPos) optional.get()).getDimensionManager()) && ((GlobalPos) optional.get()).getBlockPosition().a((IPosition) entitycreature.getPositionVector(), (double) this.c);
    }

    protected void a(WorldServer worldserver, EntityCreature entitycreature, long i) {
        if (i > this.b) {
            Optional<Vec3D> optional = Optional.ofNullable(RandomPositionGenerator.b(entitycreature, 8, 6));

            entitycreature.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, optional.map((vec3d) -> {
                return new MemoryTarget(vec3d, 0.4F, 1);
            }));
            this.b = i + 180L;
        }

    }
}
