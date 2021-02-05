package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;

public class BehaviorPickupItem extends Behavior<EntityVillager> {

    private List<EntityItem> a = new ArrayList();

    public BehaviorPickupItem() {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        this.a = worldserver.a(EntityItem.class, entityvillager.getBoundingBox().grow(4.0D, 2.0D, 4.0D));
        return !this.a.isEmpty();
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        EntityItem entityitem = (EntityItem) this.a.get(worldserver.random.nextInt(this.a.size()));

        if (entityvillager.b(entityitem.getItemStack().getItem())) {
            Vec3D vec3d = entityitem.getPositionVector();

            entityvillager.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorTarget(new BlockPosition(vec3d))));
            entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(vec3d, 0.5F, 0)));
        }

    }
}
