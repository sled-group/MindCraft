package net.minecraft.server;

import javax.annotation.Nullable;

public class PathfinderGoalStrollVillage extends PathfinderGoalRandomStroll {

    public PathfinderGoalStrollVillage(EntityCreature entitycreature, double d0) {
        super(entitycreature, d0, 10);
    }

    @Override
    public boolean a() {
        WorldServer worldserver = (WorldServer) this.a.world;
        BlockPosition blockposition = new BlockPosition(this.a);

        return worldserver.b_(blockposition) ? false : super.a();
    }

    @Nullable
    @Override
    protected Vec3D g() {
        WorldServer worldserver = (WorldServer) this.a.world;
        BlockPosition blockposition = new BlockPosition(this.a);
        SectionPosition sectionposition = SectionPosition.a(blockposition);
        SectionPosition sectionposition1 = BehaviorUtil.a(worldserver, sectionposition, 2);

        if (sectionposition1 != sectionposition) {
            BlockPosition blockposition1 = sectionposition1.t();

            return RandomPositionGenerator.a(this.a, 10, 7, new Vec3D((double) blockposition1.getX(), (double) blockposition1.getY(), (double) blockposition1.getZ()));
        } else {
            return null;
        }
    }
}
