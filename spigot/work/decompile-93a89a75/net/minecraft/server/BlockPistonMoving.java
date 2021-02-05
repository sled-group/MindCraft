package net.minecraft.server;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class BlockPistonMoving extends BlockTileEntity {

    public static final BlockStateDirection a = BlockPistonExtension.FACING;
    public static final BlockStateEnum<BlockPropertyPistonType> b = BlockPistonExtension.TYPE;

    public BlockPistonMoving(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockPistonMoving.a, EnumDirection.NORTH)).set(BlockPistonMoving.b, BlockPropertyPistonType.DEFAULT));
    }

    @Nullable
    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return null;
    }

    public static TileEntity a(IBlockData iblockdata, EnumDirection enumdirection, boolean flag, boolean flag1) {
        return new TileEntityPiston(iblockdata, enumdirection, flag, flag1);
    }

    @Override
    public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
        if (iblockdata.getBlock() != iblockdata1.getBlock()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityPiston) {
                ((TileEntityPiston) tileentity).u();
            }

        }
    }

    @Override
    public void postBreak(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
        BlockPosition blockposition1 = blockposition.shift(((EnumDirection) iblockdata.get(BlockPistonMoving.a)).opposite());
        IBlockData iblockdata1 = generatoraccess.getType(blockposition1);

        if (iblockdata1.getBlock() instanceof BlockPiston && (Boolean) iblockdata1.get(BlockPiston.EXTENDED)) {
            generatoraccess.a(blockposition1, false);
        }

    }

    @Override
    public boolean f(IBlockData iblockdata) {
        return false;
    }

    @Override
    public boolean isOccluding(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return false;
    }

    @Override
    public boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return false;
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        if (!world.isClientSide && world.getTileEntity(blockposition) == null) {
            world.a(blockposition, false);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
        TileEntityPiston tileentitypiston = this.a((IBlockAccess) loottableinfo_builder.a(), (BlockPosition) loottableinfo_builder.a(LootContextParameters.POSITION));

        return tileentitypiston == null ? Collections.emptyList() : tileentitypiston.t().a(loottableinfo_builder);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return VoxelShapes.a();
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        TileEntityPiston tileentitypiston = this.a(iblockaccess, blockposition);

        return tileentitypiston != null ? tileentitypiston.a(iblockaccess, blockposition) : VoxelShapes.a();
    }

    @Nullable
    private TileEntityPiston a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        TileEntity tileentity = iblockaccess.getTileEntity(blockposition);

        return tileentity instanceof TileEntityPiston ? (TileEntityPiston) tileentity : null;
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockPistonMoving.a, enumblockrotation.a((EnumDirection) iblockdata.get(BlockPistonMoving.a)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockPistonMoving.a)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockPistonMoving.a, BlockPistonMoving.b);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
