package net.minecraft.server;

public interface ReputationEvent {

    ReputationEvent a = a("zombie_villager_cured");
    ReputationEvent b = a("golem_killed");
    ReputationEvent c = a("villager_hurt");
    ReputationEvent d = a("villager_killed");
    ReputationEvent e = a("trade");

    static ReputationEvent a(final String s) {
        return new ReputationEvent() {
            public String toString() {
                return s;
            }
        };
    }
}
