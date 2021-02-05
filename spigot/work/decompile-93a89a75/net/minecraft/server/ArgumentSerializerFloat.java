package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.FloatArgumentType;

public class ArgumentSerializerFloat implements ArgumentSerializer<FloatArgumentType> {

    public ArgumentSerializerFloat() {}

    public void a(FloatArgumentType floatargumenttype, PacketDataSerializer packetdataserializer) {
        boolean flag = floatargumenttype.getMinimum() != -3.4028235E38F;
        boolean flag1 = floatargumenttype.getMaximum() != Float.MAX_VALUE;

        packetdataserializer.writeByte(ArgumentSerializers.a(flag, flag1));
        if (flag) {
            packetdataserializer.writeFloat(floatargumenttype.getMinimum());
        }

        if (flag1) {
            packetdataserializer.writeFloat(floatargumenttype.getMaximum());
        }

    }

    @Override
    public FloatArgumentType b(PacketDataSerializer packetdataserializer) {
        byte b0 = packetdataserializer.readByte();
        float f = ArgumentSerializers.a(b0) ? packetdataserializer.readFloat() : -3.4028235E38F;
        float f1 = ArgumentSerializers.b(b0) ? packetdataserializer.readFloat() : Float.MAX_VALUE;

        return FloatArgumentType.floatArg(f, f1);
    }

    public void a(FloatArgumentType floatargumenttype, JsonObject jsonobject) {
        if (floatargumenttype.getMinimum() != -3.4028235E38F) {
            jsonobject.addProperty("min", floatargumenttype.getMinimum());
        }

        if (floatargumenttype.getMaximum() != Float.MAX_VALUE) {
            jsonobject.addProperty("max", floatargumenttype.getMaximum());
        }

    }
}
