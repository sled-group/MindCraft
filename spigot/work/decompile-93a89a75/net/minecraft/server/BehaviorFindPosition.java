package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap.Entry;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BehaviorFindPosition extends Behavior<EntityCreature> {

    private final VillagePlaceType a;
    private final MemoryModuleType<GlobalPos> b;
    private final boolean c;
    private long d;
    private final Long2LongMap e = new Long2LongOpenHashMap();
    private int f;

    public BehaviorFindPosition(VillagePlaceType villageplacetype, MemoryModuleType<GlobalPos> memorymoduletype, boolean flag) {
        super(ImmutableMap.of(memorymoduletype, MemoryStatus.VALUE_ABSENT));
        this.a = villageplacetype;
        this.b = memorymoduletype;
        this.c = flag;
    }

    protected boolean a(WorldServer worldserver, EntityCreature entitycreature) {
        return this.c && entitycreature.isBaby() ? false : worldserver.getTime() - this.d >= 20L;
    }

    protected void a(WorldServer worldserver, EntityCreature entitycreature, long i) {
        this.f = 0;
        this.d = worldserver.getTime() + (long) worldserver.getRandom().nextInt(20);
        VillagePlace villageplace = worldserver.B();
        Predicate<BlockPosition> predicate = (blockposition) -> {
            long j = blockposition.asLong();

            if (this.e.containsKey(j)) {
                return false;
            } else if (++this.f >= 5) {
                return false;
            } else {
                this.e.put(j, this.d + 40L);
                return true;
            }
        };
        Stream<BlockPosition> stream = villageplace.a(this.a.c(), predicate, new BlockPosition(entitycreature), 48, VillagePlace.Occupancy.HAS_SPACE);
        PathEntity pathentity = entitycreature.getNavigation().a(stream, this.a.d());

        if (pathentity != null && pathentity.h()) {
            BlockPosition blockposition = pathentity.k();

            villageplace.c(blockposition).ifPresent((villageplacetype) -> {
                villageplace.a(this.a.c(), (blockposition1) -> {
                    return blockposition1.equals(blockposition);
                }, blockposition, 1);
                entitycreature.getBehaviorController().setMemory(this.b, (Object) GlobalPos.create(worldserver.getWorldProvider().getDimensionManager(), blockposition));
                PacketDebug.c(worldserver, blockposition);
            });
        } else if (this.f < 5) {
            this.e.long2LongEntrySet().removeIf((entry) -> {
                return entry.getLongValue() < this.d;
            });
        }

    }
}
