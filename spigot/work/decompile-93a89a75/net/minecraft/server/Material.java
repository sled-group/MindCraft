package net.minecraft.server;

public final class Material {

    public static final Material AIR = (new Material.a(MaterialMapColor.b)).c().j().b().f().i();
    public static final Material STRUCTURE_VOID = (new Material.a(MaterialMapColor.b)).c().j().b().f().i();
    public static final Material PORTAL = (new Material.a(MaterialMapColor.b)).c().j().b().h().i();
    public static final Material WOOL = (new Material.a(MaterialMapColor.e)).c().j().b().e().i();
    public static final Material PLANT = (new Material.a(MaterialMapColor.i)).c().j().b().g().i();
    public static final Material WATER_PLANT = (new Material.a(MaterialMapColor.n)).c().j().b().g().i();
    public static final Material REPLACEABLE_PLANT = (new Material.a(MaterialMapColor.i)).c().j().b().g().f().e().i();
    public static final Material REPLACEABLE_WATER_PLANT = (new Material.a(MaterialMapColor.n)).c().j().b().g().f().i();
    public static final Material WATER = (new Material.a(MaterialMapColor.n)).c().j().b().g().f().a().i();
    public static final Material BUBBLE_COLUMN = (new Material.a(MaterialMapColor.n)).c().j().b().g().f().a().i();
    public static final Material LAVA = (new Material.a(MaterialMapColor.f)).c().j().b().g().f().a().i();
    public static final Material PACKED_ICE = (new Material.a(MaterialMapColor.j)).c().j().b().g().f().d().i();
    public static final Material FIRE = (new Material.a(MaterialMapColor.b)).c().j().b().g().f().i();
    public static final Material ORIENTABLE = (new Material.a(MaterialMapColor.b)).c().j().b().g().i();
    public static final Material WEB = (new Material.a(MaterialMapColor.e)).c().j().g().d().i();
    public static final Material BUILDABLE_GLASS = (new Material.a(MaterialMapColor.b)).i();
    public static final Material CLAY = (new Material.a(MaterialMapColor.k)).i();
    public static final Material EARTH = (new Material.a(MaterialMapColor.l)).i();
    public static final Material GRASS = (new Material.a(MaterialMapColor.c)).i();
    public static final Material SNOW_LAYER = (new Material.a(MaterialMapColor.g)).i();
    public static final Material SAND = (new Material.a(MaterialMapColor.d)).i();
    public static final Material SPONGE = (new Material.a(MaterialMapColor.t)).i();
    public static final Material SHULKER_SHELL = (new Material.a(MaterialMapColor.z)).i();
    public static final Material WOOD = (new Material.a(MaterialMapColor.o)).e().i();
    public static final Material BAMBOO_SAPLING = (new Material.a(MaterialMapColor.o)).e().g().c().i();
    public static final Material BAMBOO = (new Material.a(MaterialMapColor.o)).e().g().i();
    public static final Material CLOTH = (new Material.a(MaterialMapColor.e)).e().i();
    public static final Material TNT = (new Material.a(MaterialMapColor.f)).e().j().i();
    public static final Material LEAVES = (new Material.a(MaterialMapColor.i)).e().j().g().i();
    public static final Material SHATTERABLE = (new Material.a(MaterialMapColor.b)).j().i();
    public static final Material ICE = (new Material.a(MaterialMapColor.g)).j().i();
    public static final Material CACTUS = (new Material.a(MaterialMapColor.i)).j().g().i();
    public static final Material STONE = (new Material.a(MaterialMapColor.m)).d().i();
    public static final Material ORE = (new Material.a(MaterialMapColor.h)).d().i();
    public static final Material SNOW_BLOCK = (new Material.a(MaterialMapColor.j)).d().i();
    public static final Material HEAVY = (new Material.a(MaterialMapColor.h)).d().h().i();
    public static final Material BANNER = (new Material.a(MaterialMapColor.b)).d().h().i();
    public static final Material PISTON = (new Material.a(MaterialMapColor.m)).h().i();
    public static final Material CORAL = (new Material.a(MaterialMapColor.i)).g().i();
    public static final Material PUMPKIN = (new Material.a(MaterialMapColor.i)).g().i();
    public static final Material DRAGON_EGG = (new Material.a(MaterialMapColor.i)).g().i();
    public static final Material CAKE = (new Material.a(MaterialMapColor.b)).g().i();
    private final MaterialMapColor Q;
    private final EnumPistonReaction R;
    private final boolean S;
    private final boolean canBurn;
    private final boolean U;
    private final boolean V;
    private final boolean W;
    private final boolean X;
    private final boolean Y;

    public Material(MaterialMapColor materialmapcolor, boolean flag, boolean flag1, boolean flag2, boolean flag3, boolean flag4, boolean flag5, boolean flag6, EnumPistonReaction enumpistonreaction) {
        this.Q = materialmapcolor;
        this.V = flag;
        this.Y = flag1;
        this.S = flag2;
        this.W = flag3;
        this.U = flag4;
        this.canBurn = flag5;
        this.X = flag6;
        this.R = enumpistonreaction;
    }

    public boolean isLiquid() {
        return this.V;
    }

    public boolean isBuildable() {
        return this.Y;
    }

    public boolean isSolid() {
        return this.S;
    }

    public boolean isBurnable() {
        return this.canBurn;
    }

    public boolean isReplaceable() {
        return this.X;
    }

    public boolean f() {
        return this.W;
    }

    public boolean isAlwaysDestroyable() {
        return this.U;
    }

    public EnumPistonReaction getPushReaction() {
        return this.R;
    }

    public MaterialMapColor i() {
        return this.Q;
    }

    public static class a {

        private EnumPistonReaction a;
        private boolean b;
        private boolean c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private final MaterialMapColor h;
        private boolean i;

        public a(MaterialMapColor materialmapcolor) {
            this.a = EnumPistonReaction.NORMAL;
            this.b = true;
            this.d = true;
            this.g = true;
            this.i = true;
            this.h = materialmapcolor;
        }

        public Material.a a() {
            this.e = true;
            return this;
        }

        public Material.a b() {
            this.g = false;
            return this;
        }

        public Material.a c() {
            this.b = false;
            return this;
        }

        private Material.a j() {
            this.i = false;
            return this;
        }

        protected Material.a d() {
            this.d = false;
            return this;
        }

        protected Material.a e() {
            this.c = true;
            return this;
        }

        public Material.a f() {
            this.f = true;
            return this;
        }

        protected Material.a g() {
            this.a = EnumPistonReaction.DESTROY;
            return this;
        }

        protected Material.a h() {
            this.a = EnumPistonReaction.BLOCK;
            return this;
        }

        public Material i() {
            return new Material(this.h, this.e, this.g, this.b, this.i, this.d, this.c, this.f, this.a);
        }
    }
}
