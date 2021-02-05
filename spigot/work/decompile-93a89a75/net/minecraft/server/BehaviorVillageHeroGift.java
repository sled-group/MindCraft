package net.minecraft.server;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BehaviorVillageHeroGift extends Behavior<EntityVillager> {

    private static final Map<VillagerProfession, MinecraftKey> a = (Map) SystemUtils.a((Object) Maps.newHashMap(), (hashmap) -> {
        hashmap.put(VillagerProfession.ARMORER, LootTables.ag);
        hashmap.put(VillagerProfession.BUTCHER, LootTables.ah);
        hashmap.put(VillagerProfession.CARTOGRAPHER, LootTables.ai);
        hashmap.put(VillagerProfession.CLERIC, LootTables.aj);
        hashmap.put(VillagerProfession.FARMER, LootTables.ak);
        hashmap.put(VillagerProfession.FISHERMAN, LootTables.al);
        hashmap.put(VillagerProfession.FLETCHER, LootTables.am);
        hashmap.put(VillagerProfession.LEATHERWORKER, LootTables.an);
        hashmap.put(VillagerProfession.LIBRARIAN, LootTables.ao);
        hashmap.put(VillagerProfession.MASON, LootTables.ap);
        hashmap.put(VillagerProfession.SHEPHERD, LootTables.aq);
        hashmap.put(VillagerProfession.TOOLSMITH, LootTables.ar);
        hashmap.put(VillagerProfession.WEAPONSMITH, LootTables.as);
    });
    private int b = 600;
    private boolean c;
    private long d;

    public BehaviorVillageHeroGift(int i) {
        super(ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryStatus.VALUE_PRESENT), i);
    }

    protected boolean a(WorldServer worldserver, EntityVillager entityvillager) {
        if (!this.b(entityvillager)) {
            return false;
        } else if (this.b > 0) {
            --this.b;
            return false;
        } else {
            return true;
        }
    }

    protected void a(WorldServer worldserver, EntityVillager entityvillager, long i) {
        this.c = false;
        this.d = i;
        EntityHuman entityhuman = (EntityHuman) this.c(entityvillager).get();

        entityvillager.getBehaviorController().setMemory(MemoryModuleType.INTERACTION_TARGET, (Object) entityhuman);
        BehaviorUtil.c(entityvillager, entityhuman);
    }

    protected boolean g(WorldServer worldserver, EntityVillager entityvillager, long i) {
        return this.b(entityvillager) && !this.c;
    }

    protected void d(WorldServer worldserver, EntityVillager entityvillager, long i) {
        EntityHuman entityhuman = (EntityHuman) this.c(entityvillager).get();

        BehaviorUtil.c(entityvillager, entityhuman);
        if (this.a(entityvillager, entityhuman)) {
            if (i - this.d > 20L) {
                this.a(entityvillager, (EntityLiving) entityhuman);
                this.c = true;
            }
        } else {
            BehaviorUtil.a((EntityLiving) entityvillager, (EntityLiving) entityhuman, 5);
        }

    }

    protected void f(WorldServer worldserver, EntityVillager entityvillager, long i) {
        this.b = a(worldserver);
        entityvillager.getBehaviorController().removeMemory(MemoryModuleType.INTERACTION_TARGET);
        entityvillager.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
        entityvillager.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
    }

    private void a(EntityVillager entityvillager, EntityLiving entityliving) {
        List<ItemStack> list = this.a(entityvillager);
        Iterator iterator = list.iterator();

        while (iterator.hasNext()) {
            ItemStack itemstack = (ItemStack) iterator.next();

            BehaviorUtil.a((EntityLiving) entityvillager, itemstack, entityliving);
        }

    }

    private List<ItemStack> a(EntityVillager entityvillager) {
        if (entityvillager.isBaby()) {
            return ImmutableList.of(new ItemStack(Items.aV));
        } else {
            VillagerProfession villagerprofession = entityvillager.getVillagerData().getProfession();

            if (BehaviorVillageHeroGift.a.containsKey(villagerprofession)) {
                LootTable loottable = entityvillager.world.getMinecraftServer().getLootTableRegistry().getLootTable((MinecraftKey) BehaviorVillageHeroGift.a.get(villagerprofession));
                LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder((WorldServer) entityvillager.world)).set(LootContextParameters.POSITION, new BlockPosition(entityvillager)).set(LootContextParameters.THIS_ENTITY, entityvillager).a(entityvillager.getRandom());

                return loottable.populateLoot(loottableinfo_builder.build(LootContextParameterSets.GIFT));
            } else {
                return ImmutableList.of(new ItemStack(Items.WHEAT_SEEDS));
            }
        }
    }

    private boolean b(EntityVillager entityvillager) {
        return this.c(entityvillager).isPresent();
    }

    private Optional<EntityHuman> c(EntityVillager entityvillager) {
        return entityvillager.getBehaviorController().getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER).filter(this::a);
    }

    private boolean a(EntityHuman entityhuman) {
        return entityhuman.hasEffect(MobEffects.HERO_OF_THE_VILLAGE);
    }

    private boolean a(EntityVillager entityvillager, EntityHuman entityhuman) {
        BlockPosition blockposition = new BlockPosition(entityhuman);
        BlockPosition blockposition1 = new BlockPosition(entityvillager);

        return blockposition1.a((BaseBlockPosition) blockposition, 5.0D);
    }

    private static int a(WorldServer worldserver) {
        return 600 + worldserver.random.nextInt(6001);
    }
}
