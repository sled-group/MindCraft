package net.minecraft.server;

import java.util.Objects;
import javax.annotation.Nullable;

public class MapIconBanner {

    private final BlockPosition a;
    private final EnumColor b;
    @Nullable
    private final IChatBaseComponent c;

    public MapIconBanner(BlockPosition blockposition, EnumColor enumcolor, @Nullable IChatBaseComponent ichatbasecomponent) {
        this.a = blockposition;
        this.b = enumcolor;
        this.c = ichatbasecomponent;
    }

    public static MapIconBanner a(NBTTagCompound nbttagcompound) {
        BlockPosition blockposition = GameProfileSerializer.c(nbttagcompound.getCompound("Pos"));
        EnumColor enumcolor = EnumColor.a(nbttagcompound.getString("Color"), EnumColor.WHITE);
        IChatBaseComponent ichatbasecomponent = nbttagcompound.hasKey("Name") ? IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("Name")) : null;

        return new MapIconBanner(blockposition, enumcolor, ichatbasecomponent);
    }

    @Nullable
    public static MapIconBanner a(IBlockAccess iblockaccess, BlockPosition blockposition) {
        TileEntity tileentity = iblockaccess.getTileEntity(blockposition);

        if (tileentity instanceof TileEntityBanner) {
            TileEntityBanner tileentitybanner = (TileEntityBanner) tileentity;
            EnumColor enumcolor = tileentitybanner.a(() -> {
                return iblockaccess.getType(blockposition);
            });
            IChatBaseComponent ichatbasecomponent = tileentitybanner.hasCustomName() ? tileentitybanner.getCustomName() : null;

            return new MapIconBanner(blockposition, enumcolor, ichatbasecomponent);
        } else {
            return null;
        }
    }

    public BlockPosition a() {
        return this.a;
    }

    public MapIcon.Type c() {
        switch (this.b) {
            case WHITE:
                return MapIcon.Type.BANNER_WHITE;
            case ORANGE:
                return MapIcon.Type.BANNER_ORANGE;
            case MAGENTA:
                return MapIcon.Type.BANNER_MAGENTA;
            case LIGHT_BLUE:
                return MapIcon.Type.BANNER_LIGHT_BLUE;
            case YELLOW:
                return MapIcon.Type.BANNER_YELLOW;
            case LIME:
                return MapIcon.Type.BANNER_LIME;
            case PINK:
                return MapIcon.Type.BANNER_PINK;
            case GRAY:
                return MapIcon.Type.BANNER_GRAY;
            case LIGHT_GRAY:
                return MapIcon.Type.BANNER_LIGHT_GRAY;
            case CYAN:
                return MapIcon.Type.BANNER_CYAN;
            case PURPLE:
                return MapIcon.Type.BANNER_PURPLE;
            case BLUE:
                return MapIcon.Type.BANNER_BLUE;
            case BROWN:
                return MapIcon.Type.BANNER_BROWN;
            case GREEN:
                return MapIcon.Type.BANNER_GREEN;
            case RED:
                return MapIcon.Type.BANNER_RED;
            case BLACK:
            default:
                return MapIcon.Type.BANNER_BLACK;
        }
    }

    @Nullable
    public IChatBaseComponent d() {
        return this.c;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object != null && this.getClass() == object.getClass()) {
            MapIconBanner mapiconbanner = (MapIconBanner) object;

            return Objects.equals(this.a, mapiconbanner.a) && this.b == mapiconbanner.b && Objects.equals(this.c, mapiconbanner.c);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.a, this.b, this.c});
    }

    public NBTTagCompound e() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        nbttagcompound.set("Pos", GameProfileSerializer.a(this.a));
        nbttagcompound.setString("Color", this.b.b());
        if (this.c != null) {
            nbttagcompound.setString("Name", IChatBaseComponent.ChatSerializer.a(this.c));
        }

        return nbttagcompound;
    }

    public String f() {
        return "banner-" + this.a.getX() + "," + this.a.getY() + "," + this.a.getZ();
    }
}
