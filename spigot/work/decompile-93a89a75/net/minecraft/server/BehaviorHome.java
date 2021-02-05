package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;

public class BehaviorHome extends Behavior<EntityLiving> {

    private final float a;
    private final int b;
    private final int c;
    private Optional<BlockPosition> d = Optional.empty();

    public BehaviorHome(int i, float f, int j) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.HOME, MemoryStatus.REGISTERED, MemoryModuleType.HIDING_PLACE, MemoryStatus.REGISTERED));
        this.b = i;
        this.a = f;
        this.c = j;
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        Optional<BlockPosition> optional = worldserver.B().b((villageplacetype) -> {
            return villageplacetype == VillagePlaceType.q;
        }, (blockposition) -> {
            return true;
        }, new BlockPosition(entityliving), this.c + 1, VillagePlace.Occupancy.ANY);

        if (optional.isPresent() && ((BlockPosition) optional.get()).a((IPosition) entityliving.getPositionVector(), (double) this.c)) {
            this.d = optional;
        } else {
            this.d = Optional.empty();
        }

        return true;
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Optional<BlockPosition> optional = this.d;

        if (!optional.isPresent()) {
            optional = worldserver.B().a((villageplacetype) -> {
                return villageplacetype == VillagePlaceType.q;
            }, (blockposition) -> {
                return true;
            }, VillagePlace.Occupancy.ANY, new BlockPosition(entityliving), this.b, entityliving.getRandom());
            if (!optional.isPresent()) {
                Optional<GlobalPos> optional1 = behaviorcontroller.getMemory(MemoryModuleType.HOME);

                if (optional1.isPresent()) {
                    optional = Optional.of(((GlobalPos) optional1.get()).getBlockPosition());
                }
            }
        }

        if (optional.isPresent()) {
            behaviorcontroller.removeMemory(MemoryModuleType.PATH);
            behaviorcontroller.removeMemory(MemoryModuleType.LOOK_TARGET);
            behaviorcontroller.removeMemory(MemoryModuleType.BREED_TARGET);
            behaviorcontroller.removeMemory(MemoryModuleType.INTERACTION_TARGET);
            behaviorcontroller.setMemory(MemoryModuleType.HIDING_PLACE, (Object) GlobalPos.create(worldserver.getWorldProvider().getDimensionManager(), (BlockPosition) optional.get()));
            if (!((BlockPosition) optional.get()).a((IPosition) entityliving.getPositionVector(), (double) this.c)) {
                behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget((BlockPosition) optional.get(), this.a, this.c)));
            }
        }

    }
}
