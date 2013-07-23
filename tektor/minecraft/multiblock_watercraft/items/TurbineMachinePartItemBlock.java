package tektor.minecraft.multiblock_watercraft.items;

import tektor.minecraft.multiblock_watercraft.WatercraftBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class TurbineMachinePartItemBlock extends ItemBlock{

	private static final String[] subNames = {"normal", "core"};
	private Icon[] icons = new Icon[2];

	public TurbineMachinePartItemBlock(int par1) {
		super(par1);
		this.setHasSubtypes(true);
		this.setUnlocalizedName("turbineMachinePart");
		this.setCreativeTab(CreativeTabs.tabBlock);
		
	}
	
	@Override
	public int getBlockID()
	{
		return WatercraftBase.turbineMachinePart.blockID;
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}
	
	@Override
	public void registerIcons(IconRegister iconRegister)
	{
	         icons[0] = iconRegister.registerIcon("multiblock_watercraft:machinePart");
	         icons[1] = iconRegister.registerIcon("multiblock_watercraft:turbineControl");
	}
	
	public Icon getIconFromDamage(int dmg)
	{
		return icons[dmg];
	}


}
