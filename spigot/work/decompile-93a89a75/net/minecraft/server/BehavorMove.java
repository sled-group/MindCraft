package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import javax.annotation.Nullable;

public class BehavorMove extends Behavior<EntityInsentient> {

    @Nullable
    private PathEntity a;
    @Nullable
    private BlockPosition b;
    private float c;
    private int d;

    public BehavorMove(int i) {
        super(ImmutableMap.of(MemoryModuleType.PATH, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT), i);
    }

    protected boolean a(WorldServer worldserver, EntityInsentient entityinsentient) {
        BehaviorController<?> behaviorcontroller = entityinsentient.getBehaviorController();
        MemoryTarget memorytarget = (MemoryTarget) behaviorcontroller.getMemory(MemoryModuleType.WALK_TARGET).get();

        if (!this.a(entityinsentient, memorytarget) && this.a(entityinsentient, memorytarget, worldserver.getTime())) {
            this.b = memorytarget.a().a();
            return true;
        } else {
            behaviorcontroller.removeMemory(MemoryModuleType.WALK_TARGET);
            return false;
        }
    }

    protected boolean g(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        if (this.a != null && this.b != null) {
            Optional<MemoryTarget> optional = entityinsentient.getBehaviorController().getMemory(MemoryModuleType.WALK_TARGET);
            NavigationAbstract navigationabstract = entityinsentient.getNavigation();

            return !navigationabstract.n() && optional.isPresent() && !this.a(entityinsentient, (MemoryTarget) optional.get());
        } else {
            return false;
        }
    }

    protected void f(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        entityinsentient.getNavigation().o();
        entityinsentient.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
        entityinsentient.getBehaviorController().removeMemory(MemoryModuleType.PATH);
        this.a = null;
    }

    protected void a(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        entityinsentient.getBehaviorController().setMemory(MemoryModuleType.PATH, (Object) this.a);
        entityinsentient.getNavigation().a(this.a, (double) this.c);
        this.d = worldserver.getRandom().nextInt(10);
    }

    protected void d(WorldServer worldserver, EntityInsentient entityinsentient, long i) {
        --this.d;
        if (this.d <= 0) {
            PathEntity pathentity = entityinsentient.getNavigation().l();
            BehaviorController<?> behaviorcontroller = entityinsentient.getBehaviorController();

            if (this.a != pathentity) {
                this.a = pathentity;
                behaviorcontroller.setMemory(MemoryModuleType.PATH, (Object) pathentity);
            }

            if (pathentity != null && this.b != null) {
                MemoryTarget memorytarget = (MemoryTarget) behaviorcontroller.getMemory(MemoryModuleType.WALK_TARGET).get();

                if (memorytarget.a().a().m(this.b) > 4.0D && this.a(entityinsentient, memorytarget, worldserver.getTime())) {
                    this.b = memorytarget.a().a();
                    this.a(worldserver, entityinsentient, i);
                }

            }
        }
    }

    private boolean a(EntityInsentient entityinsentient, MemoryTarget memorytarget, long i) {
        BlockPosition blockposition = memorytarget.a().a();

        this.a = entityinsentient.getNavigation().a(blockposition, 0);
        this.c = memorytarget.b();
        if (!this.a(entityinsentient, memorytarget)) {
            BehaviorController<?> behaviorcontroller = entityinsentient.getBehaviorController();
            boolean flag = this.a != null && this.a.h();

            if (flag) {
                behaviorcontroller.setMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, Optional.empty());
            } else if (!behaviorcontroller.hasMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE)) {
                behaviorcontroller.setMemory(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, (Object) i);
            }

            if (this.a != null) {
                return true;
            }

            Vec3D vec3d = RandomPositionGenerator.a((EntityCreature) entityinsentient, 10, 7, new Vec3D(blockposition));

            if (vec3d != null) {
                this.a = entityinsentient.getNavigation().a(vec3d.x, vec3d.y, vec3d.z, 0);
                return this.a != null;
            }
        }

        return false;
    }

    private boolean a(EntityInsentient entityinsentient, MemoryTarget memorytarget) {
        return memorytarget.a().a().n(new BlockPosition(entityinsentient)) <= memorytarget.c();
    }
}
