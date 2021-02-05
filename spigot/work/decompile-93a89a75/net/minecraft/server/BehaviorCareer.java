package net.minecraft.server;

import com.google.common.collect.ImmutableMap;

public class BehaviorCareer extends Behavior<EntityVillager> {

    public BehaviorCareer() {
        super(ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryStatus.VALUE_PRESENT));
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        return entityvillager.getVillagerData().getProfession() == VillagerProfession.NONE;
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        GlobalPos globalpos = (GlobalPos) entityvillager.getBehaviorController().getMemory(MemoryModuleType.JOB_SITE).get();
        MinecraftServer minecraftserver = worldserver.getMinecraftServer();

        minecraftserver.getWorldServer(globalpos.getDimensionManager()).B().c(globalpos.getBlockPosition()).ifPresent((villageplacetype) -> {
            IRegistry.VILLAGER_PROFESSION.d().filter((villagerprofession) -> {
                return villagerprofession.b() == villageplacetype;
            }).findFirst().ifPresent((villagerprofession) -> {
                entityvillager.setVillagerData(entityvillager.getVillagerData().withProfession(villagerprofession));
                entityvillager.a(worldserver);
            });
        });
    }
}
