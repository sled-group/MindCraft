package net.minecraft.server;

public class BlockPumpkin extends BlockStemmed {

    protected BlockPumpkin(Block.Info block_info) {
        super(block_info);
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (itemstack.getItem() == Items.SHEARS) {
            if (!world.isClientSide) {
                EnumDirection enumdirection = movingobjectpositionblock.getDirection();
                EnumDirection enumdirection1 = enumdirection.k() == EnumDirection.EnumAxis.Y ? entityhuman.getDirection().opposite() : enumdirection;

                world.playSound((EntityHuman) null, blockposition, SoundEffects.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.setTypeAndData(blockposition, (IBlockData) Blocks.CARVED_PUMPKIN.getBlockData().set(BlockPumpkinCarved.a, enumdirection1), 11);
                EntityItem entityitem = new EntityItem(world, (double) blockposition.getX() + 0.5D + (double) enumdirection1.getAdjacentX() * 0.65D, (double) blockposition.getY() + 0.1D, (double) blockposition.getZ() + 0.5D + (double) enumdirection1.getAdjacentZ() * 0.65D, new ItemStack(Items.PUMPKIN_SEEDS, 4));

                entityitem.setMot(0.05D * (double) enumdirection1.getAdjacentX() + world.random.nextDouble() * 0.02D, 0.05D, 0.05D * (double) enumdirection1.getAdjacentZ() + world.random.nextDouble() * 0.02D);
                world.addEntity(entityitem);
                itemstack.damage(1, entityhuman, (entityhuman1) -> {
                    entityhuman1.d(enumhand);
                });
            }

            return true;
        } else {
            return super.interact(iblockdata, world, blockposition, entityhuman, enumhand, movingobjectpositionblock);
        }
    }

    @Override
    public BlockStem d() {
        return (BlockStem) Blocks.PUMPKIN_STEM;
    }

    @Override
    public BlockStemAttached e() {
        return (BlockStemAttached) Blocks.ATTACHED_PUMPKIN_STEM;
    }
}
