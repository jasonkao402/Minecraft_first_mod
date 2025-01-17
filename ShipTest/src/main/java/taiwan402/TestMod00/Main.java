package taiwan402.TestMod00;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import taiwan402.TestMod00.proxy.CommonProxy;
import taiwan402.TestMod00.util.Ref;

@Mod(modid = Ref.MODID, name = Ref.MODNAME, version = Ref.VERSION, acceptedMinecraftVersions=Ref.ACCEPTED_MINECRAFT_VERSIONS)

public class Main {
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		System.out.println(Ref.MODID + ":preInit");
	}
	
	@EventHandler
	public static void Init(FMLInitializationEvent event)
	{
		System.out.println(Ref.MODID + ":init");
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
		System.out.println(Ref.MODID + ":postInit");
	}
	
}
