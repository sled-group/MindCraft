package net.minecraft.server;

import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Supplier;

public abstract class TileEntity {

    private static final Logger LOGGER = LogManager.getLogger();
    private final TileEntityTypes<?> b;
    @Nullable
    protected World world;
    protected BlockPosition position;
    protected boolean f;
    @Nullable
    private IBlockData c;
    private boolean g;

    public TileEntity(TileEntityTypes<?> tileentitytypes) {
        this.position = BlockPosition.ZERO;
        this.b = tileentitytypes;
    }

    @Nullable
    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean hasWorld() {
        return this.world != null;
    }

    public void load(NBTTagCompound nbttagcompound) {
        this.position = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
    }

    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        return this.d(nbttagcompound);
    }

    private NBTTagCompound d(NBTTagCompound nbttagcompound) {
        MinecraftKey minecraftkey = TileEntityTypes.a(this.q());

        if (minecraftkey == null) {
            throw new RuntimeException(this.getClass() + " is missing a mapping! This is a bug!");
        } else {
            nbttagcompound.setString("id", minecraftkey.toString());
            nbttagcompound.setInt("x", this.position.getX());
            nbttagcompound.setInt("y", this.position.getY());
            nbttagcompound.setInt("z", this.position.getZ());
            return nbttagcompound;
        }
    }

    @Nullable
    public static TileEntity create(NBTTagCompound nbttagcompound) {
        String s = nbttagcompound.getString("id");

        return (TileEntity) IRegistry.BLOCK_ENTITY_TYPE.getOptional(new MinecraftKey(s)).map((tileentitytypes) -> {
            try {
                return tileentitytypes.a();
            } catch (Throwable throwable) {
                TileEntity.LOGGER.error("Failed to create block entity {}", s, throwable);
                return null;
            }
        }).map((tileentity) -> {
            try {
                tileentity.load(nbttagcompound);
                return tileentity;
            } catch (Throwable throwable) {
                TileEntity.LOGGER.error("Failed to load data for block entity {}", s, throwable);
                return null;
            }
        }).orElseGet(() -> {
            TileEntity.LOGGER.warn("Skipping BlockEntity with id {}", s);
            return null;
        });
    }

    public void update() {
        if (this.world != null) {
            this.c = this.world.getType(this.position);
            this.world.b(this.position, this);
            if (!this.c.isAir()) {
                this.world.updateAdjacentComparators(this.position, this.c.getBlock());
            }
        }

    }

    public BlockPosition getPosition() {
        return this.position;
    }

    public IBlockData getBlock() {
        if (this.c == null) {
            this.c = this.world.getType(this.position);
        }

        return this.c;
    }

    @Nullable
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return null;
    }

    public NBTTagCompound b() {
        return this.d(new NBTTagCompound());
    }

    public boolean isRemoved() {
        return this.f;
    }

    public void V_() {
        this.f = true;
    }

    public void n() {
        this.f = false;
    }

    public boolean setProperty(int i, int j) {
        return false;
    }

    public void invalidateBlockCache() {
        this.c = null;
    }

    public void a(CrashReportSystemDetails crashreportsystemdetails) {
        crashreportsystemdetails.a("Name", () -> {
            return IRegistry.BLOCK_ENTITY_TYPE.getKey(this.q()) + " // " + this.getClass().getCanonicalName();
        });
        if (this.world != null) {
            CrashReportSystemDetails.a(crashreportsystemdetails, this.position, this.getBlock());
            CrashReportSystemDetails.a(crashreportsystemdetails, this.position, this.world.getType(this.position));
        }
    }

    public void setPosition(BlockPosition blockposition) {
        this.position = blockposition.immutableCopy();
    }

    public boolean isFilteredNBT() {
        return false;
    }

    public void a(EnumBlockRotation enumblockrotation) {}

    public void a(EnumBlockMirror enumblockmirror) {}

    public TileEntityTypes<?> q() {
        return this.b;
    }

    public void r() {
        if (!this.g) {
            this.g = true;
            TileEntity.LOGGER.warn("Block entity invalid: {} @ {}", new Supplier[]{() -> {
                        return IRegistry.BLOCK_ENTITY_TYPE.getKey(this.q());
                    }, this::getPosition});
        }
    }
}
