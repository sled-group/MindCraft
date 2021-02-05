package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;

public class BehaviorCelebrate extends Behavior<EntityVillager> {

    @Nullable
    private Raid a;

    public BehaviorCelebrate(int i, int j) {
        super(ImmutableMap.of(), i, j);
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        this.a = worldserver.c_(new BlockPosition(entityvillager));
        return this.a != null && this.a.e() && BehaviorOutside.b(worldserver, entityvillager);
    }

    protected boolean g(WorldServer worldserver, EntityVillager entityvillager, long i) {
        return this.a != null && !this.a.d();
    }

    protected void f(WorldServer worldserver, EntityVillager entityvillager, long i) {
        this.a = null;
        entityvillager.getBehaviorController().a(worldserver.getDayTime(), worldserver.getTime());
    }

    protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
        Random random = entityvillager.getRandom();

        if (random.nextInt(100) == 0) {
            entityvillager.ec();
        }

        if (random.nextInt(200) == 0 && BehaviorOutside.b(worldserver, entityvillager)) {
            EnumColor enumcolor = EnumColor.values()[random.nextInt(EnumColor.values().length)];
            int j = random.nextInt(3);
            ItemStack itemstack = this.a(enumcolor, j);
            EntityFireworks entityfireworks = new EntityFireworks(entityvillager.world, entityvillager.locX, entityvillager.locY + (double) entityvillager.getHeadHeight(), entityvillager.locZ, itemstack);

            entityvillager.world.addEntity(entityfireworks);
        }

    }

    private ItemStack a(EnumColor enumcolor, int i) {
        ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET, 1);
        ItemStack itemstack1 = new ItemStack(Items.FIREWORK_STAR);
        NBTTagCompound nbttagcompound = itemstack1.a("Explosion");
        List<Integer> list = Lists.newArrayList();

        list.add(enumcolor.f());
        nbttagcompound.b("Colors", (List) list);
        nbttagcompound.setByte("Type", (byte) ItemFireworks.EffectType.BURST.a());
        NBTTagCompound nbttagcompound1 = itemstack.a("Fireworks");
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound2 = itemstack1.b("Explosion");

        if (nbttagcompound2 != null) {
            nbttaglist.add(nbttagcompound2);
        }

        nbttagcompound1.setByte("Flight", (byte) i);
        if (!nbttaglist.isEmpty()) {
            nbttagcompound1.set("Explosions", nbttaglist);
        }

        return itemstack;
    }
}
