package com.blagodarsky.chaos.events;

import java.util.HashMap;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

public class InfiniteResours implements Listener
{
	private HashMap<Block, Integer > miners;
	@EventHandler
	public void infiniteResours (BlockBreakEvent e)
	{
		if (e.getPlayer().getGameMode() == GameMode.CREATIVE)
		{
			return;
		}
		if(e.getPlayer().isSneaking())
		{
			e.setCancelled(true);
			for (ItemStack i : e.getBlock().getDrops())
			{
				e.getPlayer().getWorld().dropItem(e.getBlock().getLocation().add(0.5, 0.5, 0.5), i);
			}
			int im = 0;
			miners.put(e.getBlock(), im);
			im++;
			if (im == 5)
			{
				return;
			}
		}
	}
}
