package net.minecraft.server;

import java.util.List;
import java.util.function.Consumer;
import javax.annotation.Nullable;

public class BlockShulkerBox extends BlockTileEntity {

    public static final BlockStateEnum<EnumDirection> a = BlockDirectional.FACING;
    public static final MinecraftKey b = new MinecraftKey("contents");
    @Nullable
    public final EnumColor color;

    public BlockShulkerBox(@Nullable EnumColor enumcolor, Block.Info block_info) {
        super(block_info);
        this.color = enumcolor;
        this.o((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockShulkerBox.a, EnumDirection.UP));
    }

    @Override
    public TileEntity createTile(IBlockAccess iblockaccess) {
        return new TileEntityShulkerBox(this.color);
    }

    @Override
    public boolean c(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return true;
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        if (world.isClientSide) {
            return true;
        } else if (entityhuman.isSpectator()) {
            return true;
        } else {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityShulkerBox) {
                EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockShulkerBox.a);
                TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox) tileentity;
                boolean flag;

                if (tileentityshulkerbox.s() == TileEntityShulkerBox.AnimationPhase.CLOSED) {
                    AxisAlignedBB axisalignedbb = VoxelShapes.b().getBoundingBox().b((double) (0.5F * (float) enumdirection.getAdjacentX()), (double) (0.5F * (float) enumdirection.getAdjacentY()), (double) (0.5F * (float) enumdirection.getAdjacentZ())).a((double) enumdirection.getAdjacentX(), (double) enumdirection.getAdjacentY(), (double) enumdirection.getAdjacentZ());

                    flag = world.c(axisalignedbb.a(blockposition.shift(enumdirection)));
                } else {
                    flag = true;
                }

                if (flag) {
                    entityhuman.openContainer(tileentityshulkerbox);
                    entityhuman.a(StatisticList.OPEN_SHULKER_BOX);
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
        return (IBlockData) this.getBlockData().set(BlockShulkerBox.a, blockactioncontext.getClickedFace());
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockShulkerBox.a);
    }

    @Override
    public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityShulkerBox) {
            TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox) tileentity;

            if (!world.isClientSide && entityhuman.isCreative() && !tileentityshulkerbox.isNotEmpty()) {
                ItemStack itemstack = b(this.d());
                NBTTagCompound nbttagcompound = tileentityshulkerbox.g(new NBTTagCompound());

                if (!nbttagcompound.isEmpty()) {
                    itemstack.a("BlockEntityTag", (NBTBase) nbttagcompound);
                }

                if (tileentityshulkerbox.hasCustomName()) {
                    itemstack.a(tileentityshulkerbox.getCustomName());
                }

                EntityItem entityitem = new EntityItem(world, (double) blockposition.getX(), (double) blockposition.getY(), (double) blockposition.getZ(), itemstack);

                entityitem.defaultPickupDelay();
                world.addEntity(entityitem);
            } else {
                tileentityshulkerbox.d(entityhuman);
            }
        }

        super.a(world, blockposition, iblockdata, entityhuman);
    }

    @Override
    public List<ItemStack> a(IBlockData iblockdata, LootTableInfo.Builder loottableinfo_builder) {
        TileEntity tileentity = (TileEntity) loottableinfo_builder.b(LootContextParameters.BLOCK_ENTITY);

        if (tileentity instanceof TileEntityShulkerBox) {
            TileEntityShulkerBox tileentityshulkerbox = (TileEntityShulkerBox) tileentity;

            loottableinfo_builder = loottableinfo_builder.a(BlockShulkerBox.b, (loottableinfo, consumer) -> {
                for (int i = 0; i < tileentityshulkerbox.getSize(); ++i) {
                    consumer.accept(tileentityshulkerbox.getItem(i));
                }

            });
        }

        return super.a(iblockdata, loottableinfo_builder);
    }

    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
        if (itemstack.hasName()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityShulkerBox) {
                ((TileEntityShulkerBox) tileentity).setCustomName(itemstack.getName());
            }
        }

    }

    @Override
    public void remove(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
        if (iblockdata.getBlock() != iblockdata1.getBlock()) {
            TileEntity tileentity = world.getTileEntity(blockposition);

            if (tileentity instanceof TileEntityShulkerBox) {
                world.updateAdjacentComparators(blockposition, iblockdata.getBlock());
            }

            super.remove(iblockdata, world, blockposition, iblockdata1, flag);
        }
    }

    @Override
    public EnumPistonReaction getPushReaction(IBlockData iblockdata) {
        return EnumPistonReaction.DESTROY;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        TileEntity tileentity = iblockaccess.getTileEntity(blockposition);

        return tileentity instanceof TileEntityShulkerBox ? VoxelShapes.a(((TileEntityShulkerBox) tileentity).a(iblockdata)) : VoxelShapes.b();
    }

    @Override
    public boolean f(IBlockData iblockdata) {
        return false;
    }

    @Override
    public boolean isComplexRedstone(IBlockData iblockdata) {
        return true;
    }

    @Override
    public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return Container.b((IInventory) world.getTileEntity(blockposition));
    }

    public static Block a(@Nullable EnumColor enumcolor) {
        if (enumcolor == null) {
            return Blocks.SHULKER_BOX;
        } else {
            switch (enumcolor) {
                case WHITE:
                    return Blocks.WHITE_SHULKER_BOX;
                case ORANGE:
                    return Blocks.ORANGE_SHULKER_BOX;
                case MAGENTA:
                    return Blocks.MAGENTA_SHULKER_BOX;
                case LIGHT_BLUE:
                    return Blocks.LIGHT_BLUE_SHULKER_BOX;
                case YELLOW:
                    return Blocks.YELLOW_SHULKER_BOX;
                case LIME:
                    return Blocks.LIME_SHULKER_BOX;
                case PINK:
                    return Blocks.PINK_SHULKER_BOX;
                case GRAY:
                    return Blocks.GRAY_SHULKER_BOX;
                case LIGHT_GRAY:
                    return Blocks.LIGHT_GRAY_SHULKER_BOX;
                case CYAN:
                    return Blocks.CYAN_SHULKER_BOX;
                case PURPLE:
                default:
                    return Blocks.PURPLE_SHULKER_BOX;
                case BLUE:
                    return Blocks.BLUE_SHULKER_BOX;
                case BROWN:
                    return Blocks.BROWN_SHULKER_BOX;
                case GREEN:
                    return Blocks.GREEN_SHULKER_BOX;
                case RED:
                    return Blocks.RED_SHULKER_BOX;
                case BLACK:
                    return Blocks.BLACK_SHULKER_BOX;
            }
        }
    }

    @Nullable
    public EnumColor d() {
        return this.color;
    }

    public static ItemStack b(@Nullable EnumColor enumcolor) {
        return new ItemStack(a(enumcolor));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockShulkerBox.a, enumblockrotation.a((EnumDirection) iblockdata.get(BlockShulkerBox.a)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockShulkerBox.a)));
    }
}
