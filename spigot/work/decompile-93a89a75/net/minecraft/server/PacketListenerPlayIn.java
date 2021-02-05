package net.minecraft.server;

public interface PacketListenerPlayIn extends PacketListener {

    void a(PacketPlayInArmAnimation packetplayinarmanimation);

    void a(PacketPlayInChat packetplayinchat);

    void a(PacketPlayInClientCommand packetplayinclientcommand);

    void a(PacketPlayInSettings packetplayinsettings);

    void a(PacketPlayInTransaction packetplayintransaction);

    void a(PacketPlayInEnchantItem packetplayinenchantitem);

    void a(PacketPlayInWindowClick packetplayinwindowclick);

    void a(PacketPlayInAutoRecipe packetplayinautorecipe);

    void a(PacketPlayInCloseWindow packetplayinclosewindow);

    void a(PacketPlayInCustomPayload packetplayincustompayload);

    void a(PacketPlayInUseEntity packetplayinuseentity);

    void a(PacketPlayInKeepAlive packetplayinkeepalive);

    void a(PacketPlayInFlying packetplayinflying);

    void a(PacketPlayInAbilities packetplayinabilities);

    void a(PacketPlayInBlockDig packetplayinblockdig);

    void a(PacketPlayInEntityAction packetplayinentityaction);

    void a(PacketPlayInSteerVehicle packetplayinsteervehicle);

    void a(PacketPlayInHeldItemSlot packetplayinhelditemslot);

    void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot);

    void a(PacketPlayInUpdateSign packetplayinupdatesign);

    void a(PacketPlayInUseItem packetplayinuseitem);

    void a(PacketPlayInBlockPlace packetplayinblockplace);

    void a(PacketPlayInSpectate packetplayinspectate);

    void a(PacketPlayInResourcePackStatus packetplayinresourcepackstatus);

    void a(PacketPlayInBoatMove packetplayinboatmove);

    void a(PacketPlayInVehicleMove packetplayinvehiclemove);

    void a(PacketPlayInTeleportAccept packetplayinteleportaccept);

    void a(PacketPlayInRecipeDisplayed packetplayinrecipedisplayed);

    void a(PacketPlayInAdvancements packetplayinadvancements);

    void a(PacketPlayInTabComplete packetplayintabcomplete);

    void a(PacketPlayInSetCommandBlock packetplayinsetcommandblock);

    void a(PacketPlayInSetCommandMinecart packetplayinsetcommandminecart);

    void a(PacketPlayInPickItem packetplayinpickitem);

    void a(PacketPlayInItemName packetplayinitemname);

    void a(PacketPlayInBeacon packetplayinbeacon);

    void a(PacketPlayInStruct packetplayinstruct);

    void a(PacketPlayInTrSel packetplayintrsel);

    void a(PacketPlayInBEdit packetplayinbedit);

    void a(PacketPlayInEntityNBTQuery packetplayinentitynbtquery);

    void a(PacketPlayInTileNBTQuery packetplayintilenbtquery);

    void a(PacketPlayInSetJigsaw packetplayinsetjigsaw);

    void a(PacketPlayInDifficultyChange packetplayindifficultychange);

    void a(PacketPlayInDifficultyLock packetplayindifficultylock);
}
