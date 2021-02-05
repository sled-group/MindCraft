package net.minecraft.server;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public abstract class EntityMonsterPatrolling extends EntityMonster {

    private BlockPosition patrolTarget;
    private boolean patrolLeader;
    private boolean patrolling;

    protected EntityMonsterPatrolling(EntityTypes<? extends EntityMonsterPatrolling> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    protected void initPathfinder() {
        super.initPathfinder();
        this.goalSelector.a(4, new EntityMonsterPatrolling.a<>(this, 0.7D, 0.595D));
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.patrolTarget != null) {
            nbttagcompound.set("PatrolTarget", GameProfileSerializer.a(this.patrolTarget));
        }

        nbttagcompound.setBoolean("PatrolLeader", this.patrolLeader);
        nbttagcompound.setBoolean("Patrolling", this.patrolling);
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.hasKey("PatrolTarget")) {
            this.patrolTarget = GameProfileSerializer.c(nbttagcompound.getCompound("PatrolTarget"));
        }

        this.patrolLeader = nbttagcompound.getBoolean("PatrolLeader");
        this.patrolling = nbttagcompound.getBoolean("Patrolling");
    }

    @Override
    public double aO() {
        return -0.45D;
    }

    public boolean dX() {
        return true;
    }

    @Nullable
    @Override
    public GroupDataEntity prepare(GeneratorAccess generatoraccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
        if (enummobspawn != EnumMobSpawn.PATROL && enummobspawn != EnumMobSpawn.EVENT && enummobspawn != EnumMobSpawn.STRUCTURE && this.random.nextFloat() < 0.06F && this.dX()) {
            this.patrolLeader = true;
        }

        if (this.isPatrolLeader()) {
            this.setSlot(EnumItemSlot.HEAD, Raid.s());
            this.a(EnumItemSlot.HEAD, 2.0F);
        }

        if (enummobspawn == EnumMobSpawn.PATROL) {
            this.patrolling = true;
        }

        return super.prepare(generatoraccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
    }

    public static boolean b(EntityTypes<? extends EntityMonsterPatrolling> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
        return generatoraccess.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 8 ? false : d(entitytypes, generatoraccess, enummobspawn, blockposition, random);
    }

    @Override
    public boolean isTypeNotPersistent(double d0) {
        return !this.patrolling || d0 > 16384.0D;
    }

    public void setPatrolTarget(BlockPosition blockposition) {
        this.patrolTarget = blockposition;
        this.patrolling = true;
    }

    public BlockPosition getPatrolTarget() {
        return this.patrolTarget;
    }

    public boolean dZ() {
        return this.patrolTarget != null;
    }

    public void setPatrolLeader(boolean flag) {
        this.patrolLeader = flag;
        this.patrolling = true;
    }

    public boolean isPatrolLeader() {
        return this.patrolLeader;
    }

    public boolean ec() {
        return true;
    }

    public void ed() {
        this.patrolTarget = (new BlockPosition(this)).b(-500 + this.random.nextInt(1000), 0, -500 + this.random.nextInt(1000));
        this.patrolling = true;
    }

    protected boolean isPatrolling() {
        return this.patrolling;
    }

    public static class a<T extends EntityMonsterPatrolling> extends PathfinderGoal {

        private final T a;
        private final double b;
        private final double c;

        public a(T t0, double d0, double d1) {
            this.a = t0;
            this.b = d0;
            this.c = d1;
            this.a(EnumSet.of(PathfinderGoal.Type.MOVE));
        }

        @Override
        public boolean a() {
            return this.a.isPatrolling() && this.a.getGoalTarget() == null && !this.a.isVehicle() && this.a.dZ();
        }

        @Override
        public void c() {}

        @Override
        public void d() {}

        @Override
        public void e() {
            boolean flag = this.a.isPatrolLeader();
            NavigationAbstract navigationabstract = this.a.getNavigation();

            if (navigationabstract.n()) {
                if (flag && this.a.getPatrolTarget().a((IPosition) this.a.getPositionVector(), 10.0D)) {
                    this.a.ed();
                } else {
                    Vec3D vec3d = new Vec3D(this.a.getPatrolTarget());
                    Vec3D vec3d1 = new Vec3D(this.a.locX, this.a.locY, this.a.locZ);
                    Vec3D vec3d2 = vec3d1.d(vec3d);

                    vec3d = vec3d2.b(90.0F).a(0.4D).e(vec3d);
                    Vec3D vec3d3 = vec3d.d(vec3d1).d().a(10.0D).e(vec3d1);
                    BlockPosition blockposition = new BlockPosition(vec3d3);

                    blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, blockposition);
                    if (!navigationabstract.a((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), flag ? this.c : this.b)) {
                        this.g();
                    } else if (flag) {
                        List<EntityMonsterPatrolling> list = this.a.world.a(EntityMonsterPatrolling.class, this.a.getBoundingBox().g(16.0D), (entitymonsterpatrolling) -> {
                            return !entitymonsterpatrolling.isPatrolLeader() && entitymonsterpatrolling.ec();
                        });
                        Iterator iterator = list.iterator();

                        while (iterator.hasNext()) {
                            EntityMonsterPatrolling entitymonsterpatrolling = (EntityMonsterPatrolling) iterator.next();

                            entitymonsterpatrolling.setPatrolTarget(blockposition);
                        }
                    }
                }
            }

        }

        private void g() {
            Random random = this.a.getRandom();
            BlockPosition blockposition = this.a.world.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, (new BlockPosition(this.a)).b(-8 + random.nextInt(16), 0, -8 + random.nextInt(16)));

            this.a.getNavigation().a((double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), this.b);
        }
    }
}
