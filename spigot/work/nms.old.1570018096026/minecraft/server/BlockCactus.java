package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;

import org.bukkit.craftbukkit.event.CraftEventFactory; // CraftBukkit

public class BlockCactus extends Block {

    public static final BlockStateInteger AGE = BlockProperties.ad;
    protected static final VoxelShape b = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);
    protected static final VoxelShape c = Block.a(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    protected BlockCactus(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockCactus.AGE, 0));
    }

    @Override
    public void tick(IBlockData iblockdata, World world, BlockPosition blockposition, Random random) {
        if (!iblockdata.canPlace(world, blockposition)) {
            world.b(blockposition, true);
        } else {
            BlockPosition blockposition1 = blockposition.up();

            if (world.isEmpty(blockposition1)) {
                int i;

                for (i = 1; world.getType(blockposition.down(i)).getBlock() == this; ++i) {
                    ;
                }

                if (i < 3) {
                    int j = (Integer) iblockdata.get(BlockCactus.AGE);

                    if (j == 15) {
                        CraftEventFactory.handleBlockGrowEvent(world, blockposition1, this.getBlockData()); // CraftBukkit
                        IBlockData iblockdata1 = (IBlockData) iblockdata.set(BlockCactus.AGE, 0);

                        world.setTypeAndData(blockposition, iblockdata1, 4);
                        iblockdata1.doPhysics(world, blockposition1, this, blockposition, false);
                    } else {
                        world.setTypeAndData(blockposition, (IBlockData) iblockdata.set(BlockCactus.AGE, j + 1), 4);
                    }

                }
            }
        }
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockCactus.b;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return BlockCactus.c;
    }

    @Override
    public boolean f(IBlockData iblockdata) {
        return true;
    }

    @Override
    public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
        if (!iblockdata.canPlace(generatoraccess, blockposition)) {
            generatoraccess.getBlockTickList().a(blockposition, this, 1);
        }

        return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        Iterator iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();

        EnumDirection enumdirection;
        Material material;

        do {
            if (!iterator.hasNext()) {
                Block block = iworldreader.getType(blockposition.down()).getBlock();

                return (block == Blocks.CACTUS || block == Blocks.SAND || block == Blocks.RED_SAND) && !iworldreader.getType(blockposition.up()).getMaterial().isLiquid();
            }

            enumdirection = (EnumDirection) iterator.next();
            IBlockData iblockdata1 = iworldreader.getType(blockposition.shift(enumdirection));

            material = iblockdata1.getMaterial();
        } while (!material.isBuildable() && !iworldreader.getFluid(blockposition.shift(enumdirection)).a(TagsFluid.LAVA));

        return false;
    }

    @Override
    public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Entity entity) {
        CraftEventFactory.blockDamage = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()); // CraftBukkit
        entity.damageEntity(DamageSource.CACTUS, 1.0F);
        CraftEventFactory.blockDamage = null; // CraftBukkit
    }

    @Override
    public TextureType c() {
        return TextureType.CUTOUT;
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockCactus.AGE);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
