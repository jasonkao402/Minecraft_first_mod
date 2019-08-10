package taiwan402.TestMod00.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModBlocks;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class BlockBase extends Block implements IHasModel
{
	public BlockBase(String n, Material m)
	{
		super(m);
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		ModBlocks.BLOCKS.add(this);
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
