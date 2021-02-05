package net.minecraft.server;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableMap.Builder;
import java.util.Map;
import java.util.Optional;

public class IScoreboardCriteria {

    public static final Map<String, IScoreboardCriteria> criteria = Maps.newHashMap();
    public static final IScoreboardCriteria DUMMY = new IScoreboardCriteria("dummy");
    public static final IScoreboardCriteria TRIGGER = new IScoreboardCriteria("trigger");
    public static final IScoreboardCriteria DEATH_COUNT = new IScoreboardCriteria("deathCount");
    public static final IScoreboardCriteria PLAYER_KILL_COUNT = new IScoreboardCriteria("playerKillCount");
    public static final IScoreboardCriteria TOTAL_KILL_COUNT = new IScoreboardCriteria("totalKillCount");
    public static final IScoreboardCriteria HEALTH = new IScoreboardCriteria("health", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.HEARTS);
    public static final IScoreboardCriteria FOOD = new IScoreboardCriteria("food", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    public static final IScoreboardCriteria AIR = new IScoreboardCriteria("air", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    public static final IScoreboardCriteria ARMOR = new IScoreboardCriteria("armor", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    public static final IScoreboardCriteria XP = new IScoreboardCriteria("xp", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    public static final IScoreboardCriteria LEVEL = new IScoreboardCriteria("level", true, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    public static final IScoreboardCriteria[] m = new IScoreboardCriteria[]{new IScoreboardCriteria("teamkill." + EnumChatFormat.BLACK.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_BLUE.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_GREEN.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_AQUA.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_RED.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_PURPLE.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GOLD.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GRAY.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.DARK_GRAY.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.BLUE.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.GREEN.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.AQUA.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.RED.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.LIGHT_PURPLE.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.YELLOW.g()), new IScoreboardCriteria("teamkill." + EnumChatFormat.WHITE.g())};
    public static final IScoreboardCriteria[] n = new IScoreboardCriteria[]{new IScoreboardCriteria("killedByTeam." + EnumChatFormat.BLACK.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_BLUE.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_GREEN.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_AQUA.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_RED.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_PURPLE.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GOLD.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GRAY.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.DARK_GRAY.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.BLUE.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.GREEN.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.AQUA.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.RED.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.LIGHT_PURPLE.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.YELLOW.g()), new IScoreboardCriteria("killedByTeam." + EnumChatFormat.WHITE.g())};
    private final String o;
    private final boolean p;
    private final IScoreboardCriteria.EnumScoreboardHealthDisplay q;

    public IScoreboardCriteria(String s) {
        this(s, false, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
    }

    protected IScoreboardCriteria(String s, boolean flag, IScoreboardCriteria.EnumScoreboardHealthDisplay iscoreboardcriteria_enumscoreboardhealthdisplay) {
        this.o = s;
        this.p = flag;
        this.q = iscoreboardcriteria_enumscoreboardhealthdisplay;
        IScoreboardCriteria.criteria.put(s, this);
    }

    public static Optional<IScoreboardCriteria> a(String s) {
        if (IScoreboardCriteria.criteria.containsKey(s)) {
            return Optional.of(IScoreboardCriteria.criteria.get(s));
        } else {
            int i = s.indexOf(58);

            return i < 0 ? Optional.empty() : IRegistry.STATS.getOptional(MinecraftKey.a(s.substring(0, i), '.')).flatMap((statisticwrapper) -> {
                return a(statisticwrapper, MinecraftKey.a(s.substring(i + 1), '.'));
            });
        }
    }

    private static <T> Optional<IScoreboardCriteria> a(StatisticWrapper<T> statisticwrapper, MinecraftKey minecraftkey) {
        Optional optional = statisticwrapper.getRegistry().getOptional(minecraftkey);

        statisticwrapper.getClass();
        return optional.map(statisticwrapper::b);
    }

    public String getName() {
        return this.o;
    }

    public boolean isReadOnly() {
        return this.p;
    }

    public IScoreboardCriteria.EnumScoreboardHealthDisplay e() {
        return this.q;
    }

    public static enum EnumScoreboardHealthDisplay {

        INTEGER("integer"), HEARTS("hearts");

        private final String c;
        private static final Map<String, IScoreboardCriteria.EnumScoreboardHealthDisplay> d;

        private EnumScoreboardHealthDisplay(String s) {
            this.c = s;
        }

        public String a() {
            return this.c;
        }

        public static IScoreboardCriteria.EnumScoreboardHealthDisplay a(String s) {
            return (IScoreboardCriteria.EnumScoreboardHealthDisplay) IScoreboardCriteria.EnumScoreboardHealthDisplay.d.getOrDefault(s, IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        }

        static {
            Builder<String, IScoreboardCriteria.EnumScoreboardHealthDisplay> builder = ImmutableMap.builder();
            IScoreboardCriteria.EnumScoreboardHealthDisplay[] aiscoreboardcriteria_enumscoreboardhealthdisplay = values();
            int i = aiscoreboardcriteria_enumscoreboardhealthdisplay.length;

            for (int j = 0; j < i; ++j) {
                IScoreboardCriteria.EnumScoreboardHealthDisplay iscoreboardcriteria_enumscoreboardhealthdisplay = aiscoreboardcriteria_enumscoreboardhealthdisplay[j];

                builder.put(iscoreboardcriteria_enumscoreboardhealthdisplay.c, iscoreboardcriteria_enumscoreboardhealthdisplay);
            }

            d = builder.build();
        }
    }
}
