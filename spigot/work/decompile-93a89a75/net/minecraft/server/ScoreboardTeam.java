package net.minecraft.server;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

public class ScoreboardTeam extends ScoreboardTeamBase {

    private final Scoreboard a;
    private final String b;
    private final Set<String> c = Sets.newHashSet();
    private IChatBaseComponent d;
    private IChatBaseComponent e = new ChatComponentText("");
    private IChatBaseComponent f = new ChatComponentText("");
    private boolean g = true;
    private boolean h = true;
    private ScoreboardTeamBase.EnumNameTagVisibility i;
    private ScoreboardTeamBase.EnumNameTagVisibility j;
    private EnumChatFormat k;
    private ScoreboardTeamBase.EnumTeamPush l;

    public ScoreboardTeam(Scoreboard scoreboard, String s) {
        this.i = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS;
        this.j = ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS;
        this.k = EnumChatFormat.RESET;
        this.l = ScoreboardTeamBase.EnumTeamPush.ALWAYS;
        this.a = scoreboard;
        this.b = s;
        this.d = new ChatComponentText(s);
    }

    @Override
    public String getName() {
        return this.b;
    }

    public IChatBaseComponent getDisplayName() {
        return this.d;
    }

    public IChatBaseComponent d() {
        IChatBaseComponent ichatbasecomponent = ChatComponentUtils.a(this.d.h().a((chatmodifier) -> {
            chatmodifier.setInsertion(this.b).setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_TEXT, new ChatComponentText(this.b)));
        }));
        EnumChatFormat enumchatformat = this.getColor();

        if (enumchatformat != EnumChatFormat.RESET) {
            ichatbasecomponent.a(enumchatformat);
        }

        return ichatbasecomponent;
    }

    public void setDisplayName(IChatBaseComponent ichatbasecomponent) {
        if (ichatbasecomponent == null) {
            throw new IllegalArgumentException("Name cannot be null");
        } else {
            this.d = ichatbasecomponent;
            this.a.handleTeamChanged(this);
        }
    }

    public void setPrefix(@Nullable IChatBaseComponent ichatbasecomponent) {
        this.e = (IChatBaseComponent) (ichatbasecomponent == null ? new ChatComponentText("") : ichatbasecomponent.h());
        this.a.handleTeamChanged(this);
    }

    public IChatBaseComponent getPrefix() {
        return this.e;
    }

    public void setSuffix(@Nullable IChatBaseComponent ichatbasecomponent) {
        this.f = (IChatBaseComponent) (ichatbasecomponent == null ? new ChatComponentText("") : ichatbasecomponent.h());
        this.a.handleTeamChanged(this);
    }

    public IChatBaseComponent getSuffix() {
        return this.f;
    }

    @Override
    public Collection<String> getPlayerNameSet() {
        return this.c;
    }

    @Override
    public IChatBaseComponent getFormattedName(IChatBaseComponent ichatbasecomponent) {
        IChatBaseComponent ichatbasecomponent1 = (new ChatComponentText("")).addSibling(this.e).addSibling(ichatbasecomponent).addSibling(this.f);
        EnumChatFormat enumchatformat = this.getColor();

        if (enumchatformat != EnumChatFormat.RESET) {
            ichatbasecomponent1.a(enumchatformat);
        }

        return ichatbasecomponent1;
    }

    public static IChatBaseComponent a(@Nullable ScoreboardTeamBase scoreboardteambase, IChatBaseComponent ichatbasecomponent) {
        return scoreboardteambase == null ? ichatbasecomponent.h() : scoreboardteambase.getFormattedName(ichatbasecomponent);
    }

    @Override
    public boolean allowFriendlyFire() {
        return this.g;
    }

    public void setAllowFriendlyFire(boolean flag) {
        this.g = flag;
        this.a.handleTeamChanged(this);
    }

    public boolean canSeeFriendlyInvisibles() {
        return this.h;
    }

    public void setCanSeeFriendlyInvisibles(boolean flag) {
        this.h = flag;
        this.a.handleTeamChanged(this);
    }

    public ScoreboardTeamBase.EnumNameTagVisibility getNameTagVisibility() {
        return this.i;
    }

    @Override
    public ScoreboardTeamBase.EnumNameTagVisibility getDeathMessageVisibility() {
        return this.j;
    }

    public void setNameTagVisibility(ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility) {
        this.i = scoreboardteambase_enumnametagvisibility;
        this.a.handleTeamChanged(this);
    }

    public void setDeathMessageVisibility(ScoreboardTeamBase.EnumNameTagVisibility scoreboardteambase_enumnametagvisibility) {
        this.j = scoreboardteambase_enumnametagvisibility;
        this.a.handleTeamChanged(this);
    }

    @Override
    public ScoreboardTeamBase.EnumTeamPush getCollisionRule() {
        return this.l;
    }

    public void setCollisionRule(ScoreboardTeamBase.EnumTeamPush scoreboardteambase_enumteampush) {
        this.l = scoreboardteambase_enumteampush;
        this.a.handleTeamChanged(this);
    }

    public int packOptionData() {
        int i = 0;

        if (this.allowFriendlyFire()) {
            i |= 1;
        }

        if (this.canSeeFriendlyInvisibles()) {
            i |= 2;
        }

        return i;
    }

    public void setColor(EnumChatFormat enumchatformat) {
        this.k = enumchatformat;
        this.a.handleTeamChanged(this);
    }

    @Override
    public EnumChatFormat getColor() {
        return this.k;
    }
}
