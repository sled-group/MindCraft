package net.minecraft.server;

public abstract class IAsyncTaskHandlerReentrant<R extends Runnable> extends IAsyncTaskHandler<R> {

    private int depth;

    public IAsyncTaskHandlerReentrant(String s) {
        super(s);
    }

    @Override
    protected boolean isNotMainThread() {
        return this.isEntered() || super.isNotMainThread();
    }

    protected boolean isEntered() {
        return this.depth != 0;
    }

    @Override
    protected void executeTask(R r0) {
        ++this.depth;

        try {
            super.executeTask(r0);
        } finally {
            --this.depth;
        }

    }
}
