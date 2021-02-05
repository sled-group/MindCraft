package net.minecraft.server;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import javax.annotation.Nullable;

public class TileEntitySkull extends TileEntity implements ITickable {

    public GameProfile gameProfile;
    private int b;
    private boolean c;
    private static UserCache userCache;
    private static MinecraftSessionService sessionService;

    public TileEntitySkull() {
        super(TileEntityTypes.SKULL);
    }

    public static void a(UserCache usercache) {
        TileEntitySkull.userCache = usercache;
    }

    public static void a(MinecraftSessionService minecraftsessionservice) {
        TileEntitySkull.sessionService = minecraftsessionservice;
    }

    @Override
    public NBTTagCompound save(NBTTagCompound nbttagcompound) {
        super.save(nbttagcompound);
        if (this.gameProfile != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            GameProfileSerializer.serialize(nbttagcompound1, this.gameProfile);
            nbttagcompound.set("Owner", nbttagcompound1);
        }

        return nbttagcompound;
    }

    @Override
    public void load(NBTTagCompound nbttagcompound) {
        super.load(nbttagcompound);
        if (nbttagcompound.hasKeyOfType("Owner", 10)) {
            this.setGameProfile(GameProfileSerializer.deserialize(nbttagcompound.getCompound("Owner")));
        } else if (nbttagcompound.hasKeyOfType("ExtraType", 8)) {
            String s = nbttagcompound.getString("ExtraType");

            if (!UtilColor.b(s)) {
                this.setGameProfile(new GameProfile((UUID) null, s));
            }
        }

    }

    @Override
    public void tick() {
        Block block = this.getBlock().getBlock();

        if (block == Blocks.DRAGON_HEAD || block == Blocks.DRAGON_WALL_HEAD) {
            if (this.world.isBlockIndirectlyPowered(this.position)) {
                this.c = true;
                ++this.b;
            } else {
                this.c = false;
            }
        }

    }

    @Nullable
    @Override
    public PacketPlayOutTileEntityData getUpdatePacket() {
        return new PacketPlayOutTileEntityData(this.position, 4, this.b());
    }

    @Override
    public NBTTagCompound b() {
        return this.save(new NBTTagCompound());
    }

    public void setGameProfile(@Nullable GameProfile gameprofile) {
        this.gameProfile = gameprofile;
        this.f();
    }

    private void f() {
        this.gameProfile = b(this.gameProfile);
        this.update();
    }

    public static GameProfile b(GameProfile gameprofile) {
        if (gameprofile != null && !UtilColor.b(gameprofile.getName())) {
            if (gameprofile.isComplete() && gameprofile.getProperties().containsKey("textures")) {
                return gameprofile;
            } else if (TileEntitySkull.userCache != null && TileEntitySkull.sessionService != null) {
                GameProfile gameprofile1 = TileEntitySkull.userCache.getProfile(gameprofile.getName());

                if (gameprofile1 == null) {
                    return gameprofile;
                } else {
                    Property property = (Property) Iterables.getFirst(gameprofile1.getProperties().get("textures"), (Object) null);

                    if (property == null) {
                        gameprofile1 = TileEntitySkull.sessionService.fillProfileProperties(gameprofile1, true);
                    }

                    return gameprofile1;
                }
            } else {
                return gameprofile;
            }
        } else {
            return gameprofile;
        }
    }
}
