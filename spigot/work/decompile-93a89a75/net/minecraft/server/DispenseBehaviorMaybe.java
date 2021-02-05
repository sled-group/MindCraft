package net.minecraft.server;

public abstract class DispenseBehaviorMaybe extends DispenseBehaviorItem {

    protected boolean dispensed = true;

    public DispenseBehaviorMaybe() {}

    @Override
    protected void a(ISourceBlock isourceblock) {
        isourceblock.getWorld().triggerEffect(this.dispensed ? 1000 : 1001, isourceblock.getBlockPosition(), 0);
    }
}
