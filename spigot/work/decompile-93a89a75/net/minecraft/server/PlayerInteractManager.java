package net.minecraft.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerInteractManager {

    private static final Logger LOGGER = LogManager.getLogger();
    public WorldServer world;
    public EntityPlayer player;
    private EnumGamemode gamemode;
    private boolean e;
    private int lastDigTick;
    private BlockPosition g;
    private int currentTick;
    private boolean i;
    private BlockPosition j;
    private int k;
    private int l;

    public PlayerInteractManager(WorldServer worldserver) {
        this.gamemode = EnumGamemode.NOT_SET;
        this.g = BlockPosition.ZERO;
        this.j = BlockPosition.ZERO;
        this.l = -1;
        this.world = worldserver;
    }

    public void setGameMode(EnumGamemode enumgamemode) {
        this.gamemode = enumgamemode;
        enumgamemode.a(this.player.abilities);
        this.player.updateAbilities();
        this.player.server.getPlayerList().sendAll(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_GAME_MODE, new EntityPlayer[]{this.player}));
        this.world.everyoneSleeping();
    }

    public EnumGamemode getGameMode() {
        return this.gamemode;
    }

    public boolean c() {
        return this.gamemode.f();
    }

    public boolean isCreative() {
        return this.gamemode.isCreative();
    }

    public void b(EnumGamemode enumgamemode) {
        if (this.gamemode == EnumGamemode.NOT_SET) {
            this.gamemode = enumgamemode;
        }

        this.setGameMode(this.gamemode);
    }

    public void a() {
        ++this.currentTick;
        IBlockData iblockdata;

        if (this.i) {
            iblockdata = this.world.getType(this.j);
            if (iblockdata.isAir()) {
                this.i = false;
            } else {
                float f = this.a(iblockdata, this.j);

                if (f >= 1.0F) {
                    this.i = false;
                    this.breakBlock(this.j);
                }
            }
        } else if (this.e) {
            iblockdata = this.world.getType(this.g);
            if (iblockdata.isAir()) {
                this.world.a(this.player.getId(), this.g, -1);
                this.l = -1;
                this.e = false;
            } else {
                this.a(iblockdata, this.g);
            }
        }

    }

    private float a(IBlockData iblockdata, BlockPosition blockposition) {
        int i = this.currentTick - this.k;
        float f = iblockdata.getDamage(this.player, this.player.world, blockposition) * (float) (i + 1);
        int j = (int) (f * 10.0F);

        if (j != this.l) {
            this.world.a(this.player.getId(), blockposition, j);
            this.l = j;
        }

        return f;
    }

    public void a(BlockPosition blockposition, PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype, EnumDirection enumdirection, int i) {
        double d0 = this.player.locX - ((double) blockposition.getX() + 0.5D);
        double d1 = this.player.locY - ((double) blockposition.getY() + 0.5D) + 1.5D;
        double d2 = this.player.locZ - ((double) blockposition.getZ() + 0.5D);
        double d3 = d0 * d0 + d1 * d1 + d2 * d2;

        if (d3 > 36.0D) {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false));
        } else if (blockposition.getY() >= i) {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false));
        } else {
            IBlockData iblockdata;

            if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.START_DESTROY_BLOCK) {
                if (!this.world.a((EntityHuman) this.player, blockposition)) {
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false));
                    return;
                }

                if (this.isCreative()) {
                    if (!this.world.douseFire((EntityHuman) null, blockposition, enumdirection)) {
                        this.a(blockposition, packetplayinblockdig_enumplayerdigtype);
                    } else {
                        this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true));
                    }

                    return;
                }

                if (this.player.a((World) this.world, blockposition, this.gamemode)) {
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false));
                    return;
                }

                this.world.douseFire((EntityHuman) null, blockposition, enumdirection);
                this.lastDigTick = this.currentTick;
                float f = 1.0F;

                iblockdata = this.world.getType(blockposition);
                if (!iblockdata.isAir()) {
                    iblockdata.attack(this.world, blockposition, this.player);
                    f = iblockdata.getDamage(this.player, this.player.world, blockposition);
                }

                if (!iblockdata.isAir() && f >= 1.0F) {
                    this.a(blockposition, packetplayinblockdig_enumplayerdigtype);
                } else {
                    this.e = true;
                    this.g = blockposition;
                    int j = (int) (f * 10.0F);

                    this.world.a(this.player.getId(), blockposition, j);
                    this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true));
                    this.l = j;
                }
            } else if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.STOP_DESTROY_BLOCK) {
                if (blockposition.equals(this.g)) {
                    int k = this.currentTick - this.lastDigTick;

                    iblockdata = this.world.getType(blockposition);
                    if (!iblockdata.isAir()) {
                        float f1 = iblockdata.getDamage(this.player, this.player.world, blockposition) * (float) (k + 1);

                        if (f1 >= 0.7F) {
                            this.e = false;
                            this.world.a(this.player.getId(), blockposition, -1);
                            this.a(blockposition, packetplayinblockdig_enumplayerdigtype);
                            return;
                        }

                        if (!this.i) {
                            this.e = false;
                            this.i = true;
                            this.j = blockposition;
                            this.k = this.lastDigTick;
                        }
                    }
                }

                this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true));
            } else if (packetplayinblockdig_enumplayerdigtype == PacketPlayInBlockDig.EnumPlayerDigType.ABORT_DESTROY_BLOCK) {
                this.e = false;
                this.world.a(this.player.getId(), this.g, -1);
                this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true));
            }

        }
    }

    public void a(BlockPosition blockposition, PacketPlayInBlockDig.EnumPlayerDigType packetplayinblockdig_enumplayerdigtype) {
        if (this.breakBlock(blockposition)) {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, true));
        } else {
            this.player.playerConnection.sendPacket(new PacketPlayOutBlockBreak(blockposition, this.world.getType(blockposition), packetplayinblockdig_enumplayerdigtype, false));
        }

    }

    public boolean breakBlock(BlockPosition blockposition) {
        IBlockData iblockdata = this.world.getType(blockposition);

        if (!this.player.getItemInMainHand().getItem().a(iblockdata, (World) this.world, blockposition, (EntityHuman) this.player)) {
            return false;
        } else {
            TileEntity tileentity = this.world.getTileEntity(blockposition);
            Block block = iblockdata.getBlock();

            if ((block instanceof BlockCommand || block instanceof BlockStructure || block instanceof BlockJigsaw) && !this.player.isCreativeAndOp()) {
                this.world.notify(blockposition, iblockdata, iblockdata, 3);
                return false;
            } else if (this.player.a((World) this.world, blockposition, this.gamemode)) {
                return false;
            } else {
                block.a((World) this.world, blockposition, iblockdata, (EntityHuman) this.player);
                boolean flag = this.world.a(blockposition, false);

                if (flag) {
                    block.postBreak(this.world, blockposition, iblockdata);
                }

                if (this.isCreative()) {
                    return true;
                } else {
                    ItemStack itemstack = this.player.getItemInMainHand();
                    boolean flag1 = this.player.hasBlock(iblockdata);

                    itemstack.a(this.world, iblockdata, blockposition, this.player);
                    if (flag && flag1) {
                        ItemStack itemstack1 = itemstack.isEmpty() ? ItemStack.a : itemstack.cloneItemStack();

                        block.a(this.world, this.player, blockposition, iblockdata, tileentity, itemstack1);
                    }

                    return true;
                }
            }
        }
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, ItemStack itemstack, EnumHand enumhand) {
        if (this.gamemode == EnumGamemode.SPECTATOR) {
            return EnumInteractionResult.PASS;
        } else if (entityhuman.getCooldownTracker().hasCooldown(itemstack.getItem())) {
            return EnumInteractionResult.PASS;
        } else {
            int i = itemstack.getCount();
            int j = itemstack.getDamage();
            InteractionResultWrapper<ItemStack> interactionresultwrapper = itemstack.a(world, entityhuman, enumhand);
            ItemStack itemstack1 = (ItemStack) interactionresultwrapper.b();

            if (itemstack1 == itemstack && itemstack1.getCount() == i && itemstack1.k() <= 0 && itemstack1.getDamage() == j) {
                return interactionresultwrapper.a();
            } else if (interactionresultwrapper.a() == EnumInteractionResult.FAIL && itemstack1.k() > 0 && !entityhuman.isHandRaised()) {
                return interactionresultwrapper.a();
            } else {
                entityhuman.a(enumhand, itemstack1);
                if (this.isCreative()) {
                    itemstack1.setCount(i);
                    if (itemstack1.e()) {
                        itemstack1.setDamage(j);
                    }
                }

                if (itemstack1.isEmpty()) {
                    entityhuman.a(enumhand, ItemStack.a);
                }

                if (!entityhuman.isHandRaised()) {
                    ((EntityPlayer) entityhuman).updateInventory(entityhuman.defaultContainer);
                }

                return interactionresultwrapper.a();
            }
        }
    }

    public EnumInteractionResult a(EntityHuman entityhuman, World world, ItemStack itemstack, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
        BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
        IBlockData iblockdata = world.getType(blockposition);

        if (this.gamemode == EnumGamemode.SPECTATOR) {
            ITileInventory itileinventory = iblockdata.b(world, blockposition);

            if (itileinventory != null) {
                entityhuman.openContainer(itileinventory);
                return EnumInteractionResult.SUCCESS;
            } else {
                return EnumInteractionResult.PASS;
            }
        } else {
            boolean flag = !entityhuman.getItemInMainHand().isEmpty() || !entityhuman.getItemInOffHand().isEmpty();
            boolean flag1 = entityhuman.isSneaking() && flag;

            if (!flag1 && iblockdata.interact(world, entityhuman, enumhand, movingobjectpositionblock)) {
                return EnumInteractionResult.SUCCESS;
            } else if (!itemstack.isEmpty() && !entityhuman.getCooldownTracker().hasCooldown(itemstack.getItem())) {
                ItemActionContext itemactioncontext = new ItemActionContext(entityhuman, enumhand, movingobjectpositionblock);

                if (this.isCreative()) {
                    int i = itemstack.getCount();
                    EnumInteractionResult enuminteractionresult = itemstack.placeItem(itemactioncontext);

                    itemstack.setCount(i);
                    return enuminteractionresult;
                } else {
                    return itemstack.placeItem(itemactioncontext);
                }
            } else {
                return EnumInteractionResult.PASS;
            }
        }
    }

    public void a(WorldServer worldserver) {
        this.world = worldserver;
    }
}
