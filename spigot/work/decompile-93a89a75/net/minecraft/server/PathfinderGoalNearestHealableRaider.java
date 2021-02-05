package net.minecraft.server;

import java.util.function.Predicate;
import javax.annotation.Nullable;

public class PathfinderGoalNearestHealableRaider<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {

    private int i = 0;

    public PathfinderGoalNearestHealableRaider(EntityRaider entityraider, Class<T> oclass, boolean flag, @Nullable Predicate<EntityLiving> predicate) {
        super(entityraider, oclass, 500, flag, false, predicate);
    }

    public int h() {
        return this.i;
    }

    public void j() {
        --this.i;
    }

    @Override
    public boolean a() {
        if (this.i <= 0 && this.e.getRandom().nextBoolean()) {
            if (!((EntityRaider) this.e).ek()) {
                return false;
            } else {
                this.g();
                return this.c != null;
            }
        } else {
            return false;
        }
    }

    @Override
    public void c() {
        this.i = 200;
        super.c();
    }
}
