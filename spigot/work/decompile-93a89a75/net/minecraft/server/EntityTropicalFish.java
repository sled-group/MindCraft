package net.minecraft.server;

import javax.annotation.Nullable;

public class EntityTropicalFish extends EntityFishSchool {

    private static final DataWatcherObject<Integer> c = DataWatcher.a(EntityTropicalFish.class, DataWatcherRegistry.b);
    private static final MinecraftKey[] d = new MinecraftKey[]{new MinecraftKey("textures/entity/fish/tropical_a.png"), new MinecraftKey("textures/entity/fish/tropical_b.png")};
    private static final MinecraftKey[] bz = new MinecraftKey[]{new MinecraftKey("textures/entity/fish/tropical_a_pattern_1.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_2.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_3.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_4.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_5.png"), new MinecraftKey("textures/entity/fish/tropical_a_pattern_6.png")};
    private static final MinecraftKey[] bA = new MinecraftKey[]{new MinecraftKey("textures/entity/fish/tropical_b_pattern_1.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_2.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_3.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_4.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_5.png"), new MinecraftKey("textures/entity/fish/tropical_b_pattern_6.png")};
    public static final int[] b = new int[]{a(EntityTropicalFish.Variant.STRIPEY, EnumColor.ORANGE, EnumColor.GRAY), a(EntityTropicalFish.Variant.FLOPPER, EnumColor.GRAY, EnumColor.GRAY), a(EntityTropicalFish.Variant.FLOPPER, EnumColor.GRAY, EnumColor.BLUE), a(EntityTropicalFish.Variant.CLAYFISH, EnumColor.WHITE, EnumColor.GRAY), a(EntityTropicalFish.Variant.SUNSTREAK, EnumColor.BLUE, EnumColor.GRAY), a(EntityTropicalFish.Variant.KOB, EnumColor.ORANGE, EnumColor.WHITE), a(EntityTropicalFish.Variant.SPOTTY, EnumColor.PINK, EnumColor.LIGHT_BLUE), a(EntityTropicalFish.Variant.BLOCKFISH, EnumColor.PURPLE, EnumColor.YELLOW), a(EntityTropicalFish.Variant.CLAYFISH, EnumColor.WHITE, EnumColor.RED), a(EntityTropicalFish.Variant.SPOTTY, EnumColor.WHITE, EnumColor.YELLOW), a(EntityTropicalFish.Variant.GLITTER, EnumColor.WHITE, EnumColor.GRAY), a(EntityTropicalFish.Variant.CLAYFISH, EnumColor.WHITE, EnumColor.ORANGE), a(EntityTropicalFish.Variant.DASHER, EnumColor.CYAN, EnumColor.PINK), a(EntityTropicalFish.Variant.BRINELY, EnumColor.LIME, EnumColor.LIGHT_BLUE), a(EntityTropicalFish.Variant.BETTY, EnumColor.RED, EnumColor.WHITE), a(EntityTropicalFish.Variant.SNOOPER, EnumColor.GRAY, EnumColor.RED), a(EntityTropicalFish.Variant.BLOCKFISH, EnumColor.RED, EnumColor.WHITE), a(EntityTropicalFish.Variant.FLOPPER, EnumColor.WHITE, EnumColor.YELLOW), a(EntityTropicalFish.Variant.KOB, EnumColor.RED, EnumColor.WHITE), a(EntityTropicalFish.Variant.SUNSTREAK, EnumColor.GRAY, EnumColor.WHITE), a(EntityTropicalFish.Variant.DASHER, EnumColor.CYAN, EnumColor.YELLOW), a(EntityTropicalFish.Variant.FLOPPER, EnumColor.YELLOW, EnumColor.YELLOW)};
    private boolean bB = true;

    private static int a(EntityTropicalFish.Variant entitytropicalfish_variant, EnumColor enumcolor, EnumColor enumcolor1) {
        return entitytropicalfish_variant.a() & 255 | (entitytropicalfish_variant.b() & 255) << 8 | (enumcolor.getColorIndex() & 255) << 16 | (enumcolor1.getColorIndex() & 255) << 24;
    }

    public EntityTropicalFish(EntityTypes<? extends EntityTropicalFish> entitytypes, World world) {
        super(entitytypes, world);
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.register(EntityTropicalFish.c, 0);
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.setInt("Variant", this.getVariant());
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.setVariant(nbttagcompound.getInt("Variant"));
    }

    public void setVariant(int i) {
        this.datawatcher.set(EntityTropicalFish.c, i);
    }

    @Override
    public boolean c(int i) {
        return !this.bB;
    }

    public int getVariant() {
        return (Integer) this.datawatcher.get(EntityTropicalFish.c);
    }

    @Override
    protected void i(ItemStack itemstack) {
        super.i(itemstack);
        NBTTagCompound nbttagcompound = itemstack.getOrCreateTag();

        nbttagcompound.setInt("BucketVariantTag", this.getVariant());
    }

    @Override
    protected ItemStack l() {
        return new ItemStack(Items.TROPICAL_FISH_BUCKET);
    }

    @Override
    protected SoundEffect getSoundAmbient() {
        return SoundEffects.ENTITY_TROPICAL_FISH_AMBIENT;
    }

    @Override
    protected SoundEffect getSoundDeath() {
        return SoundEffects.ENTITY_TROPICAL_FISH_DEATH;
    }

    @Override
    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return SoundEffects.ENTITY_TROPICAL_FISH_HURT;
    }

    @Override
    protected SoundEffect getSoundFlop() {
        return SoundEffects.ENTITY_TROPICAL_FISH_FLOP;
    }

    @Nullable
    @Override
    public GroupDataEntity prepare(GeneratorAccess generatoraccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
        Object object = super.prepare(generatoraccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);

        if (nbttagcompound != null && nbttagcompound.hasKeyOfType("BucketVariantTag", 3)) {
            this.setVariant(nbttagcompound.getInt("BucketVariantTag"));
            return (GroupDataEntity) object;
        } else {
            int i;
            int j;
            int k;
            int l;

            if (object instanceof EntityTropicalFish.b) {
                EntityTropicalFish.b entitytropicalfish_b = (EntityTropicalFish.b) object;

                i = entitytropicalfish_b.b;
                j = entitytropicalfish_b.c;
                k = entitytropicalfish_b.d;
                l = entitytropicalfish_b.e;
            } else if ((double) this.random.nextFloat() < 0.9D) {
                int i1 = EntityTropicalFish.b[this.random.nextInt(EntityTropicalFish.b.length)];

                i = i1 & 255;
                j = (i1 & '\uff00') >> 8;
                k = (i1 & 16711680) >> 16;
                l = (i1 & -16777216) >> 24;
                object = new EntityTropicalFish.b(this, i, j, k, l);
            } else {
                this.bB = false;
                i = this.random.nextInt(2);
                j = this.random.nextInt(6);
                k = this.random.nextInt(15);
                l = this.random.nextInt(15);
            }

            this.setVariant(i | j << 8 | k << 16 | l << 24);
            return (GroupDataEntity) object;
        }
    }

    static class b extends EntityFishSchool.a {

        private final int b;
        private final int c;
        private final int d;
        private final int e;

        private b(EntityTropicalFish entitytropicalfish, int i, int j, int k, int l) {
            super(entitytropicalfish);
            this.b = i;
            this.c = j;
            this.d = k;
            this.e = l;
        }
    }

    static enum Variant {

        KOB(0, 0), SUNSTREAK(0, 1), SNOOPER(0, 2), DASHER(0, 3), BRINELY(0, 4), SPOTTY(0, 5), FLOPPER(1, 0), STRIPEY(1, 1), GLITTER(1, 2), BLOCKFISH(1, 3), BETTY(1, 4), CLAYFISH(1, 5);

        private final int m;
        private final int n;
        private static final EntityTropicalFish.Variant[] o = values();

        private Variant(int i, int j) {
            this.m = i;
            this.n = j;
        }

        public int a() {
            return this.m;
        }

        public int b() {
            return this.n;
        }
    }
}
