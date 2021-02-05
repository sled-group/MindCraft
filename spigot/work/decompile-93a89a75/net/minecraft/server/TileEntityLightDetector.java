package net.minecraft.server;

public class TileEntityLightDetector extends TileEntity implements ITickable {

    public TileEntityLightDetector() {
        super(TileEntityTypes.DAYLIGHT_DETECTOR);
    }

    @Override
    public void tick() {
        if (this.world != null && !this.world.isClientSide && this.world.getTime() % 20L == 0L) {
            IBlockData iblockdata = this.getBlock();
            Block block = iblockdata.getBlock();

            if (block instanceof BlockDaylightDetector) {
                BlockDaylightDetector.d(iblockdata, this.world, this.position);
            }
        }

    }
}
