package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;

public class BlockStemAttached extends BlockPlant {

    public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
    private final BlockStemmed b;
    private static final Map<EnumDirection, VoxelShape> c = Maps.newEnumMap(ImmutableMap.of(EnumDirection.SOUTH, Block.a(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 16.0D), EnumDirection.WEST, Block.a(0.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D), EnumDirection.NORTH, Block.a(6.0D, 0.0D, 0.0D, 10.0D, 10.0D, 10.0D), EnumDirection.EAST, Block.a(6.0D, 0.0D, 6.0D, 16.0D, 10.0D, 10.0D)));

    protected BlockStemAttached(BlockStemmed blockstemmed, Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockStemAttached.a, EnumDirection.NORTH));
        this.b = blockstemmed;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return (VoxelShape) BlockStemAttached.c.get(iblockdata.get(BlockStemAttached.a));
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return iblockdata1.getBlock() != this.b && enumdirection == iblockdata.get(BlockStemAttached.a) ? (IBlockData) this.b.d().getBlockData().set(BlockStem.AGE, 7) : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    protected boolean a_(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return iblockdata.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockStemAttached.a, enumblockrotation.a((EnumDirection) iblockdata.get(BlockStemAttached.a)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockStemAttached.a)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockStemAttached.a);
    }
}
