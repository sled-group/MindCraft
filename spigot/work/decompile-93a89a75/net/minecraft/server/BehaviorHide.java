package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;

public class BehaviorHide extends Behavior<EntityLiving> {

    private final int a;
    private final int b;
    private int c;

    public BehaviorHide(int i, int j) {
        super(ImmutableMap.of(MemoryModuleType.HIDING_PLACE, MemoryStatus.VALUE_PRESENT, MemoryModuleType.HEARD_BELL_TIME, MemoryStatus.VALUE_PRESENT));
        this.b = i * 20;
        this.c = 0;
        this.a = j;
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        BehaviorController<?> behaviorcontroller = entityliving.getBehaviorController();
        Optional<Long> optional = behaviorcontroller.getMemory(MemoryModuleType.HEARD_BELL_TIME);
        boolean flag = (Long) optional.get() + 300L <= i;

        if (this.c <= this.b && !flag) {
            BlockPosition blockposition = ((GlobalPos) behaviorcontroller.getMemory(MemoryModuleType.HIDING_PLACE).get()).getBlockPosition();

            if (blockposition.a((BaseBlockPosition) (new BlockPosition(entityliving)), (double) (this.a + 1))) {
                ++this.c;
            }

        } else {
            behaviorcontroller.removeMemory(MemoryModuleType.HEARD_BELL_TIME);
            behaviorcontroller.removeMemory(MemoryModuleType.HIDING_PLACE);
            behaviorcontroller.a(worldserver.getDayTime(), worldserver.getTime());
            this.c = 0;
        }
    }
}
