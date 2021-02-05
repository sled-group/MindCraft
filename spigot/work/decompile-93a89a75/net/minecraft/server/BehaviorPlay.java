package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import javax.annotation.Nullable;

public class BehaviorPlay extends Behavior<EntityCreature> {

    public BehaviorPlay() {
        super(ImmutableMap.of(MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.REGISTERED));
    }

    protected boolean a(WorldServer worldserver, EntityCreature entitycreature) {
        return worldserver.getRandom().nextInt(10) == 0 && this.e(entitycreature);
    }

    protected void a(WorldServer worldserver, EntityCreature entitycreature, long i) {
        EntityLiving entityliving = this.b((EntityLiving) entitycreature);

        if (entityliving != null) {
            this.a(worldserver, entitycreature, entityliving);
        } else {
            Optional<EntityLiving> optional = this.b(entitycreature);

            if (optional.isPresent()) {
                a(entitycreature, (EntityLiving) optional.get());
            } else {
                this.a(entitycreature).ifPresent((entityliving1) -> {
                    a(entitycreature, entityliving1);
                });
            }
        }
    }

    private void a(WorldServer worldserver, EntityCreature entitycreature, EntityLiving entityliving) {
        for (int i = 0; i < 10; ++i) {
            Vec3D vec3d = RandomPositionGenerator.b(entitycreature, 20, 8);

            if (vec3d != null && worldserver.b_(new BlockPosition(vec3d))) {
                entitycreature.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(vec3d, 0.6F, 0)));
                return;
            }
        }

    }

    private static void a(EntityCreature entitycreature, EntityLiving entityliving) {
        BehaviorController<?> behaviorcontroller = entitycreature.getBehaviorController();

        behaviorcontroller.setMemory(MemoryModuleType.INTERACTION_TARGET, (Object) entityliving);
        behaviorcontroller.setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorPositionEntity(entityliving)));
        behaviorcontroller.setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(new BehaviorPositionEntity(entityliving), 0.6F, 1)));
    }

    private Optional<EntityLiving> a(EntityCreature entitycreature) {
        return this.d(entitycreature).stream().findAny();
    }

    private Optional<EntityLiving> b(EntityCreature entitycreature) {
        Map<EntityLiving, Integer> map = this.c(entitycreature);

        return map.entrySet().stream().sorted(Comparator.comparingInt(Entry::getValue)).filter((entry) -> {
            return (Integer) entry.getValue() > 0 && (Integer) entry.getValue() <= 5;
        }).map(Entry::getKey).findFirst();
    }

    private Map<EntityLiving, Integer> c(EntityCreature entitycreature) {
        Map<EntityLiving, Integer> map = Maps.newHashMap();

        this.d(entitycreature).stream().filter(this::c).forEach((entityliving) -> {
            Integer integer = (Integer) map.compute(this.a(entityliving), (entityliving1, integer1) -> {
                return integer1 == null ? 1 : integer1 + 1;
            });
        });
        return map;
    }

    private List<EntityLiving> d(EntityCreature entitycreature) {
        return (List) entitycreature.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES).get();
    }

    private EntityLiving a(EntityLiving entityliving) {
        return (EntityLiving) entityliving.getBehaviorController().getMemory(MemoryModuleType.INTERACTION_TARGET).get();
    }

    @Nullable
    private EntityLiving b(EntityLiving entityliving) {
        return (EntityLiving) ((List) entityliving.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES).get()).stream().filter((entityliving1) -> {
            return this.a(entityliving, entityliving1);
        }).findAny().orElse((Object) null);
    }

    private boolean c(EntityLiving entityliving) {
        return entityliving.getBehaviorController().getMemory(MemoryModuleType.INTERACTION_TARGET).isPresent();
    }

    private boolean a(EntityLiving entityliving, EntityLiving entityliving1) {
        return entityliving1.getBehaviorController().getMemory(MemoryModuleType.INTERACTION_TARGET).filter((entityliving2) -> {
            return entityliving2 == entityliving;
        }).isPresent();
    }

    private boolean e(EntityCreature entitycreature) {
        return entitycreature.getBehaviorController().hasMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES);
    }
}
