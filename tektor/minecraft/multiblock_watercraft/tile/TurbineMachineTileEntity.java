package tektor.minecraft.multiblock_watercraft.tile;

import tektor.minecraft.multiblock_watercraft.WatercraftBase;
import universalelectricity.prefab.tile.TileEntityElectrical;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TurbineMachineTileEntity extends TileEntityElectrical {

	public boolean isValidMultiblock;
	public boolean xaxis = false;
	public int forward = 0;
	private TurbineMachineTileEntity tileEntityCore;
	private int coreX;
	private int coreY;
	private int coreZ;
	public int turbine;
	

	public TurbineMachineTileEntity() {
		isValidMultiblock = false;
	}

	@Override
	public float getRequest(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		if (turbine == 0) {
			return 900;
		} else {
			return 1800;
		}
	}

	@Override
	public float getMaxEnergyStored() {
		return 3600000;
	}

	@Override
	public void writeToNBT(NBTTagCompound par1) {
		super.writeToNBT(par1);
		par1.setBoolean("valid", isValidMultiblock);
		par1.setBoolean("axis", xaxis);
		par1.setInteger("forward", forward);
		par1.setInteger("coreX", coreX);
		par1.setInteger("coreY", coreY);
		par1.setInteger("coreZ", coreZ);
		par1.setInteger("turbine", turbine);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
		isValidMultiblock = par1.getBoolean("valid");
		xaxis = par1.getBoolean("axis");
		forward = par1.getInteger("forward");
		coreX = par1.getInteger("coreX");
		coreY = par1.getInteger("coreY");
		coreZ = par1.getInteger("coreZ");
		turbine = par1.getInteger("turbine");
	}

	public boolean getIsValid() {
		return isValidMultiblock;
	}

	private void revertDummies() {
		if (xaxis) {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord + (k * forward),
							yCoord, zCoord + i, 0, 3);
					worldObj.setBlockMetadataWithNotify(xCoord + (k * forward),
							yCoord - 1, zCoord + i, 0, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord, 0,
					3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward * 2), yCoord,
					zCoord, 0, 3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward * 2),
					yCoord - 1, zCoord, 0, 3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord + 1,
					zCoord, 0, 3);
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord - 1,
					zCoord, 0, 3);
			isValidMultiblock = false;
		} else {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord,
							zCoord + (k * forward), 0, 3);
					worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord - 1,
							zCoord + (k * forward), 0, 3);
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord, 0,
					3);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord
					+ (forward * 2), 0, 3);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord
					+ (forward * 2), 0, 3);
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 1, zCoord
					+ (forward), 0, 3);
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord
					+ (forward), 0, 3);
			isValidMultiblock = false;
		}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public void convertDummies() {
		if (xaxis) {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					int x = xCoord + (k * forward);
					int z = zCoord + i;
					worldObj.setBlockMetadataWithNotify(x, yCoord, z, 5, 3);
					TurbineMachineTileEntity ent = (TurbineMachineTileEntity) worldObj
							.getBlockTileEntity(x, yCoord, z);
					if (ent != null) {
						ent.setCore(this);
					}
					worldObj.setBlockMetadataWithNotify(x, yCoord - 1, z, 5, 3);
					ent = (TurbineMachineTileEntity) worldObj
							.getBlockTileEntity(x, yCoord - 1, z);
					if (ent != null) {
						ent.setCore(this);
					}
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord + (forward * 2), yCoord,
					zCoord, 5, 3);
			TurbineMachineTileEntity ent = (TurbineMachineTileEntity) worldObj
					.getBlockTileEntity(xCoord + (forward * 2), yCoord, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord + (forward * 2),
					yCoord - 1, zCoord, 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(xCoord
					+ (forward * 2), yCoord - 1, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord, 0,
					3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(
					xCoord, yCoord - 1, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord + 1,
					zCoord, 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(xCoord
					+ (forward), yCoord + 1, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord + (forward), yCoord - 1,
					zCoord, 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(xCoord
					+ (forward), yCoord - 1, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			isValidMultiblock = true;

		} else {
			// ring
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord,
							zCoord + (k * forward), 5, 3);
					TurbineMachineTileEntity ent = (TurbineMachineTileEntity) worldObj
							.getBlockTileEntity(xCoord + i, yCoord, zCoord
									+ (k * forward));
					if (ent != null) {
						ent.setCore(this);
					}
					worldObj.setBlockMetadataWithNotify(xCoord + i, yCoord - 1,
							zCoord + (k * forward), 5, 3);
					ent = (TurbineMachineTileEntity) worldObj
							.getBlockTileEntity(xCoord + i, yCoord - 1, zCoord
									+ (k * forward));
					if (ent != null) {
						ent.setCore(this);
					}
				}
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord
					+ (forward * 2), 5, 3);
			TurbineMachineTileEntity ent = (TurbineMachineTileEntity) worldObj
					.getBlockTileEntity(xCoord, yCoord, zCoord + (forward * 2));
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord
					+ (forward * 2), 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(
					xCoord, yCoord - 1, zCoord + (forward * 2));
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord, 0,
					3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(
					xCoord, yCoord - 1, zCoord);
			if (ent != null) {
				ent.setCore(this);
			}
			// middle
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord + 1, zCoord
					+ (forward), 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(
					xCoord, yCoord + 1, zCoord + (forward));
			if (ent != null) {
				ent.setCore(this);
			}
			worldObj.setBlockMetadataWithNotify(xCoord, yCoord - 1, zCoord
					+ (forward), 5, 3);
			ent = (TurbineMachineTileEntity) worldObj.getBlockTileEntity(
					xCoord, yCoord - 1, zCoord + (forward));
			if (ent != null) {
				ent.setCore(this);
			}
			isValidMultiblock = true;
		}
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

	}

	public boolean checkIfProperlyFormed() {
		// check first where the turbine is
		// then same level
		// top
		// and the base at the end

		if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord + 1, yCoord, zCoord)) {
			forward = 1;
			xaxis = true;
			turbine = worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord);
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord + 2, yCoord, zCoord))
				return false;
			// top
			if (!checkTurbinePart(xCoord + 1, yCoord + 1, zCoord))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord - 1, yCoord, zCoord)) {
			forward = -1;
			xaxis = true;
			turbine = worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord);
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord - k, yCoord, zCoord + i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord - 2, yCoord, zCoord))
				return false;
			// top
			if (!checkTurbinePart(xCoord - 1, yCoord + 1, zCoord))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord + 1)) {
			forward = 1;
			xaxis = false;
			turbine = worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1);
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + i, yCoord, zCoord + k))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord + 2))
				return false;
			// top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord + 1))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else if (WatercraftBase.waterTurbine.blockID == worldObj.getBlockId(
				xCoord, yCoord, zCoord - 1)) {
			forward = -1;
			xaxis = false;
			turbine = worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1);
			// same
			for (int i = -1; i < 2; i = i + 2) {
				for (int k = 0; k < 3; k++) {
					if (!checkTurbinePart(xCoord + k, yCoord, zCoord - i))
						return false;
				}
			}
			if (!checkTurbinePart(xCoord, yCoord, zCoord - 2))
				return false;
			// top
			if (!checkTurbinePart(xCoord, yCoord + 1, zCoord - 1))
				return false;
			// base
			return checkBase(xaxis, forward);
		} else {
			return false;
		}
	}

	private boolean checkTurbinePart(int i, int j, int k) {
		return ((WatercraftBase.turbineMachinePart.blockID == worldObj
				.getBlockId(i, j, k)) && (0 == worldObj.getBlockMetadata(i, j,
				k)));
	}

	private boolean checkBase(boolean axis, int forward) {
		for (int x = 0; x < 3; x++) {
			for (int z = -1; z < 2; z++) {
				if (axis) {
					if (!checkTurbinePart(xCoord + (forward * x), yCoord - 1,
							zCoord + (z))) {

						return false;
					}

				} else {
					if (!checkTurbinePart(xCoord + (z), yCoord - 1, zCoord
							+ (x * forward))) {
						return false;
					}
				}

			}
		}

		return true;
	}

	public void invalidateMultiblock() {
		isValidMultiblock = false;
		revertDummies();
	}

	public TurbineMachineTileEntity getCore() {
		if (tileEntityCore == null)
			tileEntityCore = (TurbineMachineTileEntity) worldObj
					.getBlockTileEntity(coreX, coreY, coreZ);

		return tileEntityCore;
	}

	public void setCore(TurbineMachineTileEntity core) {
		coreX = core.xCoord;
		coreY = core.yCoord;
		coreZ = core.zCoord;
		tileEntityCore = core;
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		NBTTagCompound tag = pkt.customParam1;

		this.readFromNBT(tag);
	}
	
	@Override
	public boolean canUpdate()
	{
		return true;
	}
	
	@Override
	public void updateEntity()
	{
		if (isValidMultiblock && tileEntityCore == null && worldObj.isRemote == false) {
			this.produce();
		}
	}

}
