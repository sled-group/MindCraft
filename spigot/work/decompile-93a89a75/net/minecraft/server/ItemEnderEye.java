package net.minecraft.server;

public class ItemEnderEye extends Item {

    public ItemEnderEye(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public EnumInteractionResult a(ItemActionContext itemactioncontext) {
        World world = itemactioncontext.getWorld();
        BlockPosition blockposition = itemactioncontext.getClickPosition();
        IBlockData iblockdata = world.getType(blockposition);

        if (iblockdata.getBlock() == Blocks.END_PORTAL_FRAME && !(Boolean) iblockdata.get(BlockEnderPortalFrame.EYE)) {
            if (world.isClientSide) {
                return EnumInteractionResult.SUCCESS;
            } else {
                IBlockData iblockdata1 = (IBlockData) iblockdata.set(BlockEnderPortalFrame.EYE, true);

                Block.a(iblockdata, iblockdata1, world, blockposition);
                world.setTypeAndData(blockposition, iblockdata1, 2);
                world.updateAdjacentComparators(blockposition, Blocks.END_PORTAL_FRAME);
                itemactioncontext.getItemStack().subtract(1);
                world.triggerEffect(1503, blockposition, 0);
                ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = BlockEnderPortalFrame.d().a(world, blockposition);

                if (shapedetector_shapedetectorcollection != null) {
                    BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a().b(-3, 0, -3);

                    for (int i = 0; i < 3; ++i) {
                        for (int j = 0; j < 3; ++j) {
                            world.setTypeAndData(blockposition1.b(i, 0, j), Blocks.END_PORTAL.getBlockData(), 2);
                        }
                    }

                    world.b(1038, blockposition1.b(1, 0, 1), 0);
                }

                return EnumInteractionResult.SUCCESS;
            }
        } else {
            return EnumInteractionResult.PASS;
        }
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        MovingObjectPosition movingobjectposition = a(world, entityhuman, RayTrace.FluidCollisionOption.NONE);

        if (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK && world.getType(((MovingObjectPositionBlock) movingobjectposition).getBlockPosition()).getBlock() == Blocks.END_PORTAL_FRAME) {
            return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
        } else {
            entityhuman.c(enumhand);
            if (!world.isClientSide) {
                BlockPosition blockposition = world.getChunkProvider().getChunkGenerator().findNearestMapFeature(world, "Stronghold", new BlockPosition(entityhuman), 100, false);

                if (blockposition != null) {
                    EntityEnderSignal entityendersignal = new EntityEnderSignal(world, entityhuman.locX, entityhuman.locY + (double) (entityhuman.getHeight() / 2.0F), entityhuman.locZ);

                    entityendersignal.b(itemstack);
                    entityendersignal.a(blockposition);
                    world.addEntity(entityendersignal);
                    if (entityhuman instanceof EntityPlayer) {
                        CriterionTriggers.m.a((EntityPlayer) entityhuman, blockposition);
                    }

                    world.playSound((EntityHuman) null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (ItemEnderEye.i.nextFloat() * 0.4F + 0.8F));
                    world.a((EntityHuman) null, 1003, new BlockPosition(entityhuman), 0);
                    if (!entityhuman.abilities.canInstantlyBuild) {
                        itemstack.subtract(1);
                    }

                    entityhuman.b(StatisticList.ITEM_USED.b(this));
                    return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
                }
            }

            return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
        }
    }
}
