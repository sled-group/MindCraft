package net.minecraft.server;

import java.util.function.Predicate;
import javax.annotation.Nullable;

public class PathfinderGoalRandomTargetNonTamed<T extends EntityLiving> extends PathfinderGoalNearestAttackableTarget<T> {

    private final EntityTameableAnimal i;

    public PathfinderGoalRandomTargetNonTamed(EntityTameableAnimal entitytameableanimal, Class<T> oclass, boolean flag, @Nullable Predicate<EntityLiving> predicate) {
        super(entitytameableanimal, oclass, 10, flag, false, predicate);
        this.i = entitytameableanimal;
    }

    @Override
    public boolean a() {
        return !this.i.isTamed() && super.a();
    }

    @Override
    public boolean b() {
        return this.d != null ? this.d.a(this.e, this.c) : super.b();
    }
}
