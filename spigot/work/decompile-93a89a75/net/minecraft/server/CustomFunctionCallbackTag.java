package net.minecraft.server;

import java.util.Iterator;

public class CustomFunctionCallbackTag implements CustomFunctionCallbackTimer<MinecraftServer> {

    private final MinecraftKey a;

    public CustomFunctionCallbackTag(MinecraftKey minecraftkey) {
        this.a = minecraftkey;
    }

    public void a(MinecraftServer minecraftserver, CustomFunctionCallbackTimerQueue<MinecraftServer> customfunctioncallbacktimerqueue, long i) {
        CustomFunctionData customfunctiondata = minecraftserver.getFunctionData();
        Tag<CustomFunction> tag = customfunctiondata.h().b(this.a);
        Iterator iterator = tag.a().iterator();

        while (iterator.hasNext()) {
            CustomFunction customfunction = (CustomFunction) iterator.next();

            customfunctiondata.a(customfunction, customfunctiondata.f());
        }

    }

    public static class a extends CustomFunctionCallbackTimer.a<MinecraftServer, CustomFunctionCallbackTag> {

        public a() {
            super(new MinecraftKey("function_tag"), CustomFunctionCallbackTag.class);
        }

        public void a(NBTTagCompound nbttagcompound, CustomFunctionCallbackTag customfunctioncallbacktag) {
            nbttagcompound.setString("Name", customfunctioncallbacktag.a.toString());
        }

        @Override
        public CustomFunctionCallbackTag b(NBTTagCompound nbttagcompound) {
            MinecraftKey minecraftkey = new MinecraftKey(nbttagcompound.getString("Name"));

            return new CustomFunctionCallbackTag(minecraftkey);
        }
    }
}
