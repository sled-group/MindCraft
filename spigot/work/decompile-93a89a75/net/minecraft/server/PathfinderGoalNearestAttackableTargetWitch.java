package net.minecraft.server;

import java.util.function.Predicate;
import javax.annotation.Nullable;

public class PathfinderGoalNearestAttackableTargetWitch<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {

    private boolean i = true;

    public PathfinderGoalNearestAttackableTargetWitch(EntityRaider entityraider, Class<T> oclass, int i, boolean flag, boolean flag1, @Nullable Predicate<EntityLiving> predicate) {
        super(entityraider, oclass, i, flag, flag1, predicate);
    }

    public void a(boolean flag) {
        this.i = flag;
    }

    @Override
    public boolean a() {
        return this.i && super.a();
    }
}
