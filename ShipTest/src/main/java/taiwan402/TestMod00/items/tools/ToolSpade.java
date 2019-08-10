package taiwan402.TestMod00.items.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSpade;
import taiwan402.TestMod00.Main;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.util.IHasModel;

public class ToolSpade extends ItemSpade implements IHasModel
{
	public ToolSpade(String n, ToolMaterial m)
	{
		super(m);
		setUnlocalizedName(n);
		setRegistryName(n);
		setCreativeTab(CreativeTabs.TOOLS);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels()
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
