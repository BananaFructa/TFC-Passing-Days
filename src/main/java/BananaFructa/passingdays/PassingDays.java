package BananaFructa.passingdays;

import BananaFructa.passingdays.fixes.BlockDaylightDetectorFixed;
import BananaFructa.passingdays.fixes.galacticraft.BlockSolarFixed;
import BananaFructa.passingdays.fixes.galacticraft.TileEntitySolarFixed;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.core.util.CreativeTabGC;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = PassingDays.modId,name = PassingDays.name,version = PassingDays.version,dependencies = "after:galacticraftcore")
public class PassingDays {
    public static final String modId = "passingdays";
    public static final String name = "TFC Passing Days";
    public static final String version = "1.0";

    // TODO: integration with climate and check for gc loaded

    @Mod.EventHandler
    public void init(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    boolean await = false;
    boolean awaitingServer = false;

    BlockSolarFixed b = new BlockSolarFixed("solar");

    @Mod.EventHandler
    public void init(FMLServerStartedEvent event) {
        GameRegistry.registerTileEntity(TileEntitySolarFixed.class,new ResourceLocation(modId,TileEntitySolarFixed.class.getSimpleName()));
        ClientUtil.registerBlockJson("galacticraftcore:",b, 0, "solar");
        ClientUtil.registerBlockJson("galacticraftcore:", b, 4, "advanced_solar");
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (await && Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.provider != null) {
            Utils.writeDeclaredField(World.class, Minecraft.getMinecraft().world, "field_73011_w", new PassingDayWorldProviderClient(Minecraft.getMinecraft().world.provider), true);
            await = false;
        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (awaitingServer) {
            World world = DimensionManager.getWorld(0);
            if (world != null && world.provider != null) {
                Utils.writeDeclaredField(World.class, world, "field_73011_w", new PassingDayWorldProviderServer(world.provider), true);
                awaitingServer = false;
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void registry(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new BlockDaylightDetectorFixed(false).setCreativeTab(CreativeTabs.REDSTONE).setUnlocalizedName("tile.daylightDetector.name").setRegistryName("minecraft","daylight_detector"));
        event.getRegistry().register(new BlockDaylightDetectorFixed(true).setUnlocalizedName("tile.daylightDetector.name").setRegistryName("minecraft","daylight_detector_inverted"));
        event.getRegistry().register(b.setRegistryName(GCBlocks.solarPanel.getRegistryName()));
    }

    @Mod.EventHandler
    public void onServerStart(FMLServerStartedEvent event) {
        awaitingServer = true;
    }

    @SubscribeEvent
    public void onJoin(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        await = true;
    }

}
