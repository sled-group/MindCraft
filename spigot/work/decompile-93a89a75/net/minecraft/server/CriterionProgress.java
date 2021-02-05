package net.minecraft.server;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CriterionProgress {

    private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private Date b;

    public CriterionProgress() {}

    public boolean a() {
        return this.b != null;
    }

    public void b() {
        this.b = new Date();
    }

    public void c() {
        this.b = null;
    }

    public Date getDate() {
        return this.b;
    }

    public String toString() {
        return "CriterionProgress{obtained=" + (this.b == null ? "false" : this.b) + '}';
    }

    public void a(PacketDataSerializer packetdataserializer) {
        packetdataserializer.writeBoolean(this.b != null);
        if (this.b != null) {
            packetdataserializer.a(this.b);
        }

    }

    public JsonElement e() {
        return (JsonElement) (this.b != null ? new JsonPrimitive(CriterionProgress.a.format(this.b)) : JsonNull.INSTANCE);
    }

    public static CriterionProgress b(PacketDataSerializer packetdataserializer) {
        CriterionProgress criterionprogress = new CriterionProgress();

        if (packetdataserializer.readBoolean()) {
            criterionprogress.b = packetdataserializer.p();
        }

        return criterionprogress;
    }

    public static CriterionProgress a(String s) {
        CriterionProgress criterionprogress = new CriterionProgress();

        try {
            criterionprogress.b = CriterionProgress.a.parse(s);
            return criterionprogress;
        } catch (ParseException parseexception) {
            throw new JsonSyntaxException("Invalid datetime: " + s, parseexception);
        }
    }
}
