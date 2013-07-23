package tektor.minecraft.multiblock_watercraft.blocks;

import java.util.List;

import tektor.minecraft.multiblock_watercraft.WatercraftBase;
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
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachinePart extends BlockContainer {

	public static final int CORE = 1;
	public static final int NORMAL = 0;
	public static final int WATER_IN = 2;
	public static final int WATER_OUT = 3;
	public static final int ENERGY_OUT = 4;
	public static final int DUMMY = 5;
	public static final String[] subNames = { "normal", "core" };
	private Icon[] icons = new Icon[2];

	public TurbineMachinePart(int par1, Material par2Material) {
		super(par1, par2Material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		setHardness(2.2F);
		setStepSound(Block.soundMetalFootstep);
		setUnlocalizedName("turbineMachinePart");
		this.func_111022_d("multiblock_watercraft:turbineControl");
	}

	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TurbineMachineTileEntity();
	}

	@Override
	public int damageDropped(int metadata) {
		if (metadata == 1) {
			return metadata;
		} else {
			return 0;
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 2; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (player.isSneaking())
			return false;

		TurbineMachineTileEntity tileEntity = (TurbineMachineTileEntity) world
				.getBlockTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (tileEntity != null) {
			if (meta == 0) {
				return false;

			} else if (meta == 1) {
				if (!tileEntity.getIsValid()) {
					if (tileEntity.checkIfProperlyFormed()) {
						tileEntity.convertDummies();
						if (world.isRemote)
							player.addChatMessage("Water Turbine Created!");
					}
					else
					{
						player.addChatMessage("Creation Failed");
					}
					// player.openGui(WatercraftBase.instance,
					// ModConfig.GUIIDs.multiFurnace, world, x, y, z);

				}
			} else {

			}

		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if (meta == 1) {
			return icons[1];
		} else {
			return icons[0];
		}
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		this.icons[0] = par1IconRegister
				.registerIcon("multiblock_watercraft:machinePart");
		this.icons[1] = par1IconRegister
				.registerIcon("multiblock_watercraft:turbineControl");

	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getBlockTexture(IBlockAccess access, int x, int y, int z,
			int side) {
		int meta = access.getBlockMetadata(x, y, z);

		if (meta == 1) {
			return icons[1];
		} else {
			return icons[0];
		}
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		par1World.setBlockMetadataWithNotify(par2, par3, par4, par6ItemStack.getItemDamage(), 3);
		super.onBlockPlacedBy(par1World, par2, par3, par4, par5EntityLivingBase, par6ItemStack);
	}

}
