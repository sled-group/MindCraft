package net.minecraft.server;

import java.util.List;
import java.util.stream.Stream;
import javax.annotation.Nullable;

public abstract class EntityFishSchool extends EntityFish {

    private EntityFishSchool b;
    private int c = 1;

    public EntityFishSchool(EntityTypes<? extends EntityFishSchool> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    protected void initPathfinder() {
        super.initPathfinder();
        this.goalSelector.a(5, new PathfinderGoalFishSchool(this));
    }

    @Override
    public int dC() {
        return this.dX();
    }

    public int dX() {
        return super.dC();
    }

    @Override
    protected boolean dV() {
        return !this.dY();
    }

    public boolean dY() {
        return this.b != null && this.b.isAlive();
    }

    public EntityFishSchool a(EntityFishSchool entityfishschool) {
        this.b = entityfishschool;
        entityfishschool.ee();
        return entityfishschool;
    }

    public void dZ() {
        this.b.ef();
        this.b = null;
    }

    private void ee() {
        ++this.c;
    }

    private void ef() {
        --this.c;
    }

    public boolean ea() {
        return this.eb() && this.c < this.dX();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.eb() && this.world.random.nextInt(200) == 1) {
            List<EntityFish> list = this.world.a(this.getClass(), this.getBoundingBox().grow(8.0D, 8.0D, 8.0D));

            if (list.size() <= 1) {
                this.c = 1;
            }
        }

    }

    public boolean eb() {
        return this.c > 1;
    }

    public boolean ec() {
        return this.h((Entity) this.b) <= 121.0D;
    }

    public void ed() {
        if (this.dY()) {
            this.getNavigation().a((Entity) this.b, 1.0D);
        }

    }

    public void a(Stream<EntityFishSchool> stream) {
        stream.limit((long) (this.dX() - this.c)).filter((entityfishschool) -> {
            return entityfishschool != this;
        }).forEach((entityfishschool) -> {
            entityfishschool.a(this);
        });
    }

    @Nullable
    @Override
    public GroupDataEntity prepare(GeneratorAccess generatoraccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
        super.prepare(generatoraccess, difficultydamagescaler, enummobspawn, (GroupDataEntity) groupdataentity, nbttagcompound);
        if (groupdataentity == null) {
            groupdataentity = new EntityFishSchool.a(this);
        } else {
            this.a(((EntityFishSchool.a) groupdataentity).a);
        }

        return (GroupDataEntity) groupdataentity;
    }

    public static class a implements GroupDataEntity {

        public final EntityFishSchool a;

        public a(EntityFishSchool entityfishschool) {
            this.a = entityfishschool;
        }
    }
}
