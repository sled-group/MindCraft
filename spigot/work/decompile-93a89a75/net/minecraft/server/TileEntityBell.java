package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TileEntityBell extends TileEntity implements ITickable {

    private long g;
    public int a;
    public boolean b;
    public EnumDirection c;
    private List<EntityLiving> h;
    private boolean i;
    private int j;

    public TileEntityBell() {
        super(TileEntityTypes.BELL);
    }

    @Override
    public boolean setProperty(int i, int j) {
        if (i == 1) {
            this.f();
            this.j = 0;
            this.c = EnumDirection.fromType1(j);
            this.a = 0;
            this.b = true;
            return true;
        } else {
            return super.setProperty(i, j);
        }
    }

    @Override
    public void tick() {
        if (this.b) {
            ++this.a;
        }

        if (this.a >= 50) {
            this.b = false;
            this.a = 0;
        }

        if (this.a >= 5 && this.j == 0 && this.h()) {
            this.i = true;
            this.d();
        }

        if (this.i) {
            if (this.j < 40) {
                ++this.j;
            } else {
                this.b(this.world);
                this.c(this.world);
                this.i = false;
            }
        }

    }

    private void d() {
        this.world.playSound((EntityHuman) null, this.getPosition(), SoundEffects.BLOCK_BELL_RESONATE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }

    public void a(EnumDirection enumdirection) {
        BlockPosition blockposition = this.getPosition();

        this.c = enumdirection;
        if (this.b) {
            this.a = 0;
        } else {
            this.b = true;
        }

        this.world.playBlockAction(blockposition, this.getBlock().getBlock(), 1, enumdirection.a());
    }

    private void f() {
        BlockPosition blockposition = this.getPosition();

        if (this.world.getTime() > this.g + 60L || this.h == null) {
            this.g = this.world.getTime();
            AxisAlignedBB axisalignedbb = (new AxisAlignedBB(blockposition)).g(48.0D);

            this.h = this.world.a(EntityLiving.class, axisalignedbb);
        }

        if (!this.world.isClientSide) {
            Iterator iterator = this.h.iterator();

            while (iterator.hasNext()) {
                EntityLiving entityliving = (EntityLiving) iterator.next();

                if (entityliving.isAlive() && !entityliving.dead && blockposition.a((IPosition) entityliving.getPositionVector(), 32.0D)) {
                    entityliving.getBehaviorController().setMemory(MemoryModuleType.HEARD_BELL_TIME, (Object) this.world.getTime());
                }
            }
        }

    }

    private boolean h() {
        BlockPosition blockposition = this.getPosition();
        Iterator iterator = this.h.iterator();

        EntityLiving entityliving;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            entityliving = (EntityLiving) iterator.next();
        } while (!entityliving.isAlive() || entityliving.dead || !blockposition.a((IPosition) entityliving.getPositionVector(), 32.0D) || !entityliving.getEntityType().a(TagsEntity.RADIERS));

        return true;
    }

    private void b(World world) {
        if (!world.isClientSide) {
            this.h.stream().filter(this::a).forEach(this::b);
        }
    }

    private void c(World world) {
        if (world.isClientSide) {
            BlockPosition blockposition = this.getPosition();
            AtomicInteger atomicinteger = new AtomicInteger(16700985);
            int i = (int) this.h.stream().filter((entityliving) -> {
                return blockposition.a((IPosition) entityliving.getPositionVector(), 48.0D);
            }).count();

            this.h.stream().filter(this::a).forEach((entityliving) -> {
                float f = 1.0F;
                float f1 = MathHelper.sqrt((entityliving.locX - (double) blockposition.getX()) * (entityliving.locX - (double) blockposition.getX()) + (entityliving.locZ - (double) blockposition.getZ()) * (entityliving.locZ - (double) blockposition.getZ()));
                double d0 = (double) ((float) blockposition.getX() + 0.5F) + (double) (1.0F / f1) * (entityliving.locX - (double) blockposition.getX());
                double d1 = (double) ((float) blockposition.getZ() + 0.5F) + (double) (1.0F / f1) * (entityliving.locZ - (double) blockposition.getZ());
                int j = MathHelper.clamp((i - 21) / -2, 3, 15);

                for (int k = 0; k < j; ++k) {
                    atomicinteger.addAndGet(5);
                    double d2 = (double) (atomicinteger.get() >> 16 & 255) / 255.0D;
                    double d3 = (double) (atomicinteger.get() >> 8 & 255) / 255.0D;
                    double d4 = (double) (atomicinteger.get() & 255) / 255.0D;

                    world.addParticle(Particles.ENTITY_EFFECT, d0, (double) ((float) blockposition.getY() + 0.5F), d1, d2, d3, d4);
                }

            });
        }
    }

    private boolean a(EntityLiving entityliving) {
        return entityliving.isAlive() && !entityliving.dead && this.getPosition().a((IPosition) entityliving.getPositionVector(), 48.0D) && entityliving.getEntityType().a(TagsEntity.RADIERS);
    }

    private void b(EntityLiving entityliving) {
        entityliving.addEffect(new MobEffect(MobEffects.GLOWING, 60));
    }
}
