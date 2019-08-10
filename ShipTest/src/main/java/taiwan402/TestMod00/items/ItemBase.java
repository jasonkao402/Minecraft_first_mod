package taiwan402.TestMod00.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String n)
	{
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
