package com.blagodarsky.chaos;

import com.blagodarsky.chaos.commands.CommandGetBook;
import com.blagodarsky.chaos.events.*;
import com.blagodarsky.chaos.utils.KillersBook;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Chaos extends JavaPlugin
{
	public static Chaos plugin;
	public static ItemStack Capture_wand;
	public void onEnable ()
	{
		plugin = this;
		getServer().getPluginManager().registerEvents(new SpawningNewCreatuares(), plugin);
		getServer().getPluginManager().registerEvents(new InfiniteResours(), plugin);
		getServer().getPluginManager().registerEvents(new BookToKill(), plugin);
		getServer().getPluginManager().registerEvents(new InfiniteMobs(), plugin);
		getServer().getPluginManager().registerEvents(new WitherSword(), plugin);
		getServer().getPluginManager().registerEvents(new HeadCutter(), plugin);
		getServer().getPluginManager().registerEvents(new CaptureWand(), plugin);
		getServer().getPluginManager().registerEvents(new BeautifulExplosions(), plugin);
		getServer().getPluginManager().registerEvents(new InvisibleMobs(), plugin);
		getServer().getPluginManager().registerEvents(new SpawnerKeeper(), plugin);
		getServer().getPluginManager().registerEvents(new Helper(), plugin);
		getCommand("getbook").setExecutor(new CommandGetBook());
		ShapedRecipe recipt = new ShapedRecipe(new KillersBook(null).getBook());
		recipt.shape("+-+",
					 "-B-",
					 "+-+");
		recipt.setIngredient('+', Material.DIAMOND_BLOCK);
		recipt.setIngredient('-', Material.GOLDEN_APPLE);
		recipt.setIngredient('B', Material.BOOK);
		//expBottle.shape("$%$","%C%","$%$");
		getServer().addRecipe(recipt);
		
		
		Capture_wand = new ItemStack(Material.BLAZE_ROD);
		ItemMeta metan = Capture_wand.getItemMeta();
		metan.setDisplayName("§b§kEYE §6§lCapture Wand §b§kEYE");
		Capture_wand.setItemMeta(metan);
		ShapedRecipe recipe = new ShapedRecipe(Capture_wand);
		recipe.shape("--=",
					 "-B-",
					 "+--");
		recipe.setIngredient('+', Material.GOLD_INGOT);
		recipe.setIngredient('-', Material.AIR);
		recipe.setIngredient('B', Material.BLAZE_ROD);
		recipe.setIngredient('=', Material.ENDER_EYE);
		//expBottle.shape("$%$","%C%","$%$");
		getServer().addRecipe(recipe);
	}
}
