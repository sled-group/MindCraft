package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nullable;

public class BehaviorStrollPlaceList extends Behavior<EntityVillager> {

    private final MemoryModuleType<List<GlobalPos>> a;
    private final MemoryModuleType<GlobalPos> b;
    private final float c;
    private final int d;
    private final int e;
    private long f;
    @Nullable
    private GlobalPos g;

    public BehaviorStrollPlaceList(MemoryModuleType<List<GlobalPos>> memorymoduletype, float f, int i, int j, MemoryModuleType<GlobalPos> memorymoduletype1) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, memorymoduletype, MemoryStatus.VALUE_PRESENT, memorymoduletype1, MemoryStatus.VALUE_PRESENT));
        this.a = memorymoduletype;
        this.c = f;
        this.d = i;
        this.e = j;
        this.b = memorymoduletype1;
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        Optional<List<GlobalPos>> optional = entityvillager.getBehaviorController().getMemory(this.a);
        Optional<GlobalPos> optional1 = entityvillager.getBehaviorController().getMemory(this.b);

        if (optional.isPresent() && optional1.isPresent()) {
            List<GlobalPos> list = (List) optional.get();

            if (!list.isEmpty()) {
                this.g = (GlobalPos) list.get(worldserver.getRandom().nextInt(list.size()));
                return this.g != null && Objects.equals(worldserver.getWorldProvider().getDimensionManager(), this.g.getDimensionManager()) && ((GlobalPos) optional1.get()).getBlockPosition().a((IPosition) entityvillager.getPositionVector(), (double) this.e);
            }
        }

        return false;
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        if (i > this.f && this.g != null) {
            entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(this.g.getBlockPosition(), this.c, this.d)));
            this.f = i + 100L;
        }

    }
}
