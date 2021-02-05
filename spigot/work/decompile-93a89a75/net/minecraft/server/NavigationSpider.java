package net.minecraft.server;

public class NavigationSpider extends Navigation {

    private BlockPosition p;

    public NavigationSpider(EntityInsentient entityinsentient, World world) {
        super(entityinsentient, world);
    }

    @Override
    public PathEntity a(BlockPosition blockposition, int i) {
        this.p = blockposition;
        return super.a(blockposition, i);
    }

    @Override
    public PathEntity a(Entity entity, int i) {
        this.p = new BlockPosition(entity);
        return super.a(entity, i);
    }

    @Override
    public boolean a(Entity entity, double d0) {
        PathEntity pathentity = this.a(entity, 0);

        if (pathentity != null) {
            return this.a(pathentity, d0);
        } else {
            this.p = new BlockPosition(entity);
            this.d = d0;
            return true;
        }
    }

    @Override
    public void c() {
        if (!this.n()) {
            super.c();
        } else {
            if (this.p != null) {
                if (!this.p.a((IPosition) this.a.getPositionVector(), (double) this.a.getWidth()) && (this.a.locY <= (double) this.p.getY() || !(new BlockPosition((double) this.p.getX(), this.a.locY, (double) this.p.getZ())).a((IPosition) this.a.getPositionVector(), (double) this.a.getWidth()))) {
                    this.a.getControllerMove().a((double) this.p.getX(), (double) this.p.getY(), (double) this.p.getZ(), this.d);
                } else {
                    this.p = null;
                }
            }

        }
    }
}
