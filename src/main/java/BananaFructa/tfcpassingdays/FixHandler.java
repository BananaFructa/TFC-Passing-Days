package BananaFructa.tfcpassingdays;

import BananaFructa.tfcpassingdays.fixes.BlockDaylightDetectorFixed;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class FixHandler {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void registry(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockDaylightDetectorFixed(false).setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("tile.daylightDetector.name").setRegistryName("minecraft","daylight_detector"));
        event.getRegistry().register(new BlockDaylightDetectorFixed(true).setUnlocalizedName("tile.daylightDetector.name").setRegistryName("minecraft","daylight_detector_inverted"));
    }

}
