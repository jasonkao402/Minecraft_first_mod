package taiwan402.TestMod00.util.handlers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import taiwan402.TestMod00.init.ModBlocks;
import taiwan402.TestMod00.init.ModItems;
import taiwan402.TestMod00.items.tools.BowBase;
import taiwan402.TestMod00.util.IHasModel;

@EventBusSubscriber
public class RegistryHandler
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item i : ModItems.ITEMS)
		{
			if(i instanceof IHasModel)
			{
				((IHasModel)i).registerModels();
			}
		}
		
		for(Block i : ModBlocks.BLOCKS)
		{
			if(i instanceof IHasModel)
			{
				((IHasModel)i).registerModels();
			}
		}
	}
	
	@SubscribeEvent
	public static void zoom(FOVUpdateEvent event) {
		if(event.getEntity().getActiveItemStack() != null)
			if(event.getEntity().getActiveItemStack().getItem() == ModItems.RUBY_BOW ) {
				event.setNewfov(event.getFov()*((BowBase)event.getEntity().getActiveItemStack().getItem()).getZoom(event.getEntity()));
		}
	}
}
