package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Iterator;

public class BehaviorBellRing extends Behavior<EntityLiving> {

    public BehaviorBellRing() {
        super(ImmutableMap.of(MemoryModuleType.MEETING_POINT, MemoryStatus.VALUE_PRESENT));
    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return worldserver.random.nextFloat() > 0.95F;
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        BlockPosition blockposition = ((GlobalPos) behaviorcontroller.getMemory(MemoryModuleType.MEETING_POINT).get()).getBlockPosition();

        if (blockposition.a((BaseBlockPosition) (new BlockPosition(entityliving)), 3.0D)) {
            IBlockData iblockdata = worldserver.getType(blockposition);

            if (iblockdata.getBlock() == Blocks.BELL) {
                BlockBell blockbell = (BlockBell) iblockdata.getBlock();
                Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

                while (iterator.hasNext()) {
                    EnumDirection enumdirection = (EnumDirection) iterator.next();

                    if (blockbell.a(worldserver, iblockdata, worldserver.getTileEntity(blockposition), new MovingObjectPositionBlock(new Vec3D(0.5D, 0.5D, 0.5D), enumdirection, blockposition, false), (EntityHuman) null, false)) {
                        break;
                    }
                }
            }
        }

    }
}
