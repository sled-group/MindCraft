package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Objects;
import java.util.function.Predicate;

public class BehaviorPositionValidate extends Behavior<EntityLiving> {

    private final MemoryModuleType<GlobalPos> a;
    private final Predicate<VillagePlaceType> b;

    public BehaviorPositionValidate(VillagePlaceType villageplacetype, MemoryModuleType<GlobalPos> memorymoduletype) {
        super(ImmutableMap.of(memorymoduletype, MemoryStatus.VALUE_PRESENT));
        this.b = villageplacetype.c();
        this.a = memorymoduletype;
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        GlobalPos globalpos = (GlobalPos) entityliving.getBehaviorController().getMemory(this.a).get();

        return Objects.equals(worldserver.getWorldProvider().getDimensionManager(), globalpos.getDimensionManager()) && globalpos.getBlockPosition().a((IPosition) entityliving.getPositionVector(), 5.0D);
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        GlobalPos globalpos = (GlobalPos) behaviorcontroller.getMemory(this.a).get();
        WorldServer worldserver1 = worldserver.getMinecraftServer().getWorldServer(globalpos.getDimensionManager());

        if (this.a(worldserver1, globalpos.getBlockPosition()) || this.a(worldserver1, globalpos.getBlockPosition(), entityliving)) {
            behaviorcontroller.removeMemory(this.a);
        }

    }

    private boolean a(WorldServer worldserver, BlockPosition blockposition, EntityLiving entityliving) {
        IBlockData iblockdata = worldserver.getType(blockposition);

        return iblockdata.getBlock().a(TagsBlock.BEDS) && (Boolean) iblockdata.get(BlockBed.OCCUPIED) && !entityliving.isSleeping();
    }

    private boolean a(WorldServer worldserver, BlockPosition blockposition) {
        return !worldserver.B().a(blockposition, this.b);
    }
}
