package net.minecraft.server;

import java.util.function.Predicate;
import javax.annotation.Nullable;

public class ShapeDetectorBlock {

    private final IWorldReader a;
    private final BlockPosition b;
    private final boolean c;
    private IBlockData d;
    private TileEntity e;
    private boolean f;

    public ShapeDetectorBlock(IWorldReader iworldreader, BlockPosition blockposition, boolean flag) {
        this.a = iworldreader;
        this.b = blockposition.immutableCopy();
        this.c = flag;
    }

    public IBlockData a() {
        if (this.d == null && (this.c || this.a.isLoaded(this.b))) {
            this.d = this.a.getType(this.b);
        }

        return this.d;
    }

    @Nullable
    public TileEntity b() {
        if (this.e == null && !this.f) {
            this.e = this.a.getTileEntity(this.b);
            this.f = true;
        }

        return this.e;
    }

    public IWorldReader c() {
        return this.a;
    }

    public BlockPosition getPosition() {
        return this.b;
    }

    public static Predicate<ShapeDetectorBlock> a(Predicate<IBlockData> predicate) {
        return (shapedetectorblock) -> {
            return shapedetectorblock != null && predicate.test(shapedetectorblock.a());
        };
    }
}
