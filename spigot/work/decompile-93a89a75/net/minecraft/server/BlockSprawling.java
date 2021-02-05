package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.EnumMap;
import java.util.Map;

public class BlockSprawling extends Block {

    private static final EnumDirection[] i = EnumDirection.values();
    public static final BlockStateBoolean a = BlockProperties.H;
    public static final BlockStateBoolean b = BlockProperties.I;
    public static final BlockStateBoolean c = BlockProperties.J;
    public static final BlockStateBoolean d = BlockProperties.K;
    public static final BlockStateBoolean e = BlockProperties.F;
    public static final BlockStateBoolean f = BlockProperties.G;
    public static final Map<EnumDirection, BlockStateBoolean> g = (Map) SystemUtils.a((Object) Maps.newEnumMap(EnumDirection.class), (enummap) -> {
        enummap.put(EnumDirection.NORTH, BlockSprawling.a);
        enummap.put(EnumDirection.EAST, BlockSprawling.b);
        enummap.put(EnumDirection.SOUTH, BlockSprawling.c);
        enummap.put(EnumDirection.WEST, BlockSprawling.d);
        enummap.put(EnumDirection.UP, BlockSprawling.e);
        enummap.put(EnumDirection.DOWN, BlockSprawling.f);
    });
    protected final VoxelShape[] h;

    protected BlockSprawling(float f, Block.Info block_info) {
        super(block_info);
        this.h = this.a(f);
    }

    private VoxelShape[] a(float f) {
        float f1 = 0.5F - f;
        float f2 = 0.5F + f;
        VoxelShape voxelshape = Block.a((double) (f1 * 16.0F), (double) (f1 * 16.0F), (double) (f1 * 16.0F), (double) (f2 * 16.0F), (double) (f2 * 16.0F), (double) (f2 * 16.0F));
        VoxelShape[] avoxelshape = new VoxelShape[BlockSprawling.i.length];

        for (int i = 0; i < BlockSprawling.i.length; ++i) {
            EnumDirection enumdirection = BlockSprawling.i[i];

            avoxelshape[i] = VoxelShapes.create(0.5D + Math.min((double) (-f), (double) enumdirection.getAdjacentX() * 0.5D), 0.5D + Math.min((double) (-f), (double) enumdirection.getAdjacentY() * 0.5D), 0.5D + Math.min((double) (-f), (double) enumdirection.getAdjacentZ() * 0.5D), 0.5D + Math.max((double) f, (double) enumdirection.getAdjacentX() * 0.5D), 0.5D + Math.max((double) f, (double) enumdirection.getAdjacentY() * 0.5D), 0.5D + Math.max((double) f, (double) enumdirection.getAdjacentZ() * 0.5D));
        }

        VoxelShape[] avoxelshape1 = new VoxelShape[64];

        for (int j = 0; j < 64; ++j) {
            VoxelShape voxelshape1 = voxelshape;

            for (int k = 0; k < BlockSprawling.i.length; ++k) {
                if ((j & 1 << k) != 0) {
                    voxelshape1 = VoxelShapes.a(voxelshape1, avoxelshape[k]);
                }
            }

            avoxelshape1[j] = voxelshape1;
        }

        return avoxelshape1;
    }

    @Override
    public boolean b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
        return false;
    }

    @Override
    public VoxelShape a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition, VoxelShapeCollision voxelshapecollision) {
        return this.h[this.j(iblockdata)];
    }

    protected int j(IBlockData iblockdata) {
        int i = 0;

        for (int j = 0; j < BlockSprawling.i.length; ++j) {
            if ((Boolean) iblockdata.get((IBlockState) BlockSprawling.g.get(BlockSprawling.i[j]))) {
                i |= 1 << j;
            }
        }

        return i;
    }
}
