package net.minecraft.server;

public class DamageSourceNetherBed extends DamageSource {

    protected DamageSourceNetherBed() {
        super("netherBed");
        this.r();
        this.e();
    }

    @Override
    public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
        IChatBaseComponent ichatbasecomponent = ChatComponentUtils.a((IChatBaseComponent) (new ChatMessage("death.attack.netherBed.link", new Object[0]))).a((chatmodifier) -> {
            chatmodifier.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.OPEN_URL, "https://bugs.mojang.com/browse/MCPE-28723")).setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_TEXT, new ChatComponentText("MCPE-28723")));
        });

        return new ChatMessage("death.attack.netherBed.message", new Object[]{entityliving.getScoreboardDisplayName(), ichatbasecomponent});
    }
}
