package net.minecraft.server;

public enum EnchantmentSlotType {

    ALL {
        @Override
        public boolean canEnchant(Item item) {
            EnchantmentSlotType[] aenchantmentslottype = EnchantmentSlotType.values();
            int i = aenchantmentslottype.length;

            for (int j = 0; j < i; ++j) {
                EnchantmentSlotType enchantmentslottype = aenchantmentslottype[j];

                if (enchantmentslottype != EnchantmentSlotType.ALL && enchantmentslottype.canEnchant(item)) {
                    return true;
                }
            }

            return false;
        }
    },
    ARMOR {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemArmor;
        }
    },
    ARMOR_FEET {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemArmor && ((ItemArmor) item).b() == EnumItemSlot.FEET;
        }
    },
    ARMOR_LEGS {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemArmor && ((ItemArmor) item).b() == EnumItemSlot.LEGS;
        }
    },
    ARMOR_CHEST {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemArmor && ((ItemArmor) item).b() == EnumItemSlot.CHEST;
        }
    },
    ARMOR_HEAD {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemArmor && ((ItemArmor) item).b() == EnumItemSlot.HEAD;
        }
    },
    WEAPON {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemSword;
        }
    },
    DIGGER {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemTool;
        }
    },
    FISHING_ROD {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemFishingRod;
        }
    },
    TRIDENT {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemTrident;
        }
    },
    BREAKABLE {
        @Override
        public boolean canEnchant(Item item) {
            return item.usesDurability();
        }
    },
    BOW {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemBow;
        }
    },
    WEARABLE {
        @Override
        public boolean canEnchant(Item item) {
            Block block = Block.asBlock(item);

            return item instanceof ItemArmor || item instanceof ItemElytra || block instanceof BlockSkullAbstract || block instanceof BlockPumpkin;
        }
    },
    CROSSBOW {
        @Override
        public boolean canEnchant(Item item) {
            return item instanceof ItemCrossbow;
        }
    };

    private EnchantmentSlotType() {}

    public abstract boolean canEnchant(Item item);
}
