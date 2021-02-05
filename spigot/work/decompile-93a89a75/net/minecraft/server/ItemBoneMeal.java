package net.minecraft.server;

import javax.annotation.Nullable;

public class ItemBoneMeal extends Item {

    public ItemBoneMeal(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public EnumInteractionResult a(ItemActionContext itemactioncontext) {
        World world = itemactioncontext.getWorld();
        BlockPosition blockposition = itemactioncontext.getClickPosition();
        BlockPosition blockposition1 = blockposition.shift(itemactioncontext.getClickedFace());

        if (a(itemactioncontext.getItemStack(), world, blockposition)) {
            if (!world.isClientSide) {
                world.triggerEffect(2005, blockposition, 0);
            }

            return EnumInteractionResult.SUCCESS;
        } else {
            IBlockData iblockdata = world.getType(blockposition);
            boolean flag = iblockdata.d(world, blockposition, itemactioncontext.getClickedFace());

            if (flag && a(itemactioncontext.getItemStack(), world, blockposition1, itemactioncontext.getClickedFace())) {
                if (!world.isClientSide) {
                    world.triggerEffect(2005, blockposition1, 0);
                }

                return EnumInteractionResult.SUCCESS;
            } else {
                return EnumInteractionResult.PASS;
            }
        }
    }

    public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition) {
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() instanceof IBlockFragilePlantElement) {
            IBlockFragilePlantElement iblockfragileplantelement = (IBlockFragilePlantElement) iblockdata.getBlock();

            if (iblockfragileplantelement.a(world, blockposition, iblockdata, world.isClientSide)) {
                if (!world.isClientSide) {
                    if (iblockfragileplantelement.a(world, world.random, blockposition, iblockdata)) {
                        iblockfragileplantelement.b(world, world.random, blockposition, iblockdata);
                    }

                    itemstack.subtract(1);
                }

                return true;
            }
        }

        return false;
    }

    public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition, @Nullable EnumDirection enumdirection) {
        if (world.getType(blockposition).getBlock() == Blocks.WATER && world.getFluid(blockposition).g() == 8) {
            if (!world.isClientSide) {
                label77:
                for (int i = 0; i < 128; ++i) {
                    BlockPosition blockposition1 = blockposition;
                    BiomeBase biomebase = world.getBiome(blockposition);
                    IBlockData iblockdata = Blocks.SEAGRASS.getBlockData();

                    int j;

                    for (j = 0; j < i / 16; ++j) {
                        blockposition1 = blockposition1.b(ItemBoneMeal.i.nextInt(3) - 1, (ItemBoneMeal.i.nextInt(3) - 1) * ItemBoneMeal.i.nextInt(3) / 2, ItemBoneMeal.i.nextInt(3) - 1);
                        biomebase = world.getBiome(blockposition1);
                        if (world.getType(blockposition1).o(world, blockposition1)) {
                            continue label77;
                        }
                    }

                    if (biomebase == Biomes.WARM_OCEAN || biomebase == Biomes.DEEP_WARM_OCEAN) {
                        if (i == 0 && enumdirection != null && enumdirection.k().c()) {
                            iblockdata = (IBlockData) ((Block) TagsBlock.WALL_CORALS.a(world.random)).getBlockData().set(BlockCoralFanWallAbstract.a, enumdirection);
                        } else if (ItemBoneMeal.i.nextInt(4) == 0) {
                            iblockdata = ((Block) TagsBlock.UNDERWATER_BONEMEALS.a(ItemBoneMeal.i)).getBlockData();
                        }
                    }

                    if (iblockdata.getBlock().a(TagsBlock.WALL_CORALS)) {
                        for (j = 0; !iblockdata.canPlace(world, blockposition1) && j < 4; ++j) {
                            iblockdata = (IBlockData) iblockdata.set(BlockCoralFanWallAbstract.a, EnumDirection.EnumDirectionLimit.HORIZONTAL.a(ItemBoneMeal.i));
                        }
                    }

                    if (iblockdata.canPlace(world, blockposition1)) {
                        IBlockData iblockdata1 = world.getType(blockposition1);

                        if (iblockdata1.getBlock() == Blocks.WATER && world.getFluid(blockposition1).g() == 8) {
                            world.setTypeAndData(blockposition1, iblockdata, 3);
                        } else if (iblockdata1.getBlock() == Blocks.SEAGRASS && ItemBoneMeal.i.nextInt(10) == 0) {
                            ((IBlockFragilePlantElement) Blocks.SEAGRASS).b(world, ItemBoneMeal.i, blockposition1, iblockdata1);
                        }
                    }
                }

                itemstack.subtract(1);
            }

            return true;
        } else {
            return false;
        }
    }
}
