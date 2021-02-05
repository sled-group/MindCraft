package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import java.util.Optional;

public class BehaviorStrollRandom extends Behavior<EntityCreature> {

    private final float a;
    private final int b;
    private final int c;

    public BehaviorStrollRandom(float f) {
        this(f, 10, 7);
    }

    public BehaviorStrollRandom(float f, int i, int j) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));
        this.a = f;
        this.b = i;
        this.c = j;
    }

    protected void a(WorldServer worldserver, EntityCreature entitycreature, long i) {
        BlockPosition blockposition = new BlockPosition(entitycreature);

        if (worldserver.b_(blockposition)) {
            this.a(entitycreature);
        } else {
            SectionPosition sectionposition = SectionPosition.a(blockposition);
            SectionPosition sectionposition1 = BehaviorUtil.a(worldserver, sectionposition, 2);

            if (sectionposition1 != sectionposition) {
                this.a(entitycreature, sectionposition1);
            } else {
                this.a(entitycreature);
            }
        }

    }

    private void a(EntityCreature entitycreature, SectionPosition sectionposition) {
        BlockPosition blockposition = sectionposition.t();
        Optional<Vec3D> optional = Optional.ofNullable(RandomPositionGenerator.a(entitycreature, this.b, this.c, new Vec3D((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ())));

        entitycreature.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, optional.map((vec3d) -> {
            return new MemoryTarget(vec3d, this.a, 0);
        }));
    }

    private void a(EntityCreature entitycreature) {
        Optional<Vec3D> optional = Optional.ofNullable(RandomPositionGenerator.b(entitycreature, this.b, this.c));

        entitycreature.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, optional.map((vec3d) -> {
            return new MemoryTarget(vec3d, this.a, 0);
        }));
    }
}
