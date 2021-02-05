package net.minecraft.server;

public class CustomFunctionCallback implements CustomFunctionCallbackTimer<MinecraftServer> {

    private final MinecraftKey a;

    public CustomFunctionCallback(MinecraftKey minecraftkey) {
        this.a = minecraftkey;
    }

    public void a(MinecraftServer minecraftserver, CustomFunctionCallbackTimerQueue<MinecraftServer> customfunctioncallbacktimerqueue, long i) {
        CustomFunctionData customfunctiondata = minecraftserver.getFunctionData();

        customfunctiondata.a(this.a).ifPresent((customfunction) -> {
            customfunctiondata.a(customfunction, customfunctiondata.f());
        });
    }

    public static class a extends CustomFunctionCallbackTimer.a<MinecraftServer, CustomFunctionCallback> {

        public a() {
            super(new MinecraftKey("function"), CustomFunctionCallback.class);
        }

        public void a(NBTTagCompound nbttagcompound, CustomFunctionCallback customfunctioncallback) {
            nbttagcompound.setString("Name", customfunctioncallback.a.toString());
        }

        @Override
        public CustomFunctionCallback b(NBTTagCompound nbttagcompound) {
            MinecraftKey minecraftkey = new MinecraftKey(nbttagcompound.getString("Name"));

            return new CustomFunctionCallback(minecraftkey);
        }
    }
}
