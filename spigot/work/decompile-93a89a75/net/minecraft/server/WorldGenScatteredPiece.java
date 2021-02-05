package net.minecraft.server;

import java.util.Random;

public abstract class WorldGenScatteredPiece extends StructurePiece {

    protected final int a;
    protected final int b;
    protected final int c;
    protected int d = -1;

    protected WorldGenScatteredPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, Random random, int i, int j, int k, int l, int i1, int j1) {
        super(worldgenfeaturestructurepiecetype, 0);
        this.a = l;
        this.b = i1;
        this.c = j1;
        this.a(EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random));
        if (this.i().k() == EnumDirection.EnumAxis.Z) {
            this.n = new StructureBoundingBox(i, j, k, i + l - 1, j + i1 - 1, k + j1 - 1);
        } else {
            this.n = new StructureBoundingBox(i, j, k, i + j1 - 1, j + i1 - 1, k + l - 1);
        }

    }

    protected WorldGenScatteredPiece(WorldGenFeatureStructurePieceType worldgenfeaturestructurepiecetype, NBTTagCompound nbttagcompound) {
        super(worldgenfeaturestructurepiecetype, nbttagcompound);
        this.a = nbttagcompound.getInt("Width");
        this.b = nbttagcompound.getInt("Height");
        this.c = nbttagcompound.getInt("Depth");
        this.d = nbttagcompound.getInt("HPos");
    }

    @Override
    protected void a(NBTTagCompound nbttagcompound) {
        nbttagcompound.setInt("Width", this.a);
        nbttagcompound.setInt("Height", this.b);
        nbttagcompound.setInt("Depth", this.c);
        nbttagcompound.setInt("HPos", this.d);
    }

    protected boolean a(GeneratorAccess generatoraccess, StructureBoundingBox structureboundingbox, int i) {
        if (this.d >= 0) {
            return true;
        } else {
            int j = 0;
            int k = 0;
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();

            for (int l = this.n.c; l <= this.n.f; ++l) {
                for (int i1 = this.n.a; i1 <= this.n.d; ++i1) {
                    blockposition_mutableblockposition.d(i1, 64, l);
                    if (structureboundingbox.b((BaseBlockPosition) blockposition_mutableblockposition)) {
                        j += generatoraccess.getHighestBlockYAt(HeightMap.Type.MOTION_BLOCKING_NO_LEAVES, blockposition_mutableblockposition).getY();
                        ++k;
                    }
                }
            }

            if (k == 0) {
                return false;
            } else {
                this.d = j / k;
                this.n.a(0, this.d - this.n.b + i, 0);
                return true;
            }
        }
    }
}
