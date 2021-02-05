package net.minecraft.server;

public interface ICommandListener {

    ICommandListener DUMMY = new ICommandListener() {
        @Override
        public void sendMessage(IChatBaseComponent ichatbasecomponent) {}

        @Override
        public boolean shouldSendSuccess() {
            return false;
        }

        @Override
        public boolean shouldSendFailure() {
            return false;
        }

        @Override
        public boolean shouldBroadcastCommands() {
            return false;
        }
    };

    void sendMessage(IChatBaseComponent ichatbasecomponent);

    boolean shouldSendSuccess();

    boolean shouldSendFailure();

    boolean shouldBroadcastCommands();
}
