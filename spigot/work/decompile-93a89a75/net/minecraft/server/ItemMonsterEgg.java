package net.minecraft.server;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

public class ItemMonsterEgg extends Item {

    private static final Map<EntityTypes<?>, ItemMonsterEgg> a = Maps.newIdentityHashMap();
    private final int b;
    private final int c;
    private final EntityTypes<?> d;

    public ItemMonsterEgg(EntityTypes<?> entitytypes, int i, int j, Item.Info item_info) {
        super(item_info);
        this.d = entitytypes;
        this.b = i;
        this.c = j;
        ItemMonsterEgg.a.put(entitytypes, this);
    }

    @Override
    public EnumInteractionResult a(ItemActionContext itemactioncontext) {
        World world = itemactioncontext.getWorld();

        if (world.isClientSide) {
            return EnumInteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = itemactioncontext.getItemStack();
            BlockPosition blockposition = itemactioncontext.getClickPosition();
            EnumDirection enumdirection = itemactioncontext.getClickedFace();
            IBlockData iblockdata = world.getType(blockposition);
            Block block = iblockdata.getBlock();

            if (block == Blocks.SPAWNER) {
                TileEntity tileentity = world.getTileEntity(blockposition);

                if (tileentity instanceof TileEntityMobSpawner) {
                    MobSpawnerAbstract mobspawnerabstract = ((TileEntityMobSpawner) tileentity).getSpawner();
                    EntityTypes<?> entitytypes = this.b(itemstack.getTag());

                    mobspawnerabstract.setMobName(entitytypes);
                    tileentity.update();
                    world.notify(blockposition, iblockdata, iblockdata, 3);
                    itemstack.subtract(1);
                    return EnumInteractionResult.SUCCESS;
                }
            }

            BlockPosition blockposition1;

            if (iblockdata.getCollisionShape(world, blockposition).isEmpty()) {
                blockposition1 = blockposition;
            } else {
                blockposition1 = blockposition.shift(enumdirection);
            }

            EntityTypes<?> entitytypes1 = this.b(itemstack.getTag());

            if (entitytypes1.spawnCreature(world, itemstack, itemactioncontext.getEntity(), blockposition1, EnumMobSpawn.SPAWN_EGG, true, !Objects.equals(blockposition, blockposition1) && enumdirection == EnumDirection.UP) != null) {
                itemstack.subtract(1);
            }

            return EnumInteractionResult.SUCCESS;
        }
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);

        if (world.isClientSide) {
            return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
        } else {
            MovingObjectPosition movingobjectposition = a(world, entityhuman, RayTrace.FluidCollisionOption.SOURCE_ONLY);

            if (movingobjectposition.getType() != MovingObjectPosition.EnumMovingObjectType.BLOCK) {
                return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
            } else {
                MovingObjectPositionBlock movingobjectpositionblock = (MovingObjectPositionBlock) movingobjectposition;
                BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();

                if (!(world.getType(blockposition).getBlock() instanceof BlockFluids)) {
                    return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
                } else if (world.a(entityhuman, blockposition) && entityhuman.a(blockposition, movingobjectpositionblock.getDirection(), itemstack)) {
                    EntityTypes<?> entitytypes = this.b(itemstack.getTag());

                    if (entitytypes.spawnCreature(world, itemstack, entityhuman, blockposition, EnumMobSpawn.SPAWN_EGG, false, false) == null) {
                        return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemstack);
                    } else {
                        if (!entityhuman.abilities.canInstantlyBuild) {
                            itemstack.subtract(1);
                        }

                        entityhuman.b(StatisticList.ITEM_USED.b(this));
                        return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
                    }
                } else {
                    return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
                }
            }
        }
    }

    public boolean a(@Nullable NBTTagCompound nbttagcompound, EntityTypes<?> entitytypes) {
        return Objects.equals(this.b(nbttagcompound), entitytypes);
    }

    public static Iterable<ItemMonsterEgg> d() {
        return Iterables.unmodifiableIterable(ItemMonsterEgg.a.values());
    }

    public EntityTypes<?> b(@Nullable NBTTagCompound nbttagcompound) {
        if (nbttagcompound != null && nbttagcompound.hasKeyOfType("EntityTag", 10)) {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("EntityTag");

            if (nbttagcompound1.hasKeyOfType("id", 8)) {
                return (EntityTypes) EntityTypes.a(nbttagcompound1.getString("id")).orElse(this.d);
            }
        }

        return this.d;
    }
}
