package net.minecraft.server;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import javax.annotation.Nullable;

public class ArgumentTileLocation implements Predicate<ShapeDetectorBlock> {

    private final IBlockData a;
    private final Set<IBlockState<?>> b;
    @Nullable
    private final NBTTagCompound c;

    public ArgumentTileLocation(IBlockData iblockdata, Set<IBlockState<?>> set, @Nullable NBTTagCompound nbttagcompound) {
        this.a = iblockdata;
        this.b = set;
        this.c = nbttagcompound;
    }

    public IBlockData a() {
        return this.a;
    }

    public boolean test(ShapeDetectorBlock shapedetectorblock) {
        IBlockData iblockdata = shapedetectorblock.a();

        if (iblockdata.getBlock() != this.a.getBlock()) {
            return false;
        } else {
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                IBlockState<?> iblockstate = (IBlockState) iterator.next();

                if (iblockdata.get(iblockstate) != this.a.get(iblockstate)) {
                    return false;
                }
            }

            if (this.c == null) {
                return true;
            } else {
                TileEntity tileentity = shapedetectorblock.b();

                return tileentity != null && GameProfileSerializer.a(this.c, tileentity.save(new NBTTagCompound()), true);
            }
        }
    }

    public boolean a(WorldServer worldserver, BlockPosition blockposition, int i) {
        if (!worldserver.setTypeAndData(blockposition, this.a, i)) {
            return false;
        } else {
            if (this.c != null) {
                TileEntity tileentity = worldserver.getTileEntity(blockposition);

                if (tileentity != null) {
                    NBTTagCompound nbttagcompound = this.c.clone();

                    nbttagcompound.setInt("x", blockposition.getX());
                    nbttagcompound.setInt("y", blockposition.getY());
                    nbttagcompound.setInt("z", blockposition.getZ());
                    tileentity.load(nbttagcompound);
                }
            }

            return true;
        }
    }
}
