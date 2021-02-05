package net.minecraft.server;

import java.util.Map;

public class BlockHugeMushroom extends Block {

    public static final BlockStateBoolean a = BlockSprawling.a;
    public static final BlockStateBoolean b = BlockSprawling.b;
    public static final BlockStateBoolean c = BlockSprawling.c;
    public static final BlockStateBoolean d = BlockSprawling.d;
    public static final BlockStateBoolean e = BlockSprawling.e;
    public static final BlockStateBoolean f = BlockSprawling.f;
    private static final Map<EnumDirection, BlockStateBoolean> g = BlockSprawling.g;

    public BlockHugeMushroom(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockHugeMushroom.a, true)).set(BlockHugeMushroom.b, true)).set(BlockHugeMushroom.c, true)).set(BlockHugeMushroom.d, true)).set(BlockHugeMushroom.e, true)).set(BlockHugeMushroom.f, true));
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        World world = blockactioncontext.getWorld();
        BlockPosition blockposition = blockactioncontext.getClickPosition();

        return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) this.getBlockData().set(BlockHugeMushroom.f, this != world.getType(blockposition.down()).getBlock())).set(BlockHugeMushroom.e, this != world.getType(blockposition.up()).getBlock())).set(BlockHugeMushroom.a, this != world.getType(blockposition.north()).getBlock())).set(BlockHugeMushroom.b, this != world.getType(blockposition.east()).getBlock())).set(BlockHugeMushroom.c, this != world.getType(blockposition.south()).getBlock())).set(BlockHugeMushroom.d, this != world.getType(blockposition.west()).getBlock());
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return iblockdata1.getBlock() == this ? (IBlockData) iblockdata.set((IBlockState) BlockHugeMushroom.g.get(enumdirection), false) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) iblockdata.set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.NORTH)), iblockdata.get(BlockHugeMushroom.a))).set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.SOUTH)), iblockdata.get(BlockHugeMushroom.c))).set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.EAST)), iblockdata.get(BlockHugeMushroom.b))).set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.WEST)), iblockdata.get(BlockHugeMushroom.d))).set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.UP)), iblockdata.get(BlockHugeMushroom.e))).set((IBlockState) BlockHugeMushroom.g.get(enumblockrotation.a(EnumDirection.DOWN)), iblockdata.get(BlockHugeMushroom.f));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return (IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) ((IBlockData) iblockdata.set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.NORTH)), iblockdata.get(BlockHugeMushroom.a))).set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.SOUTH)), iblockdata.get(BlockHugeMushroom.c))).set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.EAST)), iblockdata.get(BlockHugeMushroom.b))).set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.WEST)), iblockdata.get(BlockHugeMushroom.d))).set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.UP)), iblockdata.get(BlockHugeMushroom.e))).set((IBlockState) BlockHugeMushroom.g.get(enumblockmirror.b(EnumDirection.DOWN)), iblockdata.get(BlockHugeMushroom.f));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockHugeMushroom.e, BlockHugeMushroom.f, BlockHugeMushroom.a, BlockHugeMushroom.b, BlockHugeMushroom.c, BlockHugeMushroom.d);
    }
}
