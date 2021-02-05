package net.minecraft.server;

import javax.annotation.Nullable;

public class BlockJigsaw extends BlockDirectional implements ITileEntity {

    protected BlockJigsaw(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockJigsaw.FACING, EnumDirection.UP));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockJigsaw.FACING);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockJigsaw.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockJigsaw.FACING)));
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockJigsaw.FACING, blockactioncontext.getClickedFace());
    }

    @Nullable
    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntityJigsaw();
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityJigsaw && entityhuman.isCreativeAndOp()) {
            entityhuman.a((TileEntityJigsaw) tileentity);
            return true;
        } else {
            return false;
        }
    }

    public static boolean a(DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1) {
        return definedstructure_blockinfo.b.get(BlockJigsaw.FACING) == ((EnumDirection) definedstructure_blockinfo1.b.get(BlockJigsaw.FACING)).opposite() && definedstructure_blockinfo.c.getString("attachement_type").equals(definedstructure_blockinfo1.c.getString("attachement_type"));
    }
}
