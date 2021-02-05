package net.minecraft.server;

public class PathfinderGoalJumpOnBlock extends PathfinderGoalGotoTarget {

    private final EntityCat g;

    public PathfinderGoalJumpOnBlock(EntityCat entitycat, double d0) {
        super(entitycat, d0, 8);
        this.g = entitycat;
    }

    @Override
    public boolean a() {
        return this.g.isTamed() && !this.g.isSitting() && super.a();
    }

    @Override
    public void c() {
        super.c();
        this.g.getGoalSit().setSitting(false);
    }

    @Override
    public void d() {
        super.d();
        this.g.setSitting(false);
    }

    @Override
    public void e() {
        super.e();
        this.g.getGoalSit().setSitting(false);
        if (!this.k()) {
            this.g.setSitting(false);
        } else if (!this.g.isSitting()) {
            this.g.setSitting(true);
        }

    }

    @Override
    protected boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
        if (!iworldreader.isEmpty(blockposition.up())) {
            return false;
        } else {
            IBlockData iblockdata = iworldreader.getType(blockposition);
            Block block = iblockdata.getBlock();

            return block == Blocks.CHEST ? TileEntityChest.a((IBlockAccess) iworldreader, blockposition) < 1 : (block == Blocks.FURNACE && (Boolean) iblockdata.get(BlockFurnaceFurace.LIT) ? true : block.a(TagsBlock.BEDS) && iblockdata.get(BlockBed.PART) != BlockPropertyBedPart.HEAD);
        }
    }
}
