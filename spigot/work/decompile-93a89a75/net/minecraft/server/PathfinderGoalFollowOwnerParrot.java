package net.minecraft.server;

public class PathfinderGoalFollowOwnerParrot extends PathfinderGoalFollowOwner {

    public PathfinderGoalFollowOwnerParrot(EntityTameableAnimal entitytameableanimal, double d0, float f, float f1) {
        super(entitytameableanimal, d0, f, f1);
    }

    @Override
    protected boolean a(BlockPosition blockposition) {
        IBlockData iblockdata = this.b.getType(blockposition);

        return (iblockdata.a((IBlockAccess) this.b, blockposition, (Entity) this.a) || iblockdata.a(TagsBlock.LEAVES)) && this.b.isEmpty(blockposition.up()) && this.b.isEmpty(blockposition.up(2));
    }
}
