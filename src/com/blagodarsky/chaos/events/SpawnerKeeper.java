package com.blagodarsky.chaos.events;

import java.util.logging.Handler;

import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerKeeper implements Listener 
{
	@EventHandler
	public void spawnerKeeper (BlockBreakEvent e)
	{
		if (e.getBlock().getType() == Material.SPAWNER)
		{
			ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
			if(i.getType().toString().contains("_PICKAXE"))
			{
				if (i.containsEnchantment(Enchantment.SILK_TOUCH))
				{
					ItemStack spawner = new ItemStack(Material.SPAWNER);
					CreatureSpawner n = (CreatureSpawner) e.getBlock().getState();
					BlockStateMeta k = (BlockStateMeta) spawner.getItemMeta();
					k.setBlockState(n);
					spawner.setItemMeta(k);
					e.getPlayer().getWorld().dropItem(e.getBlock().getLocation().add(0.5, 0.5, 0.5), spawner);
				}
			}
		}
	}
}
