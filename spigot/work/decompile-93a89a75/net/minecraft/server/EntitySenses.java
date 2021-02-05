package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;

public class EntitySenses {

    private final EntityInsentient a;
    private final List<Entity> b = Lists.newArrayList();
    private final List<Entity> c = Lists.newArrayList();

    public EntitySenses(EntityInsentient entityinsentient) {
        this.a = entityinsentient;
    }

    public void a() {
        this.b.clear();
        this.c.clear();
    }

    public boolean a(Entity entity) {
        if (this.b.contains(entity)) {
            return true;
        } else if (this.c.contains(entity)) {
            return false;
        } else {
            this.a.world.getMethodProfiler().enter("canSee");
            boolean flag = this.a.hasLineOfSight(entity);

            this.a.world.getMethodProfiler().exit();
            if (flag) {
                this.b.add(entity);
            } else {
                this.c.add(entity);
            }

            return flag;
        }
    }
}
