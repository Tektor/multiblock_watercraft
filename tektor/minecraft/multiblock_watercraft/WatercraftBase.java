package tektor.minecraft.multiblock_watercraft;

import tektor.minecraft.multiblock_watercraft.blocks.TurbineMachinePart;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="multiblock_watercraft", name="MultiBlock Watercraft", version="0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class WatercraftBase {

        // The instance of your mod that Forge uses.
        @Instance("multiblock_watercraft")
        public static WatercraftBase instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="tektor.minecraft.multiblock_watercraft.client.WatercraftClientProxy", serverSide="tektor.minecraft.multiblock_watercraft.WatercraftCommonProcy")
        public static WatercraftCommonProxy proxy;
        
        public static final Block turbineMachinePart = new TurbineMachinePart(1001,Material.iron);
       
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
            registerMachines();    
        	proxy.registerRenderers();
        }
       
        private void registerMachines() {
        	//Turbine Machine Part
        	LanguageRegistry.addName(turbineMachinePart, "Turbine Machine Part");
    		GameRegistry.registerBlock(turbineMachinePart, "turbineMachinePart");
			
		}

		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}
