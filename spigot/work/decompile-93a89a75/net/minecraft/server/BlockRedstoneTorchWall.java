package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockRedstoneTorchWall extends BlockRedstoneTorch {

    public static final BlockStateDirection b = BlockFacingHorizontal.FACING;
    public static final BlockStateBoolean c = BlockRedstoneTorch.LIT;

    protected BlockRedstoneTorchWall(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockRedstoneTorchWall.b, EnumDirection.NORTH)).set(BlockRedstoneTorchWall.c, true));
    }

    @Override
    public String l() {
        return this.getItem().getName();
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockTorchWall.j(iblockdata);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return Blocks.WALL_TORCH.canPlace(iblockdata, iworldreader, blockposition);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return Blocks.WALL_TORCH.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Nullable
    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        IBlockData iblockdata = Blocks.WALL_TORCH.getPlacedState(blockactioncontext);

        return iblockdata == null ? null : (IBlockData) this.getBlockData().set(BlockRedstoneTorchWall.b, iblockdata.get(BlockRedstoneTorchWall.b));
    }

    @Override
    protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata) {
        EnumDirection enumdirection = ((EnumDirection) iblockdata.get(BlockRedstoneTorchWall.b)).opposite();

        return world.isBlockFacePowered(blockposition.shift(enumdirection), enumdirection);
    }

    @Override
    public int a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, EnumDirection enumdirection) {
        return (Boolean) iblockdata.get(BlockRedstoneTorchWall.c) && iblockdata.get(BlockRedstoneTorchWall.b) != enumdirection ? 15 : 0;
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return Blocks.WALL_TORCH.a(iblockdata, enumblockrotation);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return Blocks.WALL_TORCH.a(iblockdata, enumblockmirror);
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockRedstoneTorchWall.b, BlockRedstoneTorchWall.c);
    }
}
