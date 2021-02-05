package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import javax.annotation.Nullable;

public class AdvancementDisplay {

    private final IChatBaseComponent a;
    private final IChatBaseComponent b;
    private final ItemStack c;
    private final MinecraftKey d;
    private final AdvancementFrameType e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private float i;
    private float j;

    public AdvancementDisplay(ItemStack itemstack, IChatBaseComponent ichatbasecomponent, IChatBaseComponent ichatbasecomponent1, @Nullable MinecraftKey minecraftkey, AdvancementFrameType advancementframetype, boolean flag, boolean flag1, boolean flag2) {
        this.a = ichatbasecomponent;
        this.b = ichatbasecomponent1;
        this.c = itemstack;
        this.d = minecraftkey;
        this.e = advancementframetype;
        this.f = flag;
        this.g = flag1;
        this.h = flag2;
    }

    public void a(float f, float f1) {
        this.i = f;
        this.j = f1;
    }

    public IChatBaseComponent a() {
        return this.a;
    }

    public IChatBaseComponent b() {
        return this.b;
    }

    public AdvancementFrameType e() {
        return this.e;
    }

    public boolean i() {
        return this.g;
    }

    public boolean j() {
        return this.h;
    }

    public static AdvancementDisplay a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
        IChatBaseComponent ichatbasecomponent = (IChatBaseComponent) ChatDeserializer.a(jsonobject, "title", jsondeserializationcontext, IChatBaseComponent.class);
        IChatBaseComponent ichatbasecomponent1 = (IChatBaseComponent) ChatDeserializer.a(jsonobject, "description", jsondeserializationcontext, IChatBaseComponent.class);

        if (ichatbasecomponent != null && ichatbasecomponent1 != null) {
            ItemStack itemstack = a(ChatDeserializer.t(jsonobject, "icon"));
            MinecraftKey minecraftkey = jsonobject.has("background") ? new MinecraftKey(ChatDeserializer.h(jsonobject, "background")) : null;
            AdvancementFrameType advancementframetype = jsonobject.has("frame") ? AdvancementFrameType.a(ChatDeserializer.h(jsonobject, "frame")) : AdvancementFrameType.TASK;
            boolean flag = ChatDeserializer.a(jsonobject, "show_toast", true);
            boolean flag1 = ChatDeserializer.a(jsonobject, "announce_to_chat", true);
            boolean flag2 = ChatDeserializer.a(jsonobject, "hidden", false);

            return new AdvancementDisplay(itemstack, ichatbasecomponent, ichatbasecomponent1, minecraftkey, advancementframetype, flag, flag1, flag2);
        } else {
            throw new JsonSyntaxException("Both title and description must be set");
        }
    }

    private static ItemStack a(JsonObject jsonobject) {
        if (!jsonobject.has("item")) {
            throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
        } else {
            Item item = ChatDeserializer.i(jsonobject, "item");

            if (jsonobject.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            } else {
                ItemStack itemstack = new ItemStack(item);

                if (jsonobject.has("nbt")) {
                    try {
                        NBTTagCompound nbttagcompound = MojangsonParser.parse(ChatDeserializer.a(jsonobject.get("nbt"), "nbt"));

                        itemstack.setTag(nbttagcompound);
                    } catch (CommandSyntaxException commandsyntaxexception) {
                        throw new JsonSyntaxException("Invalid nbt tag: " + commandsyntaxexception.getMessage());
                    }
                }

                return itemstack;
            }
        }
    }

    public void a(PacketDataSerializer packetdataserializer) {
        packetdataserializer.a(this.a);
        packetdataserializer.a(this.b);
        packetdataserializer.a(this.c);
        packetdataserializer.a((Enum) this.e);
        int i = 0;

        if (this.d != null) {
            i |= 1;
        }

        if (this.f) {
            i |= 2;
        }

        if (this.h) {
            i |= 4;
        }

        packetdataserializer.writeInt(i);
        if (this.d != null) {
            packetdataserializer.a(this.d);
        }

        packetdataserializer.writeFloat(this.i);
        packetdataserializer.writeFloat(this.j);
    }

    public static AdvancementDisplay b(PacketDataSerializer packetdataserializer) {
        IChatBaseComponent ichatbasecomponent = packetdataserializer.h();
        IChatBaseComponent ichatbasecomponent1 = packetdataserializer.h();
        ItemStack itemstack = packetdataserializer.m();
        AdvancementFrameType advancementframetype = (AdvancementFrameType) packetdataserializer.a(AdvancementFrameType.class);
        int i = packetdataserializer.readInt();
        MinecraftKey minecraftkey = (i & 1) != 0 ? packetdataserializer.o() : null;
        boolean flag = (i & 2) != 0;
        boolean flag1 = (i & 4) != 0;
        AdvancementDisplay advancementdisplay = new AdvancementDisplay(itemstack, ichatbasecomponent, ichatbasecomponent1, minecraftkey, advancementframetype, flag, false, flag1);

        advancementdisplay.a(packetdataserializer.readFloat(), packetdataserializer.readFloat());
        return advancementdisplay;
    }

    public JsonElement k() {
        JsonObject jsonobject = new JsonObject();

        jsonobject.add("icon", this.l());
        jsonobject.add("title", IChatBaseComponent.ChatSerializer.b(this.a));
        jsonobject.add("description", IChatBaseComponent.ChatSerializer.b(this.b));
        jsonobject.addProperty("frame", this.e.a());
        jsonobject.addProperty("show_toast", this.f);
        jsonobject.addProperty("announce_to_chat", this.g);
        jsonobject.addProperty("hidden", this.h);
        if (this.d != null) {
            jsonobject.addProperty("background", this.d.toString());
        }

        return jsonobject;
    }

    private JsonObject l() {
        JsonObject jsonobject = new JsonObject();

        jsonobject.addProperty("item", IRegistry.ITEM.getKey(this.c.getItem()).toString());
        if (this.c.hasTag()) {
            jsonobject.addProperty("nbt", this.c.getTag().toString());
        }

        return jsonobject;
    }
}
