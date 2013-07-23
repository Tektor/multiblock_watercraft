package tektor.minecraft.multiblock_watercraft.blocks;

import java.util.List;

import tektor.minecraft.multiblock_watercraft.tile.TurbineMachineTileEntity;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WaterTurbine extends Block {

	public static final int LOWEST = 0;
	public static final int LOWER = 1;
	public static final String[] subNames = { "lowest", "lower" };
	private Icon[] icons = new Icon[3];

	public WaterTurbine(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("waterTurbine");
	}

	@Override
	public int damageDropped(int metadata) {
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if(side == 1 || side == 0)
		{
			if (meta == 1) {
				return icons[2];
			} else {
				return icons[1];
			}
		}
		else
		{
			return icons[0];
		}
		
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons[0] = par1IconRegister
				.registerIcon("multiblock_watercraft:machinePart");
		this.icons[1] = par1IconRegister
				.registerIcon("multiblock_watercraft:lowestWaterTurbine");
		this.icons[2] = par1IconRegister
				.registerIcon("multiblock_watercraft:lowerWaterTurbine");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z,
			int side) {
		int meta = access.getBlockMetadata(x, y, z);

		if(side == 1 || side == 0)
		{
			if (meta == 1) {
				return icons[2];
			} else {
				return icons[1];
			}
		}
		else
		{
			return icons[0];
		}
	}

	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4,
			EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4,
				par6ItemStack.getItemDamage(), 3);
		super.onBlockPlacedBy(par1World, par2, par3, par4,
				par5EntityLivingBase, par6ItemStack);
	}

}
