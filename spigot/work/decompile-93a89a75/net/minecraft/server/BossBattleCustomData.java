package net.minecraft.server;

import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class BossBattleCustomData {

    private final MinecraftServer a;
    private final Map<MinecraftKey, BossBattleCustom> b = Maps.newHashMap();

    public BossBattleCustomData(MinecraftServer minecraftserver) {
        this.a = minecraftserver;
    }

    @Nullable
    public BossBattleCustom a(MinecraftKey minecraftkey) {
        return (BossBattleCustom) this.b.get(minecraftkey);
    }

    public BossBattleCustom register(MinecraftKey minecraftkey, IChatBaseComponent ichatbasecomponent) {
        BossBattleCustom bossbattlecustom = new BossBattleCustom(minecraftkey, ichatbasecomponent);

        this.b.put(minecraftkey, bossbattlecustom);
        return bossbattlecustom;
    }

    public void remove(BossBattleCustom bossbattlecustom) {
        this.b.remove(bossbattlecustom.getKey());
    }

    public Collection<MinecraftKey> a() {
        return this.b.keySet();
    }

    public Collection<BossBattleCustom> getBattles() {
        return this.b.values();
    }

    public NBTTagCompound c() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            BossBattleCustom bossbattlecustom = (BossBattleCustom) iterator.next();

            nbttagcompound.set(bossbattlecustom.getKey().toString(), bossbattlecustom.f());
        }

        return nbttagcompound;
    }

    public void a(NBTTagCompound nbttagcompound) {
        Iterator iterator = nbttagcompound.getKeys().iterator();

        while (iterator.hasNext()) {
            String s = (String) iterator.next();
            MinecraftKey minecraftkey = new MinecraftKey(s);

            this.b.put(minecraftkey, BossBattleCustom.a(nbttagcompound.getCompound(s), minecraftkey));
        }

    }

    public void a(EntityPlayer entityplayer) {
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            BossBattleCustom bossbattlecustom = (BossBattleCustom) iterator.next();

            bossbattlecustom.c(entityplayer);
        }

    }

    public void b(EntityPlayer entityplayer) {
        Iterator iterator = this.b.values().iterator();

        while (iterator.hasNext()) {
            BossBattleCustom bossbattlecustom = (BossBattleCustom) iterator.next();

            bossbattlecustom.d(entityplayer);
        }

    }
}
