package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;

public class BehaviorFarm extends Behavior<EntityVillager> {

    @Nullable
    private BlockPosition a;
    private boolean b;
    private boolean c;
    private long d;
    private int e;
    private final List<BlockPosition> f = Lists.newArrayList();

    public BehaviorFarm() {
        super(ImmutableMap.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.SECONDARY_JOB_SITE, MemoryStatus.VALUE_PRESENT));
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        if (!worldserver.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
            return false;
        } else if (entityvillager.getVillagerData().getProfession() != VillagerProfession.FARMER) {
            return false;
        } else {
            this.b = entityvillager.er();
            this.c = false;
            InventorySubcontainer inventorysubcontainer = entityvillager.getInventory();
            int i = inventorysubcontainer.getSize();

            for (int j = 0; j < i; ++j) {
                ItemStack itemstack = inventorysubcontainer.getItem(j);

                if (itemstack.isEmpty()) {
                    this.c = true;
                    break;
                }

                if (itemstack.getItem() == Items.WHEAT_SEEDS || itemstack.getItem() == Items.BEETROOT_SEEDS) {
                    this.c = true;
                    break;
                }
            }

            BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition(entityvillager.locX, entityvillager.locY, entityvillager.locZ);

            this.f.clear();

            for (int k = -1; k <= 1; ++k) {
                for (int l = -1; l <= 1; ++l) {
                    for (int i1 = -1; i1 <= 1; ++i1) {
                        blockposition_mutableblockposition.c(entityvillager.locX + (double) k, entityvillager.locY + (double) l, entityvillager.locZ + (double) i1);
                        if (this.a((BlockPosition) blockposition_mutableblockposition, worldserver)) {
                            this.f.add(new BlockPosition(blockposition_mutableblockposition));
                        }
                    }
                }
            }

            this.a = this.a(worldserver);
            return (this.b || this.c) && this.a != null;
        }
    }

    @Nullable
    private BlockPosition a(WorldServer worldserver) {
        return this.f.isEmpty() ? null : (BlockPosition) this.f.get(worldserver.getRandom().nextInt(this.f.size()));
    }

    private boolean a(BlockPosition blockposition, WorldServer worldserver) {
        IBlockData iblockdata = worldserver.getType(blockposition);
        Block block = iblockdata.getBlock();
        Block block1 = worldserver.getType(blockposition.down()).getBlock();

        return block instanceof BlockCrops && ((BlockCrops) block).isRipe(iblockdata) && this.c || iblockdata.isAir() && block1 instanceof BlockSoil && this.b;
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        if (i > this.d && this.a != null) {
            entityvillager.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorTarget(this.a)));
            entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(new BehaviorTarget(this.a), 0.5F, 1)));
        }

    }

    protected void f(WorldServer worldserver, EntityVillager entityvillager, long i) {
        entityvillager.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
        entityvillager.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
        this.e = 0;
        this.d = i + 40L;
    }

    protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
        if (this.a != null && i > this.d) {
            IBlockData iblockdata = worldserver.getType(this.a);
            Block block = iblockdata.getBlock();
            Block block1 = worldserver.getType(this.a.down()).getBlock();

            if (block instanceof BlockCrops && ((BlockCrops) block).isRipe(iblockdata) && this.c) {
                worldserver.b(this.a, true);
            }

            if (iblockdata.isAir() && block1 instanceof BlockSoil && this.b) {
                InventorySubcontainer inventorysubcontainer = entityvillager.getInventory();

                for (int j = 0; j < inventorysubcontainer.getSize(); ++j) {
                    ItemStack itemstack = inventorysubcontainer.getItem(j);
                    boolean flag = false;

                    if (!itemstack.isEmpty()) {
                        if (itemstack.getItem() == Items.WHEAT_SEEDS) {
                            worldserver.setTypeAndData(this.a, Blocks.WHEAT.getBlockData(), 3);
                            flag = true;
                        } else if (itemstack.getItem() == Items.POTATO) {
                            worldserver.setTypeAndData(this.a, Blocks.POTATOES.getBlockData(), 3);
                            flag = true;
                        } else if (itemstack.getItem() == Items.CARROT) {
                            worldserver.setTypeAndData(this.a, Blocks.CARROTS.getBlockData(), 3);
                            flag = true;
                        } else if (itemstack.getItem() == Items.BEETROOT_SEEDS) {
                            worldserver.setTypeAndData(this.a, Blocks.BEETROOTS.getBlockData(), 3);
                            flag = true;
                        }
                    }

                    if (flag) {
                        worldserver.playSound((EntityHuman) null, (double) this.a.getX(), (double) this.a.getY(), (double) this.a.getZ(), SoundEffects.ITEM_CROP_PLANT, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        itemstack.subtract(1);
                        if (itemstack.isEmpty()) {
                            inventorysubcontainer.setItem(j, ItemStack.a);
                        }
                        break;
                    }
                }
            }

            if (block instanceof BlockCrops && !((BlockCrops) block).isRipe(iblockdata)) {
                this.f.remove(this.a);
                this.a = this.a(worldserver);
                if (this.a != null) {
                    this.d = i + 20L;
                    entityvillager.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, (Object) (new MemoryTarget(new BehaviorTarget(this.a), 0.5F, 1)));
                    entityvillager.getBehaviorController().setMemory(MemoryModuleType.LOOK_TARGET, (Object) (new BehaviorTarget(this.a)));
                }
            }
        }

        ++this.e;
    }

    protected boolean g(WorldServer worldserver, EntityVillager entityvillager, long i) {
        return this.e < 200;
    }
}
