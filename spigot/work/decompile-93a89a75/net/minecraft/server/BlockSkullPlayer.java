package net.minecraft.server;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class BlockSkullPlayer extends BlockSkull {

    protected BlockSkullPlayer(Block.Info block_info) {
        super(BlockSkull.Type.PLAYER, block_info);
    }

    @Override
    public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
        super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
        TileEntity tileentity = world.getTileEntity(blockposition);

        if (tileentity instanceof TileEntitySkull) {
            TileEntitySkull tileentityskull = (TileEntitySkull) tileentity;
            GameProfile gameprofile = null;

            if (itemstack.hasTag()) {
                NBTTagCompound nbttagcompound = itemstack.getTag();

                if (nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
                    gameprofile = GameProfileSerializer.deserialize(nbttagcompound.getCompound("SkullOwner"));
                } else if (nbttagcompound.hasKeyOfType("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
                    gameprofile = new GameProfile((UUID) null, nbttagcompound.getString("SkullOwner"));
                }
            }

            tileentityskull.setGameProfile(gameprofile);
        }

    }
}
