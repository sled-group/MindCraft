package net.minecraft.server;

@FunctionalInterface
public interface CustomFunctionCallbackTimer<T> {

    void a(T t0, CustomFunctionCallbackTimerQueue<T> customfunctioncallbacktimerqueue, long i);

    public abstract static class a<T, C extends CustomFunctionCallbackTimer<T>> {

        private final MinecraftKey a;
        private final Class<?> b;

        public a(MinecraftKey minecraftkey, Class<?> oclass) {
            this.a = minecraftkey;
            this.b = oclass;
        }

        public MinecraftKey a() {
            return this.a;
        }

        public Class<?> b() {
            return this.b;
        }

        public abstract void a(NBTTagCompound nbttagcompound, C c0);

        public abstract C b(NBTTagCompound nbttagcompound);
    }
}
