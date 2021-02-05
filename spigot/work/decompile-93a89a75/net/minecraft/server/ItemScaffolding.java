package net.minecraft.server;

import javax.annotation.Nullable;

public class ItemScaffolding extends ItemBlock {

    public ItemScaffolding(Block block, Item.Info item_info) {
        super(block, item_info);
    }

    @Nullable
    @Override
    public BlockActionContext b(BlockActionContext blockactioncontext) {
        BlockPosition blockposition = blockactioncontext.getClickPosition();
        World world = blockactioncontext.getWorld();
        IBlockData iblockdata = world.getType(blockposition);
        Block block = this.getBlock();

        if (iblockdata.getBlock() != block) {
            return BlockScaffolding.a((IBlockAccess) world, blockposition) == 7 ? null : blockactioncontext;
        } else {
            EnumDirection enumdirection;

            if (blockactioncontext.isSneaking()) {
                enumdirection = blockactioncontext.k() ? blockactioncontext.getClickedFace().opposite() : blockactioncontext.getClickedFace();
            } else {
                enumdirection = blockactioncontext.getClickedFace() == EnumDirection.UP ? blockactioncontext.f() : EnumDirection.UP;
            }

            int i = 0;
            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = (new BlockPosition.MutableBlockPosition(blockposition)).c(enumdirection);

            while (i < 7) {
                if (!world.isClientSide && !World.isValidLocation(blockposition_mutableblockposition)) {
                    EntityHuman entityhuman = blockactioncontext.getEntity();
                    int j = world.getBuildHeight();

                    if (entityhuman instanceof EntityPlayer && blockposition_mutableblockposition.getY() >= j) {
                        PacketPlayOutChat packetplayoutchat = new PacketPlayOutChat((new ChatMessage("build.tooHigh", new Object[]{j})).a(EnumChatFormat.RED), ChatMessageType.GAME_INFO);

                        ((EntityPlayer) entityhuman).playerConnection.sendPacket(packetplayoutchat);
                    }
                    break;
                }

                iblockdata = world.getType(blockposition_mutableblockposition);
                if (iblockdata.getBlock() != this.getBlock()) {
                    if (iblockdata.a(blockactioncontext)) {
                        return BlockActionContext.a(blockactioncontext, blockposition_mutableblockposition, enumdirection);
                    }
                    break;
                }

                blockposition_mutableblockposition.c(enumdirection);
                if (enumdirection.k().c()) {
                    ++i;
                }
            }

            return null;
        }
    }

    @Override
    protected boolean d() {
        return false;
    }
}
