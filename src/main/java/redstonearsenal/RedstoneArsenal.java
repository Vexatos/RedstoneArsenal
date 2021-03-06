package redstonearsenal;

import cofh.CoFHCore;
import cofh.core.CoFHProps;
import cofh.mod.BaseMod;
import cofh.updater.UpdateManager;
import cofh.util.ConfigHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redstonearsenal.gui.RACreativeTab;
import redstonearsenal.item.RAItems;

@Mod(modid = RedstoneArsenal.modId, name = RedstoneArsenal.modName, version = RedstoneArsenal.version, dependencies = RedstoneArsenal.dependencies)
public class RedstoneArsenal extends BaseMod {

	public static final String modId = "RedstoneArsenal";
	public static final String modName = "Redstone Arsenal";
	public static final String version = "1.7.10R1.0.1B2";
	public static final String dependencies = "required-after:CoFHCore@[" + CoFHCore.version + ",);after:ThermalExpansion";
	public static final String releaseURL = "https://raw.github.com/CoFH/RedstoneArsenal/master/VERSION";

	@Instance("RedstoneArsenal")
	public static RedstoneArsenal instance;

	public static final Logger log = LogManager.getLogger(modId);
	public static final ConfigHandler config = new ConfigHandler(version);
	public static final CreativeTabs tab = new RACreativeTab();

	/* INIT SEQUENCE */
	public RedstoneArsenal() {

		super(log);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		UpdateManager.registerUpdater(new UpdateManager(this, releaseURL));

		config.setConfiguration(new Configuration(new File(CoFHProps.configDir, "/cofh/RedstoneArsenal.cfg")));

		RAItems.preInit();
	}

	@EventHandler
	public void initialize(FMLInitializationEvent event) {

		RAItems.initialize();
		RACreativeTab.initialize();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		RAItems.postInit();

		config.cleanUp(false, true);
	}

	/* BaseMod */
	@Override
	public String getModId() {

		return modId;
	}

	@Override
	public String getModName() {

		return modName;
	}

	@Override
	public String getModVersion() {

		return version;
	}

}
