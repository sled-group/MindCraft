package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Map;

public class BlockBanner extends BlockBannerAbstract {

    public static final BlockStateInteger ROTATION = BlockProperties.av;
    private static final Map<EnumColor, Block> b = Maps.newHashMap();
    private static final VoxelShape c = Block.a(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);

    public BlockBanner(EnumColor enumcolor, Block.Info block_info) {
        super(enumcolor, block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockBanner.ROTATION, 0));
        BlockBanner.b.put(enumcolor, this);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return iworldreader.getType(blockposition.down()).getMaterial().isBuildable();
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockBanner.c;
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockBanner.ROTATION, MathHelper.floor((double) ((180.0F + blockactioncontext.h()) * 16.0F / 360.0F) + 0.5D) & 15);
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        return enumdirection == EnumDirection.DOWN && !iblockdata.canPlace(generatoraccess, blockposition) ? Blocks.AIR.getBlockData() : super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockBanner.ROTATION, enumblockrotation.a((Integer) iblockdata.get(BlockBanner.ROTATION), 16));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return (IBlockData) iblockdata.set(BlockBanner.ROTATION, enumblockmirror.a((Integer) iblockdata.get(BlockBanner.ROTATION), 16));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockBanner.ROTATION);
    }
}
