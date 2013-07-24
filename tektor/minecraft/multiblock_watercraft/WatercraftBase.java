package tektor.minecraft.multiblock_watercraft;

import tektor.minecraft.multiblock_watercraft.blocks.TurbineMachinePart;
import tektor.minecraft.multiblock_watercraft.blocks.WaterTurbine;
import tektor.minecraft.multiblock_watercraft.tile.TurbineMachineTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
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

@Mod(modid="multiblock_watercraft", name="MultiBlock Watercraft", version="0.0.7")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class WatercraftBase {

        // The instance of your mod that Forge uses.
        @Instance("multiblock_watercraft")
        public static WatercraftBase instance;
       
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="tektor.minecraft.multiblock_watercraft.client.WatercraftClientProxy", serverSide="tektor.minecraft.multiblock_watercraft.WatercraftCommonProcy")
        public static WatercraftCommonProxy proxy;
        
        public static final Block turbineMachinePart = new TurbineMachinePart(1001,Material.iron);
        public static final Block waterTurbine = new WaterTurbine(1002,Material.iron);
        @EventHandler
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
       
        @EventHandler
        public void load(FMLInitializationEvent event) {
            registerMachines();    
            
            
        	proxy.registerRenderers();
        }
       
        private void registerRecipes() {
			ItemStack ironStack = new ItemStack(Item.ingotIron, 1);
			ItemStack goldStack = new ItemStack(Item.ingotGold, 1);
			GameRegistry.addRecipe(new ItemStack(turbineMachinePart,1,1), new Object[]{"AAA","BAB","AAA",'A',ironStack,'B',goldStack});
			GameRegistry.addRecipe(new ItemStack(turbineMachinePart,1,0), new Object[]{"AAA","ABA","AAA",'A',ironStack,'B',goldStack});
			
			
		}

		private void registerMachines() {
			//Water Turbine
			GameRegistry.registerBlock(waterTurbine, "waterTurbine");
    		LanguageRegistry.addName(waterTurbine,"Water Turbine");
    		
        	//Turbine Machine Part
        	GameRegistry.registerBlock(turbineMachinePart, "turbineMachinePart");
    		LanguageRegistry.addName(turbineMachinePart,"Turbine Machine Part");
    		GameRegistry.registerTileEntity(TurbineMachineTileEntity.class, "turbineMachinePartTileEntity");
    		
			
		}

		@EventHandler
        public void postInit(FMLPostInitializationEvent event) {
			registerRecipes();
        }
		
		
}
