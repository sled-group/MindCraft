package net.minecraft.server;

import com.google.common.collect.ImmutableSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public final class ProjectileHelper {

    public static MovingObjectPosition a(Entity entity, boolean flag, boolean flag1, @Nullable Entity entity1, RayTrace.BlockCollisionOption raytrace_blockcollisionoption) {
        return a(entity, flag, flag1, entity1, raytrace_blockcollisionoption, true, (entity2) -> {
            return !entity2.isSpectator() && entity2.isInteractable() && (flag1 || !entity2.s(entity1)) && !entity2.noclip;
        }, entity.getBoundingBox().a(entity.getMot()).g(1.0D));
    }

    public static MovingObjectPosition a(Entity entity, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate, RayTrace.BlockCollisionOption raytrace_blockcollisionoption, boolean flag) {
        return a(entity, flag, false, (Entity) null, raytrace_blockcollisionoption, false, predicate, axisalignedbb);
    }

    @Nullable
    public static MovingObjectPositionEntity a(World world, Entity entity, Vec3D vec3d, Vec3D vec3d1, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate) {
        return a(world, entity, vec3d, vec3d1, axisalignedbb, predicate, Double.MAX_VALUE);
    }

    private static MovingObjectPosition a(Entity entity, boolean flag, boolean flag1, @Nullable Entity entity1, RayTrace.BlockCollisionOption raytrace_blockcollisionoption, boolean flag2, Predicate<Entity> predicate, AxisAlignedBB axisalignedbb) {
        double d0 = entity.locX;
        double d1 = entity.locY;
        double d2 = entity.locZ;
        Vec3D vec3d = entity.getMot();
        World world = entity.world;
        Vec3D vec3d1 = new Vec3D(d0, d1, d2);

        if (flag2 && !world.b(entity, entity.getBoundingBox(), (Set) (!flag1 && entity1 != null ? a(entity1) : ImmutableSet.of()))) {
            return new MovingObjectPositionBlock(vec3d1, EnumDirection.a(vec3d.x, vec3d.y, vec3d.z), new BlockPosition(entity), false);
        } else {
            Vec3D vec3d2 = vec3d1.e(vec3d);
            Object object = world.rayTrace(new RayTrace(vec3d1, vec3d2, raytrace_blockcollisionoption, RayTrace.FluidCollisionOption.NONE, entity));

            if (flag) {
                if (((MovingObjectPosition) object).getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
                    vec3d2 = ((MovingObjectPosition) object).getPos();
                }

                MovingObjectPositionEntity movingobjectpositionentity = a(world, entity, vec3d1, vec3d2, axisalignedbb, predicate);

                if (movingobjectpositionentity != null) {
                    object = movingobjectpositionentity;
                }
            }

            return (MovingObjectPosition) object;
        }
    }

    @Nullable
    public static MovingObjectPositionEntity a(World world, Entity entity, Vec3D vec3d, Vec3D vec3d1, AxisAlignedBB axisalignedbb, Predicate<Entity> predicate, double d0) {
        double d1 = d0;
        Entity entity1 = null;
        Iterator iterator = world.getEntities(entity, axisalignedbb, predicate).iterator();

        while (iterator.hasNext()) {
            Entity entity2 = (Entity) iterator.next();
            AxisAlignedBB axisalignedbb1 = entity2.getBoundingBox().g(0.30000001192092896D);
            Optional<Vec3D> optional = axisalignedbb1.b(vec3d, vec3d1);

            if (optional.isPresent()) {
                double d2 = vec3d.distanceSquared((Vec3D) optional.get());

                if (d2 < d1) {
                    entity1 = entity2;
                    d1 = d2;
                }
            }
        }

        if (entity1 == null) {
            return null;
        } else {
            return new MovingObjectPositionEntity(entity1);
        }
    }

    private static Set<Entity> a(Entity entity) {
        Entity entity1 = entity.getVehicle();

        return entity1 != null ? ImmutableSet.of(entity, entity1) : ImmutableSet.of(entity);
    }

    public static final void a(Entity entity, float f) {
        Vec3D vec3d = entity.getMot();
        float f1 = MathHelper.sqrt(Entity.b(vec3d));

        entity.yaw = (float) (MathHelper.d(vec3d.z, vec3d.x) * 57.2957763671875D) + 90.0F;

        for (entity.pitch = (float) (MathHelper.d((double) f1, vec3d.y) * 57.2957763671875D) - 90.0F; entity.pitch - entity.lastPitch < -180.0F; entity.lastPitch -= 360.0F) {
            ;
        }

        while (entity.pitch - entity.lastPitch >= 180.0F) {
            entity.lastPitch += 360.0F;
        }

        while (entity.yaw - entity.lastYaw < -180.0F) {
            entity.lastYaw -= 360.0F;
        }

        while (entity.yaw - entity.lastYaw >= 180.0F) {
            entity.lastYaw += 360.0F;
        }

        entity.pitch = MathHelper.g(f, entity.lastPitch, entity.pitch);
        entity.yaw = MathHelper.g(f, entity.lastYaw, entity.yaw);
    }

    public static EnumHand a(EntityLiving entityliving, Item item) {
        return entityliving.getItemInMainHand().getItem() == item ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND;
    }

    public static EntityArrow a(EntityLiving entityliving, ItemStack itemstack, float f) {
        ItemArrow itemarrow = (ItemArrow) ((ItemArrow) (itemstack.getItem() instanceof ItemArrow ? itemstack.getItem() : Items.ARROW));
        EntityArrow entityarrow = itemarrow.a(entityliving.world, itemstack, entityliving);

        entityarrow.a(entityliving, f);
        if (itemstack.getItem() == Items.TIPPED_ARROW && entityarrow instanceof EntityTippedArrow) {
            ((EntityTippedArrow) entityarrow).b(itemstack);
        }

        return entityarrow;
    }
}
