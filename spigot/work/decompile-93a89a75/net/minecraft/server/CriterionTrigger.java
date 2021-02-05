package net.minecraft.server;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

public interface CriterionTrigger<T extends CriterionInstance> {

    MinecraftKey a();

    void a(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<T> criteriontrigger_a);

    void b(AdvancementDataPlayer advancementdataplayer, CriterionTrigger.a<T> criteriontrigger_a);

    void a(AdvancementDataPlayer advancementdataplayer);

    T a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext);

    public static class a<T extends CriterionInstance> {

        private final T a;
        private final Advancement b;
        private final String c;

        public a(T t0, Advancement advancement, String s) {
            this.a = t0;
            this.b = advancement;
            this.c = s;
        }

        public T a() {
            return this.a;
        }

        public void a(AdvancementDataPlayer advancementdataplayer) {
            advancementdataplayer.grantCriteria(this.b, this.c);
        }

        public boolean equals(Object object) {
            if (this == object) {
                return true;
            } else if (object != null && this.getClass() == object.getClass()) {
                CriterionTrigger.a<?> criteriontrigger_a = (CriterionTrigger.a) object;

                return !this.a.equals(criteriontrigger_a.a) ? false : (!this.b.equals(criteriontrigger_a.b) ? false : this.c.equals(criteriontrigger_a.c));
            } else {
                return false;
            }
        }

        public int hashCode() {
            int i = this.a.hashCode();

            i = 31 * i + this.b.hashCode();
            i = 31 * i + this.c.hashCode();
            return i;
        }
    }
}
