package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2LongMap.Entry;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class BehaviorWalkHome extends Behavior<EntityLiving> {

    private final float a;
    private final Long2LongMap b = new Long2LongOpenHashMap();
    private int c;
    private long d;

    public BehaviorWalkHome(float f) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.HOME, MemoryStatus.VALUE_ABSENT));
        this.a = f;
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        if (worldserver.getTime() - this.d < 20L) {
            return false;
        } else {
            EntityCreature entitycreature = (EntityCreature) entityliving;
            VillagePlace villageplace = worldserver.B();
            Optional<BlockPosition> optional = villageplace.c(VillagePlaceType.q.c(), (blockposition) -> {
                return true;
            }, new BlockPosition(entityliving), 48, VillagePlace.Occupancy.ANY);

            return optional.isPresent() && ((BlockPosition) optional.get()).m(new BaseBlockPosition(entitycreature.locX, entitycreature.locY, entitycreature.locZ)) > 4.0D;
        }
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        this.c = 0;
        this.d = worldserver.getTime() + (long) worldserver.getRandom().nextInt(20);
        EntityCreature entitycreature = (EntityCreature) entityliving;
        VillagePlace villageplace = worldserver.B();
        Predicate<BlockPosition> predicate = (blockposition) -> {
            long j = blockposition.asLong();

            if (this.b.containsKey(j)) {
                return false;
            } else if (++this.c >= 5) {
                return false;
            } else {
                this.b.put(j, this.d + 40L);
                return true;
            }
        };
        Stream<BlockPosition> stream = villageplace.a(VillagePlaceType.q.c(), predicate, new BlockPosition(entityliving), 48, VillagePlace.Occupancy.ANY);
        PathEntity pathentity = entitycreature.getNavigation().a(stream, VillagePlaceType.q.d());

        if (pathentity != null && pathentity.h()) {
            BlockPosition blockposition = pathentity.k();
            Optional<VillagePlaceType> optional = villageplace.c(blockposition);

            if (optional.isPresent()) {
                entityliving.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(blockposition, this.a, 1)));
                PacketDebug.c(worldserver, blockposition);
            }
        } else if (this.c < 5) {
            this.b.long2LongEntrySet().removeIf((entry) -> {
                return entry.getLongValue() < this.d;
            });
        }

    }
}
