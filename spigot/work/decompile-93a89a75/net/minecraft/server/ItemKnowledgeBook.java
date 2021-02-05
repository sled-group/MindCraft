package net.minecraft.server;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemKnowledgeBook extends Item {

    private static final Logger LOGGER = LogManager.getLogger();

    public ItemKnowledgeBook(Item.Info item_info) {
        super(item_info);
    }

    @Override
    public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
        ItemStack itemstack = entityhuman.b(enumhand);
        NBTTagCompound nbttagcompound = itemstack.getTag();

        if (!entityhuman.abilities.canInstantlyBuild) {
            entityhuman.a(enumhand, ItemStack.a);
        }

        if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Recipes", 9)) {
            if (!world.isClientSide) {
                NBTTagList nbttaglist = nbttagcompound.getList("Recipes", 8);
                List<IRecipe<?>> list = Lists.newArrayList();
                CraftingManager craftingmanager = world.getMinecraftServer().getCraftingManager();

                for (int i = 0; i < nbttaglist.size(); ++i) {
                    String s = nbttaglist.getString(i);
                    Optional<? extends IRecipe<?>> optional = craftingmanager.a(new MinecraftKey(s));

                    if (!optional.isPresent()) {
                        ItemKnowledgeBook.LOGGER.error("Invalid recipe: {}", s);
                        return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
                    }

                    list.add(optional.get());
                }

                entityhuman.discoverRecipes(list);
                entityhuman.b(StatisticList.ITEM_USED.b(this));
            }

            return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
        } else {
            ItemKnowledgeBook.LOGGER.error("Tag not valid: {}", nbttagcompound);
            return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
        }
    }
}
