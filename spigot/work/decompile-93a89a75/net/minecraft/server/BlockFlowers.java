package net.minecraft.server;

public class BlockFlowers extends BlockPlant {

    protected static final VoxelShape a = Block.a(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    private final MobEffectList b;
    private final int c;

    public BlockFlowers(MobEffectList mobeffectlist, int i, Block.Info block_info) {
        super(block_info);
        this.b = mobeffectlist;
        if (mobeffectlist.isInstant()) {
            this.c = i;
        } else {
            this.c = i * 20;
        }

    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        Vec3D vec3d = iblockdata.l(iblockaccess, blockposition);

        return BlockFlowers.a.a(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public Block.EnumRandomOffset R_() {
        return Block.EnumRandomOffset.XZ;
    }

    public MobEffectList d() {
        return this.b;
    }

    public int e() {
        return this.c;
    }
}
