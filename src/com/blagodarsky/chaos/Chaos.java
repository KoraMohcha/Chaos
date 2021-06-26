package com.blagodarsky.chaos;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.blagodarsky.chaos.events.BeautifulExplosions;
import com.blagodarsky.chaos.events.BookToKill;
import com.blagodarsky.chaos.events.CaptureWand;
import com.blagodarsky.chaos.events.HeadCutter;
import com.blagodarsky.chaos.events.Helper;
import com.blagodarsky.chaos.events.InfiniteMobs;
import com.blagodarsky.chaos.events.InfiniteResours;
import com.blagodarsky.chaos.events.InvisibleMobs;
import com.blagodarsky.chaos.events.SpawnerKeeper;
import com.blagodarsky.chaos.events.SpawningNewCreatuares;
import com.blagodarsky.chaos.events.WitherSword;

public class Chaos extends JavaPlugin
{
	public static Chaos plugin;
	public static ItemStack deathNote;
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
		
		deathNote = new ItemStack(Material.WRITABLE_BOOK);
		ItemMeta meta = deathNote.getItemMeta();
		meta.setDisplayName(ChatColor.UNDERLINE +"" + ChatColor.RED + "☠...Death Note...☠");
		deathNote.setItemMeta(meta);
		ShapedRecipe recipt = new ShapedRecipe(deathNote);
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
