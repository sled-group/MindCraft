package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import javax.annotation.Nullable;

public class DefinedStructureProcessorJigsawReplacement extends DefinedStructureProcessor {

    public static final DefinedStructureProcessorJigsawReplacement a = new DefinedStructureProcessorJigsawReplacement();

    private DefinedStructureProcessorJigsawReplacement() {}

    @Nullable
    @Override
    public DefinedStructure.BlockInfo a(IWorldReader iworldreader, BlockPosition blockposition, DefinedStructure.BlockInfo definedstructure_blockinfo, DefinedStructure.BlockInfo definedstructure_blockinfo1, DefinedStructureInfo definedstructureinfo) {
        Block block = definedstructure_blockinfo1.b.getBlock();

        if (block != Blocks.JIGSAW) {
            return definedstructure_blockinfo1;
        } else {
            String s = definedstructure_blockinfo1.c.getString("final_state");
            ArgumentBlock argumentblock = new ArgumentBlock(new StringReader(s), false);

            try {
                argumentblock.a(true);
            } catch (CommandSyntaxException commandsyntaxexception) {
                throw new RuntimeException(commandsyntaxexception);
            }

            return argumentblock.getBlockData().getBlock() == Blocks.STRUCTURE_VOID ? null : new DefinedStructure.BlockInfo(definedstructure_blockinfo1.a, argumentblock.getBlockData(), (NBTTagCompound) null);
        }
    }

    @Override
    protected DefinedStructureStructureProcessorType a() {
        return DefinedStructureStructureProcessorType.e;
    }

    @Override
    protected <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
        return new Dynamic(dynamicops, dynamicops.emptyMap());
    }
}
