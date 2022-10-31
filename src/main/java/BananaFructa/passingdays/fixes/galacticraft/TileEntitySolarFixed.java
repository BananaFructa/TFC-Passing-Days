package BananaFructa.passingdays.fixes.galacticraft;

import BananaFructa.passingdays.PassingDayWorldProviderServer;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySolar;

public class TileEntitySolarFixed extends TileEntitySolar {

    // TODO: copy field

    public TileEntitySolarFixed() {
        super(1);
    }

    public TileEntitySolarFixed(int tier) {
        super(tier);
    }

    @Override
    public void update() {
        boolean modifiedProvider = this.world.provider instanceof PassingDayWorldProviderServer;
        if (modifiedProvider) {
            PassingDayWorldProviderServer providerServer = (PassingDayWorldProviderServer) this.world.provider;
            providerServer.shouldInterceptCalls(true);
            providerServer.setZIntercept(pos.getZ());
        }
        super.func_73660_a();
        if (modifiedProvider) {
            PassingDayWorldProviderServer providerServer = (PassingDayWorldProviderServer) this.world.provider;
            providerServer.shouldInterceptCalls(false);
        }
    }
}
