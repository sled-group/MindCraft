package net.minecraft.server;

public interface IWorldBorderListener {

    void a(WorldBorder worldborder, double d0);

    void a(WorldBorder worldborder, double d0, double d1, long i);

    void a(WorldBorder worldborder, double d0, double d1);

    void a(WorldBorder worldborder, int i);

    void b(WorldBorder worldborder, int i);

    void b(WorldBorder worldborder, double d0);

    void c(WorldBorder worldborder, double d0);

    public static class a implements IWorldBorderListener {

        private final WorldBorder a;

        public a(WorldBorder worldborder) {
            this.a = worldborder;
        }

        @Override
        public void a(WorldBorder worldborder, double d0) {
            this.a.setSize(d0);
        }

        @Override
        public void a(WorldBorder worldborder, double d0, double d1, long i) {
            this.a.transitionSizeBetween(d0, d1, i);
        }

        @Override
        public void a(WorldBorder worldborder, double d0, double d1) {
            this.a.setCenter(d0, d1);
        }

        @Override
        public void a(WorldBorder worldborder, int i) {
            this.a.setWarningTime(i);
        }

        @Override
        public void b(WorldBorder worldborder, int i) {
            this.a.setWarningDistance(i);
        }

        @Override
        public void b(WorldBorder worldborder, double d0) {
            this.a.setDamageAmount(d0);
        }

        @Override
        public void c(WorldBorder worldborder, double d0) {
            this.a.setDamageBuffer(d0);
        }
    }
}
