package net.minecraft.server;

public class BlockWeb extends Block {

    public BlockWeb(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
        entity.a(iblockdata, new Vec3D(0.25D, 0.05000000074505806D, 0.25D));
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }
}
