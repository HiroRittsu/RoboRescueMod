package migly.sample.main;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;

import it.unimi.dsi.fastutil.io.MeasurableInputStream;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

@Mod(modid = SampleMod.MOD_ID, name = SampleMod.MOD_NAME, version = SampleMod.MOD_VERSION)
public class SampleMod {

	public static final String MOD_ID = "samplemod";
	public static final String MOD_NAME = "SampleMod";
	public static final String MOD_VERSION = "1.0";
	public static EntityPlayer entityPlayer;
	public static Entity zombie;
	public static Minecraft mc = Minecraft.getMinecraft();
	public static EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
	public static Logger log;
	public static GameSettings settings;
	public int count = 0;
	public double stack = 50;

	public static ArrayList<Entity> zombi_list = new ArrayList<Entity>();

	public static Map<Integer, Vec3d> node = new HashMap<>();
	public static Map<Integer, Vec3d> edge = new HashMap<>();
	// public static Map<Integer, Vec3d> building = new HashMap<>();

	// public static ArrayList<Vec3d> node = new ArrayList<Vec3d>();
	// public static ArrayList<Vec3d> edge = new ArrayList<Vec3d>();
	public static ArrayList<Vec3d> building = new ArrayList<Vec3d>();

	// public static Block sampleblock = new Block(Material.IRON,
	// MapColor.DIAMOND).setRegistryName("sample_block");
	// public static Item sampleitem = new
	// ItemBlock(sampleblock).setRegistryName(sampleblock.getRegistryName());
	// public static Block sampleblock = new Block
	// public static Item sampleitem = new
	// ItemBlock(sampleblock).setRegistryName(sampleblock.getRegistryName());

	void addNode() {

	}

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		System.out.println("#####################preInit");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

		String line = null;

		File point = new File("D:\\Users\\migly\\Desktop\\testmap_point.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(point));
		} catch (FileNotFoundException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		try {
			while ((line = br.readLine()) != null) {

				Double x = Double.parseDouble(line.split(" ", 0)[0]);
				Double z = Double.parseDouble(line.split(" ", 0)[1]);

				Vec3d buildpoint = new Vec3d(x, 0.0, z);

				building.add(buildpoint);

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	/*
	 * @SubscribeEvent public void registerBlocks(RegistryEvent.Register<Block>
	 * event) { event.getRegistry().register(sampleblock);
	 * sampleblock.setUnlocalizedName(sampleblock.getRegistryName().getResourcePath(
	 * )); sampleblock.setCreativeTab(CreativeTabs.BUILDING_BLOCKS); }
	 * 
	 * @SubscribeEvent public void registerItems(RegistryEvent.Register<Item> event)
	 * { event.getRegistry().register(sampleitem); }
	 */

	@SubscribeEvent
	public void ServerTick(TickEvent.ServerTickEvent event) {

		/*
		 * count++; if (count % 10 == 0) {
		 * 
		 * count = 0; stack += 1;
		 * 
		 * if (mc.player != null) { double px = mc.player.posX; double py =
		 * mc.player.posY; double pz = mc.player.posZ; World world =
		 * DimensionManager.getWorld(0); BlockPos p1 = new BlockPos(px, 3, pz);
		 * world.setBlockState(p1, sampleblock.getDefaultState()); // //
		 * mc.player.sendChatMessage("x"); } }
		 * 
		 * int count = 0;
		 * 
		 * if (zombi_list != null) { // zombie.move(MoverType.SELF, 0.01, 0, 0);
		 * 
		 * for (Entity entity : zombi_list) {
		 * 
		 * entity.move(MoverType.SELF, 0, 0, 0.01 * count); count++;
		 * 
		 * }
		 * 
		 * } else { System.out.println("ヌルポ！！！"); }
		 */

	}

	@Mod.EventHandler
	public void onServerStarted(FMLServerStartedEvent event) {
		// double px = entityPlayer.posX;
		// double py = entityPlayer.posY;
		// double pz = entityPlayer.posZ;

		// BlockPos p1 = new BlockPos(50, 15, 50);
		// BlockPos p1 = new BlockPos(px, py, pz);

		// world.setBlockState(p1, sampleblock.getDefaultState());

	}

	@SubscribeEvent
	public void onPlayerLoggin(PlayerLoggedInEvent event) {

		/*
		 * World world = DimensionManager.getWorld(0); // zombie = new
		 * EntityZombie(world);
		 * 
		 * for (int i = 0; i < 10; i++) { zombi_list.add(new EntityZombie(world)); //
		 * zombie.setPosition(event.player.posX, event.player.posY, event.player.posZ);
		 * // world.spawnEntity(zombie); }
		 * 
		 * for (Entity entity : zombi_list) {
		 * 
		 * entity.setPosition(event.player.posX + (Math.random() * 10),
		 * event.player.posY + (Math.random() * 10), event.player.posZ + (Math.random()
		 * * 10));
		 * 
		 * world.spawnEntity(entity);
		 * 
		 * }
		 */
		World world = DimensionManager.getWorld(0);

		for (Vec3d p : building) {
			// BlockPos p1 = new BlockPos(p.x, 3, p.z);
			// world.setBlockState(p1, sampleblock.getDefaultState());
			world.setBlockState(new BlockPos(p.x, 3, p.z), Blocks.PLANKS.getDefaultState());
			world.setBlockState(new BlockPos(p.x, 4, p.z), Blocks.PLANKS.getDefaultState());
			world.setBlockState(new BlockPos(p.x, 5, p.z), Blocks.PLANKS.getDefaultState());
		}

	}

}
