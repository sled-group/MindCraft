package net.minecraft.server;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

public class PathfinderGoalLlamaFollow extends PathfinderGoal {

    public final EntityLlama a;
    private double b;
    private int c;

    public PathfinderGoalLlamaFollow(EntityLlama entityllama, double d0) {
        this.a = entityllama;
        this.b = d0;
        this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
    }

    @Override
    public boolean a() {
        if (!this.a.isLeashed() && !this.a.eI()) {
            List<Entity> list = this.a.world.getEntities(this.a, this.a.getBoundingBox().grow(9.0D, 4.0D, 9.0D), (entity) -> {
                EntityTypes<?> entitytypes = entity.getEntityType();

                return entitytypes == EntityTypes.LLAMA || entitytypes == EntityTypes.TRADER_LLAMA;
            });
            EntityLlama entityllama = null;
            double d0 = Double.MAX_VALUE;
            Iterator iterator = list.iterator();

            Entity entity;
            EntityLlama entityllama1;
            double d1;

            while (iterator.hasNext()) {
                entity = (Entity) iterator.next();
                entityllama1 = (EntityLlama) entity;
                if (entityllama1.eI() && !entityllama1.eH()) {
                    d1 = this.a.h((Entity) entityllama1);
                    if (d1 <= d0) {
                        d0 = d1;
                        entityllama = entityllama1;
                    }
                }
            }

            if (entityllama == null) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    entity = (Entity) iterator.next();
                    entityllama1 = (EntityLlama) entity;
                    if (entityllama1.isLeashed() && !entityllama1.eH()) {
                        d1 = this.a.h((Entity) entityllama1);
                        if (d1 <= d0) {
                            d0 = d1;
                            entityllama = entityllama1;
                        }
                    }
                }
            }

            if (entityllama == null) {
                return false;
            } else if (d0 < 4.0D) {
                return false;
            } else if (!entityllama.isLeashed() && !this.a(entityllama, 1)) {
                return false;
            } else {
                this.a.a(entityllama);
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean b() {
        if (this.a.eI() && this.a.eJ().isAlive() && this.a(this.a, 0)) {
            double d0 = this.a.h((Entity) this.a.eJ());

            if (d0 > 676.0D) {
                if (this.b <= 3.0D) {
                    this.b *= 1.2D;
                    this.c = 40;
                    return true;
                }

                if (this.c == 0) {
                    return false;
                }
            }

            if (this.c > 0) {
                --this.c;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void d() {
        this.a.eG();
        this.b = 2.1D;
    }

    @Override
    public void e() {
        if (this.a.eI()) {
            EntityLlama entityllama = this.a.eJ();
            double d0 = (double) this.a.g((Entity) entityllama);
            float f = 2.0F;
            Vec3D vec3d = (new Vec3D(entityllama.locX - this.a.locX, entityllama.locY - this.a.locY, entityllama.locZ - this.a.locZ)).d().a(Math.max(d0 - 2.0D, 0.0D));

            this.a.getNavigation().a(this.a.locX + vec3d.x, this.a.locY + vec3d.y, this.a.locZ + vec3d.z, this.b);
        }
    }

    private boolean a(EntityLlama entityllama, int i) {
        if (i > 8) {
            return false;
        } else if (entityllama.eI()) {
            if (entityllama.eJ().isLeashed()) {
                return true;
            } else {
                EntityLlama entityllama1 = entityllama.eJ();

                ++i;
                return this.a(entityllama1, i);
            }
        } else {
            return false;
        }
    }
}
