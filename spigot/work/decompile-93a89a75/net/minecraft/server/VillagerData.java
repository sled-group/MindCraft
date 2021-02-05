package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

public class VillagerData {

    private static final int[] a = new int[]{0, 10, 70, 150, 250};
    private final VillagerType b;
    private final VillagerProfession c;
    private final int d;

    public VillagerData(VillagerType villagertype, VillagerProfession villagerprofession, int i) {
        this.b = villagertype;
        this.c = villagerprofession;
        this.d = Math.max(1, i);
    }

    public VillagerData(Dynamic<?> dynamic) {
        this((VillagerType) IRegistry.VILLAGER_TYPE.get(MinecraftKey.a(dynamic.get("type").asString(""))), (VillagerProfession) IRegistry.VILLAGER_PROFESSION.get(MinecraftKey.a(dynamic.get("profession").asString(""))), dynamic.get("level").asInt(1));
    }

    public VillagerType getType() {
        return this.b;
    }

    public VillagerProfession getProfession() {
        return this.c;
    }

    public int getLevel() {
        return this.d;
    }

    public VillagerData withType(VillagerType villagertype) {
        return new VillagerData(villagertype, this.c, this.d);
    }

    public VillagerData withProfession(VillagerProfession villagerprofession) {
        return new VillagerData(this.b, villagerprofession, this.d);
    }

    public VillagerData withLevel(int i) {
        return new VillagerData(this.b, this.c, i);
    }

    public <T> T a(DynamicOps<T> dynamicops) {
        return dynamicops.createMap(ImmutableMap.of(dynamicops.createString("type"), dynamicops.createString(IRegistry.VILLAGER_TYPE.getKey(this.b).toString()), dynamicops.createString("profession"), dynamicops.createString(IRegistry.VILLAGER_PROFESSION.getKey(this.c).toString()), dynamicops.createString("level"), dynamicops.createInt(this.d)));
    }

    public static int c(int i) {
        return d(i) ? VillagerData.a[i] : 0;
    }

    public static boolean d(int i) {
        return i >= 1 && i < 5;
    }
}
