package net.minecraft.server;

public class BlockGrindstone extends BlockAttachable {

    public static final VoxelShape a = Block.a(2.0D, 0.0D, 6.0D, 4.0D, 7.0D, 10.0D);
    public static final VoxelShape b = Block.a(12.0D, 0.0D, 6.0D, 14.0D, 7.0D, 10.0D);
    public static final VoxelShape c = Block.a(2.0D, 7.0D, 5.0D, 4.0D, 13.0D, 11.0D);
    public static final VoxelShape d = Block.a(12.0D, 7.0D, 5.0D, 14.0D, 13.0D, 11.0D);
    public static final VoxelShape e = VoxelShapes.a(BlockGrindstone.a, BlockGrindstone.c);
    public static final VoxelShape f = VoxelShapes.a(BlockGrindstone.b, BlockGrindstone.d);
    public static final VoxelShape g = VoxelShapes.a(BlockGrindstone.e, BlockGrindstone.f);
    public static final VoxelShape h = VoxelShapes.a(BlockGrindstone.g, Block.a(4.0D, 4.0D, 2.0D, 12.0D, 16.0D, 14.0D));
    public static final VoxelShape i = Block.a(6.0D, 0.0D, 2.0D, 10.0D, 7.0D, 4.0D);
    public static final VoxelShape j = Block.a(6.0D, 0.0D, 12.0D, 10.0D, 7.0D, 14.0D);
    public static final VoxelShape k = Block.a(5.0D, 7.0D, 2.0D, 11.0D, 13.0D, 4.0D);
    public static final VoxelShape w = Block.a(5.0D, 7.0D, 12.0D, 11.0D, 13.0D, 14.0D);
    public static final VoxelShape x = VoxelShapes.a(BlockGrindstone.i, BlockGrindstone.k);
    public static final VoxelShape y = VoxelShapes.a(BlockGrindstone.j, BlockGrindstone.w);
    public static final VoxelShape z = VoxelShapes.a(BlockGrindstone.x, BlockGrindstone.y);
    public static final VoxelShape A = VoxelShapes.a(BlockGrindstone.z, Block.a(2.0D, 4.0D, 4.0D, 14.0D, 16.0D, 12.0D));
    public static final VoxelShape B = Block.a(2.0D, 6.0D, 0.0D, 4.0D, 10.0D, 7.0D);
    public static final VoxelShape D = Block.a(12.0D, 6.0D, 0.0D, 14.0D, 10.0D, 7.0D);
    public static final VoxelShape E = Block.a(2.0D, 5.0D, 7.0D, 4.0D, 11.0D, 13.0D);
    public static final VoxelShape F = Block.a(12.0D, 5.0D, 7.0D, 14.0D, 11.0D, 13.0D);
    public static final VoxelShape G = VoxelShapes.a(BlockGrindstone.B, BlockGrindstone.E);
    public static final VoxelShape H = VoxelShapes.a(BlockGrindstone.D, BlockGrindstone.F);
    public static final VoxelShape I = VoxelShapes.a(BlockGrindstone.G, BlockGrindstone.H);
    public static final VoxelShape J = VoxelShapes.a(BlockGrindstone.I, Block.a(4.0D, 2.0D, 4.0D, 12.0D, 14.0D, 16.0D));
    public static final VoxelShape K = Block.a(2.0D, 6.0D, 7.0D, 4.0D, 10.0D, 16.0D);
    public static final VoxelShape L = Block.a(12.0D, 6.0D, 7.0D, 14.0D, 10.0D, 16.0D);
    public static final VoxelShape M = Block.a(2.0D, 5.0D, 3.0D, 4.0D, 11.0D, 9.0D);
    public static final VoxelShape N = Block.a(12.0D, 5.0D, 3.0D, 14.0D, 11.0D, 9.0D);
    public static final VoxelShape O = VoxelShapes.a(BlockGrindstone.K, BlockGrindstone.M);
    public static final VoxelShape P = VoxelShapes.a(BlockGrindstone.L, BlockGrindstone.N);
    public static final VoxelShape Q = VoxelShapes.a(BlockGrindstone.O, BlockGrindstone.P);
    public static final VoxelShape R = VoxelShapes.a(BlockGrindstone.Q, Block.a(4.0D, 2.0D, 0.0D, 12.0D, 14.0D, 12.0D));
    public static final VoxelShape S = Block.a(7.0D, 6.0D, 2.0D, 16.0D, 10.0D, 4.0D);
    public static final VoxelShape T = Block.a(7.0D, 6.0D, 12.0D, 16.0D, 10.0D, 14.0D);
    public static final VoxelShape U = Block.a(3.0D, 5.0D, 2.0D, 9.0D, 11.0D, 4.0D);
    public static final VoxelShape V = Block.a(3.0D, 5.0D, 12.0D, 9.0D, 11.0D, 14.0D);
    public static final VoxelShape W = VoxelShapes.a(BlockGrindstone.S, BlockGrindstone.U);
    public static final VoxelShape X = VoxelShapes.a(BlockGrindstone.T, BlockGrindstone.V);
    public static final VoxelShape Y = VoxelShapes.a(BlockGrindstone.W, BlockGrindstone.X);
    public static final VoxelShape Z = VoxelShapes.a(BlockGrindstone.Y, Block.a(0.0D, 2.0D, 4.0D, 12.0D, 14.0D, 12.0D));
    public static final VoxelShape aa = Block.a(0.0D, 6.0D, 2.0D, 9.0D, 10.0D, 4.0D);
    public static final VoxelShape ab = Block.a(0.0D, 6.0D, 12.0D, 9.0D, 10.0D, 14.0D);
    public static final VoxelShape ac = Block.a(7.0D, 5.0D, 2.0D, 13.0D, 11.0D, 4.0D);
    public static final VoxelShape ad = Block.a(7.0D, 5.0D, 12.0D, 13.0D, 11.0D, 14.0D);
    public static final VoxelShape ae = VoxelShapes.a(BlockGrindstone.aa, BlockGrindstone.ac);
    public static final VoxelShape af = VoxelShapes.a(BlockGrindstone.ab, BlockGrindstone.ad);
    public static final VoxelShape ag = VoxelShapes.a(BlockGrindstone.ae, BlockGrindstone.af);
    public static final VoxelShape ah = VoxelShapes.a(BlockGrindstone.ag, Block.a(4.0D, 2.0D, 4.0D, 16.0D, 14.0D, 12.0D));
    public static final VoxelShape ai = Block.a(2.0D, 9.0D, 6.0D, 4.0D, 16.0D, 10.0D);
    public static final VoxelShape aj = Block.a(12.0D, 9.0D, 6.0D, 14.0D, 16.0D, 10.0D);
    public static final VoxelShape ak = Block.a(2.0D, 3.0D, 5.0D, 4.0D, 9.0D, 11.0D);
    public static final VoxelShape al = Block.a(12.0D, 3.0D, 5.0D, 14.0D, 9.0D, 11.0D);
    public static final VoxelShape am = VoxelShapes.a(BlockGrindstone.ai, BlockGrindstone.ak);
    public static final VoxelShape an = VoxelShapes.a(BlockGrindstone.aj, BlockGrindstone.al);
    public static final VoxelShape ao = VoxelShapes.a(BlockGrindstone.am, BlockGrindstone.an);
    public static final VoxelShape ap = VoxelShapes.a(BlockGrindstone.ao, Block.a(4.0D, 0.0D, 2.0D, 12.0D, 12.0D, 14.0D));
    public static final VoxelShape aq = Block.a(6.0D, 9.0D, 2.0D, 10.0D, 16.0D, 4.0D);
    public static final VoxelShape ar = Block.a(6.0D, 9.0D, 12.0D, 10.0D, 16.0D, 14.0D);
    public static final VoxelShape as = Block.a(5.0D, 3.0D, 2.0D, 11.0D, 9.0D, 4.0D);
    public static final VoxelShape at = Block.a(5.0D, 3.0D, 12.0D, 11.0D, 9.0D, 14.0D);
    public static final VoxelShape au = VoxelShapes.a(BlockGrindstone.aq, BlockGrindstone.as);
    public static final VoxelShape av = VoxelShapes.a(BlockGrindstone.ar, BlockGrindstone.at);
    public static final VoxelShape aw = VoxelShapes.a(BlockGrindstone.au, BlockGrindstone.av);
    public static final VoxelShape ax = VoxelShapes.a(BlockGrindstone.aw, Block.a(2.0D, 0.0D, 4.0D, 14.0D, 12.0D, 12.0D));
    private static final ChatMessage az = new ChatMessage("container.grindstone_title", new Object[0]);

    protected BlockGrindstone(Block.Info block_info) {
        super(block_info);
        this.o((IBlockData) ((IBlockData) ((IBlockData) this.blockStateList.getBlockData()).set(BlockGrindstone.FACING, EnumDirection.NORTH)).set(BlockGrindstone.FACE, BlockPropertyAttachPosition.WALL));
    }

    @Override
    public EnumRenderType c(IBlockData iblockdata) {
        return EnumRenderType.MODEL;
    }

    private VoxelShape q(IBlockData iblockdata) {
        EnumDirection enumdirection = (EnumDirection) iblockdata.get(BlockGrindstone.FACING);

        switch ((BlockPropertyAttachPosition) iblockdata.get(BlockGrindstone.FACE)) {
            case FLOOR:
                if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH) {
                    return BlockGrindstone.A;
                }

                return BlockGrindstone.h;
            case WALL:
                if (enumdirection == EnumDirection.NORTH) {
                    return BlockGrindstone.R;
                } else if (enumdirection == EnumDirection.SOUTH) {
                    return BlockGrindstone.J;
                } else {
                    if (enumdirection == EnumDirection.EAST) {
                        return BlockGrindstone.ah;
                    }

                    return BlockGrindstone.Z;
                }
            case CEILING:
                if (enumdirection != EnumDirection.NORTH && enumdirection != EnumDirection.SOUTH) {
                    return BlockGrindstone.ax;
                }

                return BlockGrindstone.ap;
            default:
                return BlockGrindstone.A;
        }
    }

    @Override
    public VoxelShape b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return this.q(iblockdata);
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return this.q(iblockdata);
    }

    @Override
    public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
        return true;
    }

    @Override
    public boolean interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        entityhuman.openContainer(iblockdata.b(world, blockposition));
        return true;
    }

    @Override
    public ITileInventory getInventory(IBlockData iblockdata, World world, BlockPosition blockposition) {
        return new TileInventory((i, playerinventory, entityhuman) -> {
            return new ContainerGrindstone(i, playerinventory, ContainerAccess.at(world, blockposition));
        }, BlockGrindstone.az);
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
        return (IBlockData) iblockdata.set(BlockGrindstone.FACING, enumblockrotation.a((EnumDirection) iblockdata.get(BlockGrindstone.FACING)));
    }

    @Override
    public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
        return iblockdata.a(enumblockmirror.a((EnumDirection) iblockdata.get(BlockGrindstone.FACING)));
    }

    @Override
    protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
        blockstatelist_a.a(BlockGrindstone.FACING, BlockGrindstone.FACE);
    }

    @Override
    public boolean a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, PathMode pathmode) {
        return false;
    }
}
