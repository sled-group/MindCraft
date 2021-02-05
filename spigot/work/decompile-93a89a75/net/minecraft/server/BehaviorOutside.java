package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;

public class BehaviorOutside extends Behavior<EntityLiving> {

    private final float a;

    public BehaviorOutside(float f) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
        this.a = f;
    }

    @Override
    protected void a(WorldServer worldserver, EntityLiving entityliving, long i) {
        Optional<Vec3D> optional = Optional.ofNullable(this.c(worldserver, entityliving));

        if (optional.isPresent()) {
            entityliving.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, optional.map((vec3d) -> {
                return new MemoryTarget(vec3d, this.a, 0);
            }));
        }

    }

    @Override
    protected boolean a(WorldServer worldserver, EntityLiving entityliving) {
        return !worldserver.f(new BlockPosition(entityliving.locX, entityliving.getBoundingBox().minY, entityliving.locZ));
    }

    @Nullable
    private Vec3D c(WorldServer worldserver, EntityLiving entityliving) {
        Random random = entityliving.getRandom();
        BlockPosition blockposition = new BlockPosition(entityliving.locX, entityliving.getBoundingBox().minY, entityliving.locZ);

        for (int i = 0; i < 10; ++i) {
            BlockPosition blockposition1 = blockposition.b(random.nextInt(20) - 10, random.nextInt(6) - 3, random.nextInt(20) - 10);

            if (b(worldserver, entityliving)) {
                return new Vec3D((double) blockposition1.getX(), (double) blockposition1.getY(), (double) blockposition1.getZ());
            }
        }

        return null;
    }

    public static boolean b(WorldServer worldserver, EntityLiving entityliving) {
        return worldserver.f(new BlockPosition(entityliving)) && (double) worldserver.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING, new BlockPosition(entityliving)).getY() <= entityliving.locY;
    }
}
