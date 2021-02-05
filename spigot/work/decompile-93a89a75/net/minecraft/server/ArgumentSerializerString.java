package net.minecraft.server;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType.StringType;

public class ArgumentSerializerString implements ArgumentSerializer<StringArgumentType> {

    public ArgumentSerializerString() {}

    public void a(StringArgumentType stringargumenttype, PacketDataSerializer packetdataserializer) {
        packetdataserializer.a((Enum) stringargumenttype.getType());
    }

    @Override
    public StringArgumentType b(PacketDataSerializer packetdataserializer) {
        StringType stringtype = (StringType) packetdataserializer.a(StringType.class);

        switch (stringtype) {
            case SINGLE_WORD:
                return StringArgumentType.word();
            case QUOTABLE_PHRASE:
                return StringArgumentType.string();
            case GREEDY_PHRASE:
            default:
                return StringArgumentType.greedyString();
        }
    }

    public void a(StringArgumentType stringargumenttype, JsonObject jsonobject) {
        switch (stringargumenttype.getType()) {
            case SINGLE_WORD:
                jsonobject.addProperty("type", "word");
                break;
            case QUOTABLE_PHRASE:
                jsonobject.addProperty("type", "phrase");
                break;
            case GREEDY_PHRASE:
            default:
                jsonobject.addProperty("type", "greedy");
        }

    }
}
