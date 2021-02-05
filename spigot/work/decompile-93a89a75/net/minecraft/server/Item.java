package net.minecraft.server;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;

public class Item implements IMaterial {

    public static final Map<Block, Item> f = Maps.newHashMap();
    private static final IDynamicTexture a = (itemstack, world, entityliving) -> {
        return itemstack.f() ? 1.0F : 0.0F;
    };
    private static final IDynamicTexture b = (itemstack, world, entityliving) -> {
        return MathHelper.a((float) itemstack.getDamage() / (float) itemstack.h(), 0.0F, 1.0F);
    };
    private static final IDynamicTexture c = (itemstack, world, entityliving) -> {
        return entityliving != null && entityliving.getMainHand() != EnumMainHand.RIGHT ? 1.0F : 0.0F;
    };
    private static final IDynamicTexture d = (itemstack, world, entityliving) -> {
        return entityliving instanceof EntityHuman ? ((EntityHuman) entityliving).getCooldownTracker().a(itemstack.getItem(), 0.0F) : 0.0F;
    };
    private static final IDynamicTexture e = (itemstack, world, entityliving) -> {
        return itemstack.hasTag() ? (float) itemstack.getTag().getInt("CustomModelData") : 0.0F;
    };
    protected static final UUID g = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    protected static final UUID h = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");
    protected static final Random i = new Random();
    private final Map<MinecraftKey, IDynamicTexture> k = Maps.newHashMap();
    protected final CreativeModeTab j;
    private final EnumItemRarity l;
    private final int maxStackSize;
    private final int durability;
    private final Item craftingResult;
    @Nullable
    private String name;
    @Nullable
    private final FoodInfo foodInfo;

    public static int getId(Item item) {
        return item == null ? 0 : IRegistry.ITEM.a((Object) item);
    }

    public static Item getById(int i) {
        return (Item) IRegistry.ITEM.fromId(i);
    }

    @Deprecated
    public static Item getItemOf(Block block) {
        return (Item) Item.f.getOrDefault(block, Items.AIR);
    }

    public Item(Item.Info item_info) {
        this.a(new MinecraftKey("lefthanded"), Item.c);
        this.a(new MinecraftKey("cooldown"), Item.d);
        this.a(new MinecraftKey("custom_model_data"), Item.e);
        this.j = item_info.d;
        this.l = item_info.e;
        this.craftingResult = item_info.c;
        this.durability = item_info.b;
        this.maxStackSize = item_info.a;
        this.foodInfo = item_info.f;
        if (this.durability > 0) {
            this.a(new MinecraftKey("damaged"), Item.a);
            this.a(new MinecraftKey("damage"), Item.b);
        }

    }

    public void a(World world, EntityLiving entityliving, ItemStack itemstack, int i) {}

    public boolean a(NBTTagCompound nbttagcompound) {
        return false;
    }

    public boolean a(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman) {
        return true;
    }

    @Override
    public Item getItem() {
        return this;
    }

    public final void a(MinecraftKey minecraftkey, IDynamicTexture idynamictexture) {
        this.k.put(minecraftkey, idynamictexture);
    }

    public EnumInteractionResult a(ItemActionContext itemactioncontext) {
        return EnumInteractionResult.PASS;
    }

    public float getDestroySpeed(ItemStack itemstack, IBlockData iblockdata) {
        return 1.0F;
    }

    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        if (this.isFood()) {
            ItemStack itemstack = entityhuman.b(enumhand);

            if (entityhuman.p(this.getFoodInfo().d())) {
                entityhuman.c(enumhand);
                return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
            } else {
                return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
            }
        } else {
            return new InteractionResultWrapper<>(EnumInteractionResult.PASS, entityhuman.b(enumhand));
        }
    }

    public ItemStack a(ItemStack itemstack, World world, EntityLiving entityliving) {
        return this.isFood() ? entityliving.a(world, itemstack) : itemstack;
    }

    public final int getMaxStackSize() {
        return this.maxStackSize;
    }

    public final int getMaxDurability() {
        return this.durability;
    }

    public boolean usesDurability() {
        return this.durability > 0;
    }

    public boolean a(ItemStack itemstack, EntityLiving entityliving, EntityLiving entityliving1) {
        return false;
    }

    public boolean a(ItemStack itemstack, World world, IBlockData iblockdata, BlockPosition blockposition, EntityLiving entityliving) {
        return false;
    }

    public boolean canDestroySpecialBlock(IBlockData iblockdata) {
        return false;
    }

    public boolean a(ItemStack itemstack, EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
        return false;
    }

    public String toString() {
        return IRegistry.ITEM.getKey(this).getKey();
    }

    protected String l() {
        if (this.name == null) {
            this.name = SystemUtils.a("item", IRegistry.ITEM.getKey(this));
        }

        return this.name;
    }

    public String getName() {
        return this.l();
    }

    public String f(ItemStack itemstack) {
        return this.getName();
    }

    public boolean m() {
        return true;
    }

    @Nullable
    public final Item n() {
        return this.craftingResult;
    }

    public boolean o() {
        return this.craftingResult != null;
    }

    public void a(ItemStack itemstack, World world, Entity entity, int i, boolean flag) {}

    public void b(ItemStack itemstack, World world, EntityHuman entityhuman) {}

    public boolean O_() {
        return false;
    }

    public EnumAnimation e_(ItemStack itemstack) {
        return itemstack.getItem().isFood() ? EnumAnimation.EAT : EnumAnimation.NONE;
    }

    public int f_(ItemStack itemstack) {
        return itemstack.getItem().isFood() ? (this.getFoodInfo().e() ? 16 : 32) : 0;
    }

    public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {}

    public IChatBaseComponent g(ItemStack itemstack) {
        return new ChatMessage(this.f(itemstack), new Object[0]);
    }

    public EnumItemRarity h(ItemStack itemstack) {
        if (!itemstack.hasEnchantments()) {
            return this.l;
        } else {
            switch (this.l) {
                case COMMON:
                case UNCOMMON:
                    return EnumItemRarity.RARE;
                case RARE:
                    return EnumItemRarity.EPIC;
                case EPIC:
                default:
                    return this.l;
            }
        }
    }

    public boolean g_(ItemStack itemstack) {
        return this.getMaxStackSize() == 1 && this.usesDurability();
    }

    protected static MovingObjectPosition a(World world, EntityHuman entityhuman, RayTrace.FluidCollisionOption raytrace_fluidcollisionoption) {
        float f = entityhuman.pitch;
        float f1 = entityhuman.yaw;
        Vec3D vec3d = entityhuman.j(1.0F);
        float f2 = MathHelper.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = MathHelper.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -MathHelper.cos(-f * 0.017453292F);
        float f5 = MathHelper.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = 5.0D;
        Vec3D vec3d1 = vec3d.add((double) f6 * 5.0D, (double) f5 * 5.0D, (double) f7 * 5.0D);

        return world.rayTrace(new RayTrace(vec3d, vec3d1, RayTrace.BlockCollisionOption.OUTLINE, raytrace_fluidcollisionoption, entityhuman));
    }

    public int c() {
        return 0;
    }

    public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
        if (this.a(creativemodetab)) {
            nonnulllist.add(new ItemStack(this));
        }

    }

    protected boolean a(CreativeModeTab creativemodetab) {
        CreativeModeTab creativemodetab1 = this.p();

        return creativemodetab1 != null && (creativemodetab == CreativeModeTab.g || creativemodetab == creativemodetab1);
    }

    @Nullable
    public final CreativeModeTab p() {
        return this.j;
    }

    public boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return false;
    }

    public Multimap<String, AttributeModifier> a(EnumItemSlot enumitemslot) {
        return HashMultimap.create();
    }

    public boolean i(ItemStack itemstack) {
        return itemstack.getItem() == Items.CROSSBOW;
    }

    public boolean a(Tag<Item> tag) {
        return tag.isTagged(this);
    }

    public boolean isFood() {
        return this.foodInfo != null;
    }

    @Nullable
    public FoodInfo getFoodInfo() {
        return this.foodInfo;
    }

    public static class Info {

        private int a = 64;
        private int b;
        private Item c;
        private CreativeModeTab d;
        private EnumItemRarity e;
        private FoodInfo f;

        public Info() {
            this.e = EnumItemRarity.COMMON;
        }

        public Item.Info a(FoodInfo foodinfo) {
            this.f = foodinfo;
            return this;
        }

        public Item.Info a(int i) {
            if (this.b > 0) {
                throw new RuntimeException("Unable to have damage AND stack.");
            } else {
                this.a = i;
                return this;
            }
        }

        public Item.Info b(int i) {
            return this.b == 0 ? this.c(i) : this;
        }

        public Item.Info c(int i) {
            this.b = i;
            this.a = 1;
            return this;
        }

        public Item.Info a(Item item) {
            this.c = item;
            return this;
        }

        public Item.Info a(CreativeModeTab creativemodetab) {
            this.d = creativemodetab;
            return this;
        }

        public Item.Info a(EnumItemRarity enumitemrarity) {
            this.e = enumitemrarity;
            return this;
        }
    }
}
