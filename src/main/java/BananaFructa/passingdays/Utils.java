package BananaFructa.passingdays;

import com.google.common.collect.ObjectArrays;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Utils {
    public static <T> T readDeclaredField(Class<?> targetType, Object target, String name) {
        try {
            Field f = targetType.getDeclaredField(name);
            f.setAccessible(true);
            return (T) f.get(target);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
        }
    }

    public static void writeDeclaredField(Class<?> targetType, Object target, String name, Object value,boolean final_) {
        try {
            Field f = targetType.getDeclaredField(name);
            f.setAccessible(true);
            if (final_) {
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
            }
            f.set(target,value);

        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public static void registerBlockReplace(Block old,Block block, Class<? extends ItemBlock> itemClass, Object... itemCtorArgs) {
        String name = block.getUnlocalizedName().substring(5);
        if (block.getRegistryName() == null) {
            block.setRegistryName(name);
        }


        GalacticraftCore.blocksList.remove(old);
        GCCoreUtil.registerGalacticraftBlock(name, block);
        if (itemClass != null) {
            ItemBlock item = null;
            Class<?>[] ctorArgClasses = new Class[itemCtorArgs.length + 1];
            ctorArgClasses[0] = Block.class;

            for(int idx = 1; idx < ctorArgClasses.length; ++idx) {
                ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
            }

            try {
                Constructor<? extends ItemBlock> constructor = itemClass.getConstructor(ctorArgClasses);
                item = (ItemBlock)constructor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
            } catch (Exception var7) {
                var7.printStackTrace();
            }

            if (item != null) {
                ItemStack r = null;
                for (ItemStack stack : GalacticraftCore.itemList) {
                    if (stack.getItem() == item) r = stack;
                }
                GalacticraftCore.itemList.remove(r);
                GCCoreUtil.registerGalacticraftItem(name, item);
                if (item.getRegistryName() == null) {
                    item.setRegistryName(name);
                }
            }
        }

    }
}
