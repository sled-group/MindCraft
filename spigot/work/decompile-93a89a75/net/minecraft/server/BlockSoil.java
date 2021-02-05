package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

public class BlockSoil extends Block {

    public static final BlockStateInteger MOISTURE = BlockProperties.ap;
    protected static final VoxelShape b = Block.a(0.0D, 0.0D, 0.0D, 16.0D, 15.0D, 16.0D);

    protected BlockSoil(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockSoil.MOISTURE, 0));
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if (enumdirection == EnumDirection.UP && !iblockdata.canPlace(generatoraccess, blockposition)) {
            generatoraccess.getBlockTickList().a(blockposition, this, 1);
        }

        return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        IBlockData iblockdata1 = iworldreader.getType(blockposition.up());

        return !iblockdata1.getMaterial().isBuildable() || iblockdata1.getBlock() instanceof BlockFenceGate;
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return !this.getBlockData().canPlace(blockactioncontext.getWorld(), blockactioncontext.getClickPosition()) ? Blocks.DIRT.getBlockData() : super.getPlacedState(blockactioncontext);
    }

    @Override
    public boolean n(IBlockData iblockdata) {
        return true;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockSoil.b;
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        if (!iblockdata.canPlace(world, blockposition)) {
            fade(iblockdata, world, blockposition);
        } else {
            int i = (Integer) iblockdata.get(BlockSoil.MOISTURE);

            if (!a((IWorldReader) world, blockposition) && !world.isRainingAt(blockposition.up())) {
                if (i > 0) {
                    world.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockSoil.MOISTURE, i - 1), 2);
                } else if (!a((IBlockAccess) world, blockposition)) {
                    fade(iblockdata, world, blockposition);
                }
            } else if (i < 7) {
                world.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockSoil.MOISTURE, 7), 2);
            }

        }
    }

    @Override
    public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
        if (!world.isClientSide && world.random.nextFloat() < f - 0.5F && entity instanceof EntityLiving && (entity instanceof EntityHuman || world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512F) {
            fade(world.getType(blockposition), world, blockposition);
        }

        super.fallOn(world, blockposition, entity, f);
    }

    public static void fade(IBlockData iblockdata, World world, BlockPosition blockposition) {
        world.setTypeUpdate(blockposition, a(iblockdata, Blocks.DIRT.getBlockData(), world, blockposition));
    }

    private static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        Block block = iblockaccess.getType(blockposition.up()).getBlock();

        return block instanceof BlockCrops || block instanceof BlockStem || block instanceof BlockStemAttached;
    }

    private static boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
        Iterator iterator = BlockPosition.a(blockposition.b(-4, 0, -4), blockposition.b(4, 1, 4)).iterator();

        BlockPosition blockposition1;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            blockposition1 = (BlockPosition) iterator.next();
        } while (!iworldreader.getFluid(blockposition1).a(TagsFluid.WATER));

        return true;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockSoil.MOISTURE);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
